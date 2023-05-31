package com.insidious.common;

import orestes.bloomfilter.FilterBuilder;
import orestes.bloomfilter.memory.BloomFilterMemory;

public class BloomFilterNonSync<T> extends BloomFilterMemory<T> {
    public BloomFilterNonSync(FilterBuilder config) {
        super(config);
    }

    @Override
    public boolean contains(byte[] element) {
        for (int position : hash(element)) {
            if (!getBit(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addRaw(byte[] element) {
        boolean added = false;
        for (int position : hash(element)) {
            if (!getBit(position)) {
                added = true;
                setBit(position, true);
            }
        }
        return added;

    }
}
