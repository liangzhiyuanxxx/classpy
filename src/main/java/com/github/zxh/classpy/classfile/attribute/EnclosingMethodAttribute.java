package com.github.zxh.classpy.classfile.attribute;

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
        u2cp("classIndex");
        u2cp("methodIndex");
    }

}
