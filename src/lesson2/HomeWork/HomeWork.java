package lesson2.HomeWork;

import java.util.Arrays;

public class HomeWork {

    private static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Задать целочисленный массив, состоящий из элементов 0 и 1.
    // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
    private static void invertArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 0){
                arr[i] = 1;
            }
            else {
                arr[i] = 0;
            }
        }
    }

    // Задать пустой целочисленный массив размером 8.
    // Написать метод, который помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
    private static void fillArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i] = 1 + (3*i);
        }
    }

    // Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], н
    // аписать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
    private static void updateArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < 6)
                arr[i] *= 2;
        }
    }

    // Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
    private static float getMaxElement(float[] arr){
        float result = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > result)
                result = arr[i];
        }
        return result;
    }

    // Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
    private static float getMinElement(float[] arr){
        float result = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < result)
                result = arr[i];
        }
        return result;
    }

    // * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое), з
    // аполнить его диагональные элементы единицами, используя цикл(ы);
    private static void fillMatrix(int[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                if ((i == j) || ( (i+j) == (arr.length - 1) )){
                    arr[i][j] = 1;
                }
            }
        }
    }

    //** Написать метод, в который передается не пустой одномерный целочисленный массив,
    // метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
    // Примеры: checkBalance([1, 1, 1, 2, 1]) → true,
    // checkBalance ([2, 1, 1, 2, 1]) → false,
    // checkBalance ([10, 1, 2, 3, 4]) → true.
    private static boolean checkBalance(int[] arr){
        int sum1 = 0;
        int sum2 = 0;
        if (arr.length == 1)
            return false;
        for (int i = 0; i < arr.length - 1; i ++){
            sum1 += arr[i];
            for (int j = i+1; j < arr.length; j++){
                sum2 += arr[j];
            }
            if (sum1 == sum2)
                return true;
            sum2 = 0;
        }
        return false;
    }

    // **** Написать метод, которому на вход подается одномерный массив и число
    // n (может быть положительным, или отрицательным),
    // при этом метод должен сместить все элементымассива на n позиций.
    // Для усложнения задачи нельзя пользоваться вспомогательными массивами
    private static void moveArrayRight(int[] arr){
        int temp = arr[arr.length-1];
        for (int i = arr.length-1; i > 0; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = temp;
    }

    private static void moveArrayLeft(int[] arr){
        int temp = arr[0];
        for (int i = 0; i < arr.length - 1; i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = temp;
    }

    private static void moveArray(int[] arr, int n){
        if (n > 0)
            for (int i = 0; i < n; i++){
                moveArrayRight(arr);
            }
        else if (n < 0)
            for (int i = 0; i < Math.abs(n); i++){
                moveArrayLeft(arr);
            }
    }

    public static void main(String[] args){
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1 ,0, 0};
        invertArray(arr);
        printArray(arr);
        int[] arr1 = new int[8];
        fillArray(arr1);
        printArray(arr1);
        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        updateArray(arr2);
        printArray(arr2);
        float[] arr3 = {1.321f, 1, 554, 45325, -98, 214321};
        float maxElement = getMaxElement(arr3);
        float minElement = getMinElement(arr3);
        System.out.println(maxElement + " " + minElement);
        int[][] arr4 = new int[5][5];
        fillMatrix(arr4);
        System.out.println(Arrays.deepToString(arr4));
        int[] arr5 = {1, 1, 1, 2, 1};
        int[] arr6 = {2, 1, 1, 2, 1};
        int[] arr7 = {10, 1, 2, 3, 4};
        System.out.println(checkBalance(arr5));
        System.out.println(checkBalance(arr6));
        System.out.println(checkBalance(arr7));
        int[] arr8 = {1, 2, 3, 4, 5};
        moveArray(arr8, -3);
        printArray(arr8);
    }
}
