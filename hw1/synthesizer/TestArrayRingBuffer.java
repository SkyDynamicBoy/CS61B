package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(5);
        assertEquals(5, arb.capacity);
        assertTrue(arb.isEmpty());
        arb.enqueue("1");
        assertFalse(arb.isEmpty());
        assertEquals("1", arb.dequeue());
        arb.enqueue("1");
        arb.enqueue("second");
        assertEquals(2, arb.fillCount);
        arb.enqueue("third");
        arb.enqueue("fourth");
        arb.enqueue("fifth");
        assertEquals("1", arb.peek());
        assertEquals(5, arb.fillCount);
        assertTrue(arb.isFull());


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
//        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
        ArrayRingBuffer<String> arb = new ArrayRingBuffer(5);
        arb.enqueue("1");
        arb.enqueue("second");
        arb.enqueue("third");
        arb.enqueue("fourth");
        arb.enqueue("fifth");
        Iterator it = arb.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
} 
