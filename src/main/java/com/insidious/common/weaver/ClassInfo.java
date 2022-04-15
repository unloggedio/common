package com.insidious.common.weaver;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * This object is to record the information of a woven class.
 */
public class ClassInfo {

    private static final String SEPARATOR = ",";

    private final int classId;
    private final String container;
    private final String filename;
    private final String className;
    private final LogLevel loglevel;
    private final String hash;
    private final String classLoaderIdentifier;

    /**
     * Create an instance to record the information.
     *
     * @param classId               specifies the ID assigned by the weaver.
     * @param container             is the name of a JAR file if the class is loaded from a JAR.
     * @param filename              is a class file name.
     * @param className             is a class name.
     * @param level                 is the level of the inserted logging code.
     * @param hash                  is a file hash of bytecode.
     * @param classLoaderIdentifier is a string representing a class loader that loaded the original class
     */
    public ClassInfo(int classId, String container, String filename, String className, LogLevel level, String hash, String classLoaderIdentifier) {
        this.classId = classId;
        this.container = container;
        this.filename = filename;
        this.className = className;
        this.loglevel = level;
        this.hash = hash;
        this.classLoaderIdentifier = classLoaderIdentifier;
    }

    /**
     * Create an instance from a string representation created by
     * ClassInfo.toString.
     *
     * @param s is the string representation.
     * @return an instance.
     */
    public static ClassInfo parse(String s) {
        Scanner sc = new Scanner(s);
        sc.useDelimiter(SEPARATOR);
        int classId = sc.nextInt();
        String container = sc.next();
        String filename = sc.next();
        String className = sc.next();
        LogLevel level = LogLevel.valueOf(sc.next());
        String hash = sc.next();
        String id = sc.next();
        sc.close();
        return new ClassInfo(classId, container, filename, className, level, hash, id);
    }

    /**
     * @return ID of the class.
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @return class name including its package name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return a container where a class is loaded.
     */
    public String getContainer() {
        return container;
    }

    /**
     * @return a class file name.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return a file hash of bytecode.
     */
    public String getHash() {
        return hash;
    }

    /**
     * @return the level of the inserted logging code.
     */
    public LogLevel getLoglevel() {
        return loglevel;
    }

    /**
     * @return a string representation of a class loader that loaded the original class.
     * The string should be the same as a string recorded by TypeIdMap in the omni mode.
     */
    public String getClassLoaderIdentifier() {
        return classLoaderIdentifier;
    }

    /**
     * @return a string representation of the information.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(classId);
        buf.append(SEPARATOR);
        buf.append(container);
        buf.append(SEPARATOR);
        buf.append(filename);
        buf.append(SEPARATOR);
        buf.append(className);
        buf.append(SEPARATOR);
        buf.append(loglevel.name());
        buf.append(SEPARATOR);
        buf.append(hash);
        buf.append(SEPARATOR);
        buf.append(classLoaderIdentifier);
        return buf.toString();
    }

    /**
     * @return a string representation of the information.
     */
    public byte[] toBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream buf = new DataOutputStream(baos);

        try {
            buf.writeInt(classId);

            buf.writeInt(container.getBytes().length);
            buf.write(container.getBytes());

            buf.writeInt(filename.getBytes().length);
            buf.write(filename.getBytes());

            buf.writeInt(className.getBytes().length);
            buf.write(className.getBytes());

            buf.writeInt(loglevel.name().getBytes().length);
            buf.write(loglevel.name().getBytes());

            buf.writeInt(hash.getBytes().length);
            buf.write(hash.getBytes());

            buf.writeInt(classLoaderIdentifier.getBytes().length);
            buf.write(classLoaderIdentifier.getBytes());

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
