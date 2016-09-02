package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.reader.ClassReader;

/*
SourceDebugExtension_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 debug_extension[attribute_length];
}
 */
public class SourceDebugExtensionAttribute extends AttributeInfo {

    @Override
    protected void readContent(ClassReader reader) {
        super.readContent(reader);
        // todo
        reader.skipBytes(super.getAttributeLength());
    }
    
}
