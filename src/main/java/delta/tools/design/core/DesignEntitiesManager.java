package delta.tools.design.core;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Manager for design entities (class, packages and archives).
 * @author DAM
 */
public class DesignEntitiesManager
{
  private HashMap<String,JavaClass> _classes;
  private HashMap<String,JavaPackage> _packages;
  private HashMap<String,JavaArchive> _archives;

  /**
   * Constructor.
   */
  public DesignEntitiesManager()
  {
    _classes=new HashMap<String,JavaClass>();
    _packages=new HashMap<String,JavaPackage>();
    _archives=new HashMap<String,JavaArchive>();
  }

  /**
   * Build/add a new class.
   * @param fullyQualifiedName Name of the class to add.
   * @param archiveName Archive that contains this class.
   * @return the newly built class.
   */
  public JavaClass buildClass(String fullyQualifiedName, String archiveName)
  {
    assert fullyQualifiedName!=null;
    JavaClass clazz=getClazz(fullyQualifiedName);
    if (clazz==null)
    {
      String packageName=JavaNamingTools.getPackageNameFromClassFQN(fullyQualifiedName);
      JavaPackage pakkage=buildPackage(packageName);
      String className=JavaNamingTools.getClassNameFromClassFQN(fullyQualifiedName);
      clazz=new JavaClass(className,pakkage);
      _classes.put(fullyQualifiedName,clazz);
      JavaArchive archive=null;
      if (archiveName.length()>0)
      {
        archive=getArchive(archiveName);
        clazz.setArchive(archive);
      }
    }
    else
    {
      // Class already exists.
      // todo Check archives are identical
    }
    return clazz;
  }

  /**
   * Get a class description using its fully qualified name.
   * @param classFQN Fully qualified name of the class to get.
   * @return A class description or <code>null</code>.
   */
  public JavaClass getClazz(String classFQN)
  {
    assert classFQN!=null;
    JavaClass clazz=_classes.get(classFQN);
    return clazz;
  }

  /**
   * Build/add a new package.
   * @param packageFQN Name of the package to add.
   * @return the newly built package.
   */
  public JavaPackage buildPackage(String packageFQN)
  {
    assert packageFQN!=null;
    JavaPackage pakkage=getPackage(packageFQN);
    if (pakkage==null)
    {
      if (packageFQN.length()==0)
      {
        pakkage=new JavaPackage();
      }
      else
      {
        String parentPackage=JavaNamingTools.getParentPackageFromPackageFQN(packageFQN);
        String packageName=JavaNamingTools.getPackageNameFromPackageFQN(packageFQN);
        JavaPackage parent=buildPackage(parentPackage);
        pakkage=new JavaPackage(packageName,parent);
      }
      _packages.put(packageFQN,pakkage);
    }
    return pakkage;
  }

  /**
   * Get a package description using its fully qualified name.
   * @param packageName Fully qualified name of the package to get.
   * @return A package description or <code>null</code>.
   */
  public JavaPackage getPackage(String packageName)
  {
    assert packageName!=null;
    JavaPackage pakkage=_packages.get(packageName);
    return pakkage;
  }

  /**
   * Get an archive description using its name.
   * @param archiveName Name of the archive to get.
   * @return An archive description or <code>null</code>.
   */
  public JavaArchive getArchive(String archiveName)
  {
    assert archiveName!=null;
    assert archiveName.length()>0;

    JavaArchive archive=_archives.get(archiveName);
    if (archive==null)
    {
      archive=new JavaArchive(archiveName);
      _archives.put(archiveName,archive);
    }
    return archive;
  }

  /**
   * Dump the contents of this object on the given stream.
   * @param out Output stream.
   */
  public void dump(PrintStream out)
  {
    // Dump archives
    {
      out.println("Archives : ");
      Collection<JavaArchive> tmp=_archives.values();
      List<JavaArchive> archives=new ArrayList<JavaArchive>(tmp);
      Collections.sort(archives,new JavaArchive.ArchiveNameComparator());
      JavaArchive archive;
      for(Iterator<JavaArchive> it=archives.iterator();it.hasNext();)
      {
        archive=it.next();
        out.println("\t"+archive);
      }
    }
    // Dump packages
    {
      out.println("Packages : ");
      Collection<JavaPackage> tmp=_packages.values();
      List<JavaPackage> packages=new ArrayList<JavaPackage>(tmp);
      Collections.sort(packages,JavaPackage.getPackageNameComparator());
      JavaPackage pakkage;
      for(Iterator<JavaPackage> it=packages.iterator();it.hasNext();)
      {
        pakkage=it.next();
        out.println("\t"+pakkage);
      }
    }
    // Dump classes
    {
      out.println("Classes : ");
      Collection<JavaClass> tmp=_classes.values();
      List<JavaClass> classes=new ArrayList<JavaClass>(tmp);
      Collections.sort(classes);
      JavaClass clazz;
      for(Iterator<JavaClass> it=classes.iterator();it.hasNext();)
      {
        clazz=it.next();
        out.println("\t"+clazz);
      }
    }
  }
}
