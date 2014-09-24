package net.acegik.einsteinpuzzle.checkers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author pnhung177
 */
public class CigarettesRuleChecker extends RuleChecker {

    @Override
    public void check(int[][] p) {
        p[N_CI] = new int[]{0, 1, 2, 3, 4};
        do {
            //8. The owner of the yellow house smokes Dunhill
            int co_yellow = helper.findIndexOf("Yellow", N_CO, p);
            int ci_dunhill = helper.findIndexOf("Dunhill", N_CI, p);
            if (ci_dunhill != co_yellow) continue;

            //12. The one that smokes Bluemaster drinks beer
            int dr_beer = helper.findIndexOf("Beer", N_DR, p);
            int ci_bluemaster = helper.findIndexOf("BlueMaster", N_CI, p);
            if (ci_bluemaster != dr_beer) continue;

            //15. The person that smokes Blend, has a neighbour
            //    that drinks water
            int dr_water = helper.findIndexOf("Water", N_DR, p);
            int ci_blend = helper.findIndexOf("Blend", N_CI, p);
            if ((dr_water - ci_blend != 1) &&
                    (dr_water - ci_blend != -1)) continue;

            if (this.nextChecker != null) {
                this.nextChecker.check(p);
            }
        } while (helper.genNextPermutation(p[N_CI]));
    }
}
