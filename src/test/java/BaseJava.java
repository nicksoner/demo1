import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class BaseJava {
        int a = 1;
        int b = 2;
        float c = 3.5F;

        //0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        @Test
        void BaseJava0() {
            System.out.println(a + b);
            System.out.println(a - b);
            System.out.println(a * b);
            System.out.println(a / b);
        }

        //1) применить несколько арифметических операций над int и double в одном выражении
        @Test
        void BaseJava1() {
            System.out.println(a + c);
            System.out.println(a - c);
            System.out.println(a * c);
            System.out.println(a / c);
        }

        //2) применить несколько логических операций ( < , >, >=, <= )
        @Test
        void BaseJava2() {
            System.out.println(a < c);
            System.out.println(a > c);
            System.out.println(a >= c);
            System.out.println(a <= c);
        }

        //3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой
        // (какие максимальные и минимальные значения есть, как их получить) и переполнение
        @Test
        void BaseJava3() {
            System.out.println(Float.MIN_VALUE);
            System.out.println(Float.MAX_VALUE);
            System.out.println(Float.MIN_NORMAL);
            System.out.println(Double.MIN_VALUE);
            System.out.println(Double.MAX_VALUE);
            System.out.println(Double.MIN_NORMAL);
            //4) получить переполнение при арифметической операции
            System.out.println(Float.MAX_VALUE * 2);
            System.out.println(Double.MAX_VALUE * 2);
        }
    }
