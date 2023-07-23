package de.hhn.mertyl;

public enum MtType {
    BOOL,
    CHAR,
    S_BYTE,
    U_BYTE,
    S_SHORT,
    U_SHORT,
    S_INT,
    U_INT,
    S_LONG,
    U_LONG,
    FLOAT,
    DOUBLE,
    STRING,
    ARRAY;

    public byte getTypeId() {
        return switch (this) {
            case BOOL -> (byte) 1;
            case CHAR -> (byte) 2;
            case S_BYTE -> (byte) 3;
            case U_BYTE -> (byte) 4;
            case S_SHORT -> (byte) 5;
            case U_SHORT -> (byte) 6;
            case S_INT -> (byte) 7;
            case U_INT -> (byte) 8;
            case S_LONG -> (byte) 9;
            case U_LONG -> (byte) 10;
            case FLOAT -> (byte) 11;
            case DOUBLE -> (byte) 12;
            case STRING -> (byte) 13;
            case ARRAY -> (byte) 14;
        };
    }

    public Class<?> getType() {
        return switch (this) {
            case BOOL -> Boolean.class;
            case CHAR -> Character.class;
            case S_BYTE, U_BYTE -> Byte.class;
            case S_SHORT, U_SHORT -> Short.class;
            case S_INT, U_INT -> Integer.class;
            case S_LONG, U_LONG -> Long.class;
            case FLOAT -> Float.class;
            case DOUBLE -> Double.class;
            case STRING -> String.class;
            case ARRAY -> null;
        };
    }

    public static Class<?> getType(byte id) {
        return switch (id) {
            case 1 -> Boolean.class;
            case 2 -> Character.class;
            case 3, 4 -> Byte.class;
            case 5, 6 -> Short.class;
            case 7, 8 -> Integer.class;
            case 9, 10 -> Long.class;
            case 11 -> Float.class;
            case 12 -> Double.class;
            case 13 -> String.class;
            default -> null;
        };
    }

    public static MtType getMtType(byte id) {
        return switch (id) {
            case 1 -> BOOL;
            case 2 -> CHAR;
            case 3 -> S_BYTE;
            case 4 -> U_BYTE;
            case 5 -> S_SHORT;
            case 6 -> U_SHORT;
            case 7 -> S_INT;
            case 8 -> U_INT;
            case 9 -> S_LONG;
            case 10 -> U_LONG;
            case 11 -> FLOAT;
            case 12 -> DOUBLE;
            case 13 -> STRING;
            case 14 -> ARRAY;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {

            case BOOL -> "bool";
            case CHAR -> "char";
            case S_BYTE -> "signed byte";
            case U_BYTE -> "unsigned byte";
            case S_SHORT -> "signed short";
            case U_SHORT -> "unsigned short";
            case S_INT -> "signed int";
            case U_INT -> "unsigned int";
            case S_LONG -> "signed long";
            case U_LONG -> "unsigned long";
            case FLOAT -> "float";
            case DOUBLE -> "double";
            case STRING -> "string";
            case ARRAY -> "array";
        };
    }
}
