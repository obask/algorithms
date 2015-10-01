package text;

public class KMP {

    private int[] p(String s) {
        int len = s.length();
        int[] p = new int[len];
        for (int i = 1; i < len; i++) {
            int j = p[i - 1];
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = p[j - 1];
            }
            if(s.charAt(i) == s.charAt(j)) {
                ++j;
            }
            p[i] = j;
        }

        return p;
    }

    private int[] z(String s) {
        int len = s.length();
        int[] z = new int[len];
        int l = 0, r = 0;
        for (int i = 1; i < len; i++) {
            if (i < r) {
                z[i] = Math.min(z[i - l], r - i);
            }
            while (i + z[i] < len && s.charAt(z[i]) == s.charAt(i + z[i])) {
                ++z[i];
            }
            if (i + z[i] > r) {
                l = i;
                r = i + z[i];
            }
        }
        return z;
    }

}
