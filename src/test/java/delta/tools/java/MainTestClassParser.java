package delta.tools.java;

import delta.common.utils.configuration.Configurations;
import delta.tools.java.classes.ClassFile;
import delta.tools.java.classes.ClassSource;

public class MainTestClassParser
{
  public MainTestClassParser()
  {
    String path=Configurations.getUserConfiguration().getStringValue("JAVA", "TEST_CLASS_NAME", "/tmp/toto");
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
