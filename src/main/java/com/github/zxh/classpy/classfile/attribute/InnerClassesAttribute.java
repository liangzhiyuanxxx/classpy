package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.AccessFlags;
import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;

/*
InnerClasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    {   u2 inner_class_info_index;
        u2 outer_class_info_index;
        u2 inner_name_index;
        u2 inner_class_access_flags;
    } classes[number_of_classes];
}
 */
public class InnerClassesAttribute extends AttributeInfo {

    {
        u2("numberOfClasses");
        table("classes", InnerClassInfo.class);
    }
    
    
    public static class InnerClassInfo extends ClassComponent {

        {
            u2CpIndex("innerClassInfoIndex");
            u2CpIndex("outerClassInfoIndex");
            u2CpIndex("innerNameIndex");
            u2("innerClassAccessFlags");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            AccessFlags.describeInnerClassFlags(
                    (U2) super.get("innerClassAccessFlags"));
        }
        
    }
    
}
