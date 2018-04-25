package stringDistance;

public class Hamming implements StringDistance {

    public double distance(String a, String b) {

        if (a.length() != b.length()) {
            return Double.POSITIVE_INFINITY;
        } else {
            int distance = 0;

            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    distance++;
                }
            }

            return distance;
        }

    }
}
