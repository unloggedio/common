package com.insidious.receiver.util;

/**
 * This enum represents a data type in Java bytecode.
 */
public class Descriptor {


    private final String desc;

    /**
     * Translate a descriptor into a Descriptor object.
     *
     * @param desc is a descriptor
     * @return a Descriptor object corresponding to desc.
     * Any descriptor representing a class is translated into Descriptor.Object.
     */
    public static Descriptor get(String desc) {
        switch (desc) {
            case "Z":
                return new Descriptor(desc);
            case "B":
                return new Descriptor(desc);
            case "C":
                return new Descriptor(desc);
            case "S":
                return new Descriptor(desc);
            case "I":
                return new Descriptor(desc);
            case "J":
                return new Descriptor(desc);
            case "F":
                return new Descriptor(desc);
            case "D":
                return new Descriptor(desc);
            case "V":
                return new Descriptor(desc);
            default:
                return new Descriptor(desc);
        }
    }

    /**
     * A constructor to record the string representation.
     *
     * @param desc
     */
    Descriptor(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Descriptor that = (Descriptor) o;

        return desc.equals(that.desc);
    }

    @Override
    public int hashCode() {
        return desc.hashCode();
    }

    /**
     * @return the descriptor string.
     */
    public String getString() {
        return desc;
    }

}
