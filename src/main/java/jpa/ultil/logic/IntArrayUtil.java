package jpa.ultil.logic;

import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;

public class IntArrayUtil {
    public static int[] remove(int[] ints,int index) {
        ArrayList<Integer> arrList = new ArrayList<>(index);
        arrList.remove(index);//will remove the 4th index and the size will decrease by 1
        return converListToArray(arrList);
    }
    public static int[] converListToArray(List<Integer> list){
        int[] ints = new int[list.size()];
        for (int i = 0 ; i< list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static ArrayList<Integer> converArrayToList(int[] ints){
        ArrayList<Integer> arrList = new ArrayList<>();
            for (int i : ints){
                arrList.add(i);
            }
            return arrList;
    }
    public static int[] join(int[] ints,int[] ints2) {
        int[] rs = new int[ints.length+ints2.length];
        for (int i = 0 ; i< ints.length;i++){
            rs[i] = ints[i];
        }
        for (int i = 0 ; i< ints2.length;i++){
            rs[i+ints.length] = ints2[i];
        }
        return rs;
    }
}
