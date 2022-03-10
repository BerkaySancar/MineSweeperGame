import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    int row;
    int column;
    String[][] mineMap;
    String[][] mainMap;
    int mineNumber;


    MineSweeper(int row, int column) {

        this.row = row;
        this.column = column;
        this.mineMap = new String[row][column];
        this.mainMap = new String[row][column];
        this.mineNumber = (row * column) / 4;

    }

    void run() {
        Random rand = new Random();

        //mineMap oluşturma.
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.mineMap[i][j] = "-";
            }
        }
        while (this.mineNumber > 0) {
            int r1 = rand.nextInt(this.row);
            int r2 = rand.nextInt(this.column);
            if (this.mineMap[r1][r2].equals("-")) {
                this.mineMap[r1][r2] = "*";
            } else {
                continue;
            }
            this.mineNumber--;
        }

        this.mineNumber = (this.column * this.row) / 4; // Döngüden çıktıktan sonra olması gereken değere dönmesi için.

        //mainMap oluşturma.
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.mainMap[i][j] = "-";
            }
        }
        printMap();
        gameplay();
    }

    void printMap() {
        // System.out.println("--------MineMap---------");   //mayın yerlerini görmek istersek yorumdan çıkarabiliriz.
        //for (String[] i : this.mineMap) {
        //    for (String j : i) {
                //    System.out.print(j + " ");
        //    }
            //    System.out.println();
       // }

        System.out.println("--------Map------------");

        for (String[] i : this.mainMap) {
            for (String j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

    }

    void gameplay() {
        Scanner scan = new Scanner(System.in);

        int round = 0;

        while (true) {

            System.out.print("Select Row: ");
            int r = scan.nextInt();
            System.out.print("Select Column: ");
            int c = scan.nextInt();

            if (r < 0 || r > this.mineMap.length - 1 || c < 0 || c > this.mineMap[0].length - 1) {
                System.out.println("Incorrect entry. The number is out of the game limit.");
                continue;
            }
            if (this.mineMap[r][c].equals("*")) {
                System.out.println("======== GAME OVER! ========");
                for (String[] i : this.mineMap) {      // Kaybettikten sonra mayın yerlerini göstermek için.
                    for (String j : i) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                }
                break;
            }
            int count = 0;
            int downside = r + 1;
            int upside = r - 1;
            int rightSide = c + 1;
            int leftSide = c - 1;

            boolean isLeftSideInLimit, isRightSideInLimit, isUpsideInLimit, isDownsideInLimit;

            // Seçtiğim noktanın etrafında ki sınırları sorgulamak için...

            if (leftSide < 0 || this.column <= leftSide) {
                isLeftSideInLimit = false;
            } else {
                isLeftSideInLimit = true;
            }
            if (this.column <= rightSide) {
                isRightSideInLimit = false;
            } else {
                isRightSideInLimit = true;
            }
            if (upside < 0 || this.row <= upside) {
                isUpsideInLimit = false;
            } else {
                isUpsideInLimit = true;
            }
            if (this.row <= downside) {
                isDownsideInLimit = false;
            } else {
                isDownsideInLimit = true;
            }

            // Noktanın etrafındaki mayınların sayısını tespit ve girilen koordinata sayı atamak için...

            if (isLeftSideInLimit) {
                if (this.mineMap[r][leftSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isRightSideInLimit) {
                if (this.mineMap[r][rightSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isDownsideInLimit) {
                if (this.mineMap[downside][c].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isUpsideInLimit) {
                if (this.mineMap[upside][c].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isLeftSideInLimit && isUpsideInLimit) {
                if (this.mineMap[upside][leftSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isLeftSideInLimit && isDownsideInLimit) {
                if (this.mineMap[downside][leftSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isRightSideInLimit && isUpsideInLimit) {
                if (this.mineMap[upside][rightSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }
            if (isRightSideInLimit && isDownsideInLimit) {
                if (this.mineMap[downside][rightSide].equals("*")) {
                    count++;
                }
                this.mainMap[r][c] = Integer.toString(count);
            }

            round++;

            // kazanma senaryosu
            if (round == (this.row * this.column) - this.mineNumber) {
                System.out.println();
                System.out.print("======== WIN ======== ");
                break;
            }

            printMap(); // Her el mapi yazdırmak için...

        }
    }
}