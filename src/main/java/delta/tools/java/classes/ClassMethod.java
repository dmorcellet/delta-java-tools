package delta.tools.java.classes;

/**
 * Description of the method of a class.
 * @author DAM
 */
public class ClassMethod
{
  private String _name;
  private String _prototype;
  private short _accessFlags;

  /**
   * Constructor.
   * @param name Method name.
   * @param prototype Encoded prototype.
   * @param accessFlags Access flags.
   */
  public ClassMethod(String name, String prototype, short accessFlags)
  {
    _name=name;
    _prototype=prototype;
    _accessFlags=accessFlags;
  }

  /**
   * Get the name of this method.
   * @return the name of this method.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the encoded prototype for this method.
   * @return an encoded prototype.
   */
  public String getEncodedPrototype()
  {
    return _prototype;
  }

  /**
   * Get a readable prototype string for this method.
   * @param useFullyQualifiedNames Use fully qualified names for types or not.
   * @return A displayable string.
   */
  public String getPrototype(boolean useFullyQualifiedNames)
  {
    StringBuffer sb=new StringBuffer();
    String accessFlags=MethodAccessFlags.toString(_accessFlags);
    sb.append(accessFlags);
    if (accessFlags.length()>0)
    {
      sb.append(' ');
    }
    sb.append(new DescriptorParser().parseMethodDescriptor(_prototype,_name,useFullyQualifiedNames));
    return sb.toString();
  }
}
