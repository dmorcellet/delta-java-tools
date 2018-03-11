package delta.tools.java;

import delta.common.utils.configuration.Configurations;
import delta.tools.java.classes.ClassFile;
import delta.tools.java.classes.ClassSource;

public class MainTestClassParser
{
  public MainTestClassParser()
  {
    String path="delta.common.utils.files.iterator.FileIterator";
    ClassSource source=new ClassSource();
    ClassFile clazz=source.getClass(path);
    while (clazz!=null)
    {
      clazz.getInterfaces();
      clazz=clazz.getSuperClass();
    }
  }

  public static void main(String[] args)
  {
    new MainTestClassParser();
  }
}
