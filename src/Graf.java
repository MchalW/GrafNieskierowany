import java.util.ArrayList;

public class Graf {
    int curId;
    int lastId;
    ArrayList<Punkt> punkty = new ArrayList<Punkt>();
    Graf(){
        curId = 1;
        lastId = 1;
    }

    void addPoint(int pkt){
        int index = getIndPoint(pkt);
        if(index == -1){
            Punkt point = new Punkt();
            point.pointId = pkt;
            System.out.println("Dodano punkt: "+pkt);
            punkty.add(point);
        }
    }

    private int getIndPoint(int pkt){
        for(int p = 0; p < punkty.size(); p++){
            if(pkt == punkty.get(p).pointId){
                return p;
            }
        }
        return -1;
    }

    void addWay(int pkt1, int pkt2, int waga){
        if(!punkty.isEmpty() && pkt1 != pkt2){
            int indexPkt1 = getIndPoint(pkt1);
            int indexPkt2 = getIndPoint(pkt2);
            if(indexPkt1 == -1 || indexPkt2 == -1){
                return;
            }
            for(int p = 0; p < punkty.get(indexPkt1).drogi.size(); p++){
                if(punkty.get(indexPkt1).drogi.get(p).point1.pointId == pkt1 && punkty.get(indexPkt1).drogi.get(p).point2.pointId == pkt2){
                    System.out.println("Droga juz istnieje");
                    return;
                }
                else if(punkty.get(indexPkt1).drogi.get(p).point1.pointId == pkt2 && punkty.get(indexPkt1).drogi.get(p).point2.pointId == pkt1){
                    System.out.println("Droga juz istnieje");
                    return;
                }
            }

            Droga dr = new Droga();
            dr.weight = waga;
            for(int p = 0; p < punkty.size(); p++){
                if(pkt1 == punkty.get(p).pointId){
                    dr.point1 = punkty.get(p);
                    punkty.get(p).drogi.add(dr);
                    break;
                }
            }
            for(int p = 0; p < punkty.size(); p++){
                if(pkt2 == punkty.get(p).pointId){
                    dr.point2 = punkty.get(p);
                    punkty.get(p).drogi.add(dr);
                    break;
                }
            }
            System.out.println("Dodano droge: "+pkt1+" + "+pkt2);
        }
    }

    void getWays(int pkt){
        int index = getIndPoint(pkt);
        if(index == -1){
            return;
        }
        for(int p = 0; p < punkty.size(); p++){
            if(pkt == punkty.get(p).pointId){
                Punkt currentPoint = punkty.get(p);
                for(int u = 0; u < currentPoint.drogi.size(); u++){
                    System.out.print(currentPoint.drogi.get(u).point1.pointId + " ");
                    System.out.print(currentPoint.drogi.get(u).point2.pointId + " ");
                    System.out.println(currentPoint.drogi.get(u).weight);
                }
                break;
            }
        }
        System.out.println();
    }

    void getPoints(){
        for(int p = 0; p < punkty.size(); p++){
            System.out.println(punkty.get(p).pointId);
        }
        System.out.println();
    }

    void deletePoint(int pkt){
        int index = getIndPoint(pkt);
        if(index == -1){
            return;
        }
        for(int p = 0; p < punkty.size(); p++){
            for(int u = 0; u < punkty.get(p).drogi.size(); u++){
                if(punkty.get(p).drogi.get(u).point1.pointId == pkt || punkty.get(p).drogi.get(u).point2.pointId == pkt){
                    punkty.get(p).drogi.remove(u);
                }
            }
        }
        punkty.remove(index);
    }
}
