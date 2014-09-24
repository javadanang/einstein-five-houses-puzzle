package net.acegik.einsteinpuzzle.checkers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author pnhung177
 */
public class DrinksRuleChecker extends RuleChecker {

    @Override
    public void check(int[][] p) {
        p[N_DR] = new int[]{0, 1, 2, 3, 4};
        do {
            //05. The owner of the green house drinks coffee
            int co_green = helper.findIndexOf("Green", N_CO, p);
            int dr_cafe = helper.findIndexOf("Coffee", N_DR, p);
            if (dr_cafe != co_green) continue;

            //7. The person that lives in the middle house drinks milk
            int dr_milk = helper.findIndexOf("Milk", N_DR, p);
            if (dr_milk != 2) continue;

            if (this.nextChecker != null) {
                this.nextChecker.check(p);
            }
        } while (helper.genNextPermutation(p[N_DR]));
    }
}
