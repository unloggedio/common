package com.insidious.common.weaver;


import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ClassInfo implements Serializable {
    public static final SimpleAttribute<ClassInfo, Integer> CLASS_ID =
            new SimpleAttribute<ClassInfo, Integer>("classId") {
                public Integer getValue(ClassInfo probeInfoDocument, QueryOptions queryOptions) {
                    return probeInfoDocument.classId;
                }
            };

    private static final String SEPARATOR = ",";
    private String[] interfaces;
    private String superName;
    private String signature;
    private int classId;
    private String container;
    private String filename;
    private String className;
    private LogLevel loglevel;
    private String hash;
    private String classLoaderIdentifier;
    private boolean isEnum;
    private boolean isPojo;

    public ClassInfo() {
    }

    /**
     * Create an instance to record the information.
     *
     * @param classId               specifies the ID assigned by the weaver.
     * @param container             is the name of a JAR file if the class is loaded from a JAR.
     * @param filename              is a class file name.
     * @param className             is a class name.
     * @param loglevel              is the level of the inserted logging code.
     * @param hash                  is a file hash of bytecode.
     * @param classLoaderIdentifier is a string representing a class loader that loaded the original class
     */
    public ClassInfo(int classId, String container, String filename,
                     String className, LogLevel loglevel,
                     String hash, String classLoaderIdentifier,
                     String[] interfaces, String superName, String signature) {
        this.classId = classId;
        this.container = container;
        this.filename = filename;
        this.className = className;
        this.loglevel = loglevel;
        this.hash = hash;
        this.classLoaderIdentifier = classLoaderIdentifier;
        this.interfaces = interfaces;
        this.superName = superName;
        this.signature = signature;
    }

    public boolean isPojo() {
        return isPojo;
    }

    public void setPojo(boolean pojo) {
        isPojo = pojo;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean anEnum) {
        isEnum = anEnum;
    }

    public int getClassId() {
        return classId;
    }

    public String getContainer() {
        return container;
    }

    public String getFilename() {
        return filename;
    }

    public String getClassName() {
        return className;
    }

    public LogLevel getLoglevel() {
        return loglevel;
    }

    public String getHash() {
        return hash;
    }

    public String getClassLoaderIdentifier() {
        return classLoaderIdentifier;
    }

    public byte[] toBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dao = new DataOutputStream(baos);

        try {
            dao.writeInt(classId);

            if (container != null) {
                dao.writeInt(container.getBytes().length);
                dao.write(container.getBytes());
            } else {
                dao.writeInt(0);
            }

            if (filename != null) {
                dao.writeInt(filename.getBytes().length);
                dao.write(filename.getBytes());
            } else {
                dao.writeInt(0);
            }

            dao.writeInt(className.getBytes().length);
            dao.write(className.getBytes());

            dao.writeInt(loglevel.toString().getBytes().length);
            dao.write(loglevel.toString().getBytes());

            dao.writeInt(hash.getBytes().length);
            dao.write(hash.getBytes());

            dao.writeInt(classLoaderIdentifier.getBytes().length);
            dao.write(classLoaderIdentifier.getBytes());

            dao.writeInt(interfaces.length);
            for (String anInterface : interfaces) {
                dao.writeInt(anInterface.length());
                dao.writeBytes(anInterface);
            }
            if (signature != null) {
                dao.writeInt(signature.length());
                dao.writeBytes(signature);
            } else {
                dao.writeInt(0);
            }

            if (superName != null) {
                dao.writeInt(superName.length());
                dao.writeBytes(superName);
            } else {
                dao.writeInt(0);
            }


            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public String getSuperName() {
        return superName;
    }

    public String getSignature() {
        return signature;
    }
}
