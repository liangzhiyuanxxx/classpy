package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U1;

/*
cp_info {
    u1 tag;
    u1 info[];
}
 */
public abstract class ConstantInfo extends ClassComponent {

    protected U1 tag;
    
    @Override
    protected final void readContent(ClassReader reader) {
        tag = reader.readU1();
        readInfo(reader);
    }
    
    protected abstract void readInfo(ClassReader reader);
    protected abstract String loadDesc(ConstantPool pool);
    
}
