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
        super.addU2("numberOfClasses");
        super.addTable("classes", InnerClassInfo.class);
    }
    
    
    public static class InnerClassInfo extends ClassComponent {

        {
            super.addU2CpIndex("innerClassInfoIndex");
            super.addU2CpIndex("outerClassInfoIndex");
            super.addU2CpIndex("innerNameIndex");
            super.addU2("innerClassAccessFlags");
        }

        @Override
        protected void afterRead(ClassReader reader) {
            AccessFlags.describeInnerClassFlags(
                    (U2) super.get("innerClassAccessFlags"));
        }
        
    }
    
}
