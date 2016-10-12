package Exercise5;

import java.util.Scanner;

/**
 * Created by simonpersson on 28/09/16.
 */
public class DiceScan {

    public static int[] diceReg = new int[6];
    public static Scanner scan = new Scanner(System.in);

    public static void registerPips(){
        System.out.println("Enter pips separated by blanks.");
        System.out.println("End with -1 and <Enter>.");

        boolean isPips = true;

        while (isPips && scan.hasNextInt()) {
            int pips = scan.nextInt();
            if (pips >= 1 && pips <= 6) {
                diceReg[pips-1]++;
            } else {
                isPips = false;
            }
        }
    }

    public static void printReg() {
        for (int i = 0 ; i < 6 ; i++) {
            System.out.println("Number of " + (i+1) + "'s: " + diceReg[i]);
        }
    }

    public static void main(String[] args) {
        registerPips();
        printReg();
    }
}
