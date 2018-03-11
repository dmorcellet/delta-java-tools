package delta.tools.java.classes;

public class ClassNameAndType
{
  private short _classIndex;
  private short _nameAndTypeIndex;

  public ClassNameAndType(short classIndex, short nameAndTypeIndex)
  {
    _classIndex=classIndex;
    _nameAndTypeIndex=nameAndTypeIndex;
  }

  short getClassIndex()
  {
    return _classIndex;
  }

  short getNameAndTypeIndex()
  {
    return _nameAndTypeIndex;
  }
}
