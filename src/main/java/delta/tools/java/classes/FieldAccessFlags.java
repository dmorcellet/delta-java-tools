package delta.tools.java.classes;

public class FieldAccessFlags
{
  public static final int PUBLIC=0x0001;
  public static final int PRIVATE=0x0002;
  public static final int PROTECTED=0x0004;
  public static final int STATIC=0x0008;
  public static final int FINAL=0x0010;
  public static final int VOLATILE=0x0040;
  public static final int TRANSIENT=0x0080;

  public static String toString(int accessFlags)
  {
    StringBuffer sb=new StringBuffer();
    int len;

    if ((accessFlags&PUBLIC)!=0)
    {
      sb.append("public ");
    }
    if ((accessFlags&PROTECTED)!=0)
    {
      sb.append("protected ");
    }
    if ((accessFlags&PRIVATE)!=0)
    {
      sb.append("private ");
    }
    if ((accessFlags&STATIC)!=0)
    {
      sb.append("static ");
    }
    if ((accessFlags&FINAL)!=0)
    {
      sb.append("final ");
    }
    if ((accessFlags&TRANSIENT)!=0)
    {
      sb.append("transient ");
    }
    if ((accessFlags&VOLATILE)!=0)
    {
      sb.append("volatile ");
    }

    len=sb.length();
    if (len>0)
    {
      // trim trailing space
      return sb.toString().substring(0,len-1);
    }
    return "";
  }
}
