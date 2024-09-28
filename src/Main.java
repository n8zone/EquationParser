import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        EZ.println("Enter any valid math equation: ");
        String eq2 = keyboard.nextLine();

        EZ.printResult(evaluatePostfix(toPostfix(tokenizeEquation(eq2))));

    }

    // TOKENIZER -STRICTLY- HANDLES CONVERTING INPUT INTO _TOKENS_ NO LOGIC!!!
    // ALL LOGIC IS HANDLED BY PARSER
    public static ArrayList<Token> tokenizeEquation(String equation) {
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter("");
        Token prevToken = null;

        while (tokenizer.hasNext()) {
            Token nextToken = new Token(tokenizer.next());

            if (nextToken.isLegal()) {
                if (prevToken != null && prevToken.isNumber() && nextToken.isNumber()) {
                    String numberString = prevToken.getValue() + nextToken.getValue();
                    nextToken = new Token(numberString);
                    tokens.removeLast();
                }
                tokens.add(nextToken);
                prevToken = nextToken;
            }

        }

        return tokens;
    }

    public static int getPrecedence(char operator) {
        return switch(operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case ')' -> 0;
            case '(' -> -1;
            default -> -2;
        };
    }

    public static ArrayList<Token> toPostfix(ArrayList<Token> tokenizedEquation) {
       // Shunting-yard algorithm

        ArrayList<Token> postfixEquation = new ArrayList<>();
        Stack<Token> operators = new Stack<>();

        for (Token token : tokenizedEquation) {
            if (token.isNumber()) {
                postfixEquation.add(token);
            } else  {

                if (operators.isEmpty()) {
                    operators.push(token);
                } else {
                    Token topToken = operators.peek();
                    try {
                        if (token.isLeftBracket() || getPrecedence(token.toOperator()) > getPrecedence(topToken.toOperator())) {
                            operators.push(token);
                        } else if (token.isRightBracket()) {
                            do {
                                postfixEquation.add(operators.pop());
                            } while (!operators.peek().isLeftBracket());
                                operators.pop(); // Discard left bracket, its useless.
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