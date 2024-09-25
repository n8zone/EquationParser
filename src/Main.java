import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("5 + 3 - 2");
        print(calculate(5, (calculate(3, 2, '*')), '+'));

        ASTNode test1 = new ASTNode("+");
        ASTNode left = new ASTNode("5");

        ASTNode test2 = new ASTNode("*");
        ASTNode left2 = new ASTNode("3");
        ASTNode right2 = new ASTNode("2");

        ASTNode test3 = new ASTNode("-");
        ASTNode left3 = new ASTNode("7");
        ASTNode right3 = new ASTNode("1");


        test1.left = left;
        test1.right = test2;

        test2.left = left2;
        test2.right = test3;

        test3.left = left3;
        test3.right = right3;

        print(test1.Compute());

        print(parseEquation(eq).toString());

        ArrayList<String> parsedEquation = parseEquation(eq);

        ASTNode treeStart = buildASTree(eq);
        print(treeStart.toString());
        print(treeStart.right.toString());


    }

    public static ArrayList<String> tokenizeEquation(String equation) {
        ArrayList<String> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter("");

        while (tokenizer.hasNext()) {
            String nextToken = tokenizer.next();
            if (!nextToken.equals(" "))
                tokens.add(nextToken);
        }

        print(tokens.size());
        print(tokens.toString());
        return tokens;
    }

    public static ArrayList<String> parseEquation(ArrayList<String> equation) {
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();

        String constructedNumber = "";
        for (int i = 0; i < equation.size(); i++) {
            String nextToken = equation.get(i);
            print(nextToken);

            if (Character.isDigit(nextToken.charAt(0))) {
                constructedNumber += nextToken;
            } else {
                numbers.add(constructedNumber);
                constructedNumber = "";
                operators.add(nextToken);
            }
        }

        numbers.add(constructedNumber);

        print(numbers.toString());
        print(operators.toString());

        ArrayList<String> parsed = new ArrayList<>();

        parsed.addAll(numbers);
        parsed.addAll(operators);

        return parsed;

    }

    public static ASTNode buildASTree(ArrayList<String> tokenizedEquation) {
        printf("Generating ASTree from: %s\n", tokenizedEquation.toString());

        String nodeValue = tokenizedEquation.removeFirst();
        ASTNode constructedNode = new ASTNode(nodeValue);
        constructedNode.right = buildNextNode(tokenizedEquation.removeFirst(), constructedNode);

        return constructedNode;
    }

    public static ASTNode buildNextNode(String value, ASTNode prev) {
        printf("Building node from: %s\n", value);
        ASTNode constructedNode = new ASTNode(value);
        constructedNode.left = prev;
        return constructedNode;
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