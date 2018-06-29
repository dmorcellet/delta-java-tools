package delta.tools.java.classes;

import java.util.HashMap;

/**
 * Represents a Java base type (byte, char, short, ... Object).
 * @author DAM
 */
public class BaseType
{
  private static HashMap<Character,BaseType> _instancesMap=new HashMap<Character,BaseType>();

  private char _code;
  private String _label;
  private short _size;

  /**
   * Constructor.
   * @param code Char code in the class file.
   * @param label Displayable label.
   * @param size Number of bytes to store a value of this type.
   */
  public BaseType(char code, String label, short size)
  {
    _code=code;
    _label=label;
    _size=size;
    _instancesMap.put(Character.valueOf(code),this);
  }

  /**
   * Get the char code for this type.
   * @return A char code.
   */
  public char getCode()
  {
    return _code;
  }

  /**
   * Get a displayable label for this type.
   * @return a displayable label.
   */
  public String getLabel()
  {
    return _label;
  }

  /**
   * Get the size of values of this type.
   * @return a number of bytes.
   */
  public short getSize()
  {
    return _size;
  }

  /**
   * Get a base type using its char code.
   * @param code Code to use.
   * @return A base type or <code>null</code> if not found.
   */
  public static BaseType getByName(char code)
  {
    Character typeName=Character.valueOf(code);
    BaseType type=_instancesMap.get(typeName);
    return type;
  }
}
