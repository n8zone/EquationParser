import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("-5.2 + 3 - 2");

        // DO NOT FORGET TO ADD DIV BY 0 HANDLING

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

        var samplePostfix = createSamplePostFix("5 2 3 5 ^ * /");
        ArrayList<Token> samplePostfix2 = createSamplePostFix("-5 5 *");
        var sample3 = createSamplePostFix("5 2 3 * -");



        EZ.printAnyln(samplePostfix);

        ASTNode treeStart = parseEquation(samplePostfix);
        ASTNode tree2Start = parseEquation(samplePostfix2);
        ASTNode tree3 = parseEquation(sample3);

        EZ.println("Computed: %.2f", treeStart.Compute());
        EZ.println("Computed: %.2f", tree2Start.Compute());
        EZ.println("Computed: %.2f", tree3.Compute());

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

    public static ASTNode parseEquation(ArrayList<Token> tokenizedEquation) {
        EZ.println("Tokens: %s", tokenizedEquation.toString());

        Stack<ASTNode> operators = new Stack<>();
        ArrayList<ASTNode> operands = new ArrayList<>();

        for (Token token : tokenizedEquation) {
            ASTNode node = new ASTNode(token);
            if (token.isNumber()) {
                operands.add(node);
            } else {
                if (!operators.isEmpty()) {
                    node.left = operators.pop();
                } else {
                    node.left = operands.removeLast();
                }

                if (!operators.isEmpty()) {
                    node.right = operators.pop();
                } else {
                    node.right = operands.removeLast();
                }

                EZ.printAnyln(node);

                operators.add(node);
            }
        }

        return operators.removeFirst();
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