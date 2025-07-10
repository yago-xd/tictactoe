import java.util.Random;
import java.util.Scanner;
public class Large {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static char[][] board={ {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' '} };
    static char user,comp;
    static int fm=0,round=0,size=7;
    static int g=0,w=0,l=0,d=0;
    static char replay='y';
    public static void printBoard() {
        int i,j;
        System.out.println("\n    1   2   3   4   5   6   7");
        System.out.println("  -----------------------------");
        for (i = 0; i < size; i++) {
            System.out.print((char)(65+i) + " |");
            for (j = 0; j < size; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println("\n  -----------------------------");
        }
    }
    public static void initial_board() {
        int i,j,b;
        char a='A';
        System.out.println("\n    1    2    3    4    5    6    7");
        System.out.println("  -----------------------------------");
        for (i = 0; i < size; i++) {
            b=1;
            System.out.print((char)(65+i) + " |");
            for (j = 0; j < size; j++) {
                System.out.print(" " + a+b + " |");
                b++;
            }
            a++;
            System.out.println("\n  -----------------------------------");
        }
    }
    public static boolean valid_move(int m, int n){
        if((m>=0 && m<size)&&(n>=0 && n<size))
            return board[m][n] == ' ';
        return false;
    }
    public static boolean valid_input(){
        return (user == 'O'|| user=='X');
    }
    public static void setUser(){
        System.out.print("\nEnter your preferred symbol (O/X): ");
        user=sc.nextLine().toUpperCase().charAt(0);
        System.out.println("-----------------------------------");
        while(!valid_input()) {
            System.out.print("\nEnter a valid symbol!! (O/X): ");
            user=sc.nextLine().toUpperCase().charAt(0);
        }
        if(user=='O')
            comp='X';
        else
            comp='O';
    }
    public static void user_move(){
        int row,col;
        String pos,apos;
        while(true){
            System.out.print("\nEnter the position reference of your cell (eg: A1): ");
            pos=sc.nextLine().toUpperCase().trim().replaceAll(" ","");
            if(pos.length()!=2) {
                System.out.println("Invalid format. Use something like 1A or A1");
                continue;
            }
            if(Character.isLetter(pos.charAt(0))&&Character.isDigit(pos.charAt(1))){
                row=pos.charAt(0)-65;
                col=pos.charAt(1)-'1';
                apos=pos;
            }
            else if(Character.isDigit(pos.charAt(0))&&Character.isLetter(pos.charAt(1))){
                row=pos.charAt(1)-65;
                col=pos.charAt(0)-'1';
                apos=""+pos.charAt(1)+pos.charAt(0);
            }
            else{
                System.out.println("Invalid format!");
                continue;
            }
            if(valid_move(row,col)) {
                board[row][col]=user;
                break;
            }
            else{
                System.out.println("Invalid choice. Position is either occupied or does not exist!");
            }
        }
        System.out.println("You made your move at: "+apos);
        fm=0;
    }
    public static void comp_random_move() throws InterruptedException {
        int a,b;
        String pos;
        Thread.sleep(1000);
        while(true) {
            a = rand.nextInt(size);
            b= rand.nextInt(size);
            if (valid_move(a, b)) {
                board[a][b] = comp;
                break;
            }
        }
        pos=""+(char)(a+65)+(b+1);
        System.out.println("\nComputer has made its move");
        System.out.println("Computer placed at: "+pos);
        fm=1;
    }
    public static void comp_ai_move() throws InterruptedException {
        Thread.sleep(1000);
        String pos;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                pos=""+(char)(i+65)+(j+1);
                if(board[i][j]==' ') {
                    board[i][j] = comp;
                    if(checkWin(comp)) {
                        System.out.println("\nComputer has made its move");
                        System.out.println("Computer placed at: "+pos);
                        return;
                    }
                    board[i][j]=' ';
                }
            }
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                pos=""+(char)(i+65)+(j+1);
                if(board[i][j]==' '){
                    board[i][j]=user;
                    if(checkWin(user)) {
                        System.out.println("\nComputer has made its move");
                        System.out.println("Computer placed at: "+pos);
                        board[i][j] = comp;
                        return;
                    }
                    board[i][j]=' ';
                }
            }
        }
        comp_random_move();
    }
    public static void first_move() throws InterruptedException{
        round++;
        initial_board();
        System.out.println("\nRound "+round);
        System.out.print("\nThe first move will be made by: ");
        int first= rand.nextInt(1,3)-1;
        Thread.sleep(2000);
        if(first==0){
            System.out.println("You");
            user_move();
            fm=1;
        }
        else if(first==1){
            System.out.println("Computer");
            comp_ai_move();
            fm=0;
        }
        printBoard();
    }
    public static boolean checkWin(char symbol) {
        for (int i=0;i<size;i++) {
            boolean rowWin=true;
            for (int j=0;j<size;j++) {
                if(board[i][j]!=symbol) {
                    rowWin=false;
                    break;
                }
            }
            if(rowWin)
                return true;
        }

        for (int j=0;j<size;j++) {
            boolean colWin=true;
            for (int i=0;i<size;i++) {
                if(board[i][j]!=symbol) {
                    colWin=false;
                    break;
                }
            }
            if(colWin)
                return true;
        }

        boolean mainDiag=true;
        for (int i=0;i<size;i++) {
            if (board[i][i]!=symbol) {
                mainDiag=false;
                break;
            }
        }
        if(mainDiag)
            return true;

        boolean antiDiag=true;
        for (int i=0;i<size;i++) {
            if (board[i][size-1-i]!=symbol) {
                antiDiag=false;
                break;
            }
        }
        return antiDiag;
    }
    public static boolean isDraw(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(board[i][j]==' ')
                    return false;
            }
        }
        return true;
    }
    public static void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }
    public static void start_game() throws InterruptedException {
        reset();
        first_move();
        while(true){
            if(fm==0){
                System.out.print("\nYour turn");
                user_move();
                printBoard();
                if (checkWin(user)) {
                    System.out.println("You win!");
                    w++;
                    g++;
                    break;
                }
                if (isDraw()) {
                    System.out.println("It's a draw!");
                    g++;
                    d++;
                    break;
                }
                fm=1;
            }
            else {
                System.out.print("\nComputer's turn");
                comp_ai_move();
                printBoard();
                if (checkWin(comp)) {
                    System.out.println("Computer wins!");
                    l++;
                    g++;
                    break;
                }
                if (isDraw()) {
                    System.out.println("It's a draw!");
                    g++;
                    d++;
                    break;
                }
                fm=0;
            }
        }
    }
    public static void score(){
        System.out.println("\n--------------Score---------------");
        System.out.println("Games Played: "+g);
        System.out.println("Games Won: "+w);
        System.out.println("Games Lost: "+l);
        System.out.println("Games Drawn: "+d);
    }
    public static void run() throws InterruptedException {
        replay='y';
        while(replay=='y') {
            start_game();
            System.out.println("\n------------------------------");
            System.out.print("\nDo you wish to play again? (Y/N): ");
            String replay_inp=sc.nextLine().trim().toLowerCase();
            if(!replay_inp.isEmpty())
                replay=replay_inp.charAt(0);
            else
                replay = 'n';
            if (replay != 'y' && replay != 'n') {
                System.out.println("Invalid input! Assuming no.");
                replay = 'n';
            }
        }
        System.out.println("Thank you for playing!");
    }
    public static void play() throws InterruptedException {
        setUser();
        run();
        score();
    }
    public static void main(String[] args) throws InterruptedException {
        play();
    }
}