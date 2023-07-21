import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {

    @Test
    public void testArrayDequeGold() {
        ArrayDequeSolution<Integer> arrS = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();

        String m = "";

        for (int i = 0; i < 1000; i++) {
            if (arrS.isEmpty()) {
                Integer addnum = StdRandom.uniform(100);
                arrS.addFirst(addnum);
                a.addFirst(addnum);
                m += "addFirst(" + addnum + ")\n";
            }
            else {
                Integer nextOp = StdRandom.uniform(4);
                Integer expected = -1;
                Integer actual = -1;
                switch (nextOp) {
                    case 0: {
                        Integer addnum = StdRandom.uniform(100);
                        arrS.addFirst(addnum);
                        a.addFirst(addnum);
                        m += "addFirst(" + addnum + ")\n";
                        break;
                    }
                    case 1: {
                        Integer addnum = StdRandom.uniform(100);
                        arrS.addLast(addnum);
                        a.addLast(addnum);
                        m += "addLast(" + addnum + ")\n";
                        break;
                    }
                    case 2: {
                        expected = arrS.removeFirst();
                        actual = a.removeFirst();
                        m += "removeFirst()\n";
                        break;
                    }
                    case 3: {
                        expected = arrS.removeLast();
                        actual = a.removeLast();
                        m += "removeLast()\n";
                        break;
                    }
                    default:
                }
                assertEquals(m, expected, actual);
            }
        }


    }


}
