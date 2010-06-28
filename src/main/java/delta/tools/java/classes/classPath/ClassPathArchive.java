package delta.tools.java.classes.classPath;

import java.io.File;

/**
 * Represents a CLASSPATH element that is an archive.
 * @author DAM
 */
public class ClassPathArchive extends ClassPathElement
{
  private static final String[] KNOWN_EXTENSIONS={".jar",".zip"};

  public ClassPathArchive(File path)
  {
    super(path);
  }

  @Override
  public boolean validate()
  {
    File path=getPath();
    if (path==null) return false;
    return path.canRead();
  }

  /**
   * Indicates if the specified file is an archive or not.
   * @param file File to test.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public static boolean isArchive(File file)
  {
    if (file==null) return false;
    String name=file.getName();
    for(int i=0;i<KNOWN_EXTENSIONS.length;i++)
    {
      if (KNOWN_EXTENSIONS[i].equalsIgnoreCase(name)) return true;
    }
    return false;
  }
}
