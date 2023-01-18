// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package com.insidious.common.parser;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.nio.charset.Charset;

public class KaitaiInsidiousClassWeaveParser extends KaitaiStruct {
    public static KaitaiInsidiousClassWeaveParser fromFile(String fileName) throws IOException {
        return new KaitaiInsidiousClassWeaveParser(new ByteBufferKaitaiStream(fileName));
    }

    public enum Descriptor {
        BOOLEAN(0),
        BYTE(1),
        CHAR(2),
        SHORT(3),
        INTEGER(4),
        LONG(5),
        FLOAT(6),
        DOUBLE(7),
        INTEGEROBJECT(8),
        CHARACTEROBJECT(9),
        BOOLEANOBJECT(10),
        FLOATOBJECT(11),
        DOUBLEOBJECT(12),
        SHORTOBJECT(13),
        LONGOBJECT(14),
        OBJECT(15),
        VOID(16);

        private final long id;
        Descriptor(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, Descriptor> byId = new HashMap<Long, Descriptor>(17);
        static {
            for (Descriptor e : Descriptor.values())
                byId.put(e.id(), e);
        }
        public static Descriptor byId(long id) { return byId.get(id); }
    }

    public enum EventType {
        RESERVED(0),
        METHOD_ENTRY(1),
        METHOD_PARAM(2),
        METHOD_OBJECT_INITIALIZED(3),
        METHOD_NORMAL_EXIT(4),
        METHOD_THROW(5),
        METHOD_EXCEPTIONAL_EXIT(6),
        CALL(7),
        CALL_PARAM(8),
        CALL_RETURN(9),
        CATCH_LABEL(10),
        CATCH(11),
        NEW_OBJECT(12),
        NEW_OBJECT_CREATED(13),
        GET_INSTANCE_FIELD(14),
        GET_INSTANCE_FIELD_RESULT(15),
        GET_STATIC_FIELD(16),
        PUT_INSTANCE_FIELD(17),
        PUT_INSTANCE_FIELD_VALUE(18),
        PUT_INSTANCE_FIELD_BEFORE_INITIALIZATION(19),
        PUT_STATIC_FIELD(20),
        ARRAY_LOAD(21),
        ARRAY_LOAD_INDEX(22),
        ARRAY_LOAD_RESULT(23),
        ARRAY_STORE(24),
        ARRAY_STORE_INDEX(25),
        ARRAY_STORE_VALUE(26),
        NEW_ARRAY(27),
        NEW_ARRAY_RESULT(28),
        MULTI_NEW_ARRAY(29),
        MULTI_NEW_ARRAY_OWNER(30),
        MULTI_NEW_ARRAY_ELEMENT(31),
        ARRAY_LENGTH(32),
        ARRAY_LENGTH_RESULT(33),
        MONITOR_ENTER(34),
        MONITOR_ENTER_RESULT(35),
        MONITOR_EXIT(36),
        OBJECT_CONSTANT_LOAD(37),
        OBJECT_INSTANCEOF(38),
        OBJECT_INSTANCEOF_RESULT(39),
        INVOKE_DYNAMIC(40),
        INVOKE_DYNAMIC_PARAM(41),
        INVOKE_DYNAMIC_RESULT(42),
        LABEL(43),
        JUMP(44),
        LOCAL_LOAD(45),
        LOCAL_STORE(46),
        LOCAL_INCREMENT(47),
        RET(48),
        DIVIDE(49),
        LINE_NUMBER(50);

        private final long id;
        EventType(long id) { this.id = id; }
        public long id() { return id; }
        private static final Map<Long, EventType> byId = new HashMap<Long, EventType>(51);
        static {
            for (EventType e : EventType.values())
                byId.put(e.id(), e);
        }
        public static EventType byId(long id) { return byId.get(id); }
    }

    public KaitaiInsidiousClassWeaveParser(KaitaiStream _io) {
        this(_io, null, null);
    }

