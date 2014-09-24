package net.acegik.einsteinpuzzle;

import java.util.HashMap;

/**
 *
 * @author pnhung177
 */
public class PuzzleHelper {
    
    public String[][] name_matrix = {
        {"Englishman", "Dane", "Swede", "German", "Norwegian"},
        {"Red", "White", "Yellow", "Green", "Blue"},
        {"Tea", "Coffee", "Milk", "Beer", "Water"},
        {"Dog", "Cat", "Horse", "Fish", "Bird"},
        {"PallMall", "Dunhill", "Prince", "BlueMaster", "Blend"}
    };

    public HashMap<String, Integer>[] name2number = null;

    public String getNameOfNumber(int number, int type) {
        return name_matrix[type][number];
    }

    public int getNumberOfName(String name, int type) {
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

    public int findIndexOf(String name, int type, int[][] position) {
        for (int i = 0; i < position[type].length; i++) {
            if (position[type][i] == getNumberOfName(name, type)) {
                return i;
            }
        }
        throw new IllegalArgumentException("findIndexOf() error!");
    }

    public boolean genNextPermutation(int[] a) {
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
}
