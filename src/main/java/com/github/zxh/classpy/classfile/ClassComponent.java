package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.datatype.*;
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
    private int offset; // the position of this ClassComponent in the file
    private int length; // how many bytes this ClassComponent has
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
                : Collections.unmodifiableList(subComponents);
    }

    /**
     * Find sub-component by name.
     * @param name
     * @return
     */
    protected final ClassComponent get(String name) {
        for (ClassComponent c : subComponents) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    protected final U1 addU1(String name) {
        U1 u1 = new U1();
        this.add(name, u1);
        return u1;
    }

    protected final U2 addU2(String name) {
        U2 u2 = new U2();
        this.add(name, u2);
        return u2;
    }

    protected final U4 addU4(String name) {
        U4 u4 = new U4();
        this.add(name, u4);
        return u4;
    }

    protected final void addTable(String name,
                                  Class<? extends ClassComponent> entryClass) {
        UInt length = (UInt) subComponents.get(subComponents.size() - 1);
        Table table = new Table(length, entryClass);
        this.add(name, table);
    }

    protected final void add(ClassComponent subComponent) {
        this.add(null, subComponent);
    }

    protected final void add(String name, ClassComponent subComponent) {
        if (name != null) {
            subComponent.setName(name);
        }
        if (subComponents == null) {
            subComponents = new ArrayList<>();
        }
        subComponents.add(subComponent);
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

    /**
     * The returned string will be displayed by ClassComponentTreeItem.
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

}
