package delta.tools.design;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;
import java.util.List;

import delta.common.utils.files.FilesFinder;
import delta.common.utils.files.filter.ExtensionPredicate;
import delta.tools.design.core.DesignEntitiesManager;

/**
 * Parser for Java source files in a directory.
 * @author DAM
 */
public class JavaSourceDirParser
{
  private DesignEntitiesManager _entitiesManager;

  /**
   * Constructor.
   * @param entitiesManager Storage for parsed entities.
   */
  public JavaSourceDirParser(DesignEntitiesManager entitiesManager)
  {
    _entitiesManager=entitiesManager;
  }

  /**
   * Find all Java source files in the given directory and parse them into the managed entities storage.
   * @param root Directory to use.
   */
  public void parse(File root)
  {
    FilesFinder finder=new FilesFinder();
    FileFilter filter=new ExtensionPredicate("java",false);
    List<File> files=finder.find(FilesFinder.ABSOLUTE_MODE,root,filter,true);
    File javaFile;
    JavaSourceFileParser parser=new JavaSourceFileParser(_entitiesManager);
    for(Iterator<File> it=files.iterator();it.hasNext();)
    {
      javaFile=it.next();
      parser.parse(javaFile);
    }
  }
}
