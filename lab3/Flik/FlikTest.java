import org.junit.Test;
import static  org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik(){
        assertTrue(Flik.isSameNumber(1,1));
        assertTrue(Flik.isSameNumber(129,129));
        assertTrue(Flik.isSameNumber(128,128));
    }

}
