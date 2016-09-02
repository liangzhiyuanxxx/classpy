package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.attribute.RuntimeVisibleAnnotationsAttribute.AnnotationInfo;

/*
RuntimeVisibleParameterAnnotations_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 num_parameters;
    {   u2         num_annotations;
        annotation annotations[num_annotations];
    } parameter_annotations[num_parameters];
}
 */
public class RuntimeVisibleParameterAnnotationsAttribute extends AttributeInfo {

    {
        U1 n = super.addU1("numParameters");
        super.addSubComponent("parameterAnnotations", new Table<>(ParameterAnnotationInfo.class, n));
    }
    
    
    public static class ParameterAnnotationInfo extends ClassComponent {

        {
            U2 n = super.addU2("numAnnotations");
            super.addSubComponent("annotations", new Table<>(AnnotationInfo.class, n));
        }
        
    }
    
}
