package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

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
        U2 n = super.addU2("localVariableTableLength");
        super.addTable("localVariableTable", n, LocalVariableTableEntry.class);
    }

    
    public static class LocalVariableTableEntry extends ClassComponent {

        {
            super.addU2("startPc");
            super.addU2("length");
            super.add("nameIndex", new U2CpIndex());
            super.add("descriptorIndex", new U2CpIndex());
            super.addU2("index");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            setDesc(reader);
        }
        
        private void setDesc(ClassReader reader) {
//            String varName = reader.getConstantPool().getConstantDesc(nameIndex.getValue());
//            int fromPc = startPc.getValue();
//            int toPc = fromPc + length.getValue() - 1;
//            setDesc(String.format("%s(%d~%d)", varName, fromPc, toPc));
        }
        
    }
    
}
