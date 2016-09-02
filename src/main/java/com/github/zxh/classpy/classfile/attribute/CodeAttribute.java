package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.Table;
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
        U4 codeLength = new U4();
        U2 exceptionTableLength = new U2();
        U2 attributesCount = new U2();

        super.addSubComponent("maxStack", new U2());
        super.addSubComponent("maxLocals", new U2());
        super.addSubComponent("codeLength", codeLength);
        super.addSubComponent("code", new Code(codeLength));
        super.addSubComponent("exceptionTableLength", exceptionTableLength);
        super.addSubComponent("exceptionTable", new Table<>(ExceptionTableEntry.class, exceptionTableLength));
        super.addSubComponent("attributesCount", attributesCount);
        super.addSubComponent("attributes", new Table<>(AttributeInfo.class, attributesCount));
    }


    public static class ExceptionTableEntry extends ClassComponent {

        {
            super.addSubComponent("startPc", new U2());
            super.addSubComponent("endPc", new U2());
            super.addSubComponent("handlerPc", new U2());
            super.addSubComponent("catchType", new U2CpIndex());
        }

    }
    
}
