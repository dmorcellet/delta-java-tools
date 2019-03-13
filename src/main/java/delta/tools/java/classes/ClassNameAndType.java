package delta.tools.java.classes;

/**
 * Reference to a class name and a 'name and type'.
 * @author DAM
 */
public class ClassNameAndType
{
  private short _classIndex;
  private short _nameAndTypeIndex;

  /**
   * Constructor.
   * @param classIndex Index of the class in the constants pool.
   * @param nameAndTypeIndex Index of the 'name and type' in the constants pool.
   */
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

  @Override
  public String toString()
  {
    return "Class index:"+_classIndex+", 'name and type' index: "+_nameAndTypeIndex;
  }
 
}
