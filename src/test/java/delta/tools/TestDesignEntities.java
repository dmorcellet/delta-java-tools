package delta.tools;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.common.utils.files.FilesFinder;
import delta.common.utils.files.filter.ExtensionPredicate;
import delta.tools.design.DependenciesComputer;
import delta.tools.design.JavaSourceFileParser;
import delta.tools.design.core.DesignEntitiesManager;
import delta.tools.design.core.JavaPackage;

/**
 * Test class for the design entities.
 * @author DAM
 */
@DisplayName("Design entities test")
class TestDesignEntities
{
  /**
   * Test build.
   */
  @Test
  void testBuild()
  {
    DesignEntitiesManager mgr=new DesignEntitiesManager();
    mgr.buildClass(TestDesignEntities.class.getName(),"delta");
    mgr.buildClass(JavaPackage.class.getName(),"delta");
    mgr.buildClass(Test.class.getName(),"junit");
    assertNotNull(mgr.getClazz(Test.class.getName()));
  }

  /**
   * Test 'dump' method.
   */
  @Test
  void testDump()
  {
    DesignEntitiesManager mgr=new DesignEntitiesManager();

    File src=new File("src").getAbsoluteFile();
    File codeRoot=new File(new File(src,"main"),"java");
    FilesFinder finder=new FilesFinder();
    FileFilter filter=new ExtensionPredicate("java",false);
    List<File> files=finder.find(FilesFinder.ABSOLUTE_MODE,codeRoot,filter,true);
    JavaSourceFileParser parser=new JavaSourceFileParser(mgr);
    for(File javaFile : files)
    {
      parser.parse(javaFile);
    }
    JavaPackage rootPackage=mgr.getPackage("");
    assertNotNull(rootPackage);
    rootPackage.dump(System.out,-1);
    mgr.dump(System.out);
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
