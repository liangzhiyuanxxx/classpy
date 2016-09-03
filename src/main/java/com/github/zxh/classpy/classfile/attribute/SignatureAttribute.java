package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
Signature_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 signature_index;
}
 */
public class SignatureAttribute extends AttributeInfo {

    {
        add("signatureIndex", new U2CpIndex());
    }
    
}
