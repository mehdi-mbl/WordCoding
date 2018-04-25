package preprocessing;

import java.util.ArrayList;
import java.util.List;

public class NoisyDataCreator {

    public List<String> NoisyData(List<String> correct, List<String> misspellings, List<String> data){
        List<String> noisyData = new ArrayList<String>();
        for (int i=0;i<data.size(); i++){
            for (int j=0; j<correct.size();j++){
                if (data.get(i).contains(correct.get(j))) noisyData.add(data.get(i).replace(correct.get(j),misspellings.get(j)));
            }
            noisyData.add(data.get(i));
        }
        return noisyData;
    }


}
