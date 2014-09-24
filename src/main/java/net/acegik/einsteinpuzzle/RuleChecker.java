/*
    ---Einstein's Five-houses Puzzle---

    There are 5 houses that are each a different colour.
    There is a person of a different nationality in each house.
    The 5 owners drink a certain drink. They each smoke a certain brand of
     cigarettes and also have a certain pet. No owner has the same pet, smokes
     the same brand of cigarettes nor drinks the same drink.
    The question is. “Who has the fish?”

    CLUES
    01. The British man lives in the red house.
    02. The Swedish man has a dog for a pet.
    03. The Danish man drinks tea.
    04. The green house is to the left of the white house.
    05. The owner of the green house drinks coffee.
    06. The person that smokes Pall Mall has a bird.
    07. The person that lives in the middle house drinks milk.
    08. The owner of the yellow house smokes Dunhill.
    09. The Norwegian lives in the first house.
    10. The person that smokes Blend, lives next to the one that has a cat.
    11. The person that has a horse lives next to the one that smokes Dunhill.
    12. The one that smokes Bluemaster drinks beer.
    13. The Norwegian lives next to a blue house.
    14. The German smokes Prince.
    15. The person that smokes Blend, has a neighbour that drinks water.
*/

package net.acegik.einsteinpuzzle;

/**
 *
 * @author pnhung177
 */
public abstract class RuleChecker {

    public static final int N_NA = 0;
    public static final int N_CO = 1;
    public static final int N_DR = 2;
    public static final int N_PE = 3;
    public static final int N_CI = 4;
    
    protected PuzzleHelper helper;

    public void setHelper(PuzzleHelper puzzleHelper) {
        this.helper = puzzleHelper;
    }

    protected RuleChecker nextChecker;

    public void setNextChecker(RuleChecker nextChecker) {
        this.nextChecker = nextChecker;
    }

    public abstract void check(int[][] permutation);
}
