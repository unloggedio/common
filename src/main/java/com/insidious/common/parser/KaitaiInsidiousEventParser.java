// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

package com.insidious.common.parser;

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

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
    public static class Block extends KaitaiStruct {
        public static Block fromFile(String fileName) throws IOException {
            return new Block(new ByteBufferKaitaiStream(fileName));
        }

        public enum Identifier {
            DETAILED_EVENT_INFORMATION(7);

            private final long id;
            Identifier(long id) { this.id = id; }
            public long id() { return id; }
            private static final Map<Long, Identifier> byId = new HashMap<Long, Identifier>(1);
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
            this.magic = this._io.readBytes(1);
            if (!(Arrays.equals(magic(), new byte[] { 7 }))) {
                throw new KaitaiStream.ValidationNotEqualError(new byte[] { 7 }, magic(), _io(), "/types/block/seq/0");
            }
            this.block = new DetailedEventBlock(this._io, this, _root);
        }
        private byte[] magic;
        private DetailedEventBlock block;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Event _parent;
        public byte[] magic() { return magic; }
        public DetailedEventBlock block() { return block; }
        public KaitaiInsidiousEventParser _root() { return _root; }
        public KaitaiInsidiousEventParser.Event _parent() { return _parent; }
    }
    public static class DetailedEventBlock extends KaitaiStruct {
        public static DetailedEventBlock fromFile(String fileName) throws IOException {
            return new DetailedEventBlock(new ByteBufferKaitaiStream(fileName));
        }

        public DetailedEventBlock(KaitaiStream _io) {
            this(_io, null, null);
        }

        public DetailedEventBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent) {
            this(_io, _parent, null);
        }

        public DetailedEventBlock(KaitaiStream _io, KaitaiInsidiousEventParser.Block _parent, KaitaiInsidiousEventParser _root) {
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
            this.lenSerializedData = this._io.readU4be();
            this.serializedData = this._io.readBytes(lenSerializedData());
        }
        private long eventId;
        private long timestamp;
        private long probeId;
        private long valueId;
        private long lenSerializedData;
        private byte[] serializedData;
        private KaitaiInsidiousEventParser _root;
        private KaitaiInsidiousEventParser.Block _parent;
        public long eventId() { return eventId; }
        public long timestamp() { return timestamp; }
        public long probeId() { return probeId; }
        public long valueId() { return valueId; }
        public long lenSerializedData() { return lenSerializedData; }
        public byte[] serializedData() { return serializedData; }
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
