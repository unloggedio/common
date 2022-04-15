package com.insidious.common;

import com.insidious.common.parser.KaitaiInsidiousClassWeaveParser;
import io.kaitai.struct.KaitaiStruct;

public class Util {
    private static final byte ATTRIBUTE_SEPARATOR = ',';
    private static final byte ATTRIBUTE_KEYVALUE_SEPARATOR = '=';

    /**
     * Access a particular attribute of the instruction, assuming the "KEY=VALUE" format.
     *
     * @param key          specifies an attribute key
     * @param defaultValue is returned if the key is unavailable.
     * @return the value corresponding to the key.
     */
    public static String getAttribute(String attributes, String key, String defaultValue) {
        int index = attributes.indexOf(key);
        while (index >= 0) {
            if (index == 0 || attributes.charAt(index - 1) == ATTRIBUTE_SEPARATOR) {
                int keyEndIndex = attributes.indexOf(ATTRIBUTE_KEYVALUE_SEPARATOR, index);
                if (keyEndIndex == index + key.length()) {
                    int valueEndIndex = attributes.indexOf(ATTRIBUTE_SEPARATOR, keyEndIndex);
                    if (valueEndIndex > keyEndIndex) {
                        return attributes.substring(index + key.length() + 1, valueEndIndex);
                    } else {
                        return attributes.substring(index + key.length() + 1);
                    }
                }
            }
            index = attributes.indexOf(key, index + 1);
        }
        return defaultValue;
    }

}
