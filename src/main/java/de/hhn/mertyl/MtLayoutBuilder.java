package de.hhn.mertyl;

import java.util.ArrayList;

public class MtLayoutBuilder {
    private ArrayList<MtType> types;
    private ArrayList<Integer> arrayLengths;
    private ArrayList<MtType> arrayTypes;

    public MtLayoutBuilder() {
        types = new ArrayList<>();
        arrayLengths = new ArrayList<>();
        arrayTypes = new ArrayList<>();
    }

    public MtLayoutBuilder addColumn(MtType type) {
        if (type == null)
            throw new NullPointerException("Type of column can't be null");
        if (type == MtType.STRING)
            throw new IllegalArgumentException("A column of type String must only be added with " +
                    "addString(int maxSize)");
        if (type == MtType.ARRAY)
            throw new IllegalArgumentException("A column of type Array must only be added with " +
                    "addArray(MtType type, int size)");
        types.add(type);
        return this;
    }

    public MtLayoutBuilder addArray(MtType type, int size) {
        if (type == null)
            throw new NullPointerException("Type of array can't be null");
        if (size <= 0)
            throw new IllegalArgumentException("Size must be greater then 0. Got " + size);
        if (type == MtType.STRING || type == MtType.ARRAY)
            throw new IllegalArgumentException("Type of the array cant be another array.");
        types.add(MtType.ARRAY);
        arrayTypes.add(type);
        arrayLengths.add(size);
        return this;
    }

    public MtLayoutBuilder addString(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("Size must be greater then 0. Got " + maxSize);
        types.add(MtType.STRING);
        arrayLengths.add(maxSize);
        return this;
    }

    public MtLayout build() {
        return new MtLayout(types.toArray(MtType[]::new), arrayLengths.stream().mapToInt(i -> i).toArray(), arrayTypes.toArray(MtType[]::new));
    }
}
