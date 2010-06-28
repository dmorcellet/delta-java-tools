package delta.tools.java.classes;

public class ClassField
{
  private String _name;
  private String _type;
  private short _accessFlags;

  public ClassField(String name, String type, short accessFlags)
  {
    _name=name;
    _type=type;
    _accessFlags=accessFlags;
  }

  public String getName()
  {
    return _name;
  }

  public String getType()
  {
    return _type;
  }

  public String getPrototype(boolean useFullyQualifiedNames)
  {
    StringBuffer sb=new StringBuffer();
    String accessFlags=MethodAccessFlags.toString(_accessFlags);
    sb.append(accessFlags);
    if(accessFlags.length()>0)
    {
      sb.append(' ');
    }
    sb.append(new DescriptorParser().parseFieldType(_type,useFullyQualifiedNames));
    sb.append(' ');
    sb.append(_name);
    return sb.toString();
  }
}
