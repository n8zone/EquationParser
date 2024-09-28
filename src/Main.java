import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("-5.2 + 3 - 2");

        var samplePostfix = createSamplePostFix("10 5 * 2 +");
        ArrayList<Token> samplePostfix2 = createSamplePostFix("-5 5 *");
        var sample3 = createSamplePostFix("5 2 3 * -");





        EZ.println("Result: %.2f", test(sample3));
        EZ.println("Result: %.2f", test(samplePostfix2));
        EZ.println("Result: %.2f", test(samplePostfix));
        EZ.println("Result: %.2f", test(createSamplePostFix("10 5 * 2 / 10 2 ^ -")));

    }

    // TOKENIZER -STRICTLY- HANDLES CONVERTING INPUT INTO _TOKENS_ NO LOGIC!!!
    // ALL LOGIC IS HANDLED BY PARSER
    public static ArrayList<Token> tokenizeEquation(String equation) {
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter(" ");

        while (tokenizer.hasNext()) {
            Token nextToken = new Token(tokenizer.next());

            if (nextToken.isLegal()) {
                tokens.add(nextToken);
            }

        }

        EZ.println(tokens.size());
        EZ.println(tokens.toString());


        return tokens;
    }

    // Parser
    // Handles operator precedence
    // Builds AST
    // Convert to postfix
    // Push operands onto stack, when encountering an operator create new 'parent' ASTNode with operands as it's children

    public static ArrayList<Token> toPostfix(ArrayList<Token> tokenizedEquation) {
       // Shunting-yard algorithm

        return tokenizedEquation;
    }

    public static Double test(ArrayList<Token> postfixEq) {
        Stack<Double> operands = new Stack<>();

        for (Token token : postfixEq) {
            try {
                operands.push(token.toNumber());
            } catch (Exception e) {
                Double a, b, c;
                b = operands.pop();
                a = operands.pop();

                c = calculate(a, b, token.getValue().charAt(0));

                EZ.println("Performing: %.1f, %c, %.1f",a, token.getValue().charAt(0), b);

                operands.push(c);
            }
        }
        return operands.pop();
    }

    private static double calculate(double a, double b, char operator) {
        double result = switch (operator) {
            case '+' -> (a + b);
            case '-' -> (a - b);
            case '*' -> (a * b);
            case '/' -> (a / b);
            case '^' -> (Math.pow(a, b));
            default -> 0;
        };
        return result;
    }

    public static ArrayList<Token> createSamplePostFix(String rawPostFixString) {
        Scanner parser = new Scanner(rawPostFixString).useDelimiter(" ");
        ArrayList<Token> samplePostFix = new ArrayList<>();

        while (parser.hasNext()) {
            String value = parser.next();
            samplePostFix.add(new Token(value));
        }

        return samplePostFix;
    }
}