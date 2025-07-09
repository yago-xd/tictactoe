import java.util.Random;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static char[][] board={ {' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '} };
    static char user,comp;
    static int fm=0,round=0;
    static int g=0,w=0,l=0,d=0;
    static char replay='y';
    public static void printBoard() {
        int i,j;
        System.out.println("\n    1   2   3");
        System.out.println("  -------------");
        for (i = 0; i < 3; i++) {
            System.out.print((char)(65+i) + " |");
            for (j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println("\n  -------------");
        }
    }
    public static void initial_board() {
        int i,j,b;
        char a='A';
        System.out.println("\n    1    2    3");
        System.out.println("  ----------------");
        for (i = 0; i < 3; i++) {
            b=1;
            System.out.print((char)(65+i) + " |");
            for (j = 0; j < 3; j++) {
                System.out.print(" " + a+b + " |");
                b++;
            }
            a++;
            System.out.println("\n  ----------------");
        }
    }
    public static boolean valid_move(int m, int n){
        if((m>=0 && m<3)&&(n>=0 && n<3))
            return board[m][n] == ' ';
        return false;
    }
    public static boolean valid_input(){
        return (user == 'O'|| user=='X');
    }
    public static void setUser(){
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter your preferred symbol (O/X): ");
        user=sc.nextLine().toUpperCase().charAt(0);
        System.out.println("-------------------------------");
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
    public static void comp_move() throws InterruptedException {
        int a,b;
        String pos;
        Thread.sleep(1000);
        while(true) {
            a = rand.nextInt(3);
            b= rand.nextInt(3);
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
    public static void first_move() throws InterruptedException{
        round++;
        initial_board();
        System.out.println("\nRound "+round);
        System.out.print("\nThe first move will be made by: ");
        int first= rand.nextInt(2);
        Thread.sleep(2000);
        if(first==0){
            System.out.println("You");
            user_move();
            fm=1;
        }
        else{
            System.out.println("Computer");
            comp_move();
            fm=0;
        }
        printBoard();
    }
    public static boolean checkWin(char symbol){
        for(int i=0;i<3;i++){
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;
        return false;
    }
    public static boolean isDraw(){
        int d=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]!=' ')
                    d++;
            }
        }
        return (d == 9);
    }
    public static void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
                comp_move();
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
    public static void main(String[] args) throws InterruptedException {
        setUser();
        run();
        score();
    }
}