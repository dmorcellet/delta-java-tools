package delta.tools.design.core;

/**
 * Tool methods related to Java naming.
 * @author DAM
 */
public class JavaNamingTools
{
  private static final String SEPARATOR=".";

  /**
   * Get the parent package from a package fully qualified name.
   * @param packageName Package fully qualified name.
   * @return the fully qualified name of the parent package.
   */
  public static String getParentPackageFromPackageFQN(String packageName)
  {
    String name;
    int lastPointIndex=packageName.lastIndexOf(SEPARATOR);
    if (lastPointIndex>-1)
    {
      name=packageName.substring(0,lastPointIndex);
    }
    else
    {
      name="";
    }
    return name;
  }

  /**
   * Get the class name from a fully qualified class name.
   * @param fullyQualifiedName Full qualified class name.
   * @return A class name.
   */
  public static String getClassNameFromClassFQN(String fullyQualifiedName)
  {
    String name;
    int lastPointIndex=fullyQualifiedName.lastIndexOf(SEPARATOR);
    if (lastPointIndex>-1)
    {
      name=fullyQualifiedName.substring(lastPointIndex+1);
    }
    else
    {
      name=fullyQualifiedName;
    }
    return name;
  }

  /**
   * Get the package name from a fully qualified package name.
   * @param fullyQualifiedName Full qualified package name.
   * @return A package name.
   */
  public static String getPackageNameFromPackageFQN(String fullyQualifiedName)
  {
    String name;
    int lastPointIndex=fullyQualifiedName.lastIndexOf(SEPARATOR);
    if (lastPointIndex>-1)
    {
      name=fullyQualifiedName.substring(lastPointIndex+1);
    }
    else
    {
      name=fullyQualifiedName;
    }
    return name;
  }

  /**
   * Get the package fully qualified name from a fully qualified class name.
   * @param fullyQualifiedName Full qualified class name.
   * @return A fully qualified package name.
   */
  public static String getPackageNameFromClassFQN(String fullyQualifiedName)
  {
    String name;
    int lastPointIndex=fullyQualifiedName.lastIndexOf(SEPARATOR);
    if (lastPointIndex>-1)
    {
      name=fullyQualifiedName.substring(0,lastPointIndex);
    }
    else
    {
      name="";
    }
    return name;
  }
}
