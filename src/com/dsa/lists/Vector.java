package com.dsa.lists;

public class Vector<T> {
    final double SHRINK_FACTOR = 1.5;
    final double EXPANSION_FACTOR = 2.0;

    Object[] data;
    int size;
    int internalSize;

    public Vector() {
        size = internalSize = 0;
        data = new Object[size];
    }

    public Vector(T... elements) {
        size = elements.length;
        internalSize = (int)(size * EXPANSION_FACTOR);
        data = new Object[internalSize];
        System.arraycopy(elements, 0, data, 0, size);
    }

    /**
     * Returns the current {@code data} array or a new appropriately
     * sized array depending on the magnitude of {@code deviation}.
     *
     * @param deviation +ve or -ve deviation in the size of array after
     *                  current operation.
     * @return The {@code data} array if no modification is required. A
     * new array with appropriate size, otherwise.
     */
    private Object[] getAppropriateArray(int deviation) {
        int newSize = size + deviation;
        Object[] appropriate = data;
        if (newSize >= internalSize) {
            internalSize = (int) (newSize * EXPANSION_FACTOR);
            appropriate = new Object[internalSize];
        } else if (newSize < (internalSize / 2)) {
            internalSize = (int) (newSize * SHRINK_FACTOR);
            appropriate = new Object[internalSize];
        }
        return appropriate;
    }

    /**
     * Copies full array.
     *
     * @param source the array to copy.
     * @param dest where {@code source} needs to be copied.
     */
    private void copyArray(Object[] source, Object[] dest) {
        if (source.length > dest.length)
            throw new IllegalArgumentException("source array is larger than destination array!");
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    /**
     * Checks the index and throws if it is out of its bounds.
     *
     * @param index index to checked for validity.
     * @param isSizeValidIndex flag to determine whether {@code size} must
     *                         be considered a valid index. For read queries,
     *                         it should be set to false.
     * @throws IndexOutOfBoundsException If index is out of bounds for current operation.
     */
    private void checkIndex(int index, boolean isSizeValidIndex) throws IndexOutOfBoundsException {
        if (index < 0 || (isSizeValidIndex ? index > size : index >= size))
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds!");
    }

    /**
     * Helper to determine whether the current {@code data} array needs to
     * be replaced by a new array.
     *
     * @param appropriateArray the possible candidate for replacing current array.
     * @return {@code true} if current needs to be replaced, {@code false} otherwise.
     */
    private boolean shouldReplaceInternalArray(Object[] appropriateArray) {
        return appropriateArray != data;
    }

    /**
     * Get the element at {@code index}
     *
     * @param index the {@code index} of the element.
     * @return element at {@code index}
     * @throws IndexOutOfBoundsException if {@code index} is not in [0, size)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, false);
        return (T) data[index];
    }

    /**
     * Appends an element to the end of the vector.
     *
     * @param element the element to be appended
     */
    public void add(T element) {
        Object[] appropriateArray = getAppropriateArray(1);
        if (shouldReplaceInternalArray(appropriateArray)) {
            copyArray(data, appropriateArray);
            data = appropriateArray;
        }
        data[size++] = element;
    }

    /**
     * Appends all the elements passed in at the end of the vector in the
     * order they appear in the array.
     *
     * @param elements elements as parameters or an array of elements
     */
    public void addAll(T... elements) {
        Object[] appropriateArray = getAppropriateArray(elements.length);
        if (shouldReplaceInternalArray(appropriateArray)) {
            copyArray(data, appropriateArray);
            data = appropriateArray;
        }
        for (T element : elements)
            data[size++] = element;
    }

    /**
     * Inserts {@code elements} starting from {@code at} in the vector.
     *
     * @param at the index where insertion should begin
     * @param elements elements as parameters or an array of elements
     * @throws IndexOutOfBoundsException if {@code at} is beyond [0, size]
     */
    public void insert(int at, T... elements) throws IndexOutOfBoundsException {
        checkIndex(at, true);
        Object[] appropriateArray = getAppropriateArray(elements.length);
        System.arraycopy(data, at, appropriateArray, elements.length + at, (size - at));
        System.arraycopy(elements, 0, appropriateArray, at, elements.length);

        if (shouldReplaceInternalArray(appropriateArray)) {
            System.arraycopy(data, 0, appropriateArray, 0, at);
            data = appropriateArray;
        }

        size += elements.length;
    }

    /**
     * Removes all occurrences of {@code value} from the vector.
     *
     * @param value value to delete from the vector
     */
    public void remove(T value) {
        int removed = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value))
                removed++;
            else {
                int newIndex = i - removed;
                data[newIndex] = data[i];
            }
        }

        Object[] appropriateArray = getAppropriateArray(-removed);
        if (shouldReplaceInternalArray(appropriateArray)) {
            System.arraycopy(data, 0, appropriateArray, 0, size - removed);
            data = appropriateArray;
        }
        size -= removed;
    }

    /**
     * Deleted element at a specific index.
     *
     * @param index index of the element to be removed
     * @return the deleted element
     */
    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkIndex(index, false);
        T removed = (T) data[index];
        Object[] appropriateArray = getAppropriateArray(-1);
        System.arraycopy(data, 0, appropriateArray, 0, index);
        System.arraycopy(data, index + 1, appropriateArray, index, size - (index + 1));
        if (shouldReplaceInternalArray(appropriateArray))
            data = appropriateArray;
        size -= 1;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        for (Object o : data)
            if (o.equals(value))
                return true;

        return false;
    }

    public void clear() {
        size = internalSize = 0;
        data = new Object[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
