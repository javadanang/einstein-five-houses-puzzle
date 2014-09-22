package net.acegik.einsteinpuzzle;

import java.util.Map;
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

    public static String[][] ids = {
        {"Anh", "Dan Mach", "Thuy Dien", "Duc", "Na Uy"},
        {"Do", "Trang", "Vang", "Xanh la cay", "Xanh nuoc bien"},
        {"Che", "Ca Phe", "Sua", "Bia", "Nuoc Loc"},
        {"Cho", "Meo", "Ngua", "Ca", "Chim"},
        {"Pall Mall", "Dunhill", "Rothmanns", "Winfield", "Marlboro"}
    };

    @SuppressWarnings("Unchecked")
    public static Map<String, Integer>[] map = new HashMap[]{
        new HashMap<String, Integer>(),
        new HashMap<String, Integer>(),
        new HashMap<String, Integer>(),
        new HashMap<String, Integer>(),
        new HashMap<String, Integer>()};

    public static int getNumberOfName(int nameType, String name) {
        if (!map[nameType].containsKey(name)) {
            for (int i = 0; i < ids[nameType].length; i++) {
                if (ids[nameType][i].equals(name)) {
                    map[nameType].put(name, i);
                }
            }
        }
        return map[nameType].get(name);
    }

    public static int findIndexOf(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return i;
            }
        }
        throw new IllegalArgumentException("findIndexOf() error!");
    }

    public static int findIndexOf(String name, int nameType, int[][] position) {
        return findIndexOf(position[nameType], getNumberOfName(nameType, name));
    }
    
    public static boolean genNextPermutation(int[] a) {
        int n = a.length;
        if (n < 2) return false;

        // tìm j là chỉ số lớn nhất mà a[j] < a[j+1]
        int j = n - 1;
        for(j=n-1; j>0; j--) if (a[j-1] < a[j]) break;
        if (j == 0) return false;
        j--;

        // tìm k
        int k = n - 1;
        for(k=n-1; k>j; k--) if (a[k] > a[j]) break;

        // đổi chỗ a[j] và a[k]
        int t = a[j]; a[j] = a[k]; a[k] = t;

        // đảo danh sách sau a[j]
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
            System.out.format("%14d | ", i + 1);

        }
        System.out.print("\n");
    }

    public static void printNames(int nameType, int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.format("%14s | ", ids[nameType][a[i]]);
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
            int co_blue = findIndexOf("Xanh nuoc bien", N_CO, p);
            if (co_blue != 1) {
                continue;
            }

            //4. ngoi nha xanh la cay nam ben trai nha mau trang
            int co_green = findIndexOf("Xanh la cay", N_CO, p);
            int co_white = findIndexOf("Trang", N_CO, p);
            if (co_green > co_white) {
                continue;
            }


            int co_yellow = findIndexOf("Vang", N_CO, p);
            int co_red = findIndexOf("Do", N_CO, p);

            //Drink
            p[N_DR] = new int[]{0, 1, 2, 3, 4};
            do {
                //5. nguoi nha xanh la cay thich uong ca phe
                int dr_cafe = findIndexOf("Ca Phe", N_DR, p);
                if (dr_cafe != co_green) {
                    continue;
                }


                //7. nguoi o nha giua thich uong sua
                int dr_milk = findIndexOf("Sua", N_DR, p);
                if (dr_milk != 2) {
                    continue;
                }


                int dr_beer = findIndexOf("Bia", N_DR, p);
                int dr_tea = findIndexOf("Che", N_DR, p);
                int dr_water = findIndexOf("Nuoc Loc", N_DR, p);

                //cigarettes
                p[N_CI] = new int[]{0, 1, 2, 3, 4};
                do {
                    //8.nguoi o nha mau vang hut thuoc hieu Dunhill
                    int ci_dunhill = findIndexOf("Dunhill", N_CI, p);
                    if (ci_dunhill != co_yellow) {
                        continue;
                    }


                    //12. nguoi hut thuoc hieu winfield thich uong bia
                    int ci_winfield = findIndexOf("Winfield", N_CI, p);
                    if (ci_winfield != dr_beer) {
                        continue;
                    }


                    //15. nguoi hut thuoc Marlboro co hang xom thich uong nuoc loc
                    int ci_marlboro = findIndexOf("Marlboro", N_CI, p);
                    if ((dr_water - ci_marlboro != 1) && (dr_water - ci_marlboro != -1)) {
                        continue;
                    }


                    int ci_pallmall = findIndexOf("Pall Mall", N_CI, p);
                    int ci_rothmanns = findIndexOf("Rothmanns", N_CI, p);

                    // Pets
                    p[N_PE] = new int[]{0, 1, 2, 3, 4};
                    do {
                        //6. nguoi hut thuoc la hieu Pall Mall nuoi chim
                        int pe_bird = findIndexOf("Chim", N_PE, p);
                        if (ci_pallmall != pe_bird) {
                            continue;
                        }


                        //10. nguoi hut thuoc hieu Marlboro o canh nha nguoi nuoi meo
                        int pe_cat = findIndexOf("Meo", N_PE, p);
                        if ((pe_cat - ci_marlboro != 1) && (pe_cat - ci_marlboro != -1)) {
                            continue;
                        }


                        //11. nguoi co nuoi ngua o canh nha nguoi hut thuoc hieu Dunhill
                        int pe_horse = findIndexOf("Ngua", N_PE, p);
                        if ((pe_horse - ci_dunhill != 1) && (pe_horse - ci_dunhill != -1)) {
                            continue;
                        }

                        int pe_dog = findIndexOf("Cho", N_PE, p);

                        //Nationalities
                        p[N_NA] = new int[]{0, 1, 2, 3, 4};
                        do {
                            //9. nguoi na-uy o nha dau tien
                            int na_nauy = findIndexOf("Na Uy", N_NA, p);
                            if (na_nauy != 0) {
                                continue;
                            }


                            //1. nguoi anh o nha mau do
                            int na_anh = findIndexOf("Anh", N_NA, p);
                            if (na_anh != co_red) {
                                continue;
                            }


                            //2. nguoi thuy dien nuoi cho
                            int na_thuydien = findIndexOf("Thuy Dien", N_NA, p);
                            if (na_thuydien != pe_dog) {
                                continue;
                            }


                            //3. nguoi dan mach thich uong che
                            int na_danmach = findIndexOf("Dan Mach", N_NA, p);
                            if (na_danmach != dr_tea) {
                                continue;
                            }


                            //14. nguoi duc hut thuoc hieu Rothmanns
                            int na_duc = findIndexOf("Duc", N_NA, p);

                            if (na_duc != ci_rothmanns) {
                                continue;
                            }


                            count++;
                            System.out.format("Dap an %d:%n", count);
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

                        } while (genNextPermutation(p[N_NA]));
                    } while (genNextPermutation(p[N_PE]));
                } while (genNextPermutation(p[N_CI]));
            } while (genNextPermutation(p[N_DR]));
        } while (genNextPermutation(p[N_CO]));
    }
}
