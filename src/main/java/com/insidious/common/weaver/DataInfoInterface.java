package com.insidious.common.weaver;

public interface DataInfoInterface {
    int getClassId();
    int getIndex();
    int getMethodId();
    int getDataId();
    int getLine();
    int getInstructionIndex();
    EventType getEventType();
    Descriptor getValueDesc();
    String getAttributes();
    void setIndex(int index);


    void setClassId(int classId);

    void setMethodId(int methodId);

    void setDataId(int dataId);

    void setLine(int line);

    void setInstructionIndex(int instructionIndex);

    void setEventType(EventType eventType);

    void setValueDesc(Descriptor descriptor);

    void setAttributes(String attributes);
}
