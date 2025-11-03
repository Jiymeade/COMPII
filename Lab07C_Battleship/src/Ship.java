import java.util.ArrayList;

public class Ship {
    private int size;
    private int hits;
    private ArrayList<Cell> cells;
    private boolean sunkNotified;


    public Ship(int size){
        this.size = size;
        this.hits = 0;
        this.cells = new ArrayList<>();
        this.sunkNotified = false;
    }
    public void addCell(Cell cell){
        cells.add(cell);
    }
    public void hit(){
        hits++;
    }
    public boolean isSunk(){
        return hits >= size;
    }
    public boolean isSunkNotified(){
        return sunkNotified;
    }
    public void setSunkNotified(boolean value){
        sunkNotified = value;
    }
    public int getSize(){
        return size;
    }
    public int getHits(){
        return hits;
    }
    public ArrayList<Cell> getCells(){
        return cells;
    }
}
