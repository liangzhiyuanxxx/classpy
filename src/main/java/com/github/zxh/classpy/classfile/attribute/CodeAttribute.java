package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;
import com.github.zxh.classpy.classfile.datatype.U4;

/*
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class CodeAttribute extends AttributeInfo {

    {
        super.add("maxStack", new U2());
        super.add("maxLocals", new U2());
        U4 codeLength = super.addU4("codeLength");
        super.add("code", new Code(codeLength));
        super.addU2("exceptionTableLength");
        super.addTable("exceptionTable", ExceptionTableEntry.class);
        super.addU2("attributesCount");
        super.addTable("attributes", AttributeInfo.class);
    }


    public static class ExceptionTableEntry extends ClassComponent {

        {
            super.add("startPc", new U2());
            super.add("endPc", new U2());
            super.add("handlerPc", new U2());
            super.add("catchType", new U2CpIndex());
        }

    }
    
}
