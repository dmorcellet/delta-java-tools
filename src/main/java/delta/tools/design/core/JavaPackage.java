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

public class JavaPackage
{
  private static PackageNameComparator _comparator=new PackageNameComparator();
  private String _name;
  private JavaPackage _parentPackage;
  private Set<JavaPackage> _subPackages;
  private Set<JavaClass> _classes;
  private Set<JavaClass> _roClasses;

  JavaPackage(String name_p, JavaPackage parentPackage)
  {
    _name=name_p;
    _parentPackage=parentPackage;
    _subPackages=new HashSet<JavaPackage>();
    _classes=new HashSet<JavaClass>();
    _roClasses=Collections.unmodifiableSet(_classes);
    parentPackage.addSubPackage(this);
  }

  JavaPackage()
  {
    _name="";
    _parentPackage=null;
    _subPackages=new HashSet<JavaPackage>();
    _classes=new HashSet<JavaClass>();
  }

  public String getName()
  {
    return _name;
  }

  public JavaPackage getParentPackage()
  {
    return _parentPackage;
  }

  void addSubPackage(JavaPackage pakkage)
  {
    _subPackages.add(pakkage);
  }

  public List<JavaPackage> getSubPackages()
  {
    List<JavaPackage> ret=new ArrayList<JavaPackage>(_subPackages.size());
    ret.addAll(_subPackages);
    Collections.sort(ret,_comparator);
    return ret;
  }

  public Set<JavaClass> getClasses()
  {
    return _roClasses;
  }

  void addClass(JavaClass class_p)
  {
    if (class_p.getPackage()==this)
    {
      _classes.add(class_p);
    }
  }

  public boolean containsClass(JavaClass clazz)
  {
    return _classes.contains(clazz);
  }

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

  public static PackageNameComparator getPackageNameComparator()
  {
    return _comparator;
  }

  private static class PackageNameComparator implements Comparator<JavaPackage>, Serializable
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
