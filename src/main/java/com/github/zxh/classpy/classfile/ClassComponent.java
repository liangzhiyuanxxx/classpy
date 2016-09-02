package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.U1;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.reader.ClassReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all class file components.
 */
public abstract class ClassComponent {
    
    private String name;
    private String desc; // description
    private int offset; // the position of this FileComponent in the file
    private int length; // how many bytes this FileComponent has
    private List<ClassComponent> subComponents;
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}

    public List<ClassComponent> getSubComponents() {
        return subComponents == null
                ? Collections.EMPTY_LIST
                : subComponents;
    }

    protected ClassComponent getSubComponent(int index) {
        return subComponents.get(index);
    }

    protected void addSubComponent(ClassComponent c) {
        this.addSubComponent(null, c);
    }

    protected U1 addU1(String name) {
        U1 u1 = new U1();
        this.addSubComponent(name, u1);
        return u1;
    }

    protected U2 addU2(String name) {
        U2 u2 = new U2();
        this.addSubComponent(name, u2);
        return u2;
    }

    protected void addSubComponent(String name, ClassComponent c) {
        if (name != null) {
            c.setName(name);
        }
        if (subComponents == null) {
            subComponents = new ArrayList<>();
        }
        subComponents.add(c);
    }

    /**
     * The returned string will be displayed by FileComponentTreeItem.
     * 
     * @return 
     */
    @Override
    public final String toString() {
        if (name != null && desc != null) {
            return name + ": " + desc;
        }
        if (name != null) {
            return name;
        }
        if (desc != null) {
            return desc;
        }
        
        return getClass().getSimpleName();
    }
    
    /**
     * Reads content, records offset and length.
     * @param reader 
     */
    public final void read(ClassReader reader) {
        offset = reader.getPosition();
        readContent(reader);
        length = reader.getPosition() - offset;
        afterRead(reader);
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected void readContent(ClassReader reader) {
        for (ClassComponent cc : subComponents) {
            cc.read(reader);
        }
    }

    protected void afterRead(ClassReader reader) {

    }

}
