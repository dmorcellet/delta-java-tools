package delta.tools.design;

import java.io.File;
import java.util.StringTokenizer;

import delta.common.utils.files.TextFileReader;
import delta.tools.design.core.DesignEntitiesManager;
import delta.tools.design.core.JavaClass;

/**
 * Parser for Java source files.
 * @author DAM
 */
public class JavaSourceFileParser
{
  private static final String JAVA_EXTENSION=".java";
  private static final String PACKAGE_DECLARATION_SEED="package ";

  private DesignEntitiesManager _entitiesManager;

  /**
   * Constructor.
   * @param entitiesManager Storage for parsed entities.
   */
  public JavaSourceFileParser(DesignEntitiesManager entitiesManager)
  {
    _entitiesManager=entitiesManager;
  }

  /**
   * Parse a Java source file.
   * @param file File to use.
   */
  public void parse(File file)
  {
    if (file==null)
    {
      return;
    }
    String fileName=file.getName();
    if (!fileName.endsWith(JAVA_EXTENSION))
    {
      return;
    }
    String className=fileName.substring(0,fileName.length()-JAVA_EXTENSION.length());

    String packageName="";

    JavaClass clazz=null;
    // clazz.setArchive(SAArchive.getArchive(jarName));
    TextFileReader reader=new TextFileReader(file);
    String ligne=null;
    if (reader.start())
    {
      while (true)
      {
        ligne=reader.getNextLine();
        if (ligne==null)
        {
          break;
        }
        ligne=ligne.trim();
        if (ligne.startsWith("import "))
        {
          StringTokenizer stImport=new StringTokenizer(ligne);
          stImport.nextToken(); // import
          String nomImport=stImport.nextToken().trim();
          if (nomImport.endsWith(";"))
          {
            nomImport=nomImport.substring(0,nomImport.length()-1);
            nomImport=nomImport.trim();
          }
          JavaClass importedClass=getClass(nomImport);
          if (importedClass!=null)
          {
            if (clazz==null)
            {
              clazz=getClass(packageName,className);
            }
            clazz.addClassDependency(importedClass);
          }
        }
        if (ligne.startsWith(PACKAGE_DECLARATION_SEED))
        {
          if (ligne.endsWith(";"))
          {
            ligne=ligne.substring(0,ligne.length()-1);
            ligne=ligne.trim();
          }
          packageName=ligne.substring(PACKAGE_DECLARATION_SEED.length());
          if (clazz==null)
          {
            clazz=getClass(packageName,className);
          }
        }
      }
      reader.terminate();
    }
  }

  private JavaClass getClass(String packageName, String className)
  {
    String classFQN="";
    if (packageName==null)
    {
      packageName="";
    }
    if (packageName.length()==0)
    {
      classFQN=className;
    }
    else
    {
      classFQN=packageName+"."+className;
    }
    JavaClass clazz=_entitiesManager.buildClass(classFQN,"");
    return clazz;
  }

  private JavaClass getClass(String classFQN)
  {
    if (classFQN==null) return null;
    if (classFQN.length()==0) return null;
    JavaClass clazz=_entitiesManager.buildClass(classFQN,"");
    return clazz;
  }
}
