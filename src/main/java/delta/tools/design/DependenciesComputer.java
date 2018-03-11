package delta.tools.design;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import delta.tools.design.core.DesignEntitiesManager;
import delta.tools.design.core.JavaClass;
import delta.tools.design.core.JavaPackage;

/**
 * @author DAM
 */
public class DependenciesComputer
{
  private DesignEntitiesManager _entitiesManager;

  public DependenciesComputer(DesignEntitiesManager entitiesManager)
  {
    _entitiesManager=entitiesManager;
  }

  public Set<JavaPackage> getPackageDependenciesForClass(String classFQN)
  {
    Set<JavaPackage> ret=null;
    JavaClass clazz=_entitiesManager.getClazz(classFQN);
    if (clazz!=null)
    {
      ret=new HashSet<JavaPackage>();
      Set<JavaClass> classDeps=clazz.getClassDependencies();
      if ((classDeps!=null)&&(classDeps.size()>0))
      {
        JavaClass currentClassDep;
        JavaPackage classPackage;
        for(Iterator<JavaClass> it=classDeps.iterator();it.hasNext();)
        {
          currentClassDep=it.next();
          classPackage=currentClassDep.getPackage();
          ret.add(classPackage);
        }
      }
      Set<JavaPackage> packageDeps=clazz.getPackageDependencies();
      ret.addAll(packageDeps);
    }
    return ret;
  }

  public Set<JavaPackage> getPackageDependenciesForPackage(String packageFQN)
  {
    Set<JavaPackage> ret=null;
    JavaPackage javaPackage=_entitiesManager.getPackage(packageFQN);
    if (javaPackage!=null)
    {
      ret=new HashSet<JavaPackage>();
      Set<JavaClass> classes=javaPackage.getClasses();
      if ((classes!=null)&&(classes.size()>0))
      {
        JavaClass currentClass;
        Set<JavaPackage> packageDeps;
        String className;
        for(Iterator<JavaClass> it=classes.iterator();it.hasNext();)
        {
          currentClass=it.next();
          className=currentClass.getFullname();
          packageDeps=getPackageDependenciesForClass(className);
          ret.addAll(packageDeps);
        }
      }

      List<JavaPackage> subPackages=javaPackage.getSubPackages();
      if ((subPackages!=null)&&(subPackages.size()>0))
      {
        JavaPackage currentPackage;
        Set<JavaPackage> packageDeps;
        String packageName;
        for(Iterator<JavaPackage> it=subPackages.iterator();it.hasNext();)
        {
          currentPackage=it.next();
          packageName=currentPackage.getFullname();
          packageDeps=getPackageDependenciesForPackage(packageName);
          ret.addAll(packageDeps);
        }
      }
    }
    return ret;
  }
}
