package com.insidious.common;

import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;

import java.nio.ByteBuffer;

public class BloomFilterUtil {

//    public static final int BLOOM_FILTER_BIT_SIZE = 1024 * 8;
//    public static final int BLOOM_AGGREGATED_FILTER_BIT_SIZE = 1024 * 32;

    public static final int BLOOM_FILTER_BIT_SIZE = 8;
    public static final int BLOOM_AGGREGATED_FILTER_BIT_SIZE = 32;

    public static BloomFilter<Long> newBloomFilterForValues(Integer SIZE) {
        return new FilterBuilder(SIZE, 0.001).buildBloomFilter();
    }

    public static BloomFilter<Integer> newBloomFilterForProbes(Integer SIZE) {
        return new FilterBuilder(SIZE, 0.001).buildBloomFilter();
    }

    public static BloomFilterNonSync<Long> newBloomFilterForValuesNonSync(Integer SIZE) {
        FilterBuilder filterBuilder = new FilterBuilder(SIZE, 0.001);
        return new BloomFilterNonSync<>(filterBuilder);
    }

    public static BloomFilterNonSync<Integer> newBloomFilterForProbesNonSync(Integer SIZE) {
        FilterBuilder filterBuilder = new FilterBuilder(SIZE, 0.001);
        return new BloomFilterNonSync<>(filterBuilder);
    }


    public static String getNextString(ByteBuffer buffer) {
        int stringLength = buffer.getInt();
// System.err.println("String length - " + stringLength);
        byte[] str = new byte[stringLength];
        buffer.get(str);
        return new String(str);
    }


    public static byte[] getNextBytes(ByteBuffer buffer) {
        int stringLength = buffer.getInt();
// System.err.println("String length - " + stringLength);
        if (stringLength == 0) {
            return new byte[]{};
        }
        byte[] str = new byte[stringLength];
        buffer.get(str);
        return str;
    }


}
