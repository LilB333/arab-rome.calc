import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int res;

    public static void main(String[] args) throws ScannerException {
        System.out.println("Введите операцию между двумя числами. В формате \"7 + 5\" или \"V + III\": ");
        
        // считываем строку
        String str = scanner.nextLine();
        
        // передаем строку в метод
        System.out.println(calc(str));
    }

    public static String calc(String input) throws ScannerException {
        int a, b;
        char OP;
        int result;
        boolean romanCheck;
        String statement;

        // разделяем строку по пробелу
        String[] numbers = input.split(" ");
        // выводим исключение, если разделенных строк в массиве не 3
        if (numbers.length != 3) {
            throw new ScannerException("Недействительный ввод");
        }
        
// определяем знак операции
        OP = arithmetic(numbers[1].charAt(0));

// проверяем числа на их принадлежность
        if (romanChecking.trueRoman(numbers[0]) && romanChecking.trueRoman(numbers[2])) {
            a = romanInt(numbers[0]);
            b = romanInt(numbers[2]);
            romanCheck = true;
        } else if (!romanChecking.trueRoman(numbers[0]) && !romanChecking.trueRoman(numbers[2])) {
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[2]);
            if (((a<=10)&&(a>0))&&((b<=10)&&(b>0))) {
                romanCheck = false;
            }
            else {
                throw new ScannerException("Арабские числа не подходят условиям");
            }
        } else {
            throw new ScannerException("Нельзя вводить арабские и римские цифры одновременно");
        }

// в зависимости от принадлежности выводим результат
        result = calculate(a, b, OP);
        if (romanCheck == true) {
            String resultRoman = romanChecking.romanRes(result);
            statement = ("Ответ для римских цифр:\n" + numbers[0] + " " + OP + " " + numbers[2] + " = " + resultRoman);
            return statement;
        }
        else {
            statement = ("Ответ для арабских цифр:\n" + a + " " + OP + " " + b + " = " + result);
            return statement;
            }
        }
    }
// проводим операцию в заисимости от знака
    private static int calculate(int A, int B, char op) {
        switch (op) {
            case '-':
                res = A - B;
                break;
            case '+':
                res = A + B;
                break;
            case '*':
                res = A * B;
                break;
            case '/':
                res = A / B;
                break;
        }
        return res;
    }

// вычисляем знак операции
    private static char arithmetic(char znak) throws ScannerException {
        char oper;
        if (znak == '-') {
            oper = '-';
        } else if (znak == '+') {
            oper = '+';
        } else if (znak == '*') {
            oper = '*';
        } else if (znak == '/') {
            oper = '/';
        } else {
            throw new ScannerException("Знак не соответствует правилам");
        }
        return oper;
    }

// превращаем римские цифры в целочисленные
    private static int romanInt(String rome) {
        if (rome.equals("I")) {
            return 1;
        } else if (rome.equals("II")) {
            return 2;
        } else if (rome.equals("III")) {
            return 3;
        } else if (rome.equals("IV")) {
            return 4;
        } else if (rome.equals("V")) {
            return 5;
        } else if (rome.equals("VI")) {
            return 6;
        } else if (rome.equals("VII")) {
            return 7;
        } else if (rome.equals("VIII")) {
            return 8;
        } else if (rome.equals("IX")) {
            return 9;
        } else if (rome.equals("X")) {
            return 10;
        }
        return -1;
    }
}

// класс для работы с римскими числами и для различения с арабскими
    class romanChecking {
    static String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

//  переводим целочисленное значение ответа в конечное римское
    public static String romanRes(int numArabian) {
        String res = null;
        try {
            res = roman[numArabian-1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Результат операций с римскими числами не может быть отрицательным или равным 0");
        }
        return res;
    }
        
// проверяем является ли введенное число римским
    public static boolean trueRoman(String rome) {
        for (int i = 1; i <= 10; i++) {
            if (rome.equals(roman[i - 1])) {
                return true;
            }
        }
        
// проверяем подходит ли римское число условиям (в данном случае, если число не парсится в целочисленное, то это значит, что это - предположительно римское число (или другой символ) меньше I и больше X
        try {
            int var = Integer.parseInt(rome);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Римские числа не подходят условиям");
        }

        return false;
    }
}

// класс ScannerException выводит присваиваемое в скобках выражение в поле вывода
class ScannerException extends Exception{
    public ScannerException(String description){
        super(description);
    }
}

