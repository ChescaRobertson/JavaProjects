import java.util.Arrays;
//import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

public class Calculator {
    private static String Add(int[] numbers, int arrayLen) {
        String output;
        int result;

        result = 0;
        output = "";

        for( int i : numbers) {
            result += i;
            if (i == numbers[arrayLen - 1]) {
                output = output + i + " = " + result;
            } else {
                output = output + i + " + ";
            }
        }
        return output;
    }

    private static String Subtract(int[] numbers, int arrayLen) {
        String output;
        int result;

        result = 0;
        output = "";

        for(int i : numbers) {
            result -= i;
            if (i == numbers[arrayLen - 1]) {
                output = output + i + " = " + result;
            } else {
                output = output + i + " - ";
            }
        }
        return output;
    }

    private static String Multiply(int numbers[], int arrayLen) {
        String output;
        int result;

        result = 1;
        output = "";

        for(int i : numbers) {
            result *= i;
            if (i == numbers[arrayLen - 1]) {
                output = output + i + " = " + result;
            } else {
                output = output + i + " * ";
            }
        }
        return output;
    }

    private static String Divide(int[] numbers, int arrayLen) {
        String output;
        float result;

        result = numbers[arrayLen - 1];
        output = numbers[arrayLen - 1] + " / ";

        for (int i = arrayLen - 2; i >= 0; i--) {
            result /= numbers[i];
            if (numbers[i] == numbers[0]) {
                output = output + numbers[i] + " = " + result;
            } else {
                output = output + numbers[i] + " / ";
            }
        }
        return output;
    }

    private static int GetArrayLen() {
        int arrayLen;
        arrayLen = 0;
        boolean repeat = true;

        while (repeat) {
            System.out.println("How many numbers do you want to enter?: ");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                arrayLen = sc.nextInt();
                repeat = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        return arrayLen;
    }

    private static int[] GetNumbersArray(int arrayLen) {
        int[] numbers = new int[arrayLen];

        for (int i = 0; i < arrayLen; i++) {
            boolean repeat = true;
            while (repeat) {
                System.out.println("Please enter a number: ");
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()){
                    numbers[i] = sc.nextInt();
                    repeat = false;
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
        Arrays.sort(numbers);
        return numbers;
    }

    private static char GetOperator() {
        char operator;
        boolean repeat = true;

        operator = ' ';

        while (repeat) {
            System.out.println("Choose an operation: '+', '-', '*', '/', 'r' for range or 'a' for all:");
            Scanner sc = new Scanner(System.in);
            operator = sc.next().charAt(0);
            if ((operator == '+') || (operator == '-') || (operator == '*') || (operator == '/') || (operator == 'a') || (operator == 'r')) {
                repeat = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        return operator;
    }

    private static void InnerRange(int[] numbers, int arrayLen) {
        for (int i = 0; i < arrayLen - 1; i++) {
            int lowernum, uppernum, result;
            String output;
            output = "";
            result = 0;
            lowernum = numbers[i];
            uppernum = numbers[i+1];

            for (int j = lowernum; j <= uppernum; j++) {
                result += j;
                if (j == uppernum) {
                    output = output + j + " = " + result;
                } else {
                    output = output + j + " + ";
                }
            }
            System.out.println(output);
        }
    }

    private static void FullRange(int[] numbers, int arrayLen) {
        int result;
        result = 0;
        String output;
        output = "";

        for (int i = numbers[0]; i <= numbers[arrayLen - 1]; i++) {
            result += i;
            if (i == numbers[arrayLen - 1]) {
                output = output + i + " = " + result;
            } else {
                output = output + i + " + ";
            }
        }
        System.out.println(output);
    }

    private static void Calculate(int arrayLen, int[] numbers, char operator) {
        switch (operator) {
            case ('+'):
                System.out.println(Add(numbers, arrayLen));
                break;
            case ('-'):
                System.out.println(Subtract(numbers, arrayLen));
                break;
            case ('*'):
                System.out.println(Multiply(numbers, arrayLen));
                break;
            case ('/'):
                System.out.println(Divide(numbers, arrayLen));
                break;
            case ('a'):
                System.out.println(Add(numbers, arrayLen));
                System.out.println(Subtract(numbers, arrayLen));
                System.out.println(Multiply(numbers, arrayLen));
                System.out.println(Divide(numbers, arrayLen));
                InnerRange(numbers, arrayLen);
                FullRange(numbers, arrayLen);
                break;
            case ('r'):
                InnerRange(numbers, arrayLen);
                FullRange(numbers, arrayLen);
                break;
        }
    }

    private static void trialWithLists(int [] numbers) {
        int result = 0;

        List<Integer> myList = new ArrayList<Integer>();

        for(int i = 0; i <= numbers.length - 1; i++) {
            myList.add(numbers[i]);
        }

       /* for(int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
            result = result + myList.get(i);
        } */

        for(Integer i : myList) {
           System.out.println(i);
            result = result + i;
        }
        System.out.println(result);
    }

    // Fixed bug in enhanced for loop above: was trying to add myList.get(i) to result
    // when i is the number not the index

    public static void main(String[] args) {
        int arrayLen;
        char operator;
        boolean useArrays;
        useArrays = true;

        if (useArrays) {
            if (args.length == 0) {
                arrayLen = GetArrayLen();
                int[] numbers;
                numbers = GetNumbersArray(arrayLen);
                //operator = GetOperator();
                //Calculate(arrayLen, numbers, operator);
                trialWithLists(numbers);
            } else {
                int[] myArray = new int[args.length];
                for (int i = 0; i < args.length; i++) {
                    myArray[i] = Integer.parseInt(args[i]);
                }
                Arrays.sort(myArray);
                operator = 'a';
                Calculate(args.length, myArray, operator);
                trialWithLists(myArray);
            }

        } else {
            //List<Integer> myArrayList = new ArrayList<Integer>();
            //List<Integer> ArrayList = Arrays.asList();
        }
    }

}
