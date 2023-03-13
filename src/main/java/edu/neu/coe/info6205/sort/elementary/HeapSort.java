package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;

public class HeapSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public HeapSort(Helper<X> helper) {
        super(helper);
    }


    @Override
    public void sort(X[] array, int from, int to) {
        if (array == null || array.length <= 1) return;

        // XXX construction phase
        buildMaxHeap(array);
        Helper<X> helper = getHelper();
        boolean instrumentation = helper.instrumented();
        // XXX sort-down phase
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i, helper, instrumentation);
            maxHeap(array, i, 0);
        }
    }

    private void buildMaxHeap(X[] array) {
        int half = array.length / 2;
        for (int i = half; i >= 0; i--) maxHeap(array, array.length, i);
    }

    private void maxHeap(X[] array, int heapSize, int index) {
        Helper<X> helper = getHelper();
        boolean instrumentation = helper.instrumented();
        final int left = index * 2 + 1;
        final int right = index * 2 + 2;
        int largest = index;
        if (left < heapSize && compare(array, largest, left,helper, instrumentation))
            largest = left;
        if (right < heapSize && compare(array, largest, right, helper, instrumentation))
            largest = right;
        if (index != largest) {
            swap(array, largest, index, helper, instrumentation);
            maxHeap(array, heapSize, largest);
        }
    }

    private boolean compare(X[] x, int i, int j, Helper<X> helper, boolean instrumentation){
        if(instrumentation)
            return helper.compare(x, i, j) <0;
        return x[i].compareTo(x[j]) < 0;
    }

    private void swap(X[] x, int i, int j,  Helper<X> helper, boolean instrumentation){
        if(instrumentation)
            helper.swap(x, i, j);
        else{
            X temp = x[i];
            x[i] = x[j];
            x[j] = temp;
        }

    }
}
