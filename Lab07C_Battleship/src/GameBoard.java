import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private int rows, cols;
    private Cell[][] cells;
    private ArrayList<Ship> ships;
    private Random random;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        this.ships = new ArrayList<>();
        this.random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
    public Cell getCell(int i, int j){
        return cells[i][j];
    }


    public void placeShipsRandomly() {
        ships.clear();
        int[] shipSizes = {5, 4, 3, 3, 2};
        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int i = random.nextInt(rows);
                int j = random.nextInt(cols);

                if(canPlaceShip(i, j, size, horizontal)){
                    Ship ship = new Ship(size);
                    for (int k = 0; k < size; k++){
                        int row = horizontal ? i : i + k;
                        int col = horizontal ? j+k : j;
                        cells[row][col].setShip(ship);
                        ship.addCell(cells[row][col]);

                    }
                    ships.add(ship);
                    placed = true;
                }
            }
        }

    }
    private boolean canPlaceShip(int i, int j, int size, boolean horizontal) {
        if (horizontal && j + size > cols) return false;
        if (!horizontal && i + size > rows) return false;

        for (int k =0; k < size; k++){
            int i1 = horizontal ? i : i +k;
            int j1 = horizontal ? j+k : j;
            if(cells[i1][j1].hasShip()) return false;
        }
        return true;
    }
    public boolean fireAt(int i, int j){
        Cell cell = cells[i][j];
        cell.setFiredUpon(true);
        return cell.hasShip();
    }
    public boolean isShipSunk(int i, int j){
        Cell cell = cells[i][j];
        Ship ship = cell.getShip();
        return ship !=null && ship.isSunk();
    }
    public boolean allShipsSunk(){
        for (Ship ship: ships){
            if(!ship.isSunk())
                return false;
        }
        return true;
    }
}
