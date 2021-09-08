package com.dsk.interview;

import java.util.HashMap;
import java.util.Map;

public class WaterKing {


    /** 该方法不符合条件的限制，因为使用和哈希表，额外的空间复杂度为O(n)
     * 方法正确，可作为对数器
     * @param arr
     * @return
     */
    public static int verify(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int N = arr.length;
        for (Map.Entry<Integer, Integer> record : map.entrySet()) {
            if (record.getValue() > (N >> 1)) {
                return record.getKey();
            }
        }
        return -1;
    }


    /**
     * 血量  0-无候选   >0-有候选  候选值
     *  （1）无候选 当前数立为候选，血量=1
     *  （2）有候选 1.当前数==候选值  血量+1
     *             2.当前数！=候选值  血量-1
     * @param args
     */

    public static void main(String[] args) {
        int[] a = {3,3,3,2,3,4};
        System.out.println(verify(a));
    }
}
