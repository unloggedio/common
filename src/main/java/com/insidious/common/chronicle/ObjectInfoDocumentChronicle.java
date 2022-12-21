package com.insidious.common.chronicle;

import net.openhft.chronicle.bytes.BytesStore;
import net.openhft.chronicle.bytes.ref.AbstractReference;
import org.jetbrains.annotations.NotNull;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class ObjectInfoDocumentChronicle extends AbstractReference {


    @Override
    public long maxSize() {
        return 16;
    }

    @Override
    public void bytesStore(@NotNull final BytesStore bytes, final long offset, final long length)
            throws IllegalStateException, IllegalArgumentException, BufferOverflowException {
        throwExceptionIfClosedInSetter();

        if (length != maxSize())
            throw new IllegalArgumentException();

        super.bytesStore(bytes, offset, length);
    }


    public long getObjectId()
            throws IllegalStateException, BufferUnderflowException {
        throwExceptionIfClosed();

        long objectId = bytes.readLong(offset);
        return objectId;

    }

    public void setObjectId(final long objectId)
            throws IllegalStateException {
        throwExceptionIfClosed();

        try {
            bytes.writeLong(offset, objectId);
        } catch (BufferOverflowException e) {
            throw new AssertionError(e);
        }
    }


    public long getTypeId()
            throws IllegalStateException, BufferUnderflowException {
        throwExceptionIfClosed();

        long objectId = bytes.readLong(offset + 8);
        return objectId;

    }

    public void setTypeId(final long objectId)
            throws IllegalStateException {
        throwExceptionIfClosed();

        try {
            bytes.writeLong(offset + 8, objectId);
        } catch (BufferOverflowException e) {
            throw new AssertionError(e);
        }
    }


}
