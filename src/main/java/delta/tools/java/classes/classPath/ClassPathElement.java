package delta.tools.java.classes.classPath;

import java.io.File;

/**
 * @author DAM
 */
public abstract class ClassPathElement
{
  private File _path;

  public ClassPathElement(File path)
  {
    _path=path;
  }

  public File getPath()
  {
    return _path;
  }

  public abstract boolean validate();

  @Override
  public String toString()
  {
    return _path.toString();
  }
}
