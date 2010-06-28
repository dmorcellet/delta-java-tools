package delta.tools.design.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JavaArchive
{
  private static Map<String,JavaArchive> _archivesMap=new TreeMap<String,JavaArchive>();
  private static ArchiveNameComparator _comparator=new ArchiveNameComparator();

  private String _name=null;

  private Set<JavaClass> _classes=new HashSet<JavaClass>();

  public static JavaArchive getArchive(String name_p)
  {
    JavaArchive archive_l=_archivesMap.get(name_p);
    if (archive_l==null)
    {
      archive_l=new JavaArchive(name_p);
      _archivesMap.put(name_p,archive_l);
    }
    return archive_l;
  }

  public static Collection<JavaArchive> getArchives()
  {
    return _archivesMap.values();
  }

  protected JavaArchive(String name_p)
  {
    _name=name_p;
  }

  public String getName()
  {
    return _name;
  }

  void addClass(JavaClass clazz)
  {
    _classes.add(clazz);
  }

  void removeClass(JavaClass clazz)
  {
    _classes.remove(clazz);
  }

  public boolean containsClass(JavaClass clazz)
  {
    return _classes.contains(clazz);
  }

  @Override
  public String toString()
  {
    return getName();
  }

  /**
   * Retourne un iterateur sur toutes les classes de l'archive.
   * @return un iterateur sur toutes les classes de l'archive.
   */
  public Iterator<JavaClass> getClasses()
  {
    return _classes.iterator();
  }

  public static ArchiveNameComparator getArchiveNameComparator()
  {
    return _comparator;
  }

  private static class ArchiveNameComparator implements Comparator<JavaArchive>,Serializable
  {
    /**
     * Compares java archives by their name.
     * @param archive1 an archive.
     * @param archive2 another archive.
     */
    public int compare(JavaArchive archive1, JavaArchive archive2)
    {
      String name1=archive1.getName();
      String name2=archive2.getName();
      return name1.compareTo(name2);
    }
  }
}
