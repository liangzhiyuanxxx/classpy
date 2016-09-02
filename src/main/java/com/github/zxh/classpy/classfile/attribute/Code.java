package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.datatype.U4;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import com.github.zxh.classpy.classfile.bytecode.Opcode;

public class Code extends ClassComponent {

    private final U4 codeLength;

    public Code(U4 codeLength) {
        this.codeLength = codeLength;
    }
    
    @Override
    protected void readContent(ClassReader reader) {
        final int startPosition = reader.getPosition();
        final int endPosition = startPosition + codeLength.getValue();
        
        int position;
        while ((position = reader.getPosition()) < endPosition) {
            int pc = position - startPosition;
            byte b = reader.getByteBuffer().get(position);
            Opcode opcode = Opcode.valueOf(Byte.toUnsignedInt(b));
            Instruction instruction = Instruction.create(opcode, pc);
            instruction.read(reader);
            super.addSubComponent(instruction);
        }
        
        setInstructionName();
    }
    
    private void setInstructionName() {
//        int maxPc = instructions.get(instructions.size() - 1).getPc();
//        int pcWidth = String.valueOf(maxPc).length();
//        String fmtStr = "%0" + pcWidth + "d";
//        instructions.forEach(instruction -> {
//            instruction.setName(String.format(fmtStr, instruction.getPc()));
//        });
    }

}
