import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrafTests {
    Graf graf;

    @BeforeEach
    void testSetup(){
        this.graf = new Graf();
        this.graf.addPoint(1);
        this.graf.addPoint(2);
        this.graf.addPoint(3);
        this.graf.addPoint(4);
        this.graf.addPoint(5);
        this.graf.addPoint(6);
        this.graf.addWay(1, 2, 2);
        this.graf.addWay(1, 3, 3);
        this.graf.addWay(1, 6, 99);
        this.graf.addWay(2, 3, 3);
        this.graf.addWay(2, 4, 3);
        this.graf.addWay(5, 4, 1);
        this.graf.addWay(5, 3, 6);
        this.graf.addWay(5, 2, 1);
    }
    @Test
    void shouldCheckColors(){
        int expected = 2;
        int actual = graf.minColors();
        Assertions.assertEquals(expected,actual);
    }
}
