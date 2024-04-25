import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrafTests {
    Graf gr;

    @BeforeEach
    void testSetup(){
        gr = new Graf();
        gr.addPoint(1);
        gr.addPoint(2);
        gr.addPoint(3);
        gr.addPoint(4);
        gr.addPoint(5);
        gr.addPoint(6);

        gr.addWay(1, 2, 2);
        gr.addWay(1, 3, 3);
        gr.addWay(1, 6, 99);
        gr.addWay(2, 3, 3);
        gr.addWay(2, 4, 3);
        gr.addWay(5, 4, 1);
        gr.addWay(5, 3, 6);
        gr.addWay(5, 2, 1);
    }
    @Test
    void shouldCheckColors(){
        int expected = 2;
        int actual = gr.minColors();
        Assertions.assertEquals(expected,actual);
    }
}
