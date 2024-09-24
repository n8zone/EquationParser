import java.util.Scanner;

public class Main {
    private static final char EXPRESSION_TERMINATOR = '@';
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        String testEquation = keyboard.nextLine();
        parseEquation(testEquation);
    }

    public static void parseEquation(String equation) {
        equation = equation + " " + EXPRESSION_TERMINATOR; // Equation terminator?
        Scanner parser = new Scanner(equation).useDelimiter(" ");


        int[] operands = new int[2];

        // This pattern will always repeat. Expressions are always N o N o ... pattern.
        // 7 + 8 * 3
        // 8x3=24
        // 7+24=31
        int a = parser.nextInt();
        char op = parser.next().charAt(0);
        int b = parser.nextInt();
        char nxtOp = parser.next().charAt(0);
        int c = 0;
        System.out.println(nxtOp);

        if (nxtOp != EXPRESSION_TERMINATOR) {
            print("More to calculate");
            c = parser.nextInt();
        }

        printf("%d%s%d%s%d\n", a, op, b, nxtOp, c);

        switch(op) {
            case '+':
                print(a + b);
                break;
            case '-':
                print(a - b);
                break;
            case '*':
                print(a * b);
                break;
            case '/':
                print(a / b);
                break;
            case '^':
                print((int) Math.pow(a, b));
                break;
        }
    }

    public static void print(int num) {
        System.out.println(num);
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