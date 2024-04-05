

public class Main {
    public static void main(String[] args) {
        Graf gr = new Graf();
        gr.addPoint(1);
        gr.addPoint(2);
        gr.addPoint(3);
        gr.addPoint(5);
        gr.addWay(1, 2, 12);
        gr.addWay(1, 3, 13);
        gr.addWay(1, 5, 14);
        gr.addWay(1, 2, 13);
        gr.addWay(2, 5, 13);
        gr.getWays(1);
        gr.getWays(5);
        gr.getPoints();
        gr.deletePoint(2);
        gr.addWay(2, 5, 13);
        gr.getWays(1);
        gr.getWays(5);
        gr.getPoints();
    }
}

//very stupid comment