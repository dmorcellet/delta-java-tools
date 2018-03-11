package delta.tools.java.classes;

public class NameAndDescriptor
{
  private short _nameIndex;
  private short _descriptorIndex;

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
}
