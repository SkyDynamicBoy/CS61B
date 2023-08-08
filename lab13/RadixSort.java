import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {

        if (asciis.length == 0 || asciis.length == 1) {
            return asciis;
        }

        int arrLength = 0;
        for (String s : asciis) {
            if (s != null) {
                arrLength++;
            }
        }

        int maxLength = 0;
        for (int i = 0; i < arrLength; i++) {
            int strLength = asciis[i].length();
            maxLength = maxLength > strLength ? maxLength : strLength;
        }

        String[] sorted = asciis;
        for (int p = maxLength - 1; p >= 0; p--) {

            int[] counts = new int[256];
            for (int i = 0; i < arrLength; i++) {
                if (p >= asciis[i].length()) {
                    counts[0]++;
                } else {
                    int ascii = asciis[i].charAt(p);
                    counts[ascii]++;
                }
            }

            int[] starts = new int[256];
            int start = 0;
            for (int i = 0; i < 255; i++) {
                starts[i] = start;
                start += counts[i];
            }

            sorted = new String[asciis.length];
            for (int i = 0; i < arrLength; i++) {
                if (p >= asciis[i].length()) {
                    sorted[starts[0]] = asciis[i];
                    starts[0]++;
                } else {
                    int ascii = asciis[i].charAt(p);
                    sorted[starts[ascii]] = asciis[i];
                    starts[ascii]++;
                }
            }

            asciis = sorted;
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    @Test
    public void test1() {
        String[] unsorted = new String[11];
        unsorted[0] = "b";
        unsorted[1] = "x";
        unsorted[2] = "a";
        unsorted[3] = "c";
        unsorted[4] = "g";
        unsorted[5] = "h";
        unsorted[6] = "m";
        unsorted[7] = "b";
        unsorted[8] = "q";
        unsorted[9] = "b";
        unsorted[10] = "c";
        String[] expected = {"a","b","b","b","c","c","g","h","m","q","x"};

        String[] sorted = sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    public void test2() {
        String[] unsorted = new String[11];
        unsorted[0] = "one";
        unsorted[1] = "two";
        unsorted[2] = "three";
        unsorted[3] = "four";
        unsorted[4] = "five";
        unsorted[5] = "six";
        unsorted[6] = "seven";
        unsorted[7] = "eight";
        unsorted[8] = "nine";
        unsorted[9] = "ten";
        unsorted[10] = "eleven";
        String[] expected = {"eight","eleven","five","four","nine","one","seven","six","ten","three","two"};

        String[] sorted = sort(unsorted);

        assertArrayEquals(expected, sorted);
    }

    @Test
    public void test3() {
        String[] unsorted = new String[12];
        unsorted[0] = "one";
        unsorted[1] = "two";
        unsorted[2] = "three";
        unsorted[3] = "four";
        unsorted[4] = "five";
        unsorted[5] = "six";
        unsorted[6] = "seven";
        unsorted[7] = "eight";
        unsorted[8] = "nine";
        unsorted[9] = "ten";
        unsorted[10] = "eleven";

        assertEquals(12, unsorted.length);;
        String[] expected = {"eight","eleven","five","four","nine","one","seven","six","ten","three","two", null};

        String[] sorted = sort(unsorted);

        assertArrayEquals(expected, sorted);
    }
}
