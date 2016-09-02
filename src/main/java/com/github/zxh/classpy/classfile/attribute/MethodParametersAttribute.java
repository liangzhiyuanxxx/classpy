package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U1;

/*
MethodParameters_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 parameters_count;
    {   u2 name_index;
        u2 access_flags;
    } parameters[parameters_count];
}
 */
public class MethodParametersAttribute extends AttributeInfo {

    {
        U1 n = super.addU1("parametersCount");
        super.addSubComponent("parameters", new Table<>(ParameterInfo.class, n));
    }

    
    public static class ParameterInfo extends ClassComponent {

        {
            super.addU2("nameIndex");
            super.addU2("accessFlags");
        }
        
    }
    
}
