package com.insidious.common.cqengine;

import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.persistence.support.serialization.PojoSerializer;

import java.io.*;

public class ObjectInfoDocumentSerializer implements PojoSerializer<ObjectInfoDocument> {
    public ObjectInfoDocumentSerializer(Class<?> cls, PersistenceConfig persistenceConfig) {
    }

    @Override
    public byte[] serialize(ObjectInfoDocument object) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(12);
        DataOutputStream bytesOut = new DataOutputStream(out);
        try {
            bytesOut.writeLong(object.objectId);
            bytesOut.writeInt(object.typeId);
        } catch (IOException e) {
            // not happening
        }
        return out.toByteArray();
    }

    @Override
    public ObjectInfoDocument deserialize(byte[] bytes) {
        DataInputStream bytesIn = new DataInputStream(new ByteArrayInputStream(bytes));
        ObjectInfoDocument objectInfoDocument = null;
        try {
            objectInfoDocument = new ObjectInfoDocument(bytesIn.readLong(), bytesIn.readInt());
        } catch (IOException e) {
            // not happening
        }
        return objectInfoDocument;
    }
}
