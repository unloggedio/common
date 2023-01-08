package com.insidious.common.weaver;


import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesMarshallable;
import net.openhft.chronicle.bytes.BytesOut;
import net.openhft.chronicle.core.io.IORuntimeException;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Map;
import java.util.Scanner;

/**
 * This object is to record attributes of a data ID.
 */
public class DataInfo implements Serializable, BytesMarshallable {

    public static final SimpleAttribute<DataInfo, Integer> PROBE_ID =
            new SimpleAttribute<DataInfo, Integer>("dataId") {
                public Integer getValue(DataInfo probeInfoDocument, QueryOptions queryOptions) {
                    return probeInfoDocument.dataId;
                }
            };

    private final static String SEPARATOR = ",";
    private final static char ATTRIBUTE_KEYVALUE_SEPARATOR = '=';
    private final static char ATTRIBUTE_SEPARATOR = ',';

    private int classId;
    private int methodId;
    private int dataId;
    private int index;
    private int line;
    private int instructionIndex;
    private EventType eventType;
    private Descriptor valueDesc;
    private String attributes;
    private Map<String, String> attributesMap;

    /**
     * Create an instance recording the data ID.
     *
     * @param classId          is a class ID assigned by the weaver.
     * @param methodId         is a method ID assigned by the weaver.
     * @param dataId           is a data ID assigned by the weaver.
     * @param line             is the line number of the instruction (if available).
     * @param instructionIndex is the location of the bytecode instruction in the ASM's InsnList.
     * @param eventType        is the event type.
     * @param valueDesc        is the value type observed by the event.
     * @param attributes       specifies additional attributes statically obtained from the instruction.
     */
    public DataInfo(int classId, int methodId, int dataId, int line,
                    int instructionIndex, EventType eventType,
                    Descriptor valueDesc, String attributes) {
        this.classId = classId;
        this.methodId = methodId;
        this.dataId = dataId;
        this.line = line;
        this.instructionIndex = instructionIndex;
        this.eventType = eventType;
        this.valueDesc = valueDesc;
        this.attributes = attributes;
    }

    public DataInfo() {
    }

    /**
     * Create an instance from a string representation created by DataInfo.toString.
     *
     * @param s is the string representation
     * @return a created instance
     */
    public static DataInfo parse(String s) {
        Scanner sc = new Scanner(s);
        sc.useDelimiter(SEPARATOR);
        int dataId = sc.nextInt();
        int classId = sc.nextInt();
        int methodId = sc.nextInt();
        int line = sc.nextInt();
        int instructionIndex = sc.nextInt();
        EventType t = EventType.valueOf(sc.next());
        Descriptor d = Descriptor.get(sc.next());
        StringBuilder b = new StringBuilder();
        while (sc.hasNext()) {
            b.append(sc.next());
            b.append(DataInfo.ATTRIBUTE_SEPARATOR);
        }
        String attributes = b.toString();
        sc.close();
        return new DataInfo(classId, methodId, dataId, line, instructionIndex, t, d, attributes);
    }

    @Override
    public void readMarshallable(BytesIn bytes) throws IORuntimeException, BufferUnderflowException, IllegalStateException {
        classId = bytes.readInt();
        methodId = bytes.readInt();
        dataId = bytes.readInt();
        index = bytes.readInt();
        line = bytes.readInt();
        instructionIndex = bytes.readInt();
        int eventTypeIndex = bytes.readInt();
        if (eventTypeIndex != -1) {
            eventType = EventType.values()[eventTypeIndex];
        }
        int descIndex = bytes.readInt();
        if (descIndex != -1){
            valueDesc = Descriptor.values()[descIndex];
        }
        int attrLength = bytes.readInt();
        byte[] attrBytes = new byte[attrLength];
        bytes.read(attrBytes);
        attributes = new String(attrBytes);
//        BytesMarshallable.super.readMarshallable(bytes);
    }

    @Override
    public void writeMarshallable(BytesOut bytes) throws IllegalStateException, BufferOverflowException, BufferUnderflowException, ArithmeticException {
        bytes.writeInt(classId);
        bytes.writeInt(methodId);
        bytes.writeInt(dataId);
        bytes.writeInt(index);
        bytes.writeInt(line);
        bytes.writeInt(instructionIndex);
        if (eventType != null) {
            bytes.writeInt(eventType.ordinal());
        } else {
            bytes.writeInt(-1);
        }
        if (valueDesc != null) {
            bytes.writeInt(valueDesc.ordinal());
        } else {
            bytes.writeInt(-1);
        }
        if (attributes != null) {
            bytes.writeInt(attributes.length());
            bytes.write(attributes);
        } else {
            bytes.writeInt(0);
        }
//        BytesMarshallable.super.writeMarshallable(bytes);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map<String, String> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(Map<String, String> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * @return the method ID.
     */
    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    /**
     * @return the data ID.
     */
    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    /**
     * @return the line number.
     */
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    /**
     * @return the location of the bytecode instruction in the ASM's InsnList.
     */
    public int getInstructionIndex() {
        return instructionIndex;
    }

    public void setInstructionIndex(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    /**
     * @return the event type.
     */
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the value type observed by the event.
     */
    public Descriptor getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(Descriptor descriptor) {
        this.valueDesc = descriptor;
    }

    /**
     * @return additional attributes statically obtained from the instruction.
     */
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * Access a particular attribute of the instruction, assuming the "KEY=VALUE" format.
     *
     * @param key          specifies an attribute key
     * @param defaultValue is returned if the key is unavailable.
     * @return the value corresponding to the key.
     */
    public String getAttribute(String key, String defaultValue) {
        int index = attributes.indexOf(key);
        while (index >= 0) {
            if (index == 0 || attributes.charAt(index - 1) == ATTRIBUTE_SEPARATOR) {
                int keyEndIndex = attributes.indexOf(ATTRIBUTE_KEYVALUE_SEPARATOR, index);
                if (keyEndIndex == index + key.length()) {
                    int valueEndIndex = attributes.indexOf(ATTRIBUTE_SEPARATOR, keyEndIndex);
                    if (valueEndIndex > keyEndIndex) {
                        return attributes.substring(index + key.length() + 1, valueEndIndex);
                    } else {
                        return attributes.substring(index + key.length() + 1);
                    }
                }
            }
            index = attributes.indexOf(key, index + 1);
        }
        return defaultValue;
    }

    /**
     * @return a string representation of the object.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder();


        buf.append(dataId);
        buf.append(SEPARATOR);
        buf.append(classId);
        buf.append(SEPARATOR);
        buf.append(methodId);
        buf.append(SEPARATOR);
        buf.append(line);
        buf.append(SEPARATOR);
        buf.append(instructionIndex);
        buf.append(SEPARATOR);
        buf.append(eventType.name());
        buf.append(SEPARATOR);
        buf.append(valueDesc.getString());
        buf.append(SEPARATOR);
        buf.append(attributes);
        return buf.toString();
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dao = new DataOutputStream(baos);

        dao.writeInt(classId);
        dao.writeInt(methodId);
        dao.writeInt(dataId);
        dao.writeInt(line);
        dao.writeInt(instructionIndex);

        dao.writeInt(eventType.ordinal());
        dao.writeInt(valueDesc.ordinal());
        dao.writeInt(attributes.getBytes().length);
        dao.write(attributes.getBytes());


        return baos.toByteArray();
    }
}
