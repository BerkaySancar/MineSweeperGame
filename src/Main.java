import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Row: ");
        int row = scan.nextInt();
        System.out.print("Column: ");
        int column = scan.nextInt();

        MineSweeper ms = new MineSweeper(row, column);
        ms.run();
    }




    }
