/*
There are 5 houses that are each a different colour.
There is a person of a different nationality in each house.
The 5 owners drink a certain drink. They each smoke a certain brand of
 cigarettes and also have a certain pet. No owner has the same pet, smokes
 the same brand of cigarettes nor drinks the same drink.
The question is. “Who has the fish?”

CLUES
The British man lives in the red house.
The Swedish man has a dog for a pet.
The Danish man drinks tea.
The green house is to the left of the white house.
The owner of the green house drinks coffee.
The person that smokes Pall Mall has a bird.
The owner of the yellow house smokes Dunhill.
The person that lives in the middle house drinks milk.
The Norwegian lives in the first house.
The person that smokes Blend, lives next to the one that has a cat.
The person that has a horse lives next to the one that smokes Dunhill.
The one that smokes Bluemaster drinks beer.
The German smokes Prince.
The Norwegian lives next to a blue house.
The person that smokes Blend, has a neighbour that drinks water.
*/
package net.acegik.einsteinpuzzle;

import java.util.HashMap;

/**
 * pnhung177
 * 
 */
public class Main {

    public static final int N_NA = 0;
    public static final int N_CO = 1;
    public static final int N_DR = 2;
    public static final int N_PE = 3;
    public static final int N_CI = 4;

    public static String[][] name_matrix = {
        {"Englishman", "Dane", "Swede", "German", "Norwegian"},
        {"Red", "White", "Yellow", "Green", "Blue"},
        {"Tea", "Coffee", "Milk", "Beer", "Water"},
        {"Dog", "Cat", "Horse", "Fish", "Bird"},
        {"PallMall", "Dunhill", "Prince", "BlueMaster", "Blend"}
    };

    public static HashMap<String, Integer>[] name2number = null;

    public static String getNameOfNumber(int number, int type) {
        return name_matrix[type][number];
    }

    public static int getNumberOfName(String name, int type) {
        if (name2number == null) {
            name2number = new HashMap[name_matrix.length];
            for(int i=0; i<name_matrix.length; i++) {
                name2number[i] = new HashMap<String, Integer>();
            }
        }
        if (!name2number[type].containsKey(name)) {
            for (int i = 0; i < name_matrix[type].length; i++) {
                if (name_matrix[type][i].equals(name)) {
                    name2number[type].put(name, i);
                }
            }
        }
        return name2number[type].get(name);
    }

    public static int findIndexOf(String name, int type, int[][] position) {
        for (int i = 0; i < position[type].length; i++) {
            if (position[type][i] == getNumberOfName(name, type)) {
                return i;
            }
        }
        throw new IllegalArgumentException("findIndexOf() error!");
    }
    
    public static boolean genNextPermutation(int[] a) {
        int n = a.length;
        if (n < 2) return false;

        // tìm j là chỉ số lớn nhất mà a[j] < a[j+1]
        int j;
        for(j=n-2; j>=0; j--) if (a[j] < a[j+1]) break;
        if (j == -1) return false;

        // tìm k là chỉ số lớn nhất mà a[j] < a[k]
        int k;
        for(k=n-1; k>j; k--) if (a[k] > a[j]) break;

        // hoán đổi giá trị a[j] và a[k]
        int t = a[j]; a[j] = a[k]; a[k] = t;

        // đảo danh sách sau a[j] (từ a[j+1] đến hết)
        int s = j + 1;
        int r = n - 1;

        while(r > s) {
            t = a[r]; a[r] = a[s]; a[s] = t;
            r--;
            s++;
        }

        return true;
    }

