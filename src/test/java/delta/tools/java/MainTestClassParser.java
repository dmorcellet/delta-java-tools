package delta.tools.java;

import delta.tools.java.classes.ClassFile;
import delta.tools.java.classes.ClassSource;

/**
 * @author DAM
 * Main class to test the class parser.
 */
public class MainTestClassParser
{
  /**
   * Constructor.
   */
  public MainTestClassParser()
  {
    String path="delta.common.utils.files.iterator.FileIterator";
    ClassSource source=new ClassSource();
    ClassFile clazz=source.getClass(path);
    while (clazz!=null)
    {
      clazz.getInterfaces();
      clazz=clazz.getSuperClass();
    }
  }

  /**
   * Main method for this test.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    new MainTestClassParser();
  }
}
