package delta.tools.design.core;

/**
 * @author DAM
 */
public class JavaNamingTools
{
  private static final String SEPARATOR=".";

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
