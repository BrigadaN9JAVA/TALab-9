package Task1;

import java.lang.reflect.Array;
import java.util.*;

public class SubSet {
    private List<Integer> listA;
    private List<Integer> listB;
    public int lengthResult;
    public String subSet;
    public int[][] L;
    public int[] N;

    public SubSet(List<Integer> listA, List<Integer> listB){
        this.listA = listA;
        this.listB = listB;
        L = new int[listA.size()][listB.size()];
        N = new int[listA.size()];
    }

    public void solveProblem(){
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                N[i] = -1;
                if(listA.get(i).equals(listB.get(j))){
                    L[i][j] = 1;
                    for (int k = 0; k < i; k++) {
                        for (int l = 0; l < j; l++) {
                            if(listA.get(k).equals(listB.get(l)) && listA.get(k)<listA.get(i) && L[i][j] < L[k][l]+1){
                                L[i][j] = L[k][l]+1;
                                N[i] = k;
                            }
                        }
                    }
                }
            }
        }
        int maxI = 0;
        int maxJ = 0;
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                if (L[maxI][maxJ] < L[i][j]) {
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        lengthResult = L[maxI][maxJ];
        int lastPos = maxI;

        ArrayList<String> helpingArray = new ArrayList<>();

        if(lengthResult != 0) {
            while (lastPos != -1) {
                helpingArray.add(listA.get(lastPos).toString());
                lastPos = N[lastPos];
            }


            subSet = "";
            for (int i = helpingArray.size() - 1; i >= 0; i--) {
                subSet += helpingArray.get(i) + " ";
            }
        }
        else {
            subSet = "SubSet doesn`t exist";
        }
    }

    public int solveAdditionProblem1(){
        //int[][] matrix = new int[2][2];
        //matrix[0][0] = maxOfSet(listA);
        //matrix[0][1] = minOfSet(listA);
        //matrix[1][0] = maxOfSet(listB);
        //matrix[1][1] = minOfSet(listB);
        //int max = (matrix[0][0] > matrix[1][0]) ? matrix[0][0] : matrix[1][0];
        //int min = (matrix[0][1] < matrix[1][1]) ? matrix[0][1] : matrix[1][1];
        int max = Math.max(maxOfSet(listA), maxOfSet(listB));
        int min = Math.min(minOfSet(listA), minOfSet(listB));
        return max - min;
    }

    private int maxOfSet(List<Integer> list){
        int max = list.get(0);
        for (int num: list) {
            if(max < num)
                max = num;
        }
        return max;
    }

    private int minOfSet(List<Integer> list){
        int min = list.get(0);
        for (int num: list) {
            if(min > num)
                min = num;
        }
        return min;
    }

    public String solveAdditionProblem2(){
        String result = "";

        if(listA.size()+listB.size() < 5){
            ArrayList<Integer> list = new ArrayList<>();
            list.addAll(listA);
            list.addAll(listB);
            double[][] resultInt = new double[list.size()][list.size()];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    resultInt[i][j] = (list.get(j)==0) ? 0 : (double)list.get(i)/list.get(j);
                }
            }
            int maxPosI = 0;
            int maxPosJ = 1;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if(resultInt[maxPosI][maxPosJ] < resultInt[i][j] && i != j) {
                        maxPosI = i;
                        maxPosJ = j;
                    }
                }
            }

            double a = list.get(maxPosI);
            double b = list.get(maxPosJ);
            if(a == 0)
                result += (int)a + "/" + (int)b;
            else if(b == 0)
                result += (int)b + "/" + (int)a;
            else if(a / b > b / a)
                result += (int)a + "/" + (int)b;
            else
                result += (int)b + "/" + (int)a;
        }
        else{
            int[] posNumber = maxAndMinPosOfSets(listA, listB);
            int[] negNumber = maxAndMinNegOfSets(listA, listB);
            if(posNumber[0]/posNumber[1]>negNumber[1]/negNumber[0]){
                result = posNumber[0]+"/"+posNumber[1];
            }
            else{
                result += negNumber[1]+"/"+negNumber[0];
            }
        }
        return result;
    }

    private int[] maxAndMinPosOfSets(List<Integer> list1, List<Integer> list2){
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer num:list1) {
            if(num > 0)
                list.add(num);
        }
        for (Integer num:list2) {
            if(num > 0)
                list.add(num);
        }
        int[] result = new int[2];
        result[0] = (list.size() > 0) ? maxOfSet(list) : 1;
        result[1] = (list.size() > 0) ? minOfSet(list) : 1;
        return result;
    }

    private int[] maxAndMinNegOfSets(List<Integer> list1, List<Integer> list2){
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer num:list1) {
            if(num < 0)
                list.add(num);
        }
        for (Integer num:list2) {
            if(num < 0)
                list.add(num);
        }
        int[] result = new int[2];
        result[0] = (list.size() > 0) ? maxOfSet(list) : 1;
        result[1] = (list.size() > 0) ? minOfSet(list) : 1;
        return result;
    }

}
