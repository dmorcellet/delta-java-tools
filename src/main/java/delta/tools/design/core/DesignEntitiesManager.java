package delta.tools.design.core;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author DAM
 */
public class DesignEntitiesManager
{
  private HashMap<String,JavaClass> _classes;
  private HashMap<String,JavaPackage> _packages;
  private HashMap<String,JavaArchive> _archives;

  public DesignEntitiesManager()
  {
    _classes=new HashMap<String,JavaClass>();
    _packages=new HashMap<String,JavaPackage>();
    _archives=new HashMap<String,JavaArchive>();
  }

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

  public JavaClass getClazz(String classFQN)
  {
    assert classFQN!=null;
    JavaClass clazz=_classes.get(classFQN);
    return clazz;
  }

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

  public JavaPackage getPackage(String packageName)
  {
    assert packageName!=null;
    JavaPackage pakkage=_packages.get(packageName);
    return pakkage;
  }

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

  public void dump(PrintStream out)
  {
    // Dump archives
    {
      out.println("Archives : ");
      Collection<JavaArchive> tmp=_archives.values();
      List<JavaArchive> archives=new ArrayList<JavaArchive>(tmp);
      Collections.sort(archives,JavaArchive.getArchiveNameComparator());
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
