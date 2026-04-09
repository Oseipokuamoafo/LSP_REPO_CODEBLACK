package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers backed by an ArrayList.
 * A set cannot contain duplicate values. Supports standard set operations
 * including union, intersection, difference, and complement.
 */
public class IntegerSet {

    /** Internal storage for set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /** Constructs an empty IntegerSet. */
    public IntegerSet() {}

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set contains exactly the same elements as {@code b},
     * regardless of order.
     *
     * @param b the IntegerSet to compare with
     * @return true if both sets contain the same elements
     */
    public boolean equals(IntegerSet b) {
        if (set.size() != b.set.size()) {
            return false;
        }
        ArrayList<Integer> copy = new ArrayList<>(set);
        Collections.sort(copy);
        ArrayList<Integer> other = new ArrayList<>(b.set);
        Collections.sort(other);
        return copy.equals(other);
    }

    /**
     * Returns true if this set contains the specified value.
     *
     * @param value the integer to search for
     * @return true if the value is in the set
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in this set.
     *
     * @return the largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in this set.
     *
     * @return the smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds the specified item to the set if it is not already present.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the specified item from the set if it is present.
     * Does nothing if the item is not in the set.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet containing all elements that appear in either
     * this set or {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new set that is the union of this set and {@code intSetb}
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        for (int val : intSetb.set) {
            if (!result.set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing only elements common to both
     * this set and {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new set that is the intersection of this set and {@code intSetb}
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int val : set) {
            if (intSetb.set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in this set but not in
     * {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new set representing this set minus {@code intSetb}
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int val : set) {
            if (!intSetb.set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in {@code intSetb} but not
     * in this set.
     *
     * @param intSetb the other IntegerSet
     * @return a new set representing {@code intSetb} minus this set
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int val : intSetb.set) {
            if (!set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of this set in ascending order.
     * Format: [1, 2, 3] or [] for an empty set.
     *
     * @return a string representation of the set
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sorted.size(); i++) {
            sb.append(sorted.get(i));
            if (i < sorted.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
