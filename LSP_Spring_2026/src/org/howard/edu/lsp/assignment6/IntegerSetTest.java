package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for IntegerSet.
 * Each method is tested with at least one normal case and one edge case.
 */
public class IntegerSetTest {

    // -------------------------------------------------------------------------
    // clear()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("clear() – normal: removes all elements from a non-empty set")
    public void testClearNormal() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        s.add(2);
        s.clear();
        assertEquals(0, s.length());
        assertTrue(s.isEmpty());
    }

    @Test
    @DisplayName("clear() – edge: clearing an already-empty set does not throw")
    public void testClearEmpty() {
        IntegerSet s = new IntegerSet();
        assertDoesNotThrow(s::clear);
        assertTrue(s.isEmpty());
    }

    // -------------------------------------------------------------------------
    // length()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("length() – normal: returns correct count for non-empty set")
    public void testLengthNormal() {
        IntegerSet s = new IntegerSet();
        s.add(10);
        s.add(20);
        s.add(30);
        assertEquals(3, s.length());
    }

    @Test
    @DisplayName("length() – edge: empty set has length 0")
    public void testLengthEmpty() {
        IntegerSet s = new IntegerSet();
        assertEquals(0, s.length());
    }

    // -------------------------------------------------------------------------
    // equals()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("equals() – normal: two sets with same elements in same order")
    public void testEqualsNormal() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(1); b.add(2); b.add(3);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("equals() – edge: same elements in different order are equal")
    public void testEqualsDifferentOrder() {
        IntegerSet a = new IntegerSet();
        a.add(3); a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(1); b.add(2); b.add(3);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("equals() – edge: sets with different elements are not equal")
    public void testEqualsMismatch() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(1); b.add(3);
        assertFalse(a.equals(b));
    }

    // -------------------------------------------------------------------------
    // contains()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("contains() – normal: value is present in the set")
    public void testContainsPresent() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        assertTrue(s.contains(5));
    }

    @Test
    @DisplayName("contains() – edge: value is not present in the set")
    public void testContainsAbsent() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        assertFalse(s.contains(99));
    }

    // -------------------------------------------------------------------------
    // largest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("largest() – normal: returns max of multi-element set")
    public void testLargestNormal() {
        IntegerSet s = new IntegerSet();
        s.add(3); s.add(7); s.add(1);
        assertEquals(7, s.largest());
    }

    @Test
    @DisplayName("largest() – edge: single-element set returns that element")
    public void testLargestSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(42);
        assertEquals(42, s.largest());
    }

    @Test
    @DisplayName("largest() – edge: throws IllegalStateException on empty set")
    public void testLargestEmptyThrows() {
        IntegerSet s = new IntegerSet();
        assertThrows(IllegalStateException.class, s::largest);
    }

    // -------------------------------------------------------------------------
    // smallest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("smallest() – normal: returns min of multi-element set")
    public void testSmallestNormal() {
        IntegerSet s = new IntegerSet();
        s.add(3); s.add(7); s.add(1);
        assertEquals(1, s.smallest());
    }

    @Test
    @DisplayName("smallest() – edge: single-element set returns that element")
    public void testSmallestSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(42);
        assertEquals(42, s.smallest());
    }

    @Test
    @DisplayName("smallest() – edge: throws IllegalStateException on empty set")
    public void testSmallestEmptyThrows() {
        IntegerSet s = new IntegerSet();
        assertThrows(IllegalStateException.class, s::smallest);
    }

    // -------------------------------------------------------------------------
    // add()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("add() – normal: adds a new element to the set")
    public void testAddNormal() {
        IntegerSet s = new IntegerSet();
        s.add(10);
        assertTrue(s.contains(10));
        assertEquals(1, s.length());
    }

    @Test
    @DisplayName("add() – edge: duplicate value is not added twice")
    public void testAddDuplicate() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(5);
        assertEquals(1, s.length());
    }

    // -------------------------------------------------------------------------
    // remove()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("remove() – normal: removes an existing element")
    public void testRemoveNormal() {
        IntegerSet s = new IntegerSet();
        s.add(1); s.add(2); s.add(3);
        s.remove(2);
        assertFalse(s.contains(2));
        assertEquals(2, s.length());
    }

    @Test
    @DisplayName("remove() – edge: removing a value not in the set does not throw")
    public void testRemoveNotPresent() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        assertDoesNotThrow(() -> s.remove(99));
        assertEquals(1, s.length());
    }

    // -------------------------------------------------------------------------
    // union()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("union() – normal: combines two overlapping sets without duplicates")
    public void testUnionNormal() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(2); b.add(3); b.add(4);
        IntegerSet result = a.union(b);
        assertEquals("[1, 2, 3, 4]", result.toString());
    }

    @Test
    @DisplayName("union() – edge: union with an empty set returns a copy of original")
    public void testUnionWithEmpty() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet empty = new IntegerSet();
        IntegerSet result = a.union(empty);
        assertEquals("[1, 2]", result.toString());
    }

    @Test
    @DisplayName("union() – edge: original sets are not modified after union")
    public void testUnionOriginalUnchanged() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3);
        a.union(b);
        assertEquals(2, a.length());
        assertEquals(1, b.length());
    }

    // -------------------------------------------------------------------------
    // intersect()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("intersect() – normal: returns common elements of two sets")
    public void testIntersectNormal() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(2); b.add(3); b.add(4);
        IntegerSet result = a.intersect(b);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    @DisplayName("intersect() – edge: no common elements returns empty set")
    public void testIntersectNoOverlap() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3); b.add(4);
        IntegerSet result = a.intersect(b);
        assertTrue(result.isEmpty());
        assertEquals("[]", result.toString());
    }

    // -------------------------------------------------------------------------
    // diff()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("diff() – normal: returns elements in first set but not second")
    public void testDiffNormal() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(2); b.add(3); b.add(4);
        IntegerSet result = a.diff(b);
        assertEquals("[1]", result.toString());
    }

    @Test
    @DisplayName("diff() – edge: diff of identical sets returns empty set")
    public void testDiffIdenticalSets() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(1); b.add(2); b.add(3);
        IntegerSet result = a.diff(b);
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // complement()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("complement() – normal: returns elements in b but not in this")
    public void testComplementNormal() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        IntegerSet b = new IntegerSet();
        b.add(2); b.add(3); b.add(4);
        IntegerSet result = a.complement(b);
        assertEquals("[4]", result.toString());
    }

    @Test
    @DisplayName("complement() – edge: disjoint sets returns all of b")
    public void testComplementDisjoint() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3); b.add(4);
        IntegerSet result = a.complement(b);
        assertEquals("[3, 4]", result.toString());
    }

    // -------------------------------------------------------------------------
    // isEmpty()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() – edge: newly created set is empty")
    public void testIsEmptyTrue() {
        IntegerSet s = new IntegerSet();
        assertTrue(s.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() – normal: set with elements is not empty")
    public void testIsEmptyFalse() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        assertFalse(s.isEmpty());
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("toString() – normal: elements appear in ascending order")
    public void testToStringNormal() {
        IntegerSet s = new IntegerSet();
        s.add(3); s.add(1); s.add(2);
        assertEquals("[1, 2, 3]", s.toString());
    }

    @Test
    @DisplayName("toString() – edge: empty set returns []")
    public void testToStringEmpty() {
        IntegerSet s = new IntegerSet();
        assertEquals("[]", s.toString());
    }
}
