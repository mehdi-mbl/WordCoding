package stringDistance;

public class Episode implements StringDistance {

    public double distance(String a, String b) {
        char[] txtArr = a.toCharArray();
        char[] patArr = b.toCharArray();

        int tLen = txtArr.length;
        int pLen = patArr.length;

        double distance= Double.POSITIVE_INFINITY;

        for (int i = 0; i < tLen - pLen; i++) {

            int charMatchCount = 0;
            int max=0;
            for (int j = 0; j < pLen; j++) {

                /**
                 * If pattern mismatch, break next searching point.
                 **/
                if (patArr[j] != txtArr[i + j]) {
                    break;
                }
                charMatchCount++;

            }
            if (charMatchCount > max) {
                max=charMatchCount;
                distance=tLen-max;
            }
        }
        return distance;
    }
}
