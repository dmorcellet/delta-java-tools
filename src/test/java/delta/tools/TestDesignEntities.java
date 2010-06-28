package delta.tools;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import delta.common.utils.environment.FileSystem;
import delta.common.utils.files.FilesFinder;
import delta.common.utils.files.filter.ExtensionPredicate;
import delta.tools.design.DependenciesComputer;
import delta.tools.design.JavaSourceFileParser;
import delta.tools.design.core.DesignEntitiesManager;
import delta.tools.design.core.JavaPackage;

public class TestDesignEntities extends TestCase
{
  /**
   * Constructor.
   */
  public TestDesignEntities()
  {
    super("Design entities test");
  }

  public void testDump()
  {
    DesignEntitiesManager mgr=new DesignEntitiesManager();
/*
    mgr.buildClass(TestDesignEntities.class.getName(),"delta");
    mgr.buildClass(JavaPackage.class.getName(),"delta");
    mgr.buildClass(TestCase.class.getName(),"junit");
*/

    File deltaHome=FileSystem.getDeltaHomeDir();
    File src=new File(deltaHome,"src");
    File codeRoot=new File(src,"java");
    FilesFinder finder=new FilesFinder();
    FileFilter filter=new ExtensionPredicate("java",false);
    List<File> files=finder.find(FilesFinder.ABSOLUTE_MODE,codeRoot,filter,true);
    File javaFile;
    JavaSourceFileParser parser=new JavaSourceFileParser(mgr);
    for(Iterator<File> it=files.iterator();it.hasNext();)
    {
      javaFile=it.next();
      parser.parse(javaFile);
    }
    //JavaPackage rootPackage=mgr.getPackage("");
    //rootPackage.dump(System.out,-1);
    //mgr.dump(System.out);
    DependenciesComputer deps=new DependenciesComputer(mgr);
    {
      String thisClassName=TestDesignEntities.class.getName();
      Set<JavaPackage> packageDeps=deps.getPackageDependenciesForClass(thisClassName);
      System.out.println("Package deps : "+packageDeps);
    }
    {
      String packageName="delta.tools";
      Set<JavaPackage> packageDeps=deps.getPackageDependenciesForPackage(packageName);
      System.out.println("Package deps : ");
      dumpSet(packageDeps);
    }
  }

  private static void dumpSet(Set<?> set)
  {
    List<String> tmp=new ArrayList<String>(set.size());
    for(Iterator<?> it=set.iterator();it.hasNext();)
    {
      tmp.add(it.next().toString());
    }
    Collections.sort(tmp);
    for(Iterator<String> it=tmp.iterator();it.hasNext();)
    {
      System.out.println(it.next());
    }
  }
}