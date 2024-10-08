package delta.tools.java.classes.classFile;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.tools.java.classes.ClassField;
import delta.tools.java.classes.ClassFile;
import delta.tools.java.classes.ClassFileConstants;
import delta.tools.java.classes.ClassMethod;
import delta.tools.java.classes.ClassNameAndType;
import delta.tools.java.classes.NameAndDescriptor;

/**
 * Parse for class files.
 * @author DAM
 */
public class ClassFileParser
{
  private static final Logger LOGGER=LoggerFactory.getLogger(ClassFileParser.class);

  private ClassFile _classFile;
  private DataInputStream _dis;
  private Object[] _constantsPool;

  /**
   * Constructor.
   * @param classFile Storage for class description.
   * @param dis Input stream for the class file.
   */
  public ClassFileParser(ClassFile classFile, DataInputStream dis)
  {
    _classFile=classFile;
    _dis=dis;
    _constantsPool=null;
  }

  /**
   * Parse.
   */
  public void parse()
  {
    try
    {
      int magic=_dis.readInt();
      short minorVersion=_dis.readShort();
      short majorVersion=_dis.readShort();
      System.out.println("Magic="+magic+", Version="+majorVersion+"."+minorVersion);
      short constantPoolCount=_dis.readShort();
      _constantsPool=new Object[constantPoolCount];

      // Traces.logNormal("Version = "+majorVersion+"."+minorVersion+" nbConstants="+constant_pool_count);
      byte tag;
      for(short i=1;i<constantPoolCount;i++)
      {
        tag=_dis.readByte();
        // Traces.logNormal("Tag : "+tag+" (i="+i+")");
        switch (tag)
        {
          case ClassFileConstants.UTF8:
          {
            _constantsPool[i]=_dis.readUTF();
          }
          break;
          case ClassFileConstants.METHOD_REF:
          {
            _constantsPool[i]=new ClassNameAndType(_dis.readShort(),_dis.readShort());
          }
          break;
          case ClassFileConstants.FIELDREF:
          {
            _constantsPool[i]=new ClassNameAndType(_dis.readShort(),_dis.readShort());
          }
          break;
          case ClassFileConstants.CLASS:
          {
            _constantsPool[i]=Short.valueOf(_dis.readShort());
          }
          break;
          case ClassFileConstants.INTEGER:
          {
            _constantsPool[i]=Integer.valueOf(_dis.readInt());
          }
          break;
          case ClassFileConstants.NAME_AND_TYPE:
          {
            _constantsPool[i]=new NameAndDescriptor(_dis.readShort(),_dis.readShort());
          }
          break;
          case ClassFileConstants.STRING:
          {
            _constantsPool[i]=Short.valueOf(_dis.readShort());
          }
          break;
          case ClassFileConstants.LONG:
          {
            _constantsPool[i]=Long.valueOf(_dis.readLong());
            i++;
          }
          break;
          case ClassFileConstants.FLOAT:
          {
            _constantsPool[i]=Float.valueOf(_dis.readFloat());
          }
          break;
          case ClassFileConstants.DOUBLE:
          {
            _constantsPool[i]=Double.valueOf(_dis.readDouble());
            i++;
          }
          break;
          case ClassFileConstants.INTERFACE_METHOD_REF:
          {
            _constantsPool[i]=new ClassNameAndType(_dis.readShort(),_dis.readShort());
          }
          break;
          default:
          {
            LOGGER.error("Unknown tag : "+tag+" (i="+i+")");
          }
        }
        //System.out.println("Constant pool #"+i+": "+_constantsPool[i]);
      }
      /* short accessFlags= */_dis.readShort();
      short thisClass=_dis.readShort();
      if (thisClass>0)
      {
        Short index=(Short)_constantsPool[thisClass];
        String className=(String)_constantsPool[index.intValue()];
        System.out.println(className);
      }
      short superClass=_dis.readShort();
      if (superClass>0)
      {
        Short index=(Short)_constantsPool[superClass];
        String superClassName=(String)_constantsPool[index.intValue()];
        _classFile.setSuperClassName(superClassName);
      }

      // Interfaces
      short interfaceCount=_dis.readShort();

      for(short i=0;i<interfaceCount;i++)
      {
        short interfaceIndex=_dis.readShort();
        _classFile.addInterface((String)_constantsPool[interfaceIndex-1]);
      }

      // Fields
      short fieldsCount=_dis.readShort();
      for(short i=0;i<fieldsCount;i++)
      {
        parseField();
      }

      // Methods
      short methodsCount=_dis.readShort();
      for(short i=0;i<methodsCount;i++)
      {
        parseMethod();
      }

      // Attributes
      short attributesCount=_dis.readShort();
      for(short i=0;i<attributesCount;i++)
      {
        parseAttribute();
      }
    }
    catch (IOException e)
    {
      LOGGER.error("",e);
    }
  }

  private void parseField() throws IOException
  {
    short accessFlags=_dis.readShort();
    short nameIndex=_dis.readShort();
    String name=(String)_constantsPool[nameIndex];
    short descriptorIndex=_dis.readShort();
    String descriptor=(String)_constantsPool[descriptorIndex];
    short attributesCount=_dis.readShort();
    for(short i=0;i<attributesCount;i++)
    {
      parseAttribute();
    }
    ClassField field=new ClassField(name,descriptor,accessFlags);
    _classFile.addField(field);
    System.out.println("Field : "+field.getPrototype(true));
  }

  private void parseConstantValue() throws IOException
  {
    int attributeLength=_dis.readInt();
    if (attributeLength!=2) throw new IllegalStateException();
    short index=_dis.readShort();
    Object constant=_constantsPool[index];
    System.out.println("Constant : "+constant);
  }

  private void parseAttribute() throws IOException
  {
    short attributeNameIndex=_dis.readShort();
    String attName=(String)_constantsPool[attributeNameIndex];
    if ("ConstantValue".equals(attName))
    {
      parseConstantValue();
    }
    else
    {
      int attributeLength=_dis.readInt();
      for(int i=0;i<attributeLength;i++)
      {
        _dis.readByte();
      }
    }
  }

  private void parseMethod() throws IOException
  {
    short accessFlags=_dis.readShort();
    short nameIndex=_dis.readShort();
    String name=(String)_constantsPool[nameIndex];
    short descriptorIndex=_dis.readShort();
    String descriptor=(String)_constantsPool[descriptorIndex];
    short attributesCount=_dis.readShort();
    for(short i=0;i<attributesCount;i++)
    {
      parseAttribute();
    }
    ClassMethod method=new ClassMethod(name,descriptor,accessFlags);
    _classFile.addMethod(method);
    System.out.println("Method : "+method.getPrototype(true));
  }
}
