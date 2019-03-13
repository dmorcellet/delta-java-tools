package delta.tools.java;

import delta.tools.java.classes.classPath.ClassPath;

/**
 * Main class to test the classpath facility.
 * @author DAM
 */
public class MainTestClassPath
{
  /**
   * Main method for this test.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    ClassPath cp=new ClassPath();
    System.out.println(cp.toString());
  }
}
