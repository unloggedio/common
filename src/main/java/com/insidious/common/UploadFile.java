package com.insidious.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.MultiValueAttribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import net.openhft.chronicle.bytes.BytesIn;
import net.openhft.chronicle.bytes.BytesOut;
import net.openhft.chronicle.wire.WireIn;
import net.openhft.chronicle.wire.WireOut;
import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.json.BloomFilterConverter;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class UploadFile extends AbstractEvent<UploadFile> {


    public static final Attribute<UploadFile, String> SESSION_ID =
            new SimpleAttribute<UploadFile, String>("sessionId") {
                @Override
                public String getValue(UploadFile object, QueryOptions queryOptions) {
                    return object.sessionId;
                }
            };
    public static final SimpleAttribute<UploadFile, String> UPLOAD_ID =
            new SimpleAttribute<UploadFile, String>("uploadId") {
                public String getValue(UploadFile UploadFile, QueryOptions queryOptions) {
                    return UploadFile.id;
                }
            };
    public static final Attribute<UploadFile, Long> THREAD_ID =
            new SimpleAttribute<UploadFile, Long>("threadId") {
                @Override
                public Long getValue(UploadFile object, QueryOptions queryOptions) {
                    return object.threadId;
                }
            };
    public static final Attribute<UploadFile, Integer> PROBE_ID =
            new MultiValueAttribute<UploadFile, Integer>("probeIds") {
                public Iterable<Integer> getValues(UploadFile file, QueryOptions queryOptions) {

                    return Arrays.asList(file.probeIds);

                }
            };
    public static final Attribute<UploadFile, Long> NANOTIME_EVENT =
            new MultiValueAttribute<UploadFile, Long>("nanotime") {
                public Iterable<Long> getValues(UploadFile file, QueryOptions queryOptions) {

                    return Arrays.asList(file.nanotimes);

                }
            };
    public static final Attribute<UploadFile, Long> VALUE_ID =
            new MultiValueAttribute<UploadFile, Long>("valueIds") {
                public Iterable<Long> getValues(UploadFile file, QueryOptions queryOptions) {
                    return Arrays.asList(file.valueIds);

                }
            };


    protected static final int MASHALLABLE_VERSION = 1;
    protected static JsonParser parser = new JsonParser();
    public String path;
    public long threadId;
    public BloomFilter<Long> valueIdBloomFilter;
    public BloomFilter<Integer> probeIdBloomFilter;
    protected String sessionId;
    protected String id;
    protected Integer[] probeIds;
    protected Long[] nanotimes;
    protected Long[] valueIds;

    public UploadFile(String pathToFile, long threadId,
                      BloomFilter<Long> valueIdBloomFilter,
                      BloomFilter<Integer> probeIdBloomFilter) {
        this.path = pathToFile;
        this.threadId = threadId;
        this.valueIdBloomFilter = valueIdBloomFilter;
        this.probeIdBloomFilter = probeIdBloomFilter;
    }

    public static UploadFile fromBytes(byte[] bytes) {
        ByteBuffer byteReader = ByteBuffer.wrap(bytes);

        String path = BloomFilterUtil.getNextString(byteReader);
        long threadId = byteReader.getLong();

        byte[] valueBytes = BloomFilterUtil.getNextBytes(byteReader);
        byte[] probeBytes = BloomFilterUtil.getNextBytes(byteReader);

        JsonElement valueFilterJson = parser.parse(new String(valueBytes));
        BloomFilter<Long> valueFilter = BloomFilterConverter.fromJson(valueFilterJson, Long.class); //{"size":240,"hashes":4,"HashMethod":"MD5","bits":"AAAAEAAAAACAgAAAAAAAAAAAAAAQ"}

        JsonElement probeFilterJson = parser.parse(new String(probeBytes));
        BloomFilter<Integer> probeFilter = BloomFilterConverter.fromJson(probeFilterJson, Integer.class); //{"size":240,"hashes":4,"HashMethod":"MD5","bits":"AAAAEAAAAACAgAAAAAAAAAAAAAAQ"}

        return new UploadFile(path, threadId, valueFilter, probeFilter);

    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4 + path.length() + 8 + 4 + 0 + 4 + 0);
        buffer.putInt(path.length());
        buffer.put(path.getBytes());
        buffer.putLong(threadId);


        if (valueIdBloomFilter != null) {
            byte[] valueBytes = BloomFilterConverter.toJson(valueIdBloomFilter).toString().getBytes();
            buffer.putInt(valueBytes.length);
            buffer.put(valueBytes);
        } else {
            buffer.putInt(0);
        }

        if (probeIdBloomFilter != null) {
            byte[] probeBytes = BloomFilterConverter.toJson(probeIdBloomFilter).toString().getBytes();
            buffer.putInt(probeBytes.length);
            buffer.put(probeBytes);
        } else {
            buffer.putInt(0);
        }
        return buffer.array();
    }

    @Override
    public void writeMarshallable(BytesOut out) {
        super.writeMarshallable(out);
        if (PREGENERATED_MARSHALLABLE) {
            out.writeStopBit(MASHALLABLE_VERSION);
            out.write(toBytes());
        }
    }

    @Override
    public void readMarshallable(BytesIn in) {
        super.readMarshallable(in);
        if (PREGENERATED_MARSHALLABLE) {
            int version = (int) in.readStopBit();
            if (version == MASHALLABLE_VERSION) {
                byte[] bytes = in.toByteArray();
                copyFrom(fromBytes(bytes));
            }
        }
    }

    @Override
    public void writeMarshallable(WireOut out) {
        super.writeMarshallable(out);
        if (PREGENERATED_MARSHALLABLE) {
            out.write("bytes").bytes(toBytes());
        }
    }

    @Override
    public void readMarshallable(WireIn in) {
        super.readMarshallable(in);
        if (PREGENERATED_MARSHALLABLE) {
            byte[] bytes = in.read("bytes").bytes();
            UploadFile uf = fromBytes(bytes);
            copyFrom(uf);
        }
    }

    protected void copyFrom(UploadFile that) {
        this.path = that.path;
        this.threadId = that.threadId;
        this.valueIdBloomFilter = that.valueIdBloomFilter;
        this.probeIdBloomFilter = that.probeIdBloomFilter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public BloomFilter<Long> getValueIdBloomFilter() {
        return valueIdBloomFilter;
    }

    public void setValueIdBloomFilter(BloomFilter<Long> valueIdBloomFilter) {
        this.valueIdBloomFilter = valueIdBloomFilter;
    }

    public BloomFilter<Integer> getProbeIdBloomFilter() {
        return probeIdBloomFilter;
    }

    public void setProbeIdBloomFilter(BloomFilter<Integer> probeIdBloomFilter) {
        this.probeIdBloomFilter = probeIdBloomFilter;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer[] getProbeIds() {
        return probeIds;
    }

    public void setProbeIds(Integer[] probeIds) {
        this.probeIds = probeIds;
    }

    public Long[] getNanotimes() {
        return nanotimes;
    }

    public void setNanotimes(Long[] nanotimes) {
        this.nanotimes = nanotimes;
    }

    public Long[] getValueIds() {
        return valueIds;
    }

    public void setValueIds(Long[] valueIds) {
        this.valueIds = valueIds;
    }
}