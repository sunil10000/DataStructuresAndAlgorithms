import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*        0                         0,0
*  1   2  3  4   5       1,1   1,2  1,3  1,4  1,5
*  6   7  8  9  10       2,1   2,2  2,3  2,4  2,5
*  11 12 13 14  15       3,1   3,2  3,3  3,4  3,5
*  16 17 18 19  20       4,1   4,2  4,3  4,4  4,5
*  21 22 23 24  25       5,1   5,2  5,3  5,4  5,5
*        26                         6,6
* */

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF gridWithoutBottom;
    private int[] tags; //0 means close and 1 means open
    private final int gridLen;
    private int noOfOpenSites;

    public Percolation(int n) {          // create gridLen by gridLen grid, with all sites blocked
        if (n <= 0) throw new java.lang.IllegalArgumentException("n is less than or equal to 0");


        grid = new WeightedQuickUnionUF(2+(n*n));
       gridWithoutBottom = new WeightedQuickUnionUF(1+(n*n));
       this.gridLen = n;
       this.tags = new int[2+n*n];
       for (int i = 1; i < (n*n)+1; i++) {
           this.tags[i] = 0;
       }
       this.tags[0] = 1;
       this.tags[1+(n*n)] = 1;
    }

    private int twodTo1D(int row, int col) {
        if (row == 0) {
            return 0;
        }
        else if (row == gridLen +1) {
            return 1+(gridLen * gridLen);
        }
        else {
            return (row-1)* gridLen +col;
        }
    }


    private int[] neighbours(int i) {   // order is north south east west
        int[] a = new int[4];
        if ((1 <= i) && (i <= this.gridLen)) {
            a[0] = 0;
        }
        else {
            a[0] = i- gridLen;
        }
        if ((gridLen *(gridLen -1) < i) && (i <= gridLen * gridLen)) {
            a[1] = 1 + (gridLen * gridLen);
        }
        else {
            a[1] = i+ gridLen;
        }
        int mod = i % gridLen;
        if (mod == 1) {
            a[2] = -1;
        }
        else {
            a[2] = i-1;
        }
        if (mod == 0) {
            a[3] = -1;
        }
        else {
            a[3] = i+1;
        }
        return a;
    }

    private void check(int row, int col) {
        if (row < 1 || row > gridLen || col < 1 || col > gridLen) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        check(row, col);
        int n = twodTo1D(row, col);
        if (this.tags[n] == 0) {
            this.tags[n] = 1;
            int[] a = neighbours(n);
            for (int i : a) {
                if ((i != -1) && (tags[i] == 1)) {
                    grid.union(n, i);
                    if (i != 1+(gridLen * gridLen)) {
                        gridWithoutBottom.union(n, i);
                    }
                }
            }
            noOfOpenSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        check(row, col);
        int n = twodTo1D(row, col);
        return tags[n]==1;
    }

    public boolean isFull(int row, int col) {
        check(row, col);
        int n = twodTo1D(row, col);
        return gridWithoutBottom.connected(0, n);
    }
    public  int numberOfOpenSites() {
        return noOfOpenSites;
    }

    public boolean percolates() {
        return grid.connected(0, 1+(gridLen * gridLen));
    }

}
