package delta.tools.design.core;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Description of a Java package.
 * @author DAM
 */
public class JavaPackage
{
  private String _name;
  private JavaPackage _parentPackage;
  private Set<JavaPackage> _subPackages;
  private Set<JavaClass> _classes;
  private Set<JavaClass> _roClasses;

  /**
   * Constructor.
   * @param name Package name.
   * @param parentPackage Parent package (may be <code>null</code> for the root package.
   */
  JavaPackage(String name, JavaPackage parentPackage)
  {
    _name=name;
    _parentPackage=parentPackage;
    _subPackages=new HashSet<JavaPackage>();
    _classes=new HashSet<JavaClass>();
    _roClasses=Collections.unmodifiableSet(_classes);
    if (parentPackage!=null)
    {
      parentPackage.addSubPackage(this);
    }
  }

  /**
   * Constructor for a "root" package.
   */
  JavaPackage()
  {
    this("",null);
  }

  /**
   * Get the name of this package.
   * @return the name of this package.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Get the parent package.
   * @return a package or <code>null</code> if no parent.
   */
  public JavaPackage getParentPackage()
  {
    return _parentPackage;
  }

  /**
   * Add a child package.
   * @param pakkage Package to add.
   */
  void addSubPackage(JavaPackage pakkage)
  {
    _subPackages.add(pakkage);
  }

  /**
   * Get all child packages.
   * @return a possibly empty, but not <code>null</code> list of child packages, sorted by name.
   */
  public List<JavaPackage> getSubPackages()
  {
    List<JavaPackage> ret=new ArrayList<JavaPackage>(_subPackages.size());
    ret.addAll(_subPackages);
    Collections.sort(ret,new PackageNameComparator());
    return ret;
  }

  /**
   * Get all the Java class directly in this package.
   * @return a possibly empty but not <code>null</code> set of classes, unmodifiable.
   */
  public Set<JavaClass> getClasses()
  {
    return _roClasses;
  }

  /**
   * Add a class in this package.
   * @param clazz Class to add.
   */
  void addClass(JavaClass clazz)
  {
    if (clazz.getPackage()==this)
    {
      _classes.add(clazz);
    }
  }

  /**
   * Indicates if this package contains the given class.
   * @param clazz Class to test.
   * @return <code>true</code> if it does, <code>false</code> otherwise.
   */
  public boolean containsClass(JavaClass clazz)
  {
    return _classes.contains(clazz);
  }

  /**
   * Get the fully qualified name of this package.
   * @return A fully qualified package name.
   */
  public String getFullname()
  {
    if (_parentPackage==null) return _name;
    String parentName=_parentPackage.getFullname();
    String fullName=parentName;
    if (parentName.length()>0) fullName=fullName+".";
    fullName=fullName+_name;
    return fullName;
  }

  @Override
  public String toString()
  {
    return getFullname();
  }

  /**
   * Dump the contents of this package to the given output stream.
   * @param out Output stream.
   * @param level Indentation level.
   */
  public void dump(PrintStream out, int level)
  {
    for(int i=0;i<level;i++)
      out.print('\t');
    out.println(getFullname());
    for(Iterator<JavaClass> it=_classes.iterator();it.hasNext();)
    {
      JavaClass item=it.next();
      for(int i=0;i<level+1;i++)
        out.print('\t');
      out.println(item.getFullname());
    }
    List<JavaPackage> subPackages=getSubPackages();
    for(Iterator<JavaPackage> i=subPackages.iterator();i.hasNext();)
    {
      JavaPackage item=i.next();
      item.dump(out,level+1);
    }
  }

  /**
   * Comparator for packages, using their name.
   * @author DAM
   */
  public static class PackageNameComparator implements Comparator<JavaPackage>, Serializable
  {
    /**
     * Compares java packages by their full name.
     * @param package1 a package.
     * @param package2 another package.
     */
    public int compare(JavaPackage package1, JavaPackage package2)
    {
      String name1=package1.getFullname();
      String name2=package2.getFullname();
      return name1.compareTo(name2);
    }
  }
}
