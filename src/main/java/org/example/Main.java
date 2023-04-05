package org.example;

import java.util.Arrays;

import static java.lang.Integer.sum;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        findMedianSortedArrays(new int[]{1, 3}, new int[]{2,4});
//    }
//    static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int n = nums1.length + nums2.length;
//        int[] numAll =new int[n];
//        System.arraycopy(nums1, 0, numAll, 0, nums1.length);
//        System.arraycopy(nums2, 0, numAll, nums1.length, nums2.length);
//        Arrays.sort(numAll);
//        int count = 0;
//        for (int i = 0; i < numAll.length; i++) {
//            count += numAll[i];
//        }
//        float res = 0;
//        if(numAll.length % 2 == 0){
//            res = (float) sum(numAll[n / 2 - 1],numAll[ n / 2]) / 2;
//        }else{
//            int j = (n + 1) / 2 - 1 ;
//            res = numAll[j];
//        }
//        System.out.println(res);
//        return res;
    }
    String[] country = {"","+*-","+**-","+***-"};
    public String maskPII(String s){
        int at = s.indexOf("@");
        if(at > 0){
            s = s.toLowerCase();
            return (s.charAt(0) + "*****" + s.substring(at - 1)).toLowerCase();
        }
        s = s.replaceAll("[^0-9]","");
        return country[s.length() - 10] + "***-***-" + s.substring(s.length() - 4);
    }
}