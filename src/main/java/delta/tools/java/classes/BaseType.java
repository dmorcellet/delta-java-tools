package delta.tools.java.classes;

import java.util.HashMap;

public class BaseType
{
  private static HashMap<Character,BaseType> _instancesMap=new HashMap<Character,BaseType>();

  private char _type;
  private String _label;
  private short _size;

  public BaseType(char type, String label, short size)
  {
    _type=type;
    _label=label;
    _size=size;
    _instancesMap.put(Character.valueOf(type),this);
  }

  public char getType()
  {
    return _type;
  }

  public String getLabel()
  {
    return _label;
  }

  public short getSize()
  {
    return _size;
  }

  public static BaseType getByName(char name)
  {
    Character typeName=Character.valueOf(name);
    BaseType type=_instancesMap.get(typeName);
    return type;
  }
}
