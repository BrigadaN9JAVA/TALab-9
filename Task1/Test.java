package Task1;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 0, 2};
        Integer[] b = new Integer[]{-6, 5, 2, -1};
        List<Integer> listA = new ArrayList<>(Arrays.asList(a));
        List<Integer> listB = new ArrayList<>(Arrays.asList(b));
        SubSet ss = new SubSet(listA, listB);

        ss.solveProblem();
        System.out.println(ss.lengthResult);
        System.out.println(ss.subSet);
        System.out.println(ss.solveAdditionProblem1());
        System.out.println(ss.solveAdditionProblem2());
    }
}
