package com.github.zxh.classpy.classfile.datatype;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.helper.StringUtil;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;

/**
 * Array of class components.
 */
public class Table extends ClassComponent {

    private final Class<? extends ClassComponent> entryClass;
    private final UInt length;

    public Table(Class<? extends ClassComponent> entryClass, UInt length) {
        this.entryClass = entryClass;
        this.length = length;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        readTable(reader);
        setEntryName();
    }
    
    private void readTable(ClassReader reader) {
        try {
            for (int i = 0; i < length.getValue(); i++) {
                ClassComponent entry = readEntry(reader);
                super.addSubComponent(entry);
            }
        } catch (ReflectiveOperationException e) {
            throw new ClassParseException(e);
        }
    }

    private ClassComponent readEntry(ClassReader reader) throws ReflectiveOperationException {
        if (entryClass == AttributeInfo.class) {
            return readAttributeInfo(reader);
        } else {
            ClassComponent entry = entryClass.newInstance();
            entry.read(reader);
            return entry;
        }
    }
    
    private AttributeInfo readAttributeInfo(ClassReader reader) {
        int attrNameIndex = reader.getByteBuffer().getShort(reader.getPosition());
        String attrName = reader.getConstantPool().getUtf8String(attrNameIndex);
        
        AttributeInfo attr = AttributeInfo.create(attrName);
        attr.read(reader);
        
        return attr;
    }
    
    private void setEntryName() {
//        for (int i = 0; i < table.length; i++) {
//            String newName = StringUtil.formatIndex(length, i);
//            String oldName = table[i].getName();
//            if (oldName != null) {
//                newName += " (" + oldName + ")";
//            }
//            table[i].setName(newName);
//        }
    }

}
