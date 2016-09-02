package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.testclasses.AnnotatedClass;
import com.github.zxh.classpy.classfile.testclasses.SimpleAttr;
import com.github.zxh.classpy.classfile.testclasses.ByteCode;
import com.github.zxh.classpy.classfile.testclasses.CodeAttr;
import com.github.zxh.classpy.classfile.testclasses.ConstantPool;
import com.github.zxh.classpy.classfile.testclasses.SimpleClass;
import com.github.zxh.classpy.classfile.testclasses.GenericClass;
import com.github.zxh.classpy.classfile.testclasses.MyInterface;
import com.github.zxh.classpy.classfile.testclasses.annotations.MyRuntimeAnnotation;
import com.github.zxh.classpy.classfile.testclasses.TypeAnnotatedClass;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassFileTest {
    
    @Test
    public void simpleClass() throws Exception {
        ClassFile cf = loadClass(SimpleClass.class);
        assertEquals(0, ((U2) cf.getSubComponent(1)).getValue());
        assertEquals(52, ((U2) cf.getSubComponent(2)).getValue());
        assertEquals(37, ((U2) cf.getSubComponent(3)).getValue());
        assertEquals(2, ((U2) cf.getSubComponent(8)).getValue());
        assertEquals(2, ((U2) cf.getSubComponent(10)).getValue());
        assertEquals(5, ((U2) cf.getSubComponent(12)).getValue());
        assertEquals(2, ((U2) cf.getSubComponent(14)).getValue());
    }
    
    @Test
    public void constantPool() throws Exception {
        loadClass(ConstantPool.class);
    }
    
    @Test
    public void simpleAttr() throws Exception {
        loadClass(SimpleAttr.class);
    }
    
    @Test
    public void codeAttr() throws Exception {
        loadClass(CodeAttr.class);
    }
    
    @Test
    public void enclosingMethodAttribute() throws Exception {
        String classFileName = SimpleAttr.class.getName().replace('.', '/') + "$1.class";
        loadClass(classFileName);
    }
    
    @Test
    public void annotationDefaultAttribute() throws Exception {
        loadClass(MyRuntimeAnnotation.class);
    }
    
    @Test
    public void methodParametersAttribute() throws Exception {
        loadClass(MyInterface.class);
    }
    
    @Test
    public void genericClass() throws Exception {
        loadClass(GenericClass.class);
    }
    
    @Test
    public void annotatedClass() throws Exception {
        loadClass(AnnotatedClass.class);
    }
    
    @Test
    public void typeAnnotatedClass() throws Exception {
        loadClass(TypeAnnotatedClass.class);
    }
    
    @Test
    public void byteCode() throws Exception {
        loadClass(ByteCode.class);
    }
    
    private static ClassFile loadClass(Class<?> cls) throws Exception {
        String classFileName = cls.getName().replace('.', '/') + ".class";
        return loadClass(classFileName);
    }
    
    private static ClassFile loadClass(String classFileName) throws Exception {
        ClassLoader cl = SimpleClass.class.getClassLoader();
        Path classFilePath = Paths.get(cl.getResource(classFileName).toURI());
        byte[] classBytes = Files.readAllBytes(classFilePath);
        ClassFile cf = new ClassParser().parse(classBytes);
        return cf;
    }
    
}
