package com.insidious.common.cqengine;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;
import com.googlecode.cqengine.query.option.QueryOptions;

import java.io.Serializable;

@PersistenceConfig(serializer = StringInfoDocumentSerializer.class)
public class StringInfoDocument implements Serializable {

    public static final SimpleAttribute<StringInfoDocument, String> STRING_VALUE =
            new SimpleAttribute<StringInfoDocument, String>("stringValue") {
                public String getValue(StringInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.string;
                }
            };

    public static final SimpleAttribute<StringInfoDocument, Long> STRING_ID =
            new SimpleAttribute<StringInfoDocument, Long>("stringId") {
                public Long getValue(StringInfoDocument typeInfoDocument, QueryOptions queryOptions) {
                    return typeInfoDocument.stringId;
                }
            };


    String string;
    long stringId;

    public StringInfoDocument(long id, String string) {
        this.stringId = id;
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public long getStringId() {
        return stringId;
    }

    public void setStringId(long stringId) {
        this.stringId = stringId;
    }
}
