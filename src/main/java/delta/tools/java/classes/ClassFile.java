package delta.tools.java.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFile
{
	private ClassSource _source;
  private Map<String, ClassField> _fields;
  private Map<String, ClassMethod> _methods;
  private List<String> _interfaceNames;
  private String _superClassName;
  private String _className;

  private int _size;

  public ClassFile(ClassSource source, String className)
  {
  	_source=source;
  	_className=className;
    _fields=new HashMap<String, ClassField>();
    _methods=new HashMap<String, ClassMethod>();
    _interfaceNames=new ArrayList<String>();
    _size=0;
  }

  public String getClassName()
  {
  	return _className;
  }

  public ClassFile getSuperClass()
  {
  	if (_superClassName==null) return null;
  	return _source.getClass(_superClassName);
  }

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

  public void setSuperClassName(String superClassName)
  {
  	_superClassName=superClassName;
  }

  public void addField(ClassField field)
  {
    _fields.put(field.getName(), field);
  }

  public void addConstant(ClassField field, Object value)
  {
    // Nothing to do
  }

  public void addInterface(String interfaceName)
  {
  	_interfaceNames.add(interfaceName);
  }

  public void addMethod(ClassMethod method)
  {
    _methods.put(method.getName(), method);
  }

  public int getSize()
  {
  	return _size;
  }
}
