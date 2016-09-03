package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class FieldInfo extends ClassComponent {

    {
        super.add("accessFlags", new U2());
        super.add("nameIndex", new U2CpIndex());
        super.add("descriptorIndex", new U2CpIndex());
        U2 attributesCount = super.addU2("attributesCount");
        super.addTable("attributes", attributesCount, AttributeInfo.class);
    }

    @Override
    protected void readContent(ClassReader reader) {
        super.readContent(reader);

        int nameIndex = ((U2CpIndex) super.getSubComponent(1)).getValue();
        if (nameIndex > 0) {
            // todo fix loading java.lang.String from rt.jar
            setDesc(reader.getConstantPool().getUtf8String(nameIndex));
        }

        describe((U2) super.getSubComponent(0));
    }
    
    protected void describe(U2 accessFlags) {
        AccessFlags.describeFieldFlags(accessFlags);
    }
    
}
