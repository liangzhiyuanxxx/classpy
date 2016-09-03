package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.U4;

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
 */
public abstract class AttributeInfo extends ClassComponent {

    {
        u2("attributeNameIndex");
        u4("attributeLength");
    }

    protected int getAttributeLength() {
        return ((U4) super.get("attributeLength")).getValue();
    }
    
}
