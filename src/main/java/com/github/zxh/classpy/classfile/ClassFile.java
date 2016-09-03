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
        U2 constantPoolCount = new U2();

        super.add("magic", new U4Hex());
        super.add("minorVersion", new U2());
        super.add("majorVersion", new U2());
        super.add("constantPoolCount", constantPoolCount);
        super.add("constantPool", new ConstantPool(constantPoolCount));
        super.add("accessFlags", new U2());
        super.add("thisClass", new U2CpIndex());
        super.add("superClass", new U2CpIndex());
        u2("interfacesCount");
        table("interfaces", U2CpIndex.class);
        u2("fieldsCount");
        table("fields", FieldInfo.class);
        u2("methodsCount");
        table("methods", MethodInfo.class);
        u2("attributesCount");
        table("attributes", AttributeInfo.class);
    }
    
}
