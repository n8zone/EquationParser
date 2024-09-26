import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("-5 + 3 - 2");

        ASTNode test1 = new ASTNode(new Token("+"));
        ASTNode left = new ASTNode(new Token("5"));

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

        EZ.println("Answer: %d", test1.Compute());

        ASTNode treeStart = buildASTree(eq);
        EZ.print(treeStart.toString());
        EZ.print(treeStart.right.toString());


    }

    // TOKENIZER -STRICTLY- HANDLES CONVERTING INPUT INTO _TOKENS_ NO LOGIC!!!
    // ALL LOGIC IS HANDLED BY PARSER
    public static ArrayList<Token> tokenizeEquation(String equation) {
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter(" ");

        // Ugly and bad; Do better later
        while (tokenizer.hasNext()) {
            Token nextToken = new Token(tokenizer.next());
            tokens.add(nextToken);
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

    public static ASTNode parseEquation(ArrayList<String> tokenizedEquation) {
        // Convert token array to postfix
            // Shunting yard algo

        // loop through array
            // if number:
                // create node
                // push onto stack
            // else if operator:
                // create parent node
                // pop first two numbers from stack
                // push parent node to stack
        return new ASTNode(new Token("hello!!!"));
    }


    public static ASTNode buildASTree(ArrayList<Token> tokenizedEquation) {
        EZ.print("Generating ASTree from: %s\n", tokenizedEquation.toString());

        Token nodeValue = tokenizedEquation.removeFirst();
        ASTNode constructedNode = new ASTNode(nodeValue);
        constructedNode.right = buildNextNode(tokenizedEquation.removeFirst(), constructedNode);

        return constructedNode;
    }

    public static ASTNode buildNextNode(Token token, ASTNode prev) {
        EZ.print("Building node from: %s\n", token.getValue());
        ASTNode constructedNode = new ASTNode(token);
        constructedNode.left = prev;
        return constructedNode;
    }
}