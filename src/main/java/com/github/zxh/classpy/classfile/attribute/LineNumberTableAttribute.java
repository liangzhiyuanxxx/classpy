package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
LineNumberTable_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 line_number_table_length;
    {   u2 start_pc;
        u2 line_number;	
    } line_number_table[line_number_table_length];
}
 */
public class LineNumberTableAttribute extends AttributeInfo {

    {
        U2 lineNumberTableLength = new U2();

        super.addSubComponent("lineNumberTableLength", lineNumberTableLength);
        super.addSubComponent("lineNumberTable", new Table<>(LineNumberTableEntry.class, lineNumberTableLength));
    }

    @Override
    protected void afterRead(ClassReader reader) {
//        lineNumberTable.getSubComponents().forEach(entry -> {
//            entry.setName("line " + entry.lineNumber.getValue());
//            entry.setDesc(Integer.toString(entry.startPc.getValue()));
//        });
    }
    
    
    public static class LineNumberTableEntry extends ClassComponent {

        {
            super.addSubComponent("startPc", new U2());
            super.addSubComponent("lineNumber", new U2());
        }

    }
    
}
