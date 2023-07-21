import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {

    @Test
    public void testArrayDequeGold() {
        ArrayDequeSolution<Integer> arrS = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();

        String m = "";
        m = "isEmpty()\n";
        assertEquals(m,true,a.isEmpty());
        int size = a.size();
        m += "size()\n";
        assertEquals(m,0,a.size());

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

        size = a.size();
        m += "size()\n";
        assertEquals(m, 10, a.size());
        Integer actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
        assertEquals(2,a.size());
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeFirst(), actual);
        actual = a.removeFirst();
        m += "removeFirst()\n";
        assertEquals(m, arrS.removeLast(), actual);
    }



}
