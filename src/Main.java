import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final char EXPRESSION_TERMINATOR = '@';
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("-35 + 3 - 2");
        parseEquation(eq);
    }

    public static ArrayList<Character> tokenizeEquation(String equation) {
        ArrayList<Character> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter("");

        while (tokenizer.hasNext()) {
            char nextToken = tokenizer.next().charAt(0);
            if (nextToken != ' ')
                tokens.add(nextToken);
        }

        print(tokens.size());
        print(tokens.toString());
        return tokens;
    }

    public static void parseEquation(ArrayList<Character> equation) {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        String constructedNumber = "";
        for (int i = 0; i < equation.size(); i++) {
            char nextToken = equation.get(i);
            print(nextToken);

            if (Character.isDigit(nextToken)) {
                constructedNumber += nextToken;
            } else {
                numbers.add(Integer.parseInt(constructedNumber));
                constructedNumber = "";
                operators.add(nextToken);
            }
        }

        numbers.add(Integer.parseInt(constructedNumber));

        print(numbers.toString());
        print(operators.toString());

    }

    public static int calculate(int a, int b, char operator) {
        printf("%d %c %d = ", a, operator, b);
        int result;
        switch(operator) {
            case '+':
                result = (a + b);
                break;
            case '-':
                result = (a - b);
                break;
            case '*':
                result = (a * b);
                break;
            case '/':
                result = (a / b);
                break;
            case '^':
                result = ((int) Math.pow(a, b));
                break;
            default:
                result = 0;
        }
        return result;
    }

    public static void print(int num) {
        System.out.println(num);
    }

    public static void print(char c) {
        System.out.println(c);
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void print(String str, double... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            print(str  + " " + (int) numbers[i]);
        }
    }

    public static void printf(String str, Object... objects) {
        System.out.printf(str, objects);
    }
}