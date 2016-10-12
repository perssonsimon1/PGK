package Exercise5;

/**
 * Created by simonpersson on 28/09/16.
 */

import java.util.Random;

public class DiceReg {
    public static void main(String[] args) {
        int[] diceReg = new int[6];
        int n = 100;
        Random rnd = new Random();
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        System.out.println("Rolling the dice " + n + " times");
        if (args.length > 1) {
            int seed = Integer.parseInt(args[1]);
            rnd.setSeed(seed);
            System.out.println(" with seed " + seed);
        }
        System.out.println(".");
        for (int i = 0 ; i < n ; i++) {
            int pips = rnd.nextInt(6);
            diceReg[pips]++;
        }
        for (int i = 1 ; i <= 6 ; i++) {
            System.out.println("Number of " + i + "'s: " + diceReg[i-1]);
        }
    }
}
