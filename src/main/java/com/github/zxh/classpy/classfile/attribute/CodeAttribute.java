package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
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
        U4 codeLength = new U4();

        u2("maxStack");
        u2("maxLocals");
        super.add("codeLength", codeLength);
        super.add("code", new Code(codeLength));
        u2("exceptionTableLength");
        table("exceptionTable", ExceptionTableEntry.class);
        u2("attributesCount");
        table("attributes", AttributeInfo.class);
    }


    public static class ExceptionTableEntry extends ClassComponent {

        {
            u2("startPc");
            u2("endPc");
            u2("handlerPc");
            u2CpIndex("catchType");
        }

    }
    
}
