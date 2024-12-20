package Workers;



public class Main {

    //* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
    private static void increaseSalary(Worker[] workers, int age, double amount){
        for (Worker w:workers){
            if (w.getAge() >= age){
                w.setSalary(w.getSalary() + amount);
            }
        }
    }

    //* Подсчитать средние арифметические зарплаты и возраста
    private static double arifmeticMean(int[] arr){
        int sum = 0;
        for (int e:arr){
            sum = sum + e;
        }
        return sum / arr.length;
    }

    private static double arifmeticMean(double[] arr){
        double sum = 0;
        for (double e:arr){
            sum = sum + e;
        }
        return sum / arr.length;
    }

    public static void main(String[] args){

        //Создать массив из 5 сотрудников.
        Worker[] workers = {
                new Worker("Backham", 50, 50500),
                new Worker("Ronaldinho", 39, 40000),
                new Worker("Roberto Carlos", 55, 31000),
                new Worker("Zidane", 52, 65000),
                new Worker("Rooney", 29, 35000),
        };

        //Вывести при помощи методов из пункта 3 ФИО и возраст.
        for (int i = 0; i < workers.length; i++){
            System.out.println(String.format("%s %d", workers[i].getName(), workers[i].getAge()));
        }

        //С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        for (Worker w: workers){
            if (w.getAge() >= 40){
                System.out.println(String.format("ФИО - %s, возраст - %d, зарплата - %.2f", w.getName(), w.getAge(), w.getSalary()));
            }
        }
        //* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
        increaseSalary(workers, 45, 5000);

        for (Worker w: workers){
            System.out.println(String.format("ФИО - %s, возраст - %d, зарплата - %.2f", w.getName(), w.getAge(), w.getSalary()));
        }

        //* Подсчитать средние арифметические зарплаты и возраста
        int[] arr1 = new int[workers.length];
        double[] arr2 = new double[workers.length];
        for (int i = 0; i < workers.length; i++){
            arr1[i] = workers[i].getAge();
            arr2[i] = workers[i].getSalary();
        }

        System.out.println(String.format("Средний возраст - %f, Средняя зарплата - %.2f", arifmeticMean(arr1), arifmeticMean(arr2)));

        System.out.println();
        for (Worker w: workers){
            System.out.println(String.format("ФИО - %s, возраст - %d, зарплата - %.2f, ИД - %d", w.getName(), w.getAge(), w.getSalary(), w.getId()));
        }
        Worker worker1 = new Worker("Pirlo", 56, 234654);
        for (Worker w: workers){
            System.out.println(String.format("ФИО - %s, возраст - %d, зарплата - %.2f, ИД - %d", w.getName(), w.getAge(), w.getSalary(), w.getId()));
        }
        System.out.println(String.format("ФИО - %s, возраст - %d, зарплата - %.2f, ИД - %d", worker1.getName(), worker1.getAge(), worker1.getSalary(), worker1.getId()));
    }
}
