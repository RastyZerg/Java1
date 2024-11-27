package lesson1.HomeWork;


public class HomeWork {

    // Написать метод вычисляющий выражение a * (b + (c / d))
    // и возвращающий результат с плавающей точкой,
    // где a, b, c, d – целочисленные входные параметры этого метода;
    private static float calculating(int a, int b, int c, int d){
        return a * (b+((c*1.0f) / d));
    }

    // Написать метод, принимающий на вход два целых числа,
    // и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
    // если да – вернуть true, в противном случае – false;
    private static boolean checkSum(int a, int b){

        return (a+b)<=20 && (a+b)>=10;
    }

    // Написать метод, которому в качестве параметра передается целое число,
    // метод должен проверить положительное ли число передали, или отрицательное.
    // Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль
    private static String checkNumber(int a){
        String result;
        if (a < 0)
            result = String.format("Число %d отрицательное", a);
        else
            result = String.format("Число %d положительное", a);
        return result;
    }

    // Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вернуть приветственное сообщение «Привет, переданное_имя!»;
    // Вывести приветствие в консоль.
    private static String helloUser(String name){
        return String.format("Привет, %s!", name);
    }

    // *Написать метод, который определяет является ли год високосным.
    // Каждый 4-й год является високосным, кроме каждого 100-го,
    // при этом каждый 400-й – високосный.
    // Для проверки работы вывести результаты работы метода в консоль
    private static String checkLeapYear(int year){
        String result;
        if ( (year % 400 == 0) || ( (year % 4 == 0) && (year % 100 != 0) ) )
            result = String.format("Год %s - високосный", year);
        else result = String.format("Год %s - не високосный", year);
        return result;
    }

    public static void main(String[] args){
        System.out.println(calculating(1,2,3,4));
        System.out.println(calculating(5,6,4,2));
        System.out.println(checkSum(12, 8));
        System.out.println(checkSum(15, 18));
        System.out.println(checkSum(0, -5));
        System.out.println(checkNumber(0));
        System.out.println(checkNumber(-8));
        System.out.println(checkNumber(15));
        System.out.println(helloUser("Anton"));
        System.out.println(helloUser("Rasty"));
        System.out.println(checkLeapYear(0));
        System.out.println(checkLeapYear(88));
        System.out.println(checkLeapYear(300));
        System.out.println(checkLeapYear(1200));
        System.out.println(checkLeapYear(1300));
        System.out.println(checkLeapYear(1500));
        System.out.println(checkLeapYear(1600));
    }
}
