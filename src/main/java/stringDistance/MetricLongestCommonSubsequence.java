package stringDistance;

import info.debatty.java.stringsimilarity.MetricLCS;

public class MetricLongestCommonSubsequence implements StringDistance {

    @Override
    public double distance(String a, String b) {
        MetricLCS metricLCS = new MetricLCS();
        return metricLCS.distance(a,b);
    }
}
