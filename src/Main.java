import java.util.Random;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static char[][] board={ {' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '} };
    static char user,comp;
    static int fm=0;
    static int g=0,w=0,l=0,d=0;
    static char replay='y';
    public static void printBoard() {
        int i,j;
        System.out.println("\n    0   1   2");
        System.out.println("  -------------");
        for (i = 0; i < 3; i++) {
            System.out.print(i + " |");
            for (j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println("\n  -------------");
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
        user=sc.next().toUpperCase().charAt(0);
        System.out.println("-------------------------------");
        if(valid_input()){
            if(user=='O')
                comp='X';
            else
                comp='O';
        }
        else{
            while(!valid_input()) {
                System.out.print("\nEnter a valid symbol!! (O/X): ");
                user=sc.next().toUpperCase().charAt(0);
            }
        }
    }
    public static String first(){
        String[] f={"user","computer"};
        return f[rand.nextInt(2)];
    }
    public static void user_move(){
        int row,col;
        while(true){
            System.out.print("\nEnter the row and column corresponding to your move separated by a space: ");
            if (sc.hasNextInt()) {
                row = sc.nextInt();
            } else {
                System.out.println("Invalid input. Enter integers only.");
                sc.next();
                continue;
            }
            if (sc.hasNextInt()) {
                col = sc.nextInt();
            } else {
                System.out.println("Invalid input. Enter integers only.");
                sc.next();
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
        System.out.println("You made your move at: "+row+" "+col);
        fm=0;
    }
    public static void comp_move() throws InterruptedException {
        int a,b;
        Thread.sleep(1000);
        while(true) {
            a = rand.nextInt(3);
            b= rand.nextInt(3);
            if (valid_move(a, b)) {
                board[a][b] = comp;
                break;
            }
        }
        System.out.println("\nComputer has made its move");
        System.out.println("Computer placed at: " + a + " " + b);
        fm=1;
    }
    public static void first_move() throws InterruptedException{
        printBoard();
        System.out.print("\nThe first move will be made by: ");
        String res=first();
        Thread.sleep(2000);
        if(res.equals("user")){
            System.out.println("You");
            user_move();
            fm=1;
        }
        else if(res.equals("computer")){
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
            replay = sc.next().trim().toLowerCase().charAt(0);
        }
        System.out.println("Thank you for playing!");
    }
    public static void main(String[] args) throws InterruptedException {
        setUser();
        run();
        score();
    }
}