package delta.tools.java.classes.classPath;

import java.io.File;

/**
 * Represents a CLASSPATH element that is an directory.
 * @author DAM
 */
public class ClassPathDir extends ClassPathElement
{
  /**
   * Constructor.
   * @param path Directory path.
   */
  public ClassPathDir(File path)
  {
    super(path);
  }

  @Override
  public boolean validate()
  {
    File path=getPath();
    if (path==null) return false;
    if (!path.isDirectory()) return false;
    return path.canRead();
  }
}
