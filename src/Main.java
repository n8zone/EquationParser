import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("-5.2 + 3 - 2");

        ASTNode test1 = new ASTNode(new Token("+"));
        ASTNode left = new ASTNode(new Token("50.3"));

        ASTNode test2 = new ASTNode(new Token("-"));
        ASTNode left2 = new ASTNode(new Token("5"));
        ASTNode right2 = new ASTNode(new Token("2"));

        ASTNode test3 = new ASTNode(new Token("-"));
        ASTNode left3 = new ASTNode(new Token("7"));
        ASTNode right3 = new ASTNode(new Token("2"));


        test1.left = left;
        test1.right = test2;

        test2.left = left2;
        test2.right = right2;

        test3.left = left3;
        test3.right = right3;

        EZ.println("Answer: %,.2f", test1.Compute());

        ArrayList<Token> samplePostfix = new ArrayList<>();

        samplePostfix.add(new Token("15"));
        samplePostfix.add(new Token("3"));
        samplePostfix.add(new Token("0"));
        samplePostfix.add(new Token("/"));
        samplePostfix.add(new Token("+"));

        EZ.printAnyln(samplePostfix);

        ASTNode treeStart = parseEquation(samplePostfix);

        EZ.println("Computed: %.2f", treeStart.Compute());


    }

    // TOKENIZER -STRICTLY- HANDLES CONVERTING INPUT INTO _TOKENS_ NO LOGIC!!!
    // ALL LOGIC IS HANDLED BY PARSER
    public static ArrayList<Token> tokenizeEquation(String equation) {
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter(" ");

        // Ugly and bad; Do better later
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

    public static ASTNode parseEquation(ArrayList<Token> tokenizedEquation) {
        // Convert token array to postfix
            // Shunting yard algo??

        EZ.println("Tokens: %s", tokenizedEquation.toString());

        Stack<ASTNode> operators = new Stack<>();
        ArrayList<ASTNode> operands = new ArrayList<>();

        for (Token token : tokenizedEquation) {
            ASTNode node = new ASTNode(token);
            if (token.isNumber()) {
                operands.add(node);
                EZ.printAnyln(operands);
            } else {
                node.right = operands.removeFirst();

                boolean hasRight = (!operands.isEmpty()  && operands.getFirst() != null);

                if (hasRight) {
                    node.left = operands.removeFirst();
                } else {
                    node.left = operators.pop();
                }

                operators.add(node);
            }

            //EZ.printAnyln(node);

        }

        return operators.removeFirst();

        // loop through array
            // if number:
                // create node
                // push onto stack
            // else if operator:
                // create parent node
                // pop first two numbers from stack
                // push parent node to stack
    }
}