package com.insidious.common.parser;

import io.kaitai.struct.KaitaiStream;
import io.kaitai.struct.KaitaiStruct;

import java.io.InputStream;

public class StreamingKaitaiStruct {
    protected OnlyForwardKaitaiStream _io;
    protected StreamingKaitaiStruct _parent;

    public StreamingKaitaiStruct(OnlyForwardKaitaiStream _io) {
        this._io = _io;
    }

    public OnlyForwardKaitaiStream _io() {
        return this._io;
    }

    public StreamingKaitaiStruct _parent() {
        return this._parent;
    }
}
