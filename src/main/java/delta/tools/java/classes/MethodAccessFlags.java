package delta.tools.java.classes;

public class MethodAccessFlags
{
  public static final int PUBLIC=0x0001;
  public static final int PRIVATE=0x0002;
  public static final int PROTECTED=0x0004;
  public static final int STATIC=0x0008;
  public static final int FINAL=0x0010;
  public static final int SYNCHRONIZED=0x0020;
  public static final int NATIVE=0x0100;
  public static final int ABSTRACT=0x0400;
  public static final int STRICT=0x0800;

  public static String toString(int accessFlags)
  {
    StringBuffer sb=new StringBuffer();
    int len;

    if((accessFlags&PUBLIC)!=0)
    {
      sb.append("public ");
    }
    if((accessFlags&PROTECTED)!=0)
    {
      sb.append("protected ");
    }
    if((accessFlags&PRIVATE)!=0)
    {
      sb.append("private ");
    }
    if((accessFlags&ABSTRACT)!=0)
    {
      sb.append("abstract ");
    }
    if((accessFlags&STATIC)!=0)
    {
      sb.append("static ");
    }
    if((accessFlags&FINAL)!=0)
    {
      sb.append("final ");
    }
    if((accessFlags&SYNCHRONIZED)!=0)
    {
      sb.append("synchronized ");
    }
    if((accessFlags&NATIVE)!=0)
    {
      sb.append("native ");
    }
    if((accessFlags&STRICT)!=0)
    {
      sb.append("strictfp ");
    }

    len=sb.length();
    if(len>0)
    {
      //trim trailing space
      return sb.toString().substring(0, len-1);
    }
    return "";
  }
}
