package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.constant.ConstantPool;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.attribute.RuntimeVisibleAnnotationsAttribute.AnnotationInfo;
import com.github.zxh.classpy.classfile.helper.StringUtil;

/*
RuntimeVisibleTypeAnnotations_attribute {
    u2              attribute_name_index;
    u4              attribute_length;
    u2              num_annotations;
    type_annotation annotations[num_annotations];
}
 */
public class RuntimeVisibleTypeAnnotationsAttribute extends AttributeInfo {

    {
        u2   ("num_annotations");
        table("annotations", TypeAnnotationInfo.class);
    }
    
    
    /*
    type_annotation {
        u1 target_type;
        union {
            type_parameter_target;
            supertype_target;
            type_parameter_bound_target;
            empty_target;
            method_formal_parameter_target;
            throws_target;
            localvar_target;
            catch_target;
            offset_target;
            type_argument_target;
        } target_info;
        type_path target_path;
        u2        type_index;
        u2        num_element_value_pairs;
        {   u2            element_name_index;
            element_value value;
        } element_value_pairs[num_element_value_pairs];
    }
    */
    public static class TypeAnnotationInfo extends ClassComponent {

        {
            U1 targetType = new U1();

            add("target_type", targetType);
            add("target_info", new TargetInfo(targetType));
            add("target_path", new TypePath());
            add("annotation", new AnnotationInfo());
        }

        @Override
        protected void afterRead(ConstantPool cp) {
            U1 targetType = (U1) super.get("target_type");
            targetType.setDesc(StringUtil.toHexString(targetType.getValue()));
        }
    
    }
    
    /*
    type_parameter_target {
        u1 type_parameter_index;
    }
    supertype_target {
        u2 supertype_index;
    }
    type_parameter_bound_target {
        u1 type_parameter_index;
        u1 bound_index;
    }
    empty_target {
    }
    formal_parameter_target {
        u1 formal_parameter_index;
    }
    throws_target {
        u2 throws_type_index;
    }
    localvar_target {
        u2 table_length;
        {   u2 start_pc;
            u2 length;
            u2 index;
        } table[table_length];
    }
    catch_target {
        u2 exception_table_index;
    }
    offset_target {
        u2 offset;
    }
    type_argument_target {
        u2 offset;
        u1 type_argument_index;
    }
    */
    public static class TargetInfo extends ClassComponent {

        private final U1 targetType;
//        private U1 typeParameterIndex; // type_parameter_target & type_parameter_bound_target
//        private U2 supertypeIndex; // supertype_target
//        private U1 boundIndex; // type_parameter_bound_target
//        private U1 formalParameterIndex; // formal_parameter_target
//        private U2 throwsTypeIndex; // throws_target
//        private U2 tableLength; // localvar_target
//        private Table<LocalVarInfo> table; // localvar_target
//        private U2 exceptionTableIndex; // catch_target
//        private U2 offset; // offset_target & type_argument_target
//        private U1 typeArgumentIndex; // type_argument_target

        public TargetInfo(U1 targetType) {
            this.targetType = targetType;
        }
        
        @Override
        protected void readContent(ClassReader reader) {
            switch (targetType.getValue()) {
                case 0x00:
                case 0x01:
                    u1("typeParameterIndex");
                    break;
                case 0x10:
                    u2("supertypeIndex");
                    break;
                case 0x11:
                case 0x12:
                    u1("typeParameterIndex");
                    u1("boundIndex");
                    break;
                case 0x13:
                case 0x14:
                case 0x15:
                    break;
                case 0x16:
                    u1("formalParameterIndex");
                    break;
                case 0x17:
                    u2("throwsTypeIndex");
                    break;
                case 0x40:
                case 0x41:
                    u2("tableLength");
                    table("table", LocalVarInfo.class);
                    break;
                case 0x42:
                    u2("exceptionTableIndex");
                    break;
                case 0x43:
                case 0x44:
                case 0x45:
                case 0x46:
                    u2("offset");
                    break;
                case 0x47:
                case 0x48:
                case 0x49:
                case 0x4A:
                case 0x4B:
                    u2("offset");
                    u1("typeArgumentIndex");
                    break;
                default: throw new ClassParseException("Invalid target_type: " + targetType);
            }
            super.readContent(reader);
        }
        
    }
    
    public static class LocalVarInfo extends ClassComponent {

        {
            u2("start_pc");
            u2("length");
            u2("index");
        }
        
    }
    
    /*
    type_path {
        u1 path_length;
        {   u1 type_path_kind;
            u1 type_argument_index;
        } path[path_length];
    }
    */
    public static class TypePath extends ClassComponent {

        {
            u1   ("path_length");
            table("path", PathInfo.class);
        }
        
    }
    
    public static class PathInfo extends ClassComponent {

        {
            u1("type_path_kind");
            u1("type_argument_index");
        }
        
    }
    
}
