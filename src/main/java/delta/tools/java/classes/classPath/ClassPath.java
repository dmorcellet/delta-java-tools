package delta.tools.java.classes.classPath;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import delta.common.utils.text.StringSplitter;

/**
 * @author DAM
 */
public class ClassPath
{
  private ArrayList<ClassPathElement> _elements;

  public ClassPath()
  {
    _elements=new ArrayList<ClassPathElement>();
    String classPath=System.getProperty("java.class.path");
    parse(classPath);
    validate();
  }

  private void parse(String classpath)
  {
    String[] elements=StringSplitter.split(classpath,File.pathSeparatorChar);
    if (elements!=null)
    {
      File current;
      ClassPathElement cpElement;
      for(int i=0;i<elements.length;i++)
      {
        current=new File(elements[i]);
        if (ClassPathArchive.isArchive(current))
        {
          cpElement=new ClassPathArchive(current);
        }
        else
        {
          cpElement=new ClassPathDir(current);
        }
        _elements.add(cpElement);
      }
    }
  }

  private void validate()
  {
    ClassPathElement element;
    boolean validated;
    for(Iterator<ClassPathElement> it=_elements.iterator();it.hasNext();)
    {
      element=it.next();
      validated=element.validate();
      if (!validated)
      {
        it.remove();
      }
    }
  }

  @Override
  public String toString()
  {
    return _elements.toString();
  }
}
