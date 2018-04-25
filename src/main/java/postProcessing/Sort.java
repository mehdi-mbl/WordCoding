package postprocessing;


import java.util.List;

public class Sort {

    private List<Pair> array;
    private int length;

    public void sort(List<Pair> inputArr) {

        if (inputArr == null || inputArr.size() == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.size();
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        double pivot = array.get(lowerIndex+(higherIndex-lowerIndex)/2).getValue();
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array.get(i).getValue() < pivot) {
                i++;
            }
            while (array.get(j).getValue() > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        Pair temp = array.get(i);
        array.set(i,array.get(j));
        array.set(j,temp);
    }

}
