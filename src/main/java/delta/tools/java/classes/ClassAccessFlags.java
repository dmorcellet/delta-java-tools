package delta.tools.java.classes;

public class ClassAccessFlags
{
  public static final int PUBLIC=0x0001;
  public static final int FINAL=0x0010;
  public static final int SUPER=0x0020;
  public static final int INTERFACE=0x0200;
  public static final int ABSTRACT=0x0400;

  public static String toString(int accessFlags)
  {
    StringBuffer sb=new StringBuffer();
    int len;
    if((accessFlags&PUBLIC)!=0)
    {
      sb.append("public ");
    }
    if((accessFlags&ABSTRACT)!=0)
    {
      sb.append("abstract ");
    }
    if((accessFlags&FINAL)!=0)
    {
      sb.append("final ");
    }
    if((accessFlags&INTERFACE)!=0)
    {
      sb.append("interface ");
    }

    len=sb.length();
    if(len>0)
    {
      return sb.toString().substring(0, len-1);
    }
    return "";
  }
}
