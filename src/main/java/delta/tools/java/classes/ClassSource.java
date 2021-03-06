package delta.tools.java.classes;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.util.ClassPath;

import delta.tools.java.classes.classFile.ClassFileParser;

/**
 * Source for classes.
 * @author DAM
 */
public class ClassSource
{
  private ClassPath _classPath;
  private Map<String,ClassFile> _classMap;

  /**
   * Constructor.
   */
  public ClassSource()
  {
    _classPath=new ClassPath(ClassPath.getClassPath());
    _classMap=new HashMap<String,ClassFile>();
  }

  /**
   * Get a class description using its name.
   * @param className Fully qualified name of the class to get.
   * @return A class description or <code>null</code> if not found.
   */
  public ClassFile getClass(String className)
  {
    ClassFile clazz=_classMap.get(className);
    if (clazz==null)
    {
      System.out.println("*************** GET "+className);
      clazz=new ClassFile(this,className);
      _classMap.put(className,clazz);
      parse(clazz);
    }
    return clazz;
  }

  private void parse(ClassFile classFile)
  {
    try
    {
      InputStream is=_classPath.getInputStream(classFile.getClassName());
      DataInputStream dis=new DataInputStream(is);
      ClassFileParser cp=new ClassFileParser(classFile,dis);
      cp.parse();
      dis.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
