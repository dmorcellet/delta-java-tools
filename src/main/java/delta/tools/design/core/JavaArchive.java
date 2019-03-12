package delta.tools.design.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Description of a JAva aRchive (JAR).
 * @author DAM
 */
public class JavaArchive
{
  private String _name=null;

  private Set<JavaClass> _classes=new HashSet<JavaClass>();

  /**
   * Constructor.
   * @param name Archive name.
   */
  protected JavaArchive(String name)
  {
    _name=name;
  }

  /**
   * Get the name of this archive.
   * @return a name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Add a class in this archive.
   * @param clazz Class to add.
   */
  void addClass(JavaClass clazz)
  {
    _classes.add(clazz);
  }

  /**
   * Remove a class from this archive.
   * @param clazz Class to remove.
   */
  void removeClass(JavaClass clazz)
  {
    _classes.remove(clazz);
  }

  /**
   * Indicates if this archives contains the given class.
   * @param clazz Class to use.
   * @return <code>true</code> if it does, <code>false</code> otherwise.
   */
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

  /**
   * Comparator for archives, using their name.
   * @author DAM
   */
  public static class ArchiveNameComparator implements Comparator<JavaArchive>, Serializable
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
