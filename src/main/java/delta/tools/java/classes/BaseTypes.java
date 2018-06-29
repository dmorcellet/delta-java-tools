package delta.tools.java.classes;

/**
 * Registry of Java base types as found in class files.
 * @author DAM
 */
public class BaseTypes
{
  /**
   * Byte.
   */
  public static final BaseType BYTE=new BaseType('B',"byte",(short)1);
  /**
   * Char.
   */
  public static final BaseType CHAR=new BaseType('C',"char",(short)2);
  /**
   * Double.
   */
  public static final BaseType DOUBLE=new BaseType('D',"double",(short)8);
  /**
   * Float.
   */
  public static final BaseType FLOAT=new BaseType('F',"float",(short)4);
  /**
   * Int.
   */
  public static final BaseType INT=new BaseType('I',"int",(short)4);
  /**
   * Long.
   */
  public static final BaseType LONG=new BaseType('J',"long",(short)8);
  /**
   * Short.
   */
  public static final BaseType SHORT=new BaseType('S',"short",(short)2);
  /**
   * Boolean.
   */
  public static final BaseType BOOLEAN=new BaseType('Z',"boolean",(short)2);
  /**
   * Void.
   */
  public static final BaseType VOID=new BaseType('V',"void",(short)0);
  /**
   * Object.
   */
  public static final BaseType OBJECT=new BaseType('L',"Object",(short)4);

  /**
   * Array indicator.
   */
  public static final char ARRAY_CODE='[';
  /**
   * 
   */
  public static final char OBJECT_CODE='L';
  /**
   * Object name terminator.
   */
  public static final char OBJECT_NAME_TERMINATOR=';';

  /**
   * Get a label for a type.
   * @param baseType Type code.
   * @return A displayable string (possibly empty but not <code>null</code>).
   */
  public static String getLabelForBaseType(char baseType)
  {
    BaseType type=BaseType.getByName(baseType);
    if (type!=null)
    {
      return type.getLabel();
    }
    return "";
  }
}
