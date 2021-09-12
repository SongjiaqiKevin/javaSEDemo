package entity;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Kevin Song
 * Created on 2021/7/10.
 */
public class Main {

    public static void main(String[] args){
        
        /*int[][] a= {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(getDay(a));*/
        CompletionStage<Integer> stage = new CompletableFuture();
        //stage.thenApply(x->square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println());

        CompletableFuture.supplyAsync(()->1).thenApply(i->{i=i+1;System.out.println(i);return i;}).whenComplete((r,e)->{System.out.println(r);});
    }

    public static int square(int x){
        return x*x;
    }

    public static int getDay(int[][] area){
        if(!check(area)){
            return -1;
        }
        int day=0;
        while (getZero(area)){
            day++;
            doOperate(area);
        }
        return day;
    }

    public static boolean check(int[][] area){
        for(int i=0;i<area.length;i++){
            for(int v=0;v<area[i].length;v++){
                if(area[i][v]==1){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean getZero(int[][] area){
        for(int i=0;i<area.length;i++){
            for(int v=0;v<area[i].length;v++){
                if(area[i][v]==0){
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] doOperate(int[][] area){
        List<Location> locations = new ArrayList<>();
        for(int i=0;i<area.length;i++){
            for(int v= 0;v<area[i].length;v++){
                if(area[i][v]==0){
                    continue;
                }
                if(i>0){
                    if(area[i-1][v]==0){
                        Location l = new Location();
                        l.setRow(i-1);
                        l.setCol(v);
                        locations.add(l);
                    }
                }
                if(i<area.length-1){
                    if(area[i+1][v]==0){
                        Location l = new Location();
                        l.setRow(i+1);
                        l.setCol(v);
                        locations.add(l);
                    }
                }
                if(v>0){
                    if(area[i][v-1]==0){
                        Location l = new Location();
                        l.setRow(i);
                        l.setCol(v-1);
                        locations.add(l);
                    }
                }
                if(v<area[i].length-1){
                    if(area[i][v+1]==0){
                        Location l = new Location();
                        l.setRow(i);
                        l.setCol(v+1);
                        locations.add(l);
                    }
                }
            }
        }
        locations.forEach(p->{
            area[p.getRow()][p.getCol()]=1;
        });
        return area;
    }

    public static class Location{
        private int row;
        private int col;

        public void setRow(int row){
            this.row=row;
        }

        public int getRow(){
            return row;
        }

        public void setCol(int col){
            this.col=col;
        }

        public int getCol(){
            return col;
        }
    }
}
