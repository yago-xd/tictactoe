import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static int choice;
    public static boolean isValidSize(int ch){
        return (ch == 1 || ch == 2 || ch == 3);
    }
    public static void getBoardSize(){
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Board Choices:");
        System.out.println("1. 3x3 Board");
        System.out.println("2. 5x5 Board");
        System.out.println("3. 7x7 Board");
        while(true) {
            System.out.print("Enter your choice (1/2/3): ");
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                if(isValidSize(choice)){
                    break;
                }
                else{
                    System.out.println("Invalid input. Please enter 1/2/3.");
                }
            }
            else{
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        getBoardSize();
        switch(choice){
            case 1:
                Small.play();
                break;
            case 2:
                Medium.play();
                break;
            case 3:
                Large.play();
                break;
        }
    }
}