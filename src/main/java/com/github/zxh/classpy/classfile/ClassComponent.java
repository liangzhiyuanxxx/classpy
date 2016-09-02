package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.helper.ClassComponentHelper;
import com.github.zxh.classpy.classfile.reader.ClassReader;
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
    
    // Getters & Setters
    public final String getName() {return name;}
    public final void setName(String name) {this.name = name;}
    public final String getDesc() {return desc;}
    public final void setDesc(String desc) {this.desc = desc;}
    public final int getOffset() {return offset;}
    public final int getLength() {return length;}

    public final void setDesc(int value) {
        desc = Integer.toString(value);
    }

    /**
     * Returns sub-components.
     * 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<? extends ClassComponent> getSubComponents() {
        try {
            return ClassComponentHelper.findSubComponents(this);
        } catch (ReflectiveOperationException e) {
            // todo
            e.printStackTrace(System.err);
            return Collections.EMPTY_LIST;
        }
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
    }
    
    /**
     * Reads content using ClassReader.
     * @param reader 
     */
    protected abstract void readContent(ClassReader reader);
    
}
