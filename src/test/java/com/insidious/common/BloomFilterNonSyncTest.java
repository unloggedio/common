package com.insidious.common;

import org.junit.jupiter.api.Test;

class BloomFilterNonSyncTest {

    @Test
    void contains() {
        BloomFilterNonSync<Integer> nonSyncBloomFilter =
                BloomFilterUtil.newBloomFilterForProbesNonSync(8 * 1024 * 1024);

//        Runnable setter = new Runnable() {
//
//            @Override
//            public void run() {
//                nonSyncBloomFilter.add()
//            }
//        };

    }

//    static class FilterSetterRunner implements Runnable {
//
//        private final BloomFilterNonSync<?> filter;
//        private final int setterMultiplier;
//
//        public FilterSetterRunner(BloomFilterNonSync<?> filter, int setterMultiplier>) {
//            this.filter = filter;
//            this.setterMultiplier = setterMultiplier;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 1000; i++) {
//                filter.addRaw()
//            }
//        }
//    }
}