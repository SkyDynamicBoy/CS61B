import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    @Test
    public void testOffByOne() {
        OffByOne t = new OffByOne();
        assertTrue(t.equalChars('a', 'b'));
        assertTrue(t.equalChars('b', 'a'));
        assertTrue(t.equalChars('&', '%'));

        assertFalse(t.equalChars('a', 'a'));
    }
}
