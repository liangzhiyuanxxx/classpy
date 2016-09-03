package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.helper.StringUtil;

public class U4Hex extends U4 {

    protected void describe(int value, ClassReader reader) {
        setDesc(StringUtil.toHexString(value));
    }

}
