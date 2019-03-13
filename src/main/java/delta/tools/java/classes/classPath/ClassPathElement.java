package delta.tools.java.classes.classPath;

import java.io.File;

/**
 * Classpath element.
 * @author DAM
 */
public abstract class ClassPathElement
{
  private File _path;

  /**
   * Constructor.
   * @param path Path (directory or file).
   */
  public ClassPathElement(File path)
  {
    _path=path;
  }

  /**
   * Get the managed file path.
   * @return the managed file path.
   */
  public File getPath()
  {
    return _path;
  }

  /**
   * Indicates if this element is usable or not.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public abstract boolean validate();

  @Override
  public String toString()
  {
    return _path.toString();
  }
}
