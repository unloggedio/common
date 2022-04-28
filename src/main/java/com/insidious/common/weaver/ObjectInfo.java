package com.insidious.common.weaver;


import java.io.Serializable;

public class ObjectInfo implements Serializable {

    private long objectId;
    private long typeId;
    private long recordedAt;

    public ObjectInfo() {
    }

    public ObjectInfo(long objectId, long typeId, long recordedAt) {
        this.objectId = objectId;
        this.typeId = typeId;
        this.recordedAt = recordedAt;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }
}
