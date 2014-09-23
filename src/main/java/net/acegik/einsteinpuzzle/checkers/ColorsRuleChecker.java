package net.acegik.einsteinpuzzle.checkers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author drupalex
 */
public class ColorsRuleChecker extends RuleChecker {

    @Override
    public void check(int[][] p) {
        p[N_CO] = new int[]{0, 1, 2, 3, 4};
        do {
            //13. The Norwegian lives next to a blue house
            //09. The Norwegian lives in the first house
            int co_blue = helper.findIndexOf("Blue", N_CO, p);
            if (co_blue != 1) continue;

            //04. The green house is to the left of the white house
            int co_green = helper.findIndexOf("Green", N_CO, p);
            int co_white = helper.findIndexOf("White", N_CO, p);
            if (co_green > co_white) continue;

            if (this.nextChecker != null) {
                this.nextChecker.check(p);
            }
        } while (helper.genNextPermutation(p[N_CO]));
    }
}
