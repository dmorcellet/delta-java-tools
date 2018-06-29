package delta.tools.java.classes;

/**
 * Represents a field description in a Java class.
 * @author DAM
 */
public class ClassField
{
  private String _name;
  private String _typeDescriptor;
  private short _accessFlags;

  /**
   * Constructor.
   * @param name Field name.
   * @param type Field type descriptor.
   * @param accessFlags Access flag for the field.
   */
  public ClassField(String name, String type, short accessFlags)
  {
    _name=name;
    _typeDescriptor=type;
    _accessFlags=accessFlags;
  }

  /**
   * Get the name of this field.
   * @return a field name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the type descriptor for this field.
   * @return A type descriptor.
   */
  public String getTypeDescriptor()
  {
    return _typeDescriptor;
  }

  /**
   * Build a full displayable prototype string for this field.
   * @param useFullyQualifiedNames Use fully qualified names for classes, or not.
   * @return A displayable prototype string. For instance:
   * <ul>
   * <li>private int id
   * <li>public String name
   * </ul>
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
    sb.append(new DescriptorParser().parseFieldType(_typeDescriptor,useFullyQualifiedNames));
    sb.append(' ');
    sb.append(_name);
    return sb.toString();
  }
}
