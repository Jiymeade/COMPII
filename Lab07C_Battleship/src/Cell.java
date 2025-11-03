public class Cell {
    private int i, j;
    private Ship ship;
    private boolean firedUpon;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        this.firedUpon = false;
    }
    public boolean hasShip(){
        return this.ship != null;
    }
    public void setShip(Ship ship){
        this.ship = ship;
    }
    public boolean isFiredUpon(){
       return firedUpon;
    }
    public void setFiredUpon(boolean firedUpon){
        this.firedUpon = firedUpon;
        if(firedUpon && ship != null){
            ship.hit();
        }
    }
    public Ship getShip(){
        return ship;
    }

}
