import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        boolean keepGoing = true;
        do {
            String equation = getEquationFromUser();

            if (equation.equalsIgnoreCase("help")) {
                EZ.println("""
                            == Valid Operators ==
                            Exponent:    ^
                            Multiply:    *
                            Divide:      /
                            Add:         +
                            Subtract":   -
                            Parentheses ()
                            == Valid Operators ==
                            
                            Equations MUST contain only numbers and valid operators.
                        """);
            } else {
                try {
                    EZ.printResult(evaluatePostfix(toPostfix(tokenizeEquation(equation))));
                } catch (Exception e) {
                    EZ.printAnyln(e);
                }


                String goAgain = "";
                do {
                    EZ.println("Would you like to evaluate another equation? (y/n)");
                    goAgain = keyboard.nextLine();
                } while (!goAgain.equalsIgnoreCase("y") && !goAgain.equalsIgnoreCase("n"));

                keepGoing = goAgain.equalsIgnoreCase("y");
            }

        }
        while(keepGoing);

        EZ.println("Okay, goodbye!");


    }

    public static String getEquationFromUser() {

        String equation;
        do {
            EZ.println("Enter any valid math equation or type 'help' for help");
            equation = keyboard.nextLine();
        } while (!isValidEquation(equation) && !equation.equalsIgnoreCase("help"));

        return equation;
    }

    public static boolean isValidEquation(String equation) {
        // Regex to match valid characters (numbers, operators, parentheses)
        String validPattern = "^[0-9()+\\-*/^\\. ]+$";

        return equation.matches(validPattern);
    }

    // TOKENIZER -STRICTLY- HANDLES CONVERTING INPUT INTO _TOKENS_ NO LOGIC!!!
    // ALL LOGIC IS HANDLED BY PARSER
    public static ArrayList<Token> tokenizeEquation(String equation) throws Exception {
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter(" ");
        Token prevToken = null;

        while (tokenizer.hasNext()) {
            Token nextToken = new Token(tokenizer.next());

            if (nextToken.isLegal()) {
                if (prevToken != null && prevToken.isNumeric() && nextToken.isNumeric()) {
                    String numberString = prevToken.getValue() + nextToken.getValue();
                    nextToken = new Token(numberString);
                    tokens.removeLast();
                }

                tokens.add(nextToken);
                prevToken = nextToken;
            }

        }
        EZ.printAnyln(tokens);

        for (Token token : tokens) {
            if (token.isDecimal()) {
                throw new Exception(String.format("ERR: Invalid decimal in equation %s", equation));
            }
        }

        return tokens;
    }

    public static int getOperatorPrecedence(char operator) {
        return switch(operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case ')' -> 0;
            case '(' -> -1;
            default -> -2;
        };
    }

    // I am fully aware this is ugly, will try to refactor a bit if I have time
    public static ArrayList<Token> toPostfix(ArrayList<Token> tokenizedEquation) {

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
                    // We need to use a try-catch because toOperator throws an Exception if used on a non-operator token
                    try {
                        if (token.isLeftBracket() || getOperatorPrecedence(token.toOperator()) > getOperatorPrecedence(topToken.toOperator())) {
                            operators.push(token);
                        } else if (token.isRightBracket()) {
                            do {
                                postfixEquation.add(operators.pop());
                            } while (!operators.peek().isLeftBracket());
                                operators.pop(); // Discard left bracket, its useless.
                        } else {
                            do {
                                postfixEquation.add(operators.pop());
                            } while (!operators.isEmpty() && getOperatorPrecedence(token.toOperator()) > getOperatorPrecedence(operators.peek().toOperator()));
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

        return postfixEquation;
    }

    public static Double evaluatePostfix(ArrayList<Token> postfixEq) throws Exception {
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

    private static double calculate(double a, double b, char operator) throws Exception {

        if (operator == '/' && b == 0) {
            throw new Exception(String.format("ERR: Attempt to divide by 0: %.2f / %.2f",a, b ));
        }

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