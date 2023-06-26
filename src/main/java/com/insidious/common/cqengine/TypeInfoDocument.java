package com.insidious.common.cqengine;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.query.option.QueryOptions;

import java.io.Serializable;

@PersistenceConfig(serializer = TypeInfoDocumentSerializer.class)
public class TypeInfoDocument implements Serializable {

    public static final SimpleAttribute<TypeInfoDocument, String> TYPE_NAME =
            new SimpleAttribute<TypeInfoDocument, String>("typeName") {
                public String getValue(TypeInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.typeName;
                }
            };
    public static final SimpleAttribute<TypeInfoDocument, Integer> TYPE_ID =
            new SimpleAttribute<TypeInfoDocument, Integer>("typeId") {
                public Integer getValue(TypeInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.typeId;
                }
            };
    private static final long serialVersionUID = 4357600885262072086L;
    private final byte[] typeBytes;

    private int typeId;
    private String typeName;

    public TypeInfoDocument(int typeId, String typeName, byte[] typeInfoBytes) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeBytes = typeInfoBytes;

    }

    @Override
    public String toString() {
        return "TypeInfoDocument{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public byte[] getTypeBytes() {
        return typeBytes;
    }
}
