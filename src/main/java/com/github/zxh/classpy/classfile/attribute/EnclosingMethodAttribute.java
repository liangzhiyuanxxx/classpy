package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
EnclosingMethod_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 class_index;
    u2 method_index;
}
 */
public class EnclosingMethodAttribute extends AttributeInfo {

    {
        super.addSubComponent("classIndex", new U2CpIndex());
        super.addSubComponent("methodIndex", new U2CpIndex());
    }

}
