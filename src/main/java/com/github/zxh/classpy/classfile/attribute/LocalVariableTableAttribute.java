package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
LocalVariableTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 local_variable_table_length;
    {   u2 start_pc;
        u2 length;
        u2 name_index;
        u2 descriptor_index;
        u2 index;
    } local_variable_table[local_variable_table_length];
}
 */
public class LocalVariableTableAttribute extends AttributeInfo {

    {
        u2("localVariableTableLength");
        super.addTable("localVariableTable", LocalVariableTableEntry.class);
    }

    
    public static class LocalVariableTableEntry extends ClassComponent {

        {
            u2("startPc");
            u2("length");
            super.addU2CpIndex("nameIndex");
            super.addU2CpIndex("descriptorIndex");
            u2("index");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            int startPc = ((U2) super.get("startPc")).getValue();
            int length = ((U2) super.get("length")).getValue();
            int nameIndex = ((U2) super.get("nameIndex")).getValue();

            int fromPc = startPc;
            int toPc = fromPc + length - 1;
            String varName = reader.getConstantPool().getConstantDesc(nameIndex);
            setDesc(String.format("%s(%d~%d)", varName, fromPc, toPc));
        }
        
    }
    
}