    public static void printHouse(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%10d | ", i + 1);

        }
        System.out.print("\n");
    }

    public static void printNames(int nameType, int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%10s | ", getNameOfNumber(a[i], nameType));
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int count = 0;
        int[][] p = {
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4}
        };

        p[N_CO] = new int[]{0, 1, 2, 3, 4};
        do {
            //13. nguoi Na-uy o canh nha mau xanh nuoc bien va
            //9. nguoi Na-uy o nha dau tien
            int co_blue = findIndexOf("Blue", N_CO, p);
            if (co_blue != 1) continue;

            //4. ngoi nha xanh la cay nam ben trai nha mau trang
            int co_green = findIndexOf("Green", N_CO, p);
            int co_white = findIndexOf("White", N_CO, p);
            if (co_green > co_white) continue;

            int co_yellow = findIndexOf("Yellow", N_CO, p);
            int co_red = findIndexOf("Red", N_CO, p);

            //Drink
            p[N_DR] = new int[]{0, 1, 2, 3, 4};
            do {
                //5. nguoi nha xanh la cay thich uong ca phe
                int dr_cafe = findIndexOf("Coffee", N_DR, p);
                if (dr_cafe != co_green) continue;

                //7. nguoi o nha giua thich uong sua
                int dr_milk = findIndexOf("Milk", N_DR, p);
                if (dr_milk != 2) continue;

                int dr_beer = findIndexOf("Beer", N_DR, p);
                int dr_tea = findIndexOf("Tea", N_DR, p);
                int dr_water = findIndexOf("Water", N_DR, p);

                //cigarettes
                p[N_CI] = new int[]{0, 1, 2, 3, 4};
                do {
                    //8.nguoi o nha mau vang hut thuoc hieu Dunhill
                    int ci_dunhill = findIndexOf("Dunhill", N_CI, p);
                    if (ci_dunhill != co_yellow) continue;

                    //12. nguoi hut thuoc hieu bluemaster thich uong bia
                    int ci_bluemaster = findIndexOf("BlueMaster", N_CI, p);
                    if (ci_bluemaster != dr_beer) continue;

                    //15. nguoi hut thuoc Blend co hang xom thich uong nuoc
                    int ci_blend = findIndexOf("Blend", N_CI, p);
                    if ((dr_water - ci_blend != 1) &&
                            (dr_water - ci_blend != -1)) continue;

                    int ci_pallmall = findIndexOf("PallMall", N_CI, p);
                    int ci_prince = findIndexOf("Prince", N_CI, p);

                    // Pets
                    p[N_PE] = new int[]{0, 1, 2, 3, 4};
                    do {
                        //6. nguoi hut thuoc la hieu PallMall nuoi chim
                        int pe_bird = findIndexOf("Bird", N_PE, p);
                        if (ci_pallmall != pe_bird) continue;

                        //10. nguoi hut thuoc Blend o canh nha nguoi nuoi meo
                        int pe_cat = findIndexOf("Cat", N_PE, p);
                        if ((pe_cat - ci_blend != 1) &&
                                (pe_cat - ci_blend != -1)) continue;

                        //11. nguoi nuoi ngua o canh nha nguoi hut thuoc Dunhill
                        int pe_horse = findIndexOf("Horse", N_PE, p);
                        if ((pe_horse - ci_dunhill != 1) &&
                                (pe_horse - ci_dunhill != -1)) continue;

                        int pe_dog = findIndexOf("Dog", N_PE, p);

                        //Nationalities
                        p[N_NA] = new int[]{0, 1, 2, 3, 4};
                        do {
                            //9. nguoi na-uy o nha dau tien
                            int na_norwegian = findIndexOf("Norwegian", N_NA, p);
                            if (na_norwegian != 0) continue;

                            //1. nguoi anh o nha mau do
                            int na_english = findIndexOf("Englishman", N_NA, p);
                            if (na_english != co_red) continue;

                            //2. nguoi thuy dien nuoi cho
                            int na_swede = findIndexOf("Swede", N_NA, p);
                            if (na_swede != pe_dog) continue;

                            //3. nguoi dan mach thich uong che
                            int na_dane = findIndexOf("Dane", N_NA, p);
                            if (na_dane != dr_tea) continue;

                            //14. nguoi duc hut thuoc hieu Prince
                            int na_german = findIndexOf("German", N_NA, p);
                            if (na_german != ci_prince) continue;

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

                        } while (genNextPermutation(p[N_NA]));
                    } while (genNextPermutation(p[N_PE]));
                } while (genNextPermutation(p[N_CI]));
            } while (genNextPermutation(p[N_DR]));
        } while (genNextPermutation(p[N_CO]));
    }
}
