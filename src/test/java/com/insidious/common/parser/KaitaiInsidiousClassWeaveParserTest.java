package com.insidious.common.parser;

import io.kaitai.struct.ByteBufferKaitaiStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class KaitaiInsidiousClassWeaveParserTest {

    public static final String FILE_NAME = "/Users/artpar/" +
            ".unlogged/sessions/selogger-output-20230701-122040686/index-00002-1688194249632/class.weave.dat";

    @Test
    void newAll() throws IOException {

        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start, end;

//        for (int j = 0; j < 5; j++) {
        /////////// non streaming
        start = new Date().getTime();
        InputStream inputStream = Files.newInputStream(new File(FILE_NAME).toPath());
//        byte[] allBytes = StreamUtil.streamToBytes(inputStream);
        KaitaiInsidiousClassWeaveParser newParserAll = new KaitaiInsidiousClassWeaveParser(
                new OnlyForwardKaitaiStream(new BufferedInputStream(inputStream)));
        newParserAll._read();
        List<KaitaiInsidiousClassWeaveParser.ClassInfo> allNewClassesAll = newParserAll.classInfo();
        end = new Date().getTime();
        System.out.println("Time to read new all: " + (end - start) + " ms");
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        assert allNewClassesAll.size() == 4104;


//            for (int i = 0; i < allNewClassesAll.size(); i++) {
////                KaitaiInsidiousClassWeaveParser.ClassInfo classNew = allNewClassesAll.get(i);
//                KaitaiInsidiousClassWeaveParser.ClassInfo classOld = allOldClasses.get(i);
//                KaitaiInsidiousClassWeaveParser.ClassInfo classNew1 = allNewClassesAll.get(i);
//                KaitaiInsidiousClassWeaveParser.ClassInfo classOld1 = allOldClassesStreaming.get(i);
////                assertClassSame(classNew, classNew1);
//                assertClassSame(classNew1, classOld);
//                assertClassSame(classOld1, classOld);
//            }
//        }


    }

    @Test
    void newStreaming() throws IOException {
        //        ///////// streaming
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start = new Date().getTime();
        InputStream inputStream1 = Files.newInputStream(new File(FILE_NAME).toPath());
//        byte[] allBytes1 = StreamUtil.streamToBytes(inputStream1);

        KaitaiInsidiousClassWeaveParser newParser = new KaitaiInsidiousClassWeaveParser(new OnlyForwardKaitaiStream(
                new BufferedInputStream(inputStream1)));
        List<KaitaiInsidiousClassWeaveParser.ClassInfo> allNewClasses = new ArrayList<>();
        while (true) {
            KaitaiInsidiousClassWeaveParser.ClassInfo classInfo = newParser.nextClass();
            if (classInfo == null) {
                break;
            }
            System.out.println("Free: " + Runtime.getRuntime().freeMemory());
//            allNewClasses.add(classInfo);
        }
        long end = new Date().getTime();
//        assert allNewClasses.size() == 4104;
        System.out.println("Time to read new streaming: " + (end - start) + " ms");

    }

    @Test
    void oldStreaming() throws IOException {

        /////////// old streaming
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start = new Date().getTime();
        KaitaiInsidiousClassWeaveParser olderStreamingParser = new KaitaiInsidiousClassWeaveParser(
                new ByteBufferKaitaiStream(FILE_NAME));

        ArrayList<KaitaiInsidiousClassWeaveParser.ClassInfo> allOldClassesStreaming = new ArrayList<>();

        while (true) {
            KaitaiInsidiousClassWeaveParser.ClassInfo nextClass = olderStreamingParser.nextClass();
            if (nextClass == null) {
                break;
            }
            System.out.println("Free: " + Runtime.getRuntime().freeMemory());
//            allOldClassesStreaming.add(nextClass);
        }
//        assert allOldClassesStreaming.size() == 4104;

        long end = new Date().getTime();
        System.out.println("Time to read old streaming: " + (end - start) + " ms");

    }

    @Test
    void oldAll() throws IOException {


        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start = new Date().getTime();
        KaitaiInsidiousClassWeaveParser olderParser = new KaitaiInsidiousClassWeaveParser(
                new ByteBufferKaitaiStream(FILE_NAME));
        olderParser._read();

        ArrayList<KaitaiInsidiousClassWeaveParser.ClassInfo> allOldClasses = olderParser.classInfo();
        assert allOldClasses.size() == 4104;

        long end = new Date().getTime();
        System.out.println("Time to read old: " + (end - start) + " ms");
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());


    }

    @Test
    void currentImplementation() throws IOException {
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start = new Date().getTime();
        FileInputStream classWeaverReader = new FileInputStream(FILE_NAME);
        ByteBuffer wrap = ByteBuffer.wrap(StreamUtil.streamToBytes(classWeaverReader));
        KaitaiInsidiousClassWeaveParser classWeaveInfo = new KaitaiInsidiousClassWeaveParser(
                new ByteBufferKaitaiStream(wrap));
        classWeaveInfo._read();
        classWeaverReader.close();
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        assert classWeaveInfo.classInfo().size() == 4104;
        long end = new Date().getTime();
        System.out.println("Time to current: " + (end - start) + " ms");

    }

    @Test
    void currentImplementationStreaming() throws IOException {
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        long start = new Date().getTime();
        FileInputStream classWeaverReader = new FileInputStream(FILE_NAME);
        ByteBuffer wrap = ByteBuffer.wrap(StreamUtil.streamToBytes(classWeaverReader));
        KaitaiInsidiousClassWeaveParser classWeaveInfo = new KaitaiInsidiousClassWeaveParser(
                new ByteBufferKaitaiStream(wrap));
//        classWeaveInfo._read();
        while(true) {
            KaitaiInsidiousClassWeaveParser.ClassInfo classInfo = classWeaveInfo.nextClass();
            System.out.println("Free: " + Runtime.getRuntime().freeMemory());
            if (classInfo == null) {
                break;
            }
        }
        System.out.println("Free: " + Runtime.getRuntime().freeMemory());
        classWeaverReader.close();
//        assert classWeaveInfo.classInfo().size() == 4104;
        long end = new Date().getTime();
        System.out.println("Time to current streaming: " + (end - start) + " ms");

    }

    private void assertClassSame(KaitaiInsidiousClassWeaveParser.ClassInfo classNew,
                                 KaitaiInsidiousClassWeaveParser.ClassInfo classOld) {
        Assertions.assertEquals(classNew.className().value(), classOld.className().value());
        Assertions.assertEquals(classNew.classId(), classOld.classId());
        Assertions.assertEquals(classNew.probeCount(), classOld.probeCount());
        Assertions.assertEquals(classNew.methodCount(), classOld.methodCount());

        ArrayList<KaitaiInsidiousClassWeaveParser.ProbeInfo> probeNewList = classNew.probeList();
        ArrayList<KaitaiInsidiousClassWeaveParser.ProbeInfo> probeOldList = classOld.probeList();
        for (int i = 0; i < probeNewList.size(); i++) {
            KaitaiInsidiousClassWeaveParser.ProbeInfo probeNew = probeNewList.get(i);
            KaitaiInsidiousClassWeaveParser.ProbeInfo probeOld = probeOldList.get(i);
            assertSameProbe(probeNew, probeOld);
        }


        ArrayList<KaitaiInsidiousClassWeaveParser.MethodInfo> methodNewList = classNew.methodList();
        ArrayList<KaitaiInsidiousClassWeaveParser.MethodInfo> methodOldList = classOld.methodList();
        for (int i = 0; i < methodNewList.size(); i++) {
            KaitaiInsidiousClassWeaveParser.MethodInfo methodNewInfo = methodNewList.get(i);
            KaitaiInsidiousClassWeaveParser.MethodInfo methodOldInfo = methodOldList.get(i);
            assertSameMethod(methodNewInfo, methodOldInfo);
        }


    }

    private void assertSameMethod(KaitaiInsidiousClassWeaveParser.MethodInfo methodNewInfo, KaitaiInsidiousClassWeaveParser.MethodInfo methodOldInfo) {
        Assertions.assertEquals(methodNewInfo.methodName().value(), methodOldInfo.methodName().value());
        Assertions.assertEquals(methodNewInfo.methodHash().value(), methodOldInfo.methodHash().value());
        Assertions.assertEquals(methodNewInfo.methodId(), methodOldInfo.methodId());

    }

    private void assertSameProbe(KaitaiInsidiousClassWeaveParser.ProbeInfo probeNew, KaitaiInsidiousClassWeaveParser.ProbeInfo probeOld) {
        Assertions.assertEquals(probeNew.dataId(), probeOld.dataId());
        Assertions.assertEquals(probeNew.classId(), probeOld.classId());
        Assertions.assertEquals(probeNew.methodId(), probeOld.methodId());
        Assertions.assertEquals(probeNew.attributes().value(), probeOld.attributes().value());
        Assertions.assertEquals(probeNew.eventType(), probeOld.eventType());
        Assertions.assertEquals(probeNew.valueDescriptor(), probeOld.valueDescriptor());


    }
}