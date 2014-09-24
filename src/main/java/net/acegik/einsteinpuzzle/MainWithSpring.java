package net.acegik.einsteinpuzzle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pnhung177
 */
public class MainWithSpring {
    
    public static void main(String[] args) {
        int[][] combination = new int[5][5];

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("/application-context.xml");
        
        RuleChecker checker = (RuleChecker) ctx.getBean("ruleCheckerChain");
        checker.check(combination);
    }
}
