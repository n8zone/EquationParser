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





        EZ.println("Result: %.2f", evaluatePostfix(sample3));
        EZ.println("Result: %.2f", evaluatePostfix(samplePostfix2));
        EZ.println("Result: %.2f", evaluatePostfix(samplePostfix));
        EZ.println("Result: %.2f", evaluatePostfix(createSamplePostFix("10 5 * 2 / 10 2 ^ -")));

        var test = toPostfix(tokenizeEquation("5 ^ 2 + 2 * 5"));

        EZ.println("Result: %.2f", evaluatePostfix(test));
        EZ.println("Result: %.2f", evaluatePostfix(toPostfix(eq)));

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

    public static int getPrecedence(char operator) {
        return switch(operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    public static ArrayList<Token> toPostfix(ArrayList<Token> tokenizedEquation) {
       // Shunting-yard algorithm

        ArrayList<Token> postfixEquation = new ArrayList<>();
        Stack<Token> operators = new Stack<>();

        for (Token token : tokenizedEquation) {
            EZ.printAnyln(operators);
            EZ.printAnyln(token);
            if (token.isNumber()) {
                postfixEquation.add(token);
            } else  {

                if (operators.isEmpty()) {
                    operators.push(token);
                } else {
                    Token topToken = operators.peek();
                    try {
                        int topTokenPrecedence = getPrecedence(topToken.toOperator());
                        int curTokenPrecedence = getPrecedence(token.toOperator());
                        if (curTokenPrecedence > topTokenPrecedence) {
                            operators.push(token);
                        } else {
                            do {
                                postfixEquation.add(operators.pop());
                            } while (!operators.isEmpty() && getPrecedence(token.toOperator()) > getPrecedence(operators.peek().toOperator()));
                            operators.push(token);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        while (!operators.isEmpty()) {
            postfixEquation.add(operators.pop());
        }

        EZ.printAnyln(postfixEquation);



        return postfixEquation;
    }

    public static Double evaluatePostfix(ArrayList<Token> postfixEq) {
        Stack<Double> operands = new Stack<>();

        EZ.println("Tokens:");
        EZ.printAnyln(postfixEq);

        for (Token token : postfixEq) {
            try {
                operands.push(token.toNumber());
            } catch (Exception e) {
                char operator;
                Double a, b, result;
                b = operands.pop();
                a = operands.pop();

                try {
                    operator = token.toOperator();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                result = calculate(a, b, operator);

                EZ.println("Performing: %.1f, %c, %.1f = %.1f",a, token.getValue().charAt(0), b, result);

                operands.push(result);
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


    // Testing function
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