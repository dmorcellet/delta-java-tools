package delta.tools.java.classes;

import java.util.ArrayList;

public class DescriptorParser
{
  private int _position;

  public DescriptorParser()
  {
    _position=0;
  }

  public String parseFieldType(String fieldDescriptor, boolean useFullyQualifiedNames)
  {
    _position=0;
    return parseType(fieldDescriptor,useFullyQualifiedNames);
  }

  public String parseMethodDescriptor(String methodDescriptor, String methodName, boolean useFullyQualifiedNames)
  {
    int max=methodDescriptor.length();
    _position=0;
    if(methodDescriptor.charAt(_position)!='(')
    {
      return "";
    }
    _position++;
    ArrayList<String> parameterTypes=new ArrayList<String>();
    while((_position<max)&&(methodDescriptor.charAt(_position)!=')'))
    {
      parameterTypes.add(parseType(methodDescriptor,useFullyQualifiedNames));
    }
    if(_position==max)
    {
      return "";
    }
    _position++;
    String returnType=parseType(methodDescriptor,useFullyQualifiedNames);
    StringBuffer sb=new StringBuffer();
    sb.append(returnType);
    sb.append(' ');
    sb.append(methodName);
    sb.append('(');
    int nbParameters=parameterTypes.size();
    for(int i=0;i<nbParameters;i++)
    {
      if(i>0)
      {
        sb.append(',');
      }
      sb.append(parameterTypes.get(i));
    }
    sb.append(')');
    return sb.toString();
  }

  private String parseType(String type, boolean useFullyQualifiedNames)
  {
    String ret="";
    int index=_position;
    int max=type.length();
    int dimensions=0;
    while((index<max)&&(type.charAt(index)==BaseTypes.ARRAY_CODE))
    {
      dimensions++;
      index++;
    }
    if(index<max)
    {
      StringBuffer sb=new StringBuffer();
      if(type.charAt(index)==BaseTypes.OBJECT_CODE)
      {
        index++;
        int lastPoint=-1;
        char c;
        while((index<max)&&(type.charAt(index)!=BaseTypes.OBJECT_NAME_TERMINATOR))
        {
          c=type.charAt(index);
          if (c=='/') c='.';
          sb.append(c);
          if (c=='.') lastPoint=sb.length();
          index++;
        }
        index++;
        if ((lastPoint!=-1) & (useFullyQualifiedNames))
        	sb.delete(0,lastPoint);
      }
      else
      {
        sb.append(BaseTypes.getLabelForBaseType(type.charAt(index)));
        index++;
      }
      for(int i=0;i<dimensions;i++)
      {
        sb.append("[]");
      }
      ret=sb.toString();
    }
    _position=index;
    return ret;
  }
}
