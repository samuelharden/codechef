import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.LinkedList;

public class Solution {
/* Head ends here */
static void tracePath(int a,int b,int c,int d) {
    if ( (backtracknode[c][d][0] == a) && (backtracknode[c][d][1] == b)) {
        return;
    }
    list.addFirst(backtracknode[c][d][0]+" "+backtracknode[c][d][1]);
  tracePath(a,b,backtracknode[c][d][0],backtracknode[c][d][1]);
}

static int dfs(int r, int c, int pacman_r, int pacman_c, int food_r, int food_c, String [][] grid,int distance){
      exploredlist.addFirst(pacman_r+" "+pacman_c);

        //Your logic here
//                    System.out.println("DEBUG("+(pacman_r)+","+(pacman_c)+")"+""+"ToArray: "+distance);
        if (pacman_r < r && pacman_c < c) {
            if ( grid[pacman_r][pacman_c].equals(".")  ) {
                    return 1;
            }
        if (pacman_r-1 > 0 && pacman_c  <c) {
            int i = pacman_r-1;
            int j = pacman_c;    
            executelogic(i,j,pacman_r,pacman_c,distance,grid);
        }
        if (pacman_r < r && pacman_c-1 > 0) {
            int i = pacman_r;
            int j = pacman_c-1;    
            executelogic(i,j,pacman_r,pacman_c,distance,grid);
        }
        if ( (pacman_r < r) && (pacman_c+1 < c)) {
            int i = pacman_r;
            int j = pacman_c+1;    
            executelogic(i,j,pacman_r,pacman_c,distance,grid);
        }
        if (pacman_r+1 < r && pacman_c < c) {
            int i = pacman_r+1;
            int j = pacman_c;    
            executelogic(i,j,pacman_r,pacman_c,distance,grid);
        }
        }
        try {
            lifonode li = getFromLifo();
            return dfs(r,c,li.getX(),li.getY(),food_r,food_c,grid,li.getDistance()+1);
        }
        catch(Exception e) {
            System.out.println("Exception" + e);
            return 0;
        }
        

 }
private static void executelogic(int i,int j,int pacman_r,int pacman_c, int distance,String [][] grid) {
            if ((grid[i][j].equals("-") || grid[i][j].equals(".") )  && nodedetails[i][j].getDistance() == -100)  {
                lifonode li = nodedetails[i][j];
                li.setDistance(distance);
                li.setPX(pacman_r);
                li.setPY(pacman_c);
                backtracknode[li.getX()][li.getY()][0] = li.getPX();
                backtracknode[li.getX()][li.getY()][1] = li.getPY();
//                System.out.println("puttinh i j Dis"+i+ " "+j+" "+distance);
                putInLifo(nodedetails[i][j]);
            }

}
public static OrderedPointSet lifo;

static void putInLifo(lifonode li) {
    lifo.add(li);
}

static lifonode getFromLifo() {
    return lifo.getnode();
}

static int backtracknode[][][];
static lifonode nodedetails[][];
static LinkedList<String> list;
static LinkedList<String> exploredlist;
/* Tail starts here */
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int pacman_r = in.nextInt();
        int pacman_c = in.nextInt();

        int food_r = in.nextInt();
        int food_c = in.nextInt();

        int r = in.nextInt();
        int c = in.nextInt();
    //        System.out.println(" "+pacman_r+" "+pacman_c+" "+food_r+" "+food_c);
        String grid[][] = new String[r][c];
        backtracknode = new int[r+1][c+2][2];
        nodedetails = new lifonode[r+1][c+1];
       lifo = new OrderedPointSet(); 

        for(int i = 0; i < r; i++) {
            
            String text = in.next();
            String abc[] = text.split("") ;
  
          for(int j = 0 ;j < c ; j++) {
            backtracknode[i][j][0] = -1;
            nodedetails[i][j] = new lifonode(i,j);
            nodedetails[i][j].setDistance(-100);
            nodedetails[i][j].setExpanded(-100);
            grid[i][j] = abc[j+1];
//            System.out.print(grid[i][j]);
            }
  //          System.out.println("");
            
          } 
        backtracknode[pacman_r][pacman_c][0] = pacman_r;
        backtracknode[pacman_r][pacman_c][1] = pacman_c;
        
        exploredlist =  new LinkedList<String>();

        dfs( r, c, pacman_r, pacman_c, food_r, food_c, grid,0);
        list =  new LinkedList<String>();
        //System.out.println(exploredlist.size());
        //while (exploredlist.size() > 0) {
          //  System.out.println(exploredlist.removeLast());
        //}
        list.addFirst(food_r+" "+food_c);
        tracePath(pacman_r,pacman_c,food_r,food_c);
        list.addFirst(pacman_r+" "+pacman_c);
        System.out.println(list.size()-1);
        while (list.size() > 0) {
            System.out.println(list.removeFirst());
        }
    }
}

class lifonode {
    int x;
    int y;
    int expanded;
    int distance;
    int px;
    int py;
    lifonode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }
    
    int getPX() {
        return px;
    }

    int getY() {
        return y;
    }

    int getPY() {
        return py;
    }

    void setX( int x) {
        this.x = x;
    }

    void setY (int y) {
        this.y = y;
    }
    
    void setPX( int x) {
        this.px = x;
    }

    void setPY (int y) {
        this.py = y;
    }

    void setDistance(int dis) {
        this.distance = dis;
    }

    int getDistance() {
        return distance;
    }
    void setExpanded(int dis) {
        this.expanded = dis;
    }

    int getExpanded() {
        return expanded;
    }

}
class SortedArrayList<T> extends ArrayList<T> {

    @SuppressWarnings("unchecked")
    public void insertSorted(T value) {
        add(value);
        Comparable<T> cmp = (Comparable<T>) value;
        for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
            Collections.swap(this, i, i-1);
    }
}

class OrderedPointSet {
    private ArrayList<lifonode> points;
    OrderedPointSet() {
        points= new ArrayList<lifonode>();
    }
    public void add(lifonode p){
        points.add(p);
        int cmp = p.getDistance();
        for (int i = points.size()-1; i > 0 && (cmp < (points.get(i-1).getDistance())) ; i--)
            Collections.swap(points, i, i-1);

    }
    public lifonode getnode() {
//        System.out.println("Size of points :"+points.size());
        lifonode l =  points.get(0);
        points.remove(l);
        return l;
    }
}
