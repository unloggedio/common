package com.insidious.common.weaver;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TypeInfo {

    private final int[] interfaces;
    String id;
    String sessionId;
    private long typeId;
    private String typeNameFromClass;
    private String classLocation;
    private int superClass;
    private int componentType;
    private String classLoaderIdentifier;

    public TypeInfo(String sessionId, long typeId, String typeNameFromClass,
                    String classLocation, int superClass,
                    int componentType, String classLoaderIdentifier, int[] interfaces) {
        this.sessionId = sessionId;
        this.typeId = typeId;
        this.typeNameFromClass = typeNameFromClass;
        this.classLocation = classLocation;
        this.superClass = superClass;
        this.componentType = componentType;
        this.classLoaderIdentifier = classLoaderIdentifier;
        this.interfaces = interfaces;
    }

    public static TypeInfo fromBytes(byte[] typeBytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(typeBytes));
        try {
            int typeId = dis.readInt();
            int nameLength = dis.readInt();

            byte[] typeNameBytes = new byte[nameLength];
            int readLength = dis.read(typeNameBytes);
            assert readLength == nameLength;
            String typeName = new String(typeNameBytes);

            int classLocationLength = dis.readInt();
            byte[] classLocationBytes = new byte[classLocationLength];
            readLength = dis.read(classLocationBytes);
            assert readLength == classLocationLength;

            String classLocation = new String(classLocationBytes);

            int superClass = dis.readInt();
            int componentClass = dis.readInt();

            int classLoaderIdentifierLength = dis.readInt();
            byte[] classLoaderIdentifierBytes = new byte[classLoaderIdentifierLength];
            readLength = dis.read(classLoaderIdentifierBytes);
            assert readLength == classLoaderIdentifierLength;
            String classLoaderIdentifier = new String(classLoaderIdentifierBytes);



            int interfaceCount = dis.readInt();
            int[] interfaces = new int[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                int interfaceId = dis.readInt();
                interfaces[i] = interfaceId;
            }

            TypeInfo typeInfo = new TypeInfo("", typeId, typeName,
                    classLocation, superClass, componentClass, classLoaderIdentifier
            , interfaces);
            return typeInfo;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getTypeId() {
        return typeId;
    }

    public String getTypeNameFromClass() {
        return typeNameFromClass;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int getComponentType() {
        return componentType;
    }

    public String getClassLoaderIdentifier() {
        return classLoaderIdentifier;
    }
}
