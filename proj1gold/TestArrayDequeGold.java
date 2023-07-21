import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {

    @Test
    public void testArrayDequeGold() {
        ArrayDequeSolution<Integer> arrS = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();

        String m = "";
        assertEquals(arrS.isEmpty(),a.isEmpty());
        assertEquals(arrS.size(),a.size());

        int i = 0;
        while (i < 10) {
            Integer num1 = StdRandom.uniform(10);
            if(i % 2 == 0) {
                arrS.addLast(num1);
                a.addLast(num1);
                m += String.format("a.addLast(%d)\n",num1);
            } else {
                arrS.addFirst(num1);
                a.addFirst(num1);
                m += String.format("a.addFirst(%d)\n",num1);
            }
            i++;
        }

        assertEquals(arrS.size(),a.size());
        assertEquals(10,a.size());
        Integer actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
        assertEquals(2,a.size());
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += String.format("a.removeFirst():%d\n",actual);
        assertEquals(m, arrS.removeLast(), actual);
    }



}
