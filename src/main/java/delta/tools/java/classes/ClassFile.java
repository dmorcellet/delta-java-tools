package delta.tools.java.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structured representation of the contents of a class file.
 * @author DAM
 */
public class ClassFile
{
  private ClassSource _source;
  private Map<String,ClassField> _fields;
  private Map<String,ClassMethod> _methods;
  private List<String> _interfaceNames;
  private String _superClassName;
  private String _className;

  /**
   * Constructor.
   * @param source Source for classes.
   * @param className Class fully-qualified name.
   */
  public ClassFile(ClassSource source, String className)
  {
    _source=source;
    _className=className;
    _fields=new HashMap<String,ClassField>();
    _methods=new HashMap<String,ClassMethod>();
    _interfaceNames=new ArrayList<String>();
  }

  /**
   * Get the class fully qualified name.
   * @return a class fully qualified name.
   */
  public String getClassName()
  {
    return _className;
  }

  /**
   * Get the super class.
   * @return A super class if any or <code>null</code> if none.
   */
  public ClassFile getSuperClass()
  {
    if (_superClassName==null) return null;
    return _source.getClass(_superClassName);
  }

  /**
   * Get the implemented interfaces.
   * @return A possibly empty, but not <code>null</code> array of class files which represent interfaces.
   */
  public ClassFile[] getInterfaces()
  {
    int nbInterfaces=_interfaceNames.size();
    ClassFile[] array=new ClassFile[nbInterfaces];
    for(int i=0;i<nbInterfaces;i++)
    {
      array[i]=_source.getClass(_interfaceNames.get(i));
    }
    return array;
  }

  /**
   * Set the superclass name.
   * @param superClassName Fully qualified name.
   */
  public void setSuperClassName(String superClassName)
  {
    _superClassName=superClassName;
  }

  /**
   * Add a field description.
   * @param field Field to add.
   */
  public void addField(ClassField field)
  {
    _fields.put(field.getName(),field);
  }

  /**
   * Add an interface.
   * @param interfaceName Fully qualified name of interface.
   */
  public void addInterface(String interfaceName)
  {
    _interfaceNames.add(interfaceName);
  }

  /**
   * Add a method description.
   * @param method Method to add.
   */
  public void addMethod(ClassMethod method)
  {
    _methods.put(method.getName(),method);
  }
}
