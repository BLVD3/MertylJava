package de.hhn.mertyl;

public class MtLayout {
    private final MtType[] types;
    private final int[] arraySizes;
    private final MtType[] arrayTypes;

    private int headerSize;
    private int stride;

    public MtLayout(MtType[] types, int[] arraySizes, MtType[] arrayTypes) {
        if (!checkViability(types, arraySizes, arrayTypes))
            throw new IllegalArgumentException("arraySizes and/or arrayTypes length do not match the count " +
                    "of array/string and/or sizes are contains numbers <= 0.");
        this.types = types.clone();
        this.arraySizes = arraySizes.clone();
        this.arrayTypes = arrayTypes.clone();
        stride = calculateStride();
        headerSize = calculateHeaderSize();
    }

    private int calculateHeaderSize() {
        int size = 0;
        for (MtType type : types) {
            size += switch (type) {
                case STRING -> 2;
                case ARRAY -> 3;
                default -> 1;
            };
        }
        return size;
    }

    private int calculateStride() {
        int stride = 0;
        int sizeCount = 0;
        int arrTypeCount = 0;
        for (MtType type : types) {
            stride += switch (type) {
                case ARRAY -> arraySizes[sizeCount++] * arrayTypes[arrTypeCount++].getSize();
                case STRING -> arraySizes[sizeCount++];
                default -> type.getSize();
            };
        }
        return stride;
    }

    private boolean checkViability(MtType[] types, int[] arraySizes, MtType[] arrayTypes) {
        int arrayCount = 0;
        int stringCount = 0;
        for (MtType type: types) {
            if (type == MtType.ARRAY)
                arrayCount++;
            else if (type == MtType.STRING) {
                stringCount++;
            }
        }
        if (arraySizes.length != stringCount + arrayCount)
            return false;
        if (arrayTypes.length != arrayCount)
            return false;
        for (MtType type: arrayTypes) {
            if (type == MtType.STRING || type == MtType.ARRAY)
                return false;
        }
        for (int size : arraySizes)
            if (size <= 0)
                return false;
        return true;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int arrayTypeCount = 0;
        int sizeCount = 0;
        builder.append("TableLayout: ");
        for (MtType type : types) {
            switch (type) {
                default -> builder.append(type);
                case ARRAY -> {
                    builder.append(arrayTypes[arrayTypeCount]).append(" ").append(type).append(" of size ").append(arraySizes[sizeCount]);
                    arrayTypeCount++;
                    sizeCount++;
                }
                case STRING -> builder.append(type).append(" with max size ").append(arraySizes[sizeCount]);
            }
            builder.append("; ");
        }
        return builder.delete(builder.length() - 2, builder.length() - 1).toString();
    }
}
