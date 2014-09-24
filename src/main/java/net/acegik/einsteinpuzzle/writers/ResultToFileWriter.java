package net.acegik.einsteinpuzzle.writers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author pnhung177
 */
public class ResultToFileWriter extends RuleChecker {

    private boolean stopWhenError = false;

    public void setStopWhenError(boolean stopWhenError) {
        this.stopWhenError = stopWhenError;
    }

    private FileOutputStream stream;
    private PrintStream output;

    public void setFilename(String filename) {
        File file = new File(filename);
        try {
            this.stream = new FileOutputStream(file);
            this.output = new PrintStream(stream);
        } catch (Exception e) {
            if (stopWhenError) {
                throw new IllegalArgumentException(e);
            } else {
                output.print("Error on create FileOutputStream. " +
                        "Use System.out instead.");
                this.output = System.out;
            }
        }
    }

    private int count;
    
    @Override
    public void check(int[][] p) {
        count++;
        output.format("Solution #%d:%n", count);
        output.format("%13s:", "Houses");
        printHouse(p[N_CO]);
        output.format("%13s:", "Colors");
        printNames(N_CO, p[N_CO]);
        output.format("%13s:", "Drinks");
        printNames(N_DR, p[N_DR]);
        output.format("%13s:", "Cigarettes");
        printNames(N_CI, p[N_CI]);
        output.format("%13s:", "Pets");
        printNames(N_PE, p[N_PE]);
        output.format("%13s:", "Nationalities");
        printNames(N_NA, p[N_NA]);
        output.println();
    }
    
    public void printHouse(int[] a) {
        for (int i = 0; i < a.length; i++) {
            output.format("%10d | ", i + 1);

        }
        output.print("\n");
    }

    public void printNames(int nameType, int[] a) {
        for (int i = 0; i < a.length; i++) {
            output.format("%10s | ", helper.getNameOfNumber(a[i], nameType));
        }
        output.print("\n");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.stream != null) {
            this.stream.flush();
            this.stream.close();
            this.stream = null;
        }
    }
}