    public KaitaiInsidiousClassWeaveParser(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public KaitaiInsidiousClassWeaveParser(KaitaiStream _io, KaitaiStruct _parent, KaitaiInsidiousClassWeaveParser _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.classInfo = new ArrayList<ClassInfo>();
        {
            int i = 0;
            while (!this._io.isEof()) {
                this.classInfo.add(new ClassInfo(this._io, this, _root));
                i++;
            }
        }
    }
    public static class ClassInfo extends KaitaiStruct {
        public static ClassInfo fromFile(String fileName) throws IOException {
            return new ClassInfo(new ByteBufferKaitaiStream(fileName));
        }

        public ClassInfo(KaitaiStream _io) {
            this(_io, null, null);
        }

        public ClassInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser _parent) {
            this(_io, _parent, null);
        }

        public ClassInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser _parent, KaitaiInsidiousClassWeaveParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.classId = this._io.readU4be();
            this.container = new StrWithLen(this._io, this, _root);
            this.fileName = new StrWithLen(this._io, this, _root);
            this.className = new StrWithLen(this._io, this, _root);
            this.logLevel = new StrWithLen(this._io, this, _root);
            this.hash = new StrWithLen(this._io, this, _root);
            this.classLoaderIdentifier = new StrWithLen(this._io, this, _root);
            this.interfaceCount = this._io.readU4be();
            this.interfaceNames = new ArrayList<StrWithLen>();
            for (int i = 0; i < interfaceCount(); i++) {
                this.interfaceNames.add(new StrWithLen(this._io, this, _root));
            }
            this.signature = new StrWithLen(this._io, this, _root);
            this.superclass = new StrWithLen(this._io, this, _root);
            this.probeCount = this._io.readU4be();
            this.probeList = new ArrayList<ProbeInfo>();
            for (int i = 0; i < probeCount(); i++) {
                this.probeList.add(new ProbeInfo(this._io, this, _root));
            }
            this.methodCount = this._io.readU4be();
            this.methodList = new ArrayList<MethodInfo>();
            for (int i = 0; i < methodCount(); i++) {
                this.methodList.add(new MethodInfo(this._io, this, _root));
            }
        }
        private long classId;
        private StrWithLen container;
        private StrWithLen fileName;
        private StrWithLen className;
        private StrWithLen logLevel;
        private StrWithLen hash;
        private StrWithLen classLoaderIdentifier;
        private long interfaceCount;
        private ArrayList<StrWithLen> interfaceNames;
        private StrWithLen signature;
        private StrWithLen superclass;
        private long probeCount;
        private ArrayList<ProbeInfo> probeList;
        private long methodCount;
        private ArrayList<MethodInfo> methodList;
        private KaitaiInsidiousClassWeaveParser _root;
        private KaitaiInsidiousClassWeaveParser _parent;
        public long classId() { return classId; }
        public StrWithLen container() { return container; }
        public StrWithLen fileName() { return fileName; }
        public StrWithLen className() { return className; }
        public StrWithLen logLevel() { return logLevel; }
        public StrWithLen hash() { return hash; }
        public StrWithLen classLoaderIdentifier() { return classLoaderIdentifier; }
        public long interfaceCount() { return interfaceCount; }
        public ArrayList<StrWithLen> interfaceNames() { return interfaceNames; }
        public StrWithLen signature() { return signature; }
        public StrWithLen superclass() { return superclass; }
        public long probeCount() { return probeCount; }
        public ArrayList<ProbeInfo> probeList() { return probeList; }
        public long methodCount() { return methodCount; }
        public ArrayList<MethodInfo> methodList() { return methodList; }
        public KaitaiInsidiousClassWeaveParser _root() { return _root; }
        public KaitaiInsidiousClassWeaveParser _parent() { return _parent; }
    }
    public static class MethodInfo extends KaitaiStruct {
        public static MethodInfo fromFile(String fileName) throws IOException {
            return new MethodInfo(new ByteBufferKaitaiStream(fileName));
        }

        public MethodInfo(KaitaiStream _io) {
            this(_io, null, null);
        }

        public MethodInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser.ClassInfo _parent) {
            this(_io, _parent, null);
        }

