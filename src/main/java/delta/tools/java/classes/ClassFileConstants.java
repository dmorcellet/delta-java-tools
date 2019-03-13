package delta.tools.java.classes;

/**
 * Constants used in class files.
 * @author DAM
 */
public class ClassFileConstants
{
  /**
   * Magic number at the beginning of class files.
   */
  public static final int MAGIC=0xCAFEBABE;

  /**
   * Class.
   */
  public static final int CLASS=7;
  /**
   * Field reference.
   */
  public static final int FIELDREF=9;
  /**
   * Method reference.
   */
  public static final int METHOD_REF=10;
  /**
   * Interface method reference.
   */
  public static final int INTERFACE_METHOD_REF=11;
  /**
   * String constant reference.
   */
  public static final int STRING=8;
  /**
   * Integer constant.
   */
  public static final int INTEGER=3;
  /**
   * Float constant.
   */
  public static final int FLOAT=4;
  /**
   * Long constant.
   */
  public static final int LONG=5;
  /**
   * Double constant.
   */
  public static final int DOUBLE=6;
  /**
   * Name and type.
   */
  public static final int NAME_AND_TYPE=12;
  /**
   * UTF-8 string constant.
   */
  public static final int UTF8=1;
}
