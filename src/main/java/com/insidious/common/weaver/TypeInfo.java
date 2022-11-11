package com.insidious.common.weaver;


import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;

public class TypeInfo implements Serializable {

    public static final SimpleAttribute<TypeInfo, Integer> TYPE_ID =
            new SimpleAttribute<TypeInfo, Integer>("typeId") {
                public Integer getValue(TypeInfo probeInfoDocument, QueryOptions queryOptions) {
                    return probeInfoDocument.typeId;
                }
            };


    private final int[] interfaces;
    private int typeId;
    private String typeNameFromClass;
    private String classLocation;
    private int superClass;
    private int componentType;
    private String classLoaderIdentifier;

    public TypeInfo(int typeId, String typeNameFromClass,
                    String classLocation, int superClass,
                    int componentType, String classLoaderIdentifier, int[] interfaces) {
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

            return new TypeInfo(typeId, typeName, classLocation, superClass,
                    componentClass, classLoaderIdentifier, interfaces);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public int getTypeId() {
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