        public MethodInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser.ClassInfo _parent, KaitaiInsidiousClassWeaveParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.classId = this._io.readU4be();
            this.methodId = this._io.readU4be();
            this.methodName = new StrWithLen(this._io, this, _root);
            this.methodDescriptor = new StrWithLen(this._io, this, _root);
            this.access = this._io.readU4be();
            this.sourceFileName = new StrWithLen(this._io, this, _root);
            this.methodHash = new StrWithLen(this._io, this, _root);
        }
        private long classId;
        private long methodId;
        private StrWithLen methodName;
        private StrWithLen methodDescriptor;
        private long access;
        private StrWithLen sourceFileName;
        private StrWithLen methodHash;
        private KaitaiInsidiousClassWeaveParser _root;
        private KaitaiInsidiousClassWeaveParser.ClassInfo _parent;
        public long classId() { return classId; }
        public long methodId() { return methodId; }
        public StrWithLen methodName() { return methodName; }
        public StrWithLen methodDescriptor() { return methodDescriptor; }
        public long access() { return access; }
        public StrWithLen sourceFileName() { return sourceFileName; }
        public StrWithLen methodHash() { return methodHash; }
        public KaitaiInsidiousClassWeaveParser _root() { return _root; }
        public KaitaiInsidiousClassWeaveParser.ClassInfo _parent() { return _parent; }
    }
    public static class ProbeInfo extends KaitaiStruct {
        public static ProbeInfo fromFile(String fileName) throws IOException {
            return new ProbeInfo(new ByteBufferKaitaiStream(fileName));
        }

        public ProbeInfo(KaitaiStream _io) {
            this(_io, null, null);
        }

        public ProbeInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser.ClassInfo _parent) {
            this(_io, _parent, null);
        }

        public ProbeInfo(KaitaiStream _io, KaitaiInsidiousClassWeaveParser.ClassInfo _parent, KaitaiInsidiousClassWeaveParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.classId = this._io.readU4be();
            this.methodId = this._io.readU4be();
            this.dataId = this._io.readU4be();
            this.lineNumber = this._io.readU4be();
            this.instructionIndex = this._io.readU4be();
            this.eventType = KaitaiInsidiousClassWeaveParser.EventType.byId(this._io.readU4be());
            this.valueDescriptor = KaitaiInsidiousClassWeaveParser.Descriptor.byId(this._io.readU4be());
            this.attributes = new StrWithLen(this._io, this, _root);
        }
        private long classId;
        private long methodId;
        private long dataId;
        private long lineNumber;
        private long instructionIndex;
        private EventType eventType;
        private Descriptor valueDescriptor;
        private StrWithLen attributes;
        private KaitaiInsidiousClassWeaveParser _root;
        private KaitaiInsidiousClassWeaveParser.ClassInfo _parent;
        public long classId() { return classId; }
        public long methodId() { return methodId; }
        public long dataId() { return dataId; }
        public long lineNumber() { return lineNumber; }
        public long instructionIndex() { return instructionIndex; }
        public EventType eventType() { return eventType; }
        public Descriptor valueDescriptor() { return valueDescriptor; }
        public StrWithLen attributes() { return attributes; }
        public KaitaiInsidiousClassWeaveParser _root() { return _root; }
        public KaitaiInsidiousClassWeaveParser.ClassInfo _parent() { return _parent; }
    }
    public static class StrWithLen extends KaitaiStruct {
        public static StrWithLen fromFile(String fileName) throws IOException {
            return new StrWithLen(new ByteBufferKaitaiStream(fileName));
        }

        public StrWithLen(KaitaiStream _io) {
            this(_io, null, null);
        }

        public StrWithLen(KaitaiStream _io, KaitaiStruct _parent) {
            this(_io, _parent, null);
        }

        public StrWithLen(KaitaiStream _io, KaitaiStruct _parent, KaitaiInsidiousClassWeaveParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.len = this._io.readU4be();
            this.value = new String(this._io.readBytes(len()), Charset.forName("UTF-8"));
        }
        private long len;
        private String value;
        private KaitaiInsidiousClassWeaveParser _root;
        private KaitaiStruct _parent;
        public long len() { return len; }
        public String value() { return value; }
        public KaitaiInsidiousClassWeaveParser _root() { return _root; }
        public KaitaiStruct _parent() { return _parent; }
    }
    private ArrayList<ClassInfo> classInfo;
    private KaitaiInsidiousClassWeaveParser _root;
    private KaitaiStruct _parent;
    public ArrayList<ClassInfo> classInfo() { return classInfo; }
    public KaitaiInsidiousClassWeaveParser _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
