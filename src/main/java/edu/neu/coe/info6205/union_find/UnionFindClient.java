package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFindClient {

    public static int count(int n){
        UF_HWQUPC uf = new UF_HWQUPC(n);
        int count = 0;
        int i;
        int j;
        Random random = new Random();
        while(uf.components() != 1){
            i = random.nextInt(n);
            j = random.nextInt(n);
            uf.union(i,j);
            count++;
        }

        return count;
    }


    public static void main(String[] args) {
        int m;
        int n;
        int count = Integer.valueOf(args[0]);
        for(int i = 1; i < args.length ; i++ ){
            m = 0;
            n = Integer.valueOf(args[i]);
            for(int j =0 ;j < count ; j++){
                m += count(n);
            }
            m = m/count;
            System.out.printf("Number of pairs generated to reduce the number of components from %d to 1 is %d" ,n,m);
            System.out.println();
        }
    }
}
