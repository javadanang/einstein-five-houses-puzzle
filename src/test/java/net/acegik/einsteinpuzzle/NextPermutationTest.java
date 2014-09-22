package net.acegik.einsteinpuzzle;

import static org.junit.Assert.*;
import org.junit.Test;

public class NextPermutationTest {

    @Test
    public void test_single_item_array() {
        int[] a = {0};
        assertFalse(Main.genNextPermutation(a));
    }

    @Test
    public void test_two_items_array() {
        int[] a = {0, 1};

        assertTrue(Main.genNextPermutation(a));
        assertArrayEquals(new int[] {1, 0}, a);

        assertFalse(Main.genNextPermutation(a));
    }

    @Test
    public void test_four_items_array_verify_next_permutation() {
        int[] a = {0, 1, 2, 3};

        assertTrue(Main.genNextPermutation(a));
        assertArrayEquals(new int[] {0, 1, 3, 2}, a);

        assertTrue(Main.genNextPermutation(a));
        assertArrayEquals(new int[] {0, 2, 1, 3}, a);
    }

    @Test
    public void test_four_items_array_with_no_more_permutation() {
        int[] a = {3, 2, 1, 0};
        assertFalse(Main.genNextPermutation(a));
    }
}
