package net.acegik.einsteinpuzzle.writers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author pnhung177
 */
public class ResultToConsoleWriter extends RuleChecker {

    private int count;
    
    @Override
    public void check(int[][] p) {
        count++;
        System.out.format("Solution #%d:%n", count);
        System.out.format("%13s:", "Houses");
        printHouse(p[N_CO]);
        System.out.format("%13s:", "Colors");
        printNames(N_CO, p[N_CO]);
        System.out.format("%13s:", "Drinks");
        printNames(N_DR, p[N_DR]);
        System.out.format("%13s:", "Cigarettes");
        printNames(N_CI, p[N_CI]);
        System.out.format("%13s:", "Pets");
        printNames(N_PE, p[N_PE]);
        System.out.format("%13s:", "Nationalities");
        printNames(N_NA, p[N_NA]);
        System.out.println();
    }
    
    public void printHouse(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%10d | ", i + 1);

        }
        System.out.print("\n");
    }

    public void printNames(int nameType, int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%10s | ", helper.getNameOfNumber(a[i], nameType));
        }
        System.out.print("\n");
    }
}
