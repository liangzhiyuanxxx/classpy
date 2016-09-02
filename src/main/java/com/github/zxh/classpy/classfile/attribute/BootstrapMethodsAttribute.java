package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.Table;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.datatype.U2CpIndex;

/*
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments];
    } bootstrap_methods[num_bootstrap_methods];
}
 */
public class BootstrapMethodsAttribute extends AttributeInfo {

    {
        U2 numBootstrapMethods = new U2();

        super.addSubComponent("numBootstrapMethods", numBootstrapMethods);
        super.addSubComponent("bootstrapMethods",
                new Table<>(BootstrapMethodInfo.class, numBootstrapMethods));
    }

    
    public static class BootstrapMethodInfo extends ClassComponent {

        {
            U2 numBootstrapArguments = new U2();

            super.addSubComponent("bootstrapMethodRef", new U2CpIndex());
            super.addSubComponent("numBootstrapArguments", numBootstrapArguments);
            super.addSubComponent("bootstrapArguments",
                    new Table<>(U2CpIndex.class, numBootstrapArguments));
        }
        
    }
    
}
