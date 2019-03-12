package delta.tools.design.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Description of a Java class.
 * @author DAM
 */
public class JavaClass implements Comparable<JavaClass>
{
  /**
   * Name of the class.
   */
  private String _name;
  /**
   * Package of this class.
   */
  private JavaPackage _package;
  /**
   * Archive that contains this class.
   */
  private JavaArchive _archive;

  /**
   * Set of packages this class depends on.
   */
  private Set<JavaPackage> _packageDeps;
  /**
   * Idem, as an unmodifiable set.
   */
  private Set<JavaPackage> _roPackageDeps;
  /**
   * Set of classes this class depends on.
   */
  private Set<JavaClass> _classDeps;
  /**
   * Idem, as an unmodifiable set.
   */
  private Set<JavaClass> _roClassDeps;

  /**
   * Constructor.
   * @param name Class name.
   * @param pakkage Parent package.
   */
  JavaClass(String name, JavaPackage pakkage)
  {
    assert name!=null;
    assert name.length()>0;
    _name=name;
    _package=pakkage;
    _packageDeps=new HashSet<JavaPackage>();
    _roPackageDeps=Collections.unmodifiableSet(_packageDeps);
    _classDeps=new HashSet<JavaClass>();
    _roClassDeps=Collections.unmodifiableSet(_classDeps);
    pakkage.addClass(this);
  }

  /**
   * Add a dependency to a given class.
   * @param clazz Dependency to add.
   */
  public void addClassDependency(JavaClass clazz)
  {
    assert clazz!=null;
    _classDeps.add(clazz);
  }

  /**
   * Add a dependency to a given package.
   * @param pakkage Dependency to add.
   */
  public void addPackageDependency(JavaPackage pakkage)
  {
    assert pakkage!=null;
    _packageDeps.add(pakkage);
  }

  /**
   * Get the name of this class.
   * @return the name of this class.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the fully qualified name of this class.
   * @return the fully qualified name of this class.
   */
  public String getFullname()
  {
    String packageName=_package.getFullname();
    if (packageName.length()==0) return _name;
    return packageName+"."+_name;
  }

  /**
   * Get the parent package.
   * @return the parent package.
   */
  public JavaPackage getPackage()
  {
    return _package;
  }

  /**
   * Get the parent archive;
   * @return the parent package.
   */
  public JavaArchive getArchive()
  {
    return _archive;
  }

  /**
   * Set the parent archive.
   * @param archive Archive to use.
   */
  void setArchive(JavaArchive archive)
  {
    if (_archive!=archive)
    {
      if (_archive!=null)
      {
        _archive.removeClass(this);
      }
      _archive=archive;
      if (archive!=null)
      {
        _archive.addClass(this);
      }
    }
  }

  /**
   * Get the class dependencies.
   * @return a set of classes.
   */
  public Set<JavaClass> getClassDependencies()
  {
    return _roClassDeps;
  }

  /**
   * Get the package dependencies.
   * @return a set of packages.
   */
  public Set<JavaPackage> getPackageDependencies()
  {
    return _roPackageDeps;
  }

  public int compareTo(JavaClass o)
  {
    String myName=getFullname();
    String oName=o.getFullname();
    int ret=myName.compareTo(oName);
    return ret;
  }

  /**
   * Get a hash code for this instance.
   * @see java.lang.Object#hashCode()
   * @return A hash code for this instance.
   */
  @Override
  public int hashCode()
  {
    return _name.hashCode();
  }

  @Override
  public boolean equals(Object parm1)
  {
    return parm1!=null&&parm1 instanceof JavaClass&&((JavaClass)parm1).compareTo(this)==0;
  }

  @Override
  public String toString()
  {
    StringBuffer sb=new StringBuffer();
    String fullName=getFullname();
    sb.append(fullName);
    JavaArchive archive=_archive;
    if (archive!=null)
    {
      sb.append(" (archive=");
      sb.append(archive.getName());
      sb.append(')');
    }
    return sb.toString();
  }
}
