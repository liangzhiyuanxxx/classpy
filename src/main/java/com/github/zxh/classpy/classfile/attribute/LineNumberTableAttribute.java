package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
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
        u2("lineNumberTableLength");
        table("lineNumberTable", LineNumberTableEntry.class);
    }

    
    public static class LineNumberTableEntry extends ClassComponent {

        {
            u2("startPc");
            u2("lineNumber");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            int lineNumber = ((U2) super.get("lineNumber")).getValue();
            int startPc = ((U2) super.get("startPc")).getValue();
            setName("line " + lineNumber);
            setDesc(Integer.toString(startPc));
        }

    }
    
}
