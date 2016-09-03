package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
SourceFile_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 sourcefile_index;
}
 */
public class SourceFileAttribute extends AttributeInfo {

    {
        add("sourceFileIndex", new U2CpIndex());
    }

}
