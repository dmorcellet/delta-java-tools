package delta.tools.java.classes;

/**
 * Flag values for class access modes.
 * @author DAM
 */
public class ClassAccessFlags
{
  /**
   * Public.
   */
  public static final int PUBLIC=0x0001;
  /**
   * Final.
   */
  public static final int FINAL=0x0010;
  /**
   * Super.
   */
  public static final int SUPER=0x0020;
  /**
   * Interface.
   */
  public static final int INTERFACE=0x0200;
  /**
   * Abstract.
   */
  public static final int ABSTRACT=0x0400;

  /**
   * Build a displayable string for some access flags.
   * @param accessFlags Access flags to use.
   * @return A readable string.
   */
  public static String toString(int accessFlags)
  {
    StringBuffer sb=new StringBuffer();
    if ((accessFlags&PUBLIC)!=0)
    {
      sb.append("public ");
    }
    if ((accessFlags&ABSTRACT)!=0)
    {
      sb.append("abstract ");
    }
    if ((accessFlags&FINAL)!=0)
    {
      sb.append("final ");
    }
    if ((accessFlags&INTERFACE)!=0)
    {
      sb.append("interface ");
    }
    return sb.toString().trim();
  }
}
