package stringDistance;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class OverlpCoef implements StringDistance {



    public double distance(String a, String b) {
        Set<Character> set1 = new HashSet<Character>();
        for(char c : a.toCharArray()) {
            set1.add(c);
        }

        Set<Character> set2 = new HashSet<Character>();
        for(char c : b.toCharArray()) {
            set2.add(c);

        }

        return 1 - Sets.intersection(set1, set2).size() / (double) Math.min(a.length(), b.length());
    }
}
