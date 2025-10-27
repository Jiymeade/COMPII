public class TTTBoard {
    private String[][] tiles;
    public static final int size = 3;

    public TTTBoard() {
        tiles = new String[size][size];
        clear();
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = "";
            }
        }
    }

    public boolean isEmpty(int row, int col) {
        return tiles[row][col].isEmpty();
    }

    public void setTiles(int row, int col, String symbol) {
        tiles[row][col] = symbol;
    }

    public String getTile(int row, int col) {
        return tiles[row][col];
    }

    public boolean checkWin(String symbol) {
        for (int i = 0; i < size; i++) {
            if (tiles[i][0].equals(symbol) &&
                    tiles[i][1].equals(symbol) &&
                    tiles[i][2].equals(symbol)) {
                return true;
            }
        }
        for (int j = 0; j < size; j++) {
            if (tiles[0][j].equals(symbol)
                    && tiles[1][j].equals(symbol)
                    && tiles[2][j].equals(symbol)) {
                return true;
            }
            if (tiles[0][0].equals(symbol) &&
                    tiles[1][1].equals(symbol) &&
                    tiles[2][2].equals(symbol)) {
                return true;
            }
            if (tiles[0][2].equals(symbol)
                    && tiles[1][1].equals(symbol)
                    && tiles[2][0].equals(symbol))
            {
                return true;
            }

        }
        return false;

    }
    public boolean checkTie () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


}

