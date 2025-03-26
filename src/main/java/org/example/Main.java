package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static String upperCaseFirstChar(String text){
        String result ="";
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException("Input cannot be null or empty");

        return result;
    }

    public static int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0){
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max)
                max = number;
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] array = {34, 1, 23, 4, 3, 12, 45, 33, 6, 22};
        // tim max
        int max = Arrays.stream(array)
                .max(Comparator.naturalOrder())
                .get();
        System.out.println(max);

        int min = Arrays.stream(array)
                .min(Comparator.naturalOrder())
                .get();
        System.out.println(min);

        //viet chuong trinh tinh tong cua mot mang so nguyen cho truoc
        int sum = Arrays.stream(array)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        //Viet ctrinh tim ra nhung so  <= 30 trong mang so nguyen cho truoc
        List<Integer> subList = Arrays.stream(array)
                .filter(i -> i <= 30)
                .toList();

        System.out.println(subList);

        System.out.println((Arrays.stream(array)
                .map(i -> i.toString())
                .toList()));
    }


}