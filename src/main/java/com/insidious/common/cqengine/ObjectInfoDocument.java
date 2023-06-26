package com.insidious.common.cqengine;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.query.option.QueryOptions;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesMarshallable;
import net.openhft.chronicle.bytes.BytesOut;
import net.openhft.chronicle.core.io.IORuntimeException;

import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

@PersistenceConfig (serializer = ObjectInfoDocumentSerializer.class)
public class ObjectInfoDocument implements Serializable, BytesMarshallable {

    public static final SimpleAttribute<ObjectInfoDocument, Integer> OBJECT_TYPE_ID =
            new SimpleAttribute<ObjectInfoDocument, Integer>("objectTypeId") {
                public Integer getValue(ObjectInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.typeId;
                }
            };


    public static final SimpleAttribute<ObjectInfoDocument, Long> OBJECT_ID =
            new SimpleAttribute<ObjectInfoDocument, Long>("objectId") {
                public Long getValue(ObjectInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.objectId;
                }
            };


    Long objectId;
    Integer typeId;

    public ObjectInfoDocument(long objectId, int typeId) {
        this.objectId = objectId;
        this.typeId = typeId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public void readMarshallable(BytesIn bytes) throws IORuntimeException, BufferUnderflowException, IllegalStateException {
        objectId = bytes.readLong();
        typeId = bytes.readInt();
    }

    @Override
    public void writeMarshallable(BytesOut bytes) throws IllegalStateException, BufferOverflowException, BufferUnderflowException, ArithmeticException {
        bytes.writeLong(objectId);
        bytes.writeInt(typeId);
    }
}
