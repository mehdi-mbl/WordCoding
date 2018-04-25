package postprocessing.knn;

import postprocessing.Pair;
import postprocessing.Sort;
import postprocessing.knn.KNN;
import stringDistance.StringDistance;
import java.util.ArrayList;
import java.util.List;

public class StringKNN implements KNN {

	private int k;
	private StringDistance distance;

	public StringKNN(int k, StringDistance distance) {
		this.k = k;
		this.distance = distance;
	}

	public List<Pair> knn(String a, List<String> list) {
		List<Pair> neighbors = new ArrayList<postprocessing.Pair>();
		for (int i=0; i<list.size();i++){
			if (this.distance.distance(a,list.get(i))!=Double.POSITIVE_INFINITY) neighbors.add(new Pair(list.get(i),this.distance.distance(a,list.get(i))));
		}
		Sort sort = new Sort();
		sort.sort(neighbors);
		if (neighbors.size()>k) return neighbors.subList(0,k);
		else return neighbors;
	}
}
