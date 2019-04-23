package chapter4;

import java.util.*;

/**
 * Created by ryder on 2017/7/22.
 * 字符串的排列
 */
public class P197_StringPermutation {
    public static List<char[]> permutation(char[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        List<char[]> ret = new LinkedList<>();
        permutationCore(strs, ret, 0);
        return ret;
    }
    //下标为bound的字符依次与[bound,length)的字符交换，如果相同不交换，直到最后一个元素为止。
    //如a,b,c
    //0与0交换,得a,b,c => 1与1交换,得a,b,c =>2与2交换,得a,b,c(存入)
    //                => 1与2交换，得a,c,b =>2与2交换,得a,c.b(存入)
    //0与1交换,得b,a,c => 1与1交换,得b,a,c =>2与2交换,得b,a,c(存入)
    //                => 1与2交换，得b,c,a =>2与2交换,得b,c,a(存入)
    //0与2交换,得c,b,a => 1与1交换,得c,b,a =>2与2交换,得c,b,a(存入)
    //                => 1与2交换，得c,a,b =>2与2交换,得c,a.b(存入)

    //如a,a,b
    //0与0交换,得a,a,b => 1与1交换,得a,a,b =>2与2交换,得a,a,b(存入)
    //                => 1与2交换，得a,b,a =>2与2交换,得a,b,a(存入)
    //0与1相同,跳过
    //0与2交换,得b,a,a =>2与2交换，得b,a,a(存入)
    public static void permutationCore(char[] strs, List<char[]> ret, int bound) {
        if (bound == strs.length)
            ret.add(Arrays.copyOf(strs, strs.length));
        Set<Character> set = new HashSet<>();
        for (int i = bound; i < strs.length; i++) {
            //当set中已经有strs[i]，add的时候会返回false
            if (set.add(strs[i])) { //DFS的思想，递归过程中数组是如何完成交换和恢复的
                swap(strs, bound, i);//交换
                permutationCore(strs, ret, bound + 1);//确定好i位置，对cha[i+1~length-1]范围内的字符数组完成全排列
                swap(strs, bound, i);//恢复原数组
            }
        }
    }

    public static void swap(char[] strs, int x, int y) {
        char temp = strs[x];
        strs[x] = strs[y];
        strs[y] = temp;
    }

    public static void main(String[] args) {
        char[] strs = {'a', 'b', 'c'};
        List<char[]> ret = permutation(strs);
        for (char[] item : ret) {
            for (int i = 0; i < item.length; i++)
                System.out.print(item[i]);
            System.out.println();
        }
        System.out.println();
        char[] strs2 = {'a', 'a', 'b'};
        List<char[]> ret2 = permutation(strs2);
        for (char[] item : ret2) {
            for (int i = 0; i < item.length; i++)
                System.out.print(item[i]);
            System.out.println();
        }
    }
}
