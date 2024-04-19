import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Graf {
    int curId;
    int lastId;
    ArrayList<Punkt> punkty = new ArrayList<Punkt>();
    ArrayList<Droga> drogi = new ArrayList<Droga>();
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
            drogi.add(dr);
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



    int shortestWay(int pkt1, int pkt2){
        ArrayList<Punkt> pnt = new ArrayList<Punkt>();
        for(int i = 0; i < punkty.size(); i++){
            punkty.get(i).sumWeights = 999999;
            pnt.add(punkty.get(i));
        }
        punkty.get(getIndPoint(pkt1)).fromWhatPoint = null;
        punkty.get(getIndPoint(pkt1)).sumWeights = 0;
        while(!pnt.isEmpty()){
            int indSmalPoint = 99999;
            int indSmal = 0;
            for(int i = 0; i < pnt.size(); i++){
                if(pnt.get(i).sumWeights < indSmalPoint){
                    indSmalPoint = pnt.get(i).sumWeights;
                    indSmal = i;
                }
            }
            for(int j = 0; j < pnt.get(indSmal).drogi.size(); j++){
                if(pnt.get(indSmal).drogi.get(j).point1.pointId == pnt.get(indSmal).pointId){
                    int curWeight = pnt.get(indSmal).sumWeights + pnt.get(indSmal).drogi.get(j).weight;
                    if(pnt.get(indSmal).drogi.get(j).point2.sumWeights > curWeight){
                        pnt.get(indSmal).drogi.get(j).point2.sumWeights = curWeight;
                    }
                    System.out.println(pnt.get(indSmal).pointId+" "+curWeight);
                }
                else{
                    int curWeight = pnt.get(indSmal).sumWeights + pnt.get(indSmal).drogi.get(j).weight;
                    if(pnt.get(indSmal).drogi.get(j).point1.sumWeights > curWeight){
                        pnt.get(indSmal).drogi.get(j).point1.sumWeights = curWeight;
                    }
                    System.out.println(pnt.get(indSmal).pointId+" "+curWeight);
                }
            }
            pnt.remove(indSmal);
            for(int i = 0; i < punkty.size(); i++){
                System.out.print(punkty.get(i).sumWeights+" ");
            }
            System.out.println("");
        }
        for(int i = 0; i < punkty.size(); i++){
            System.out.println(punkty.get(i).sumWeights);
        }
        System.out.println("Najkrótsza droga od "+pkt1+" do "+pkt2+" = "+punkty.get(getIndPoint(pkt2)).sumWeights);
        return punkty.get(getIndPoint(pkt2)).sumWeights;
    }

    int indexGroup (ArrayList<ArrayList<Integer>> listOfLists, int goal){
        for(int i = 0; i < listOfLists.size(); i++){
            if(listOfLists.get(i).contains(goal)){
                return i;
            }
        }
        return 0;
    }

    int MSTreeKruskala(){
        ArrayList<Droga> drg = new ArrayList();
        ArrayList<Droga> drgTemp = new ArrayList<Droga>();
        drgTemp = drogi;
        int najmn, index = -1;
        while(!drgTemp.isEmpty()){
            najmn = 999999;
            for(int i = 0; i < drgTemp.size(); i++){
                if(drgTemp.get(i).weight < najmn){
                    najmn = drgTemp.get(i).weight;
                    index = i;
                }
            }
            drg.add(drgTemp.get(index));
            drgTemp.remove(index);
        }
        for(int i = 0; i < drg.size(); i++){
            System.out.print(drg.get(i).weight+" ");
        }
        System.out.println("");
        Set<Integer> treePoints = new TreeSet<>();
        Droga curDrg;
        while(treePoints.size() < punkty.size()){
            curDrg = drg.get(0);
            if(!treePoints.contains(curDrg.point1.pointId) || !treePoints.contains(curDrg.point2.pointId)){
                treePoints.add(curDrg.point1.pointId);
                treePoints.add(curDrg.point2.pointId);
                System.out.print(treePoints);
                System.out.println("");
                System.out.println(curDrg.point1.pointId+" "+curDrg.point2.pointId);
            }

            drg.remove(0);
        }
        return 0;
    }
}
