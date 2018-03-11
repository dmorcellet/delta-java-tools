package delta.tools.design;

import java.io.File;
import java.util.StringTokenizer;

import delta.common.utils.files.TextFileReader;
import delta.tools.design.core.DesignEntitiesManager;
import delta.tools.design.core.JavaClass;

/**
 * @author DAM
 */
public class JavaSourceFileParser
{
  private static final String JAVA_EXTENSION=".java";
  private static final String PACKAGE_DECLARATION_SEED="package ";

  private DesignEntitiesManager _entitiesManager;

  public JavaSourceFileParser(DesignEntitiesManager entitiesManager)
  {
    _entitiesManager=entitiesManager;
  }

  public void parse(File fichier_p)
  {
    if (fichier_p==null)
    {
      return;
    }
    String fileName=fichier_p.getName();
    if (!fileName.endsWith(JAVA_EXTENSION))
    {
      return;
    }
    String className=fileName.substring(0,fileName.length()-JAVA_EXTENSION.length());

    String packageName="";

    JavaClass clazz=null;
    // classe_l.setArchive(SAArchive.getArchive(jarName));
    TextFileReader reader=new TextFileReader(fichier_p);
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
          StringTokenizer stImport_l=new StringTokenizer(ligne);
          stImport_l.nextToken(); // import
          String nomImport_l=stImport_l.nextToken().trim();
          if (nomImport_l.endsWith(";"))
          {
            nomImport_l=nomImport_l.substring(0,nomImport_l.length()-1);
            nomImport_l=nomImport_l.trim();
          }
          JavaClass importedClass=getClass(nomImport_l);
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
