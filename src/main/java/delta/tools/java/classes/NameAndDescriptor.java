package delta.tools.java.classes;

/**
 * Reference to a name and a descriptor.
 * @author DAM
 */
public class NameAndDescriptor
{
  private short _nameIndex;
  private short _descriptorIndex;

  /**
   * Constructor.
   * @param nameIndex Index of the name in the constants pool.
   * @param descriptorIndex Index of the descriptor in the constants pool.
   */
  public NameAndDescriptor(short nameIndex, short descriptorIndex)
  {
    _nameIndex=nameIndex;
    _descriptorIndex=descriptorIndex;
  }

  short getNameIndex()
  {
    return _nameIndex;
  }

  short getDescriptorIndex()
  {
    return _descriptorIndex;
  }

  @Override
  public String toString()
  {
    return "Name index:"+_nameIndex+", descriptor index: "+_descriptorIndex;
  }
}
