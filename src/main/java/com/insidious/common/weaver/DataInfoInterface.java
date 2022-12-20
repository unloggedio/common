package com.insidious.common.weaver;

import net.openhft.chronicle.values.MaxUtf8Length;

public interface DataInfoInterface {
    int getClassId();

    void setClassId(int classId);

    int getIndex();

    void setIndex(int index);

    int getMethodId();

    void setMethodId(int methodId);

    int getDataId();

    void setDataId(int dataId);

    int getLine();

    void setLine(int line);

    int getInstructionIndex();

    void setInstructionIndex(int instructionIndex);

    EventType getEventType();

    void setEventType(EventType eventType);

    Descriptor getValueDesc();

    void setValueDesc(Descriptor descriptor);

    String getAttributes();

    void setAttributes(@MaxUtf8Length(value = 100) String attributes);
}
