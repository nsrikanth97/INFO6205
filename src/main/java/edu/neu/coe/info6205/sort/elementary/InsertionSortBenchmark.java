package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Sort;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Timer;
import edu.neu.coe.info6205.graphs.BFS_and_prims.StdRandom;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class InsertionSortBenchmark {

    enum OrderType{
        S, R, P, O
    }

    public static Integer[] randomArray(int n, OrderType o){
        Random rand = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =  StdRandom.uniform(-100000, 100000);
        }
        if(o.equals(OrderType.S))
            Arrays.sort(arr);
        else if(o.equals(OrderType.R))
            Arrays.sort(arr, Comparator.reverseOrder());
        else if(o.equals(OrderType.P)){
            Arrays.sort(arr, (arr.length / 2) , arr.length);
        }
//        for(int i =0; i< arr.length ; i++)
//            System.out.print(arr[i] + " ");
//        System.out.println();
        return arr;
    }

    public static void runMultiple(int n, int runs){
        Timer timer = new Timer();
        DecimalFormat df = new DecimalFormat("####.######");
        Benchmark_Timer benchmarkTimer = new Benchmark_Timer<Integer[]>("Insertion Sort", t -> new InsertionSort<Integer>().sort(t,0,t.length));
//        System.out.println("Running time for insertion sort on array of length :" + n);
        double meanTimeO, meanTimeP, meanTimeS, meanTimeR;
        meanTimeO = benchmarkTimer.runFromSupplier(() -> randomArray(n,OrderType.O),runs );
        meanTimeS = benchmarkTimer.runFromSupplier(() -> randomArray(n,OrderType.S),runs );
        meanTimeP = benchmarkTimer.runFromSupplier(() -> randomArray(n,OrderType.P),runs );
        meanTimeR = benchmarkTimer.runFromSupplier(() -> randomArray(n,OrderType.R),runs );
        System.out.println(n +" \t \t" + df.format(meanTimeO) +" \t \t " +df.format(meanTimeS)+" \t \t " +df.format(meanTimeP )+" \t \t " + df.format(meanTimeR));
    }
    public static void main(String[] args) {
        int inputLen = args.length;
        System.out.println("N \t \t  Random \t \t  Ordered  \t\t  Partial \t\t   Reverse");

        for(int i =0; i < inputLen ; i++){
            runMultiple(Integer.valueOf(args[i]),50);
        }
    }
}
