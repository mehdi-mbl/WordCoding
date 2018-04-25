package postprocessing.knn;


import postprocessing.Pair;

import java.util.List;

public interface KNN {

    List<Pair> knn (String a, List<String> list);
}
