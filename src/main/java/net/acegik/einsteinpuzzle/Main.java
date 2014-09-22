package net.acegik.einsteinpuzzle;

/**
 * pnhung177
 * 
 */
public class Main {
    
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
}
