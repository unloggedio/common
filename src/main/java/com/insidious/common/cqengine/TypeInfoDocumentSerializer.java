package com.insidious.common.cqengine;

import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.persistence.support.serialization.PojoSerializer;

import java.io.*;

public class TypeInfoDocumentSerializer implements PojoSerializer<TypeInfoDocument> {
    public TypeInfoDocumentSerializer(Class<?> cls, PersistenceConfig persistenceConfig) {
    }

    @Override
    public byte[] serialize(TypeInfoDocument object) {
        int typeId = object.getTypeId();
        String typeName = object.getTypeName();
        byte[] typeBytes = object.getTypeBytes();

        ByteArrayOutputStream out = new ByteArrayOutputStream(4 + 4 + typeName.length() + 4 + typeBytes.length);
        DataOutputStream bytesOut = new DataOutputStream(out);


        try {
            bytesOut.writeInt(typeId);
            bytesOut.writeInt(typeName.length());
            bytesOut.write(typeName.getBytes());
            bytesOut.writeInt(typeBytes.length);
            bytesOut.write(typeBytes);
        } catch (IOException e) {
            // not happening
        }


        return out.toByteArray();
    }

    @Override
    public TypeInfoDocument deserialize(byte[] bytes) {
        DataInputStream bytesIn = new DataInputStream(new ByteArrayInputStream(bytes));
        TypeInfoDocument typeInfoDocument = null;
        try {

            int typeId = bytesIn.readInt();
            int typeNameLength = bytesIn.readInt();
            byte[] typeNameBytes = new byte[typeNameLength];
            bytesIn.read(typeNameBytes);

            int typeBytesLength = bytesIn.readInt();
            byte[] typeBytes = new byte[typeBytesLength];
            bytesIn.read(typeBytes);

            typeInfoDocument = new TypeInfoDocument(typeId, new String(typeNameBytes), typeBytes);
        } catch (IOException e) {
            // not happening
        }

        return typeInfoDocument;
    }
}
