package com.insidious.common.parser;

import io.kaitai.struct.KaitaiStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OnlyForwardKaitaiStream extends KaitaiStream implements Closeable {
    private static final int DEFAULT_BUFFER_SIZE = 4 * 1024;
    protected DataInputStream raf;

    public OnlyForwardKaitaiStream(InputStream inputStream) {
        this.raf = new DataInputStream(inputStream);
    }

    @Override
    public void close() throws IOException {
        raf.close();
    }

    @Override
    public boolean isEof() {
        try {
            return raf.available() == 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void seek(int newPos) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public void seek(long newPos) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public int pos() {
        return 0;
    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public byte readS1() {
        try {
            int t = raf.read();
            if (t < 0) {
                throw new EOFException();
            } else {
                return (byte) t;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public short readS2be() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            if ((b1 | b2) < 0) {
                throw new EOFException();
            } else {
                return (short) ((b1 << 8) + (b2 << 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readS4be() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            int b3 = raf.read();
            int b4 = raf.read();
            if ((b1 | b2 | b3 | b4) < 0) {
                throw new EOFException();
            } else {
                return (b1 << 24) + (b2 << 16) + (b3 << 8) + (b4 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long readS8be() {
        long b1 = readU4be();
        long b2 = readU4be();
        return (b1 << 32) + (b2 << 0);
    }

    @Override
    public short readS2le() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            if ((b1 | b2) < 0) {
                throw new EOFException();
            } else {
                return (short) ((b2 << 8) + (b1 << 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readS4le() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            int b3 = raf.read();
            int b4 = raf.read();
            if ((b1 | b2 | b3 | b4) < 0) {
                throw new EOFException();
            } else {
                return (b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long readS8le() {
        long b1 = readU4le();
        long b2 = readU4le();
        return (b2 << 32) + (b1 << 0);
    }

    @Override
    public int readU1() {
        try {
            int t = raf.read();
            if (t < 0) {
                throw new EOFException();
            } else {
                return t;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readU2be() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            if ((b1 | b2) < 0) {
                throw new EOFException();
            } else {
                return (b1 << 8) + (b2 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long readU4be() {
        try {
            long b1 = raf.read();
            long b2 = raf.read();
            long b3 = raf.read();
            long b4 = raf.read();
            if ((b1 | b2 | b3 | b4) < 0) {
                throw new EOFException();
            } else {
                return (b1 << 24) + (b2 << 16) + (b3 << 8) + (b4 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readU2le() {
        try {
            int b1 = raf.read();
            int b2 = raf.read();
            if ((b1 | b2) < 0) {
                throw new EOFException();
            } else {
                return (b2 << 8) + (b1 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //region Floating point numbers

    //region Big-endian

    @Override
    public long readU4le() {
        try {
            long b1 = raf.read();
            long b2 = raf.read();
            long b3 = raf.read();
            long b4 = raf.read();
            if ((b1 | b2 | b3 | b4) < 0) {
                throw new EOFException();
            } else {
                return (b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float readF4be() {
        return wrapBufferBe(4).getFloat();
    }

    //endregion

    //region Little-endian

    @Override
    public double readF8be() {
        return wrapBufferBe(8).getDouble();
    }

    @Override
    public float readF4le() {
        return wrapBufferLe(4).getFloat();
    }

    //endregion

    //endregion

    @Override
    public double readF8le() {
        return wrapBufferLe(8).getDouble();
    }

    @Override
    public byte[] readBytes(long n) {
        byte[] buf = new byte[toByteArrayLength(n)];
        try {
            int readCount = raf.read(buf);
            if (readCount < n) {
                throw new EOFException();
            }
            return buf;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] readBytesFull() {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int readCount;
        try {
            while (-1 != (readCount = raf.read(buffer)))
                baos.write(buffer, 0, readCount);

            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] readBytesTerm(int term, boolean includeTerm, boolean consumeTerm, boolean eosError) {
        throw new RuntimeException("not implemented");
    }

    //region Helper methods

    private ByteBuffer wrapBufferLe(int count) {
        return ByteBuffer.wrap(readBytes(count)).order(ByteOrder.LITTLE_ENDIAN);
    }

    private ByteBuffer wrapBufferBe(int count) {
        return ByteBuffer.wrap(readBytes(count)).order(ByteOrder.BIG_ENDIAN);
    }

    //endregion
}
