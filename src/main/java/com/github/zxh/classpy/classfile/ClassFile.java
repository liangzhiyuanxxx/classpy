package com.github.zxh.classpy.classfile;

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
        super.add("magic", new U4Hex());
        super.add("minorVersion", new U2());
        super.add("majorVersion", new U2());
        U2 constantPoolCount = super.addU2("constantPoolCount");
        super.add("constantPool", new ConstantPool(constantPoolCount));
        super.add("accessFlags", new U2());
        super.add("thisClass", new U2CpIndex());
        super.add("superClass", new U2CpIndex());
        U2 interfacesCount = super.addU2("interfacesCount");
        super.addTable("interfaces", interfacesCount, U2CpIndex.class);
        U2 fieldsCount = super.addU2("fieldsCount");
        super.addTable("fields", fieldsCount, FieldInfo.class);
        U2 methodsCount = super.addU2("methodsCount");
        super.addTable("methods", methodsCount, MethodInfo.class);
        U2 attributesCount = super.addU2("attributesCount");
        super.addTable("attributes", attributesCount, AttributeInfo.class);
    }
    
}
