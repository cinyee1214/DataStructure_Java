package Lecture;

public class Blobfinder {
    private int nRows;
    private int nCols;
    private int[][] grid;

    public Blobfinder(int x, int y){
        nRows = x;
        nCols = y;
        grid = new int[nRows][nCols];
        for (int r = 0; r < nRows; ++r){
            for (int c = 0; c < nCols; ++c){
                grid[r][c] = 1;
            }
        }
        grid[4][4] = 0;
        grid[4][5] = 0;
        grid[4][3] = 0;
        grid[5][4] = 0;
        grid[5][6] = 0;
        grid[3][3] = 0;
        grid[3][2] = 0;
    }

    public void printGrid(){
        for (int r = 0; r < nRows; ++r){
            for (int c = 0; c < nCols; ++c){
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    public int countCells(int r, int c){
        if (r < 0 || r >= nRows || c < 0 || c >= nCols) {
            return 0;
        }else if (grid[r][c] != 0){
            return 0;
        }else{
            grid[r][c] = 2;
            return 1 + countCells(r - 1, c - 1) + countCells(r - 1, c) +countCells(r - 1, c + 1)
                    + countCells(r , c - 1) + countCells(r , c + 1)
                    + countCells(r + 1, c - 1) + countCells(r + 1, c) + countCells(r + 1, c + 1);
        }
    }

    public static void main(String[] args){
        Blobfinder b = new Blobfinder(10, 10);
        System.out.print(b.countCells(4,5));
    }


}
