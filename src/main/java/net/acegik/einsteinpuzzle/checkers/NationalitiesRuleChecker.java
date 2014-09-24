package net.acegik.einsteinpuzzle.checkers;

import net.acegik.einsteinpuzzle.RuleChecker;

/**
 *
 * @author pnhung177
 */
public class NationalitiesRuleChecker extends RuleChecker {

    @Override
    public void check(int[][] p) {
        p[N_NA] = new int[]{0, 1, 2, 3, 4};
        do {
            //09. The Norwegian lives in the first house
            int na_norwe = helper.findIndexOf("Norwegian", N_NA, p);
            if (na_norwe != 0) continue;

            //01. The British man lives in the red house
            int co_red = helper.findIndexOf("Red", N_CO, p);
            int na_english = helper.findIndexOf("Englishman", N_NA, p);
            if (na_english != co_red) continue;

            //02. The Swedish man has a dog for a pet
            int pe_dog = helper.findIndexOf("Dog", N_PE, p);
            int na_swede = helper.findIndexOf("Swede", N_NA, p);
            if (na_swede != pe_dog) continue;
            
            //03. The Danish man drinks tea
            int dr_tea = helper.findIndexOf("Tea", N_DR, p);
            int na_dane = helper.findIndexOf("Dane", N_NA, p);
            if (na_dane != dr_tea) continue;

            //14. The German smokes Prince
            int ci_prince = helper.findIndexOf("Prince", N_CI, p);
            int na_german = helper.findIndexOf("German", N_NA, p);
            if (na_german != ci_prince) continue;

            if (this.nextChecker != null) {
                this.nextChecker.check(p);
            }
        } while (helper.genNextPermutation(p[N_NA]));
    }
}
