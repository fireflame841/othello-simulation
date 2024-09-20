import java.io.*;
import java.util.Arrays;
import java.util.*;

// class Node{
//     int[][] bord = new int[8][8];
//     ArrayList<Node> children = new ArrayList<>();
//     int[] move = new int[2];
//     int minimax;
// }

public class Othello {
    int turn;
    int winner;
    int board[][];
    int startturn;
    //add required class variables here

    class Node{
        int[][] bord = new int[8][8];
        ArrayList<Node> children = new ArrayList<>();
        int[] move = new int[2];
        int minimax;
    }

    public Othello(String filename) throws Exception {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        turn = sc.nextInt();
        board = new int[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j){
                board[i][j] = sc.nextInt();
            }
        }
        winner = -1;
        System.out.println(turn);
        startturn = turn;
        
        //Student can choose to add preprocessing here
    }

    //add required helper functions here


    public int boardScore() {
        int black = 0;
        int white = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8 ;j++){
                if (board[i][j] == 0){
                    black++;
                }
                if (board[i][j] == 1){
                    white++;
                }
            }
        }
        if (turn == 0){
            return black-white;
        }
        return white-black;
        /* Complete this function to return num_black_tiles - num_white_tiles if turn = 0, 
         * and num_white_tiles-num_black_tiles otherwise. 
        */
        // return 0;
    }


    private int getboardScore(int[][] currentboard) {
        int black = 0;
        int white = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8 ;j++){
                if (currentboard[i][j] == 0){
                    black++;
                }
                if (currentboard[i][j] == 1){
                    white++;
                }
            }
        }
        if (startturn == 0){
            return black-white;
        }
        return white-black;
        /* Complete this function to return num_black_tiles - num_white_tiles if turn = 0, 
         * and num_white_tiles-num_black_tiles otherwise. 
        */
        // return 0;
    }



    private ArrayList<Node> findmove(int[][] currentboard, int t, int[][] copy){
        int c = 0;
        int yes;
        int no;
        if (turn == 1){
            yes = 0;
            no = 1;
        }
        else{
            yes = 1;
            no = 0;
        }
        ArrayList<Node> result = new ArrayList<>();
        // int[][] copy = new int[8][8];
        // copy = getCopy(currentboard);
        for (int i = 0 ; i<8 ; i++){
            for (int j = 0 ; j<8 ; j++){
                c=0;
                if (currentboard[i][j] == -1){
                    if (j<7 && i<7){
                        if (currentboard[i+1][j+1] == yes){
                            int k = i+1;
                            int l = j+1;
                            int temp = 0;
                            while(true){
                                if (k>7 || l>7){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){ 
                                    currentboard[i][j] = no;
                                    for (int q = i+1; q<k ;q++){
                                        currentboard[q][j+q-i] = no;
                                    }
                                    c = c+temp;
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                k++;
                                l++;
                            }
                        }
                    }
                    if (j<7){
                        if (currentboard[i][j+1] == yes){
                            int k = i;
                            int l = j+1;
                            int temp = 0;
                            while(true){
                                if (k>7 || l>7){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for(int x = j+1; x<l ; x++){
                                        currentboard[i][x] = no;
                                    }
                                    
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                l++;
                            }
                        }
                    }
                    if (i<7){
                        if (currentboard[i+1][j] == yes){
                            int k = i+1;
                            int l = j;
                            int temp = 0;
                            while(true){
                                if (k>7 || l>7){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for(int x = i+1; x<k ; x++){
                                        currentboard[x][j] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                k++;
                            }

                        }
                    }
                    if (i>0 && j>0){
                        if (currentboard[i-1][j-1] == yes){
                            int k = i-1;
                            int l = j-1;
                            int temp = 0;
                            while(true){
                                if (k<0 || l<0){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for (int q = i-1; q>k ;q--){
                                        currentboard[q][j-i+q] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                l--;
                                k--;
                            }
                        }
                    }
                    if (j>0){
                        if (currentboard[i][j-1] == yes){
    
                            int k = i;
                            int l = j-1;
                            int temp = 0;
                            while(true){
                                if (k<0 || l<0){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for(int x = j-1; x>l ; x--){
                                        currentboard[i][x] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                l--;
                            }
                        }
                    }
                    if (i>0){
                        if (currentboard[i-1][j] == yes){
                            int k = i-1;
                            int l = j;
                            int temp = 0;
                            while(true){
                                if (k<0 || l<0){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for(int x = i-1; x>k ; x--){
                                        currentboard[x][j] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                k--;
                            }
                        }
                    }
                    if (i<7 && j>0){
                        if (currentboard[i+1][j-1] == yes){
                            int k = i+1;
                            int l = j-1;
                            int temp = 0;
                            while(true){
                                if (k>7 || l<0){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for (int q = i+1; q<k ;q++){
                                        currentboard[q][j-q+i] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                l--;
                                k++;
                            }
                        }
                    }
                    if (j<7 && i>0){
                        if (currentboard[i-1][j+1] == yes){
                            int k = i-1;
                            int l = j+1;
                            int temp = 0;
                            while(true){
                                if (k<0 || l>7){
                                    break;
                                }
                                if (currentboard[k][l] == yes){
                                    temp++;
                                }
                                if (currentboard[k][l] == no){
                                    c = c+temp;
                                    currentboard[i][j] = no;
                                    for (int q = i-1; q>k ;q--){
                                        currentboard[q][i-q+j] = no;
                                    }
                                    break;
                                }
                                if (currentboard[k][l] == -1){
                                    break;
                                }
                                l++;
                                k--;
                            }
                        }
                    }
                    
                    if (c>0){
                        result.add(new Node());
                        result.get(result.size()-1).move[0] = i;
                        result.get(result.size()-1).move[1] = j;
                        for (int bhai = 0 ;bhai<8; bhai++){
                            for (int ke = 0 ; ke<8;ke++){
                                result.get(result.size()-1).bord[bhai][ke] = currentboard[bhai][ke];        
                            }
                        }
                        // for (int r= 0;r<8;r++){
                        //     System.out.println(Arrays.toString(currentboard[r]));
                        // }
                        currentboard = getCopy(copy);
                        // printnode(currentboard);
                        // System.out.println(" ");
                    }
                }

            }
        }
        // System.out.println("WHY NOW WHAT THE FUCK IS WRONG WITH YOU");
        // printnode(currentboard);
        return result;
    }


    private int minimum(ArrayList<Integer> list){
        int m = list.get(0);
        for (int i = 0 ; i<list.size() ; i++){
            if (list.get(i)<m){
                m = list.get(i);
            }
        }
        return m ;
    }

    private int maximum(ArrayList<Integer> list){
        int m = list.get(0);
        for (int i = 0 ; i<list.size() ; i++){
            if (list.get(i)>m){
                m = list.get(i);
            }
        }
        return m ;
    }

    private void minimaxfunction(Node cur, int depth, int total){
        // printnode(cur.bord);
        ArrayList<Integer> ananta = new ArrayList<>();
        if (depth == total){
            cur.minimax = getboardScore(cur.bord);
            if (turn == 1){
                turn = 0;
            }
            else{
                turn =1;
            }
            return;
        }
        int[][] temp = getCopy(cur.bord);
        cur.children = findmove(cur.bord,turn, temp);
        cur.bord = getCopy(temp);
        for (int i = 0 ; i<cur.children.size() ; i++){
            // printnode(cur.children.get(i).bord);
            if (turn == 1){
                turn = 0;
            }
            else{
                turn =1;
            }
            minimaxfunction(cur.children.get(i), depth+1, total);
            ananta.add(cur.children.get(i).minimax);
        }
        if (turn == 1){
            turn = 0;
        }
        else{
            turn =1;
        }
        // System.out.println(ananta);
        if (ananta.size() == 0){
            cur.minimax = getboardScore(cur.bord);
            return;
        }
        if (depth %2 ==0){
            cur.minimax = maximum(ananta);
            return;
        }
        else{
            cur.minimax = minimum(ananta);
            return;
        }
    }

    private void printnode(int[][] node){
        for (int i = 0; i<8;i++){
            System.out.println(Arrays.toString(node[i]));
        }
        System.out.println(" ");
    }

    public int bestMove(int k) {
        printnode(board);
        System.out.println("k = " + k);
        int[][] currentboard = getCopy(board);
        Node node = new Node();
        node.bord = currentboard;
        int tempturn = turn;
        minimaxfunction(node, 0, k);
        node.bord = board;
        int m = node.children.get(0).minimax;
        
        for (int i = 0 ; i<node.children.size(); i++){
            if (node.children.get(i).minimax>m){
                m = node.children.get(i).minimax;
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0 ; i<node.children.size(); i++){
            if (node.children.get(i).minimax== m){
                arr.add(node.children.get(i).move[0]*8 + node.children.get(i).move[1]);
            }
        }
        
        turn = tempturn;
        System.out.println(minimum(arr));

        return minimum(arr);
        // int[][] temp = getCopy(board);
        // printnode(temp);
        // findmove(board, turn,temp);
        // printnode(temp);
        // return 0;


        /* Complete this function to build a Minimax tree of depth k (current board being at depth 0),
         * for the current player (siginified by the variable turn), and propagate scores upward to find
         * the best move. If the best move (move with max score at depth 0) is i,j; return i*8+j
         * In case of ties, return the smallest integer value representing the tile with best score.
         * 
         * Note: Do not alter the turn variable in this function, so that the boardScore() is the score
         * for the same player throughout the Minimax tree.
        */
        // return 0;
    }

    public ArrayList<Integer> fullGame(int k) {
        /* Complete this function to compute and execute the best move for each player starting from
         * the current turn using k-step look-ahead. Accordingly modify the board and the turn
         * at each step. In the end, modify the winner variable as required.
         */
        return new ArrayList<Integer>();
    }

    private int[][] getCopy(int[][] bored) {
        int copy[][] = new int[8][8];
        for(int i = 0; i < 8; ++i){
            for (int j = 0; j<8 ; j++){
                copy[i][j] = bored[i][j];
            }
        }
        return copy;
    }

    public int[][] getBoardCopy() {
        int copy[][] = new int[8][8];
        for(int i = 0; i < 8; ++i)
            System.arraycopy(board[i], 0, copy[i], 0, 8);
        return copy;
    }

    public int getWinner() {
        return winner;
    }

    public int getTurn() {
        return turn;
    }
    

    public static void main(String args[]){
        try{
            String s = "ananta.txt";
            Othello dummy = new Othello(s);
            System.out.println(dummy.bestMove(1));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}