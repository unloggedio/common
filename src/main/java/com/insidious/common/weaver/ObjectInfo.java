package com.insidious.common.weaver;


import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

import java.io.Serializable;

public class ObjectInfo implements Serializable {

    public static final SimpleAttribute<ObjectInfo, Long> OBJECT_ID =
            new SimpleAttribute<ObjectInfo, Long>("objectId") {
                public Long getValue(ObjectInfo probeInfoDocument, QueryOptions queryOptions) {
                    return probeInfoDocument.objectId;
                }
            };


    private long objectId;
    private int typeId;
    private long recordedAt;

    public ObjectInfo() {
    }

    public ObjectInfo(long objectId, int typeId, long recordedAt) {
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public long getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(long recordedAt) {
        this.recordedAt = recordedAt;
    }
}
