package com.insidious.common.weaver;

/**
 * This enum represents a data type in Java bytecode.
 */
public enum Descriptor {

    Boolean("Z"),
    Byte("B"),
    Char("C"),
    Short("S"),
    Integer("I"),
    Long("J"),
    Float("F"),
    Double("D"),
    IntegerObject("Ljava/lang/Integer;"),
    CharacterObject("Ljava/lang/Character;"),
    BooleanObject("Ljava/lang/Boolean;"),
    FloatObject("Ljava/lang/Float;"),
    DoubleObject("Ljava/lang/Double;"),
    ShortObject("Ljava/lang/Short;"),
    LongObject("Ljava/lang/Long;"),
    Object("Ljava/lang/Object;"),
    Void("V");

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
                return Boolean;
            case "B":
                return Byte;
            case "C":
                return Char;
            case "S":
                return Short;
            case "I":
                return Integer;
            case "J":
                return Long;
            case "F":
                return Float;
            case "D":
                return Double;
            case "V":
                return Void;
            case "Ljava/lang/Integer;":
                return IntegerObject;
            case "Ljava/lang/Double;":
                return DoubleObject;
            case "Ljava/lang/Short;":
                return ShortObject;
            case "Ljava/lang/Long;":
                return LongObject;
            case "Ljava/lang/Float;":
                return FloatObject;
            case "Ljava/lang/Boolean;":
                return BooleanObject;
            case "Ljava/lang/Character;":
                return CharacterObject;
            default:
                return Object;
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

    /**
     * @return the descriptor string.
     */
    public String getString() {
        return desc;
    }

}
