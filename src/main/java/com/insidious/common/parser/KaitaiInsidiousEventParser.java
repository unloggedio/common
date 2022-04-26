// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package com.insidious.common.parser;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.nio.charset.Charset;

public class KaitaiInsidiousEventParser extends KaitaiStruct {
    public static KaitaiInsidiousEventParser fromFile(String fileName) throws IOException {
        return new KaitaiInsidiousEventParser(new ByteBufferKaitaiStream(fileName));
    }

    public KaitaiInsidiousEventParser(KaitaiStream _io) {
        this(_io, null, null);
    }

    public KaitaiInsidiousEventParser(KaitaiStream _io, KaitaiStruct _parent) {
        this(_io, _parent, null);
    }

    public KaitaiInsidiousEventParser(KaitaiStream _io, KaitaiStruct _parent, KaitaiInsidiousEventParser _root) {
        super(_io);
        this._parent = _parent;
        this._root = _root == null ? this : _root;
        _read();
    }
    private void _read() {
        this.event = new Event(this._io, this, _root);
    }
    public static class NewExceptionBlock extends KaitaiStruct {
        public static NewExceptionBlock fromFile(String fileName) throws IOException {
            return new NewExceptionBlock(new ByteBufferKaitaiStream(fileName));
        }

        public NewExceptionBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public NewExceptionBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public NewExceptionBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.exceptionLength = this._io.readU4be();
            this.string = this._io.readBytes(exceptionLength());
        }
        private long exceptionLength;
        private byte[] string;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long exceptionLength() { return exceptionLength; }
        public byte[] string() { return string; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    public static class Event extends KaitaiStruct {
        public static Event fromFile(String fileName) throws IOException {
            return new Event(new ByteBufferKaitaiStream(fileName));
        }

        public Event(KaitaiStream _io) {
            this(_io, null, null);
        }

        public Event(KaitaiStream _io, KaitaiInsidiousEventParser _parent) {
            this(_io, _parent, null);
        }

        public Event(KaitaiStream _io, KaitaiInsidiousEventParser _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.entries = new ArrayList<Block>();
            {
                int i = 0;
                while (!this._io.isEof()) {
                    this.entries.add(new Block(this._io, this, _root));
                    i++;
                }
            }
        }
        private ArrayList<Block> entries;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser _parent;
        public ArrayList<Block> entries() { return entries; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser _parent() { return _parent; }
    }
    public static class WeaveInformationBlock extends KaitaiStruct {
        public static WeaveInformationBlock fromFile(String fileName) throws IOException {
            return new WeaveInformationBlock(new ByteBufferKaitaiStream(fileName));
        }

        public WeaveInformationBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public WeaveInformationBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public WeaveInformationBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.stringLength = this._io.readU4be();
            this.weaveBytes = this._io.readBytes(stringLength());
        }
        private long stringLength;
        private byte[] weaveBytes;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long stringLength() { return stringLength; }
        public byte[] weaveBytes() { return weaveBytes; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    public static class NewObjectBlock extends KaitaiStruct {
        public static NewObjectBlock fromFile(String fileName) throws IOException {
            return new NewObjectBlock(new ByteBufferKaitaiStream(fileName));
        }

        public NewObjectBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public NewObjectBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public NewObjectBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.objectId = this._io.readU8be();
            this.valueId = this._io.readU8be();
        }
        private long objectId;
        private long valueId;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long objectId() { return objectId; }
        public long valueId() { return valueId; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    public static class Block extends KaitaiStruct {
        public static Block fromFile(String fileName) throws IOException {
            return new Block(new ByteBufferKaitaiStream(fileName));
        }

        public enum Identifier {
            NEW_OBJECT(1),
            NEW_STRING(2),
            NEW_EXCEPTION(3),
            DATA_EVENT(4),
            TYPE_RECORD(5),
            WEAVE_INFORMATION(6);

            private final long id;
            Identifier(long id) { this.id = id; }
            public long id() { return id; }
            private static final Map<Long, Identifier> byId = new HashMap<Long, Identifier>(6);
            static {
                for (Identifier e : Identifier.values())
                    byId.put(e.id(), e);
            }
            public static Identifier byId(long id) { return byId.get(id); }
        }

        public Block(KaitaiStream _io) {
            this(_io, null, null);
        }

        public Block(KaitaiStream _io, KaitaiInsidiousEventParser.Event _parent) {
            this(_io, _parent, null);
        }

        public Block(KaitaiStream _io, KaitaiInsidiousEventParser.Event _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.magic = this._io.readU1();
            switch (magic()) {
            case 4: {
                this.block = new DataEventBlock(this._io, this, _root);
                break;
            }
            case 6: {
                this.block = new WeaveInformationBlock(this._io, this, _root);
                break;
            }
            case 1: {
                this.block = new NewObjectBlock(this._io, this, _root);
                break;
            }
            case 3: {
                this.block = new NewExceptionBlock(this._io, this, _root);
                break;
            }
            case 5: {
                this.block = new TypeRecordBlock(this._io, this, _root);
                break;
            }
            case 2: {
                this.block = new NewStringBlock(this._io, this, _root);
                break;
            }
            }
        }
        private int magic;
        private KaitaiStruct block;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Event _parent;
        public int magic() { return magic; }
        public KaitaiStruct block() { return block; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Event _parent() { return _parent; }
    }
    public static class NewStringBlock extends KaitaiStruct {
        public static NewStringBlock fromFile(String fileName) throws IOException {
            return new NewStringBlock(new ByteBufferKaitaiStream(fileName));
        }

        public NewStringBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public NewStringBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public NewStringBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.stringId = this._io.readU8be();
            this.stringLength = this._io.readU4be();
            this.string = new String(this._io.readBytes(stringLength()), Charset.forName("UTF-8"));
        }
        private long stringId;
        private long stringLength;
        private String string;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long stringId() { return stringId; }
        public long stringLength() { return stringLength; }
        public String string() { return string; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    public static class TypeRecordBlock extends KaitaiStruct {
        public static TypeRecordBlock fromFile(String fileName) throws IOException {
            return new TypeRecordBlock(new ByteBufferKaitaiStream(fileName));
        }

        public TypeRecordBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public TypeRecordBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public TypeRecordBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.bytesLength = this._io.readU4be();
            this.typeId = this._io.readU4be();
            this.typeNameLength = this._io.readU4be();
            this.typeName = new String(this._io.readBytes(typeNameLength()), Charset.forName("UTF-8"));
            this.classLocationLength = this._io.readU4be();
            this.classLocation = new String(this._io.readBytes(classLocationLength()), Charset.forName("UTF-8"));
            this.superClassId = this._io.readU4be();
            this.componentTypeId = this._io.readU4be();
            this.classLoaderLength = this._io.readU4be();
            this.classLoader = new String(this._io.readBytes(classLoaderLength()), Charset.forName("UTF-8"));
        }
        private long bytesLength;
        private long typeId;
        private long typeNameLength;
        private String typeName;
        private long classLocationLength;
        private String classLocation;
        private long superClassId;
        private long componentTypeId;
        private long classLoaderLength;
        private String classLoader;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long bytesLength() { return bytesLength; }
        public long typeId() { return typeId; }
        public long typeNameLength() { return typeNameLength; }
        public String typeName() { return typeName; }
        public long classLocationLength() { return classLocationLength; }
        public String classLocation() { return classLocation; }
        public long superClassId() { return superClassId; }
        public long componentTypeId() { return componentTypeId; }
        public long classLoaderLength() { return classLoaderLength; }
        public String classLoader() { return classLoader; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    public static class DataEventBlock extends KaitaiStruct {
        public static DataEventBlock fromFile(String fileName) throws IOException {
            return new DataEventBlock(new ByteBufferKaitaiStream(fileName));
        }

        public DataEventBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public DataEventBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public DataEventBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
            super(_io);
            this._parent = _parent;
            this._root = _root;
            _read();
        }
        private void _read() {
            this.eventId = this._io.readU8be();
            this.timestamp = this._io.readU8be();
            this.probeId = this._io.readU4be();
            this.valueId = this._io.readU8be();
        }
        private long eventId;
        private long timestamp;
        private long probeId;
        private long valueId;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long eventId() { return eventId; }
        public long timestamp() { return timestamp; }
        public long probeId() { return probeId; }
        public long valueId() { return valueId; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Block _parent() { return _parent; }
    }
    private Event event;
    private KaitaiInsidiousEventParser _root;
    private KaitaiStruct _parent;
    public Event event() { return event; }
    public KaitaiInsidiousEventParser _root() { return _root; }
    public KaitaiStruct _parent() { return _parent; }
}
