package stringDistance;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class SimpleMatchingCoef implements StringDistance {

    public double distance(String a, String b) {
        Set<Character> set1 = new HashSet<Character>();
        for(char c : a.toCharArray()) {
            set1.add(c);
        }

        Set<Character> set2 = new HashSet<Character>();
        for(char c : b.toCharArray()) {
            set2.add(c);

        }
        // Implementation note: The size of the union of two sets is equal to
        // the size of both lists minus the duplicate elements.
        return Sets.intersection(set1, set2).size() / (double) (set1.size() + set2.size() - Sets.intersection(set1, set2).size());
    }
}
