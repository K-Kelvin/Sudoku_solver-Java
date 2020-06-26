import java.util.*;


class Board{
    static int[][] board1 = {{1,0,0,4},
                             {2,0,3,0},
                             {0,2,1,0},
                             {3,0,4,0}};

    static int[][] board2 = {{0,0,0,0},
                             {0,0,0,0},
                             {0,0,0,0},
                             {0,0,0,0}};

    static int[][] board3 = {{0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0},};

    static int[][] board;
    public Board(){
        this(3);
    }
    public Board(int board){
        switch (board){
            case 1:
                this.board = board1;
                break;
            case 2:
                this.board = board2;
                break;
            case 3:
                this.board = board3;
                break;
            default:
                this.board = board1;
                break;
        }
    }
    public Board(int[][] board){ // constructor for setting custom board
        this.board = board;
    }

    public void printSolution(){
        solve();
        printBoard();
    }

    public static boolean solve(){
        int row, col;
        ArrayList<Integer> pos = findEmpty();
        if (pos.isEmpty()){ // no empty space found
            return true;
        }else {
            row = pos.get(0);
            col = pos.get(1);
        }

        // input numbers if valid
        for (int num=1; num<=board.length; num++){
            if (isValid(num,pos)){
                board[row][col] = num;
                if (solve()){
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isValid(int num, ArrayList<Integer> pos){
        int row = pos.get(0);
        int col = pos.get(1);
        // check entire row
        for (int j=0; j<board.length; j++){
            if (board[row][j] == num && col != j){
                return false;
            }
        }
        // check entire column
        for (int i=0; i<board.length; i++){
            if (board[i][col] == num && row != i){
                return false;
            }
        }
        // check box
        int n = (board.length==4) ? 2:3 ;
        int box_x = col/n;
        int box_y = row/n;
        for (int i=box_y*n; i<box_y*n+n; i++){
            for (int j=box_x*n; j<box_x*n+n; j++){
                if (board[i][j] == num && row != i && col != j){return false;}
            }
        }
        return true;
    }

    public static ArrayList<Integer> findEmpty(){
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board.length; j++){
                if (board[i][j] == 0){
                    pos.add(i); pos.add(j);
                    return pos;
                }
            }
        }
        return pos;
    }

    public static void printBoard(){
        System.out.println();
        for (int row=0; row<board.length; row++){
            System.out.println(Arrays.toString(board[row]));
        }
    }
}

public class Sudoku{
    public static void main(String[] args){
        Board bd = new Board(1);
        bd.printBoard();
        bd.printSolution();
        //new Board(1).printSolution();
        //new Board(2).printSolution();
        //new Board().printSolution();
    }
}
