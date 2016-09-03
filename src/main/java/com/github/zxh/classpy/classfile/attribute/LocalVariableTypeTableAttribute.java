package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
LocalVariableTypeTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_type_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 signature_index;
        u2 index;
    } local_variable_type_table[local_variable_type_table_length];
}
 */
public class LocalVariableTypeTableAttribute extends AttributeInfo {

    {
        super.addU2("localVariableTypeTableLength");
        super.addTable("localVariableTypeTable", LocalVariableTypeTableEntry.class);
    }


    public static class LocalVariableTypeTableEntry extends ClassComponent {

        {
            super.addU2("startPc");
            super.addU2("length");
            super.add("nameIndex", new U2CpIndex());
            super.add("signatureIndex", new U2CpIndex());
            super.addU2("index");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            //setDesc(reader.getConstantPool().getUtf8String(nameIndex));
        }
    
    }
    
}
