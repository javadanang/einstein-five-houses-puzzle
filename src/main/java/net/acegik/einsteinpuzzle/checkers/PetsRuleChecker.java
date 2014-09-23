package net.acegik.einsteinpuzzle.checkers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author drupalex
 */
public class PetsRuleChecker extends RuleChecker {

    @Override
    public void check(int[][] p) {
        p[N_PE] = new int[]{0, 1, 2, 3, 4};
        do {
            //06. The person that smokes Pall Mall has a bird
            int ci_pallmall = helper.findIndexOf("PallMall", N_CI, p);
            int pe_bird = helper.findIndexOf("Bird", N_PE, p);
            if (ci_pallmall != pe_bird) continue;

            //10. The person that smokes Blend, lives next to the one
            //    that has a cat
            int ci_blend = helper.findIndexOf("Blend", N_CI, p);
            int pe_cat = helper.findIndexOf("Cat", N_PE, p);
            if ((pe_cat - ci_blend != 1) &&
                    (pe_cat - ci_blend != -1)) continue;

            //11. The person that has a horse lives next to the one
            //    that smokes Dunhill
            int pe_horse = helper.findIndexOf("Horse", N_PE, p);
            int ci_dunhill = helper.findIndexOf("Dunhill", N_CI, p);
            if ((pe_horse - ci_dunhill != 1) &&
                    (pe_horse - ci_dunhill != -1)) continue;

            if (this.nextChecker != null) {
                this.nextChecker.check(p);
            }
        } while (helper.genNextPermutation(p[N_PE]));
    }
}
