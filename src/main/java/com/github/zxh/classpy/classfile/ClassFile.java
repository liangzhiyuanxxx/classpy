package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U4Hex;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.constant.ConstantPool;

/*
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
*/
public class ClassFile extends ClassComponent {

    {
        U2 constantPoolCount = new U2();
        U2 interfacesCount = new U2();
        U2 fieldsCount = new U2();
        U2 methodsCount = new U2();
        U2 attributesCount = new U2();

        super.addSubComponent("magic", new U4Hex());
        super.addSubComponent("minorVersion", new U2());
        super.addSubComponent("majorVersion", new U2());
        super.addSubComponent("constantPoolCount", constantPoolCount);
        super.addSubComponent("constantPool", new ConstantPool(constantPoolCount));
        super.addSubComponent("accessFlags", new U2());
        super.addSubComponent("thisClass", new U2CpIndex());
        super.addSubComponent("superClass", new U2CpIndex());
        super.addSubComponent("interfacesCount", interfacesCount);
        super.addSubComponent("interfaces", new Table<>(U2CpIndex.class, interfacesCount));
        super.addSubComponent("fieldsCount", fieldsCount);
        super.addSubComponent("fields", new Table<>(FieldInfo.class, fieldsCount));
        super.addSubComponent("methodsCount", methodsCount);
        super.addSubComponent("methods", new Table<>(MethodInfo.class, methodsCount));
        super.addSubComponent("attributesCount", attributesCount);
        super.addSubComponent("attributes", new Table<>(AttributeInfo.class, attributesCount));
    }
    
}
