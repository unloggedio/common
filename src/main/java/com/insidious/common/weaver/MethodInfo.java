package com.insidious.common.weaver;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 * This object is to record the information of a method processed by a weaver.
 */
public class MethodInfo {

    private static final String SEPARATOR = ",";

    private final int classId;
    private final int methodId;
    private final String className;
    private final String methodName;
    private final String methodDesc;
    private final int access;
    private final String sourceFileName;
    private final String methodHash;

    /**
     * Create an instance recording the information.
     *
     * @param classId        is a class ID assigned by the weaver.
     * @param methodId       is a method ID assigned by the weaver.
     * @param className      is the class name.
     * @param methodName     is the method name.
     * @param methodDesc     is the descriptor representing parameters and return value.
     * @param access         includes modifiers of the method.
     * @param sourceFileName is a source file name recorded in the class.  This may be null.
     */
    public MethodInfo(int classId, int methodId, String className, String methodName, String methodDesc, int access, String sourceFileName, String methodHash) {
        this.classId = classId;
        this.methodId = methodId;
        this.className = className;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.access = access;
        this.sourceFileName = sourceFileName;
        this.methodHash = methodHash;
    }

    /**
     * Extract MethodInfo from a string
     *
     * @param s specifies the content created by MethodInfo.toString
     * @return a MethodInfo instance.
     */
    public static MethodInfo parse(String s) {
        Scanner sc = new Scanner(s);
        sc.useDelimiter(SEPARATOR);
        int classId = sc.nextInt();
        int methodId = sc.nextInt();
        String className = sc.next();
        String methodName = sc.next();
        String methodDesc = sc.next();
        int access = sc.nextInt();
        String sourceFileName = sc.hasNext() ? sc.next() : "";
        String methodHash = sc.hasNext() ? sc.next() : "";
        sc.close();
        return new MethodInfo(classId, methodId, className, methodName, methodDesc, access, sourceFileName, methodHash);
    }

    /**
     * @return the class ID of the method
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @return the method ID of the method
     */
    public int getMethodId() {
        return methodId;
    }

    /**
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return the name of the method
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @return the descriptor of the method
     */
    public String getMethodDesc() {
        return methodDesc;
    }

    /**
     * @return the access flags
     */
    public int getAccess() {
        return access;
    }

    /**
     * @return a source file name.  This may return null.
     */
    public String getSourceFileName() {
        return sourceFileName;
    }

    /**
     * @return a hash code for method instructions.
     * If two methods have the same instructions except for line numbers, they return a same hash code.
     */
    public String getMethodHash() {
        return methodHash;
    }

    /**
     * Create a string representation to be stored in a text file.
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(classId);
        buf.append(SEPARATOR);
        buf.append(methodId);
        buf.append(SEPARATOR);
        buf.append(className);
        buf.append(SEPARATOR);
        buf.append(methodName);
        buf.append(SEPARATOR);
        buf.append(methodDesc);
        buf.append(SEPARATOR);
        buf.append(access);
        buf.append(SEPARATOR);
        if (sourceFileName != null) buf.append(sourceFileName);
        buf.append(SEPARATOR);
        if (methodHash != null) buf.append(methodHash);
        return buf.toString();
    }

    public byte[] toBytes() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dao = new DataOutputStream(baos);


        dao.writeInt(classId);
        dao.writeInt(methodId);

        if (methodName != null) {
            dao.writeInt(methodName.getBytes().length);
            dao.write(methodName.getBytes());
        } else {
            dao.writeInt(0);
        }

        dao.writeInt(methodDesc.getBytes().length);
        dao.write(methodDesc.getBytes());

        dao.writeInt(access);

        if (sourceFileName != null) {
            dao.writeInt(sourceFileName.getBytes().length);
            dao.write(sourceFileName.getBytes());
        } else {
            dao.writeInt(0);
        }

        dao.writeInt(methodHash.getBytes().length);
        dao.write(methodHash.getBytes());


        return baos.toByteArray();
    }
}
