package com.insidious.common.cqengine;

import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.persistence.support.serialization.PojoSerializer;

import java.io.*;

public class StringInfoDocumentSerializer implements PojoSerializer<StringInfoDocument> {

    public StringInfoDocumentSerializer(Class<?> cls, PersistenceConfig persistenceConfig) {
    }

    @Override
    public byte[] serialize(StringInfoDocument object) {
        String string = object.getString();

        ByteArrayOutputStream out = new ByteArrayOutputStream(8 + 4 + string.length());
        DataOutputStream bytesOut = new DataOutputStream(out);

        try {
            bytesOut.writeLong(object.stringId);
            bytesOut.writeInt(string.length());
            bytesOut.write(string.getBytes());
        } catch (IOException e) {
            // not happening
        }

        return out.toByteArray();
    }

    @Override
    public StringInfoDocument deserialize(byte[] bytes) {
        DataInputStream bytesIn = new DataInputStream(new ByteArrayInputStream(bytes));
        StringInfoDocument stringInfoDocument = null;
        try {
            long id = bytesIn.readLong();
            int stringLength = bytesIn.readInt();
            byte[] stringBytes = new byte[stringLength];
            bytesIn.read(stringBytes);
            stringInfoDocument = new StringInfoDocument(id, new String(stringBytes));
        } catch (IOException e) {
            // not happening
        }
        return stringInfoDocument;
    }
}
