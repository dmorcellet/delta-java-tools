package delta.tools.java.classes;

public class BaseTypes
{
  public static final BaseType BYTE=new BaseType('B',"byte",(short)1);
  public static final BaseType CHAR=new BaseType('C',"char",(short)2);
  public static final BaseType DOUBLE=new BaseType('D',"double",(short)8);
  public static final BaseType FLOAT=new BaseType('F',"float",(short)4);
  public static final BaseType INT=new BaseType('I',"int",(short)4);
  public static final BaseType LONG=new BaseType('J',"long",(short)8);
  public static final BaseType SHORT=new BaseType('S',"short",(short)2);
  public static final BaseType BOOLEAN=new BaseType('Z',"boolean",(short)2);
  public static final BaseType VOID=new BaseType('V',"void",(short)0);
  public static final BaseType OBJECT=new BaseType('L',"Object",(short)4);

  public static final char ARRAY_CODE='[';
  public static final char OBJECT_CODE='L';
  public static final char OBJECT_NAME_TERMINATOR=';';

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
