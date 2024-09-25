import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        var eq = tokenizeEquation("5 + 3 - 2");

        ASTNode test1 = new ASTNode("+");
        ASTNode left = new ASTNode("5");

        ASTNode test2 = new ASTNode("-");
        ASTNode left2 = new ASTNode("3");
        ASTNode right2 = new ASTNode("2");

        ASTNode test3 = new ASTNode("-");
        ASTNode left3 = new ASTNode("7");
        ASTNode right3 = new ASTNode("1");


        test1.left = left;
        test1.right = test2;

        test2.left = left2;
        test2.right = right2;

        test3.left = left3;
        test3.right = right3;

        EZ.println(test1.Compute());

        EZ.print(parseEquation(eq).toString());

        ArrayList<String> parsedEquation = parseEquation(eq);

        ASTNode treeStart = buildASTree(eq);
        EZ.print(treeStart.toString());
        EZ.print(treeStart.right.toString());


    }

    public static ArrayList<String> tokenizeEquation(String equation) {
        ArrayList<String> tokens = new ArrayList<>();
        Scanner tokenizer = new Scanner(equation).useDelimiter("");

        while (tokenizer.hasNext()) {
            String nextToken = tokenizer.next();
            if (!nextToken.equals(" "))
                tokens.add(nextToken);
        }

        EZ.println(tokens.size());
        EZ.println(tokens.toString());
        return tokens;
    }

    public static ArrayList<String> parseEquation(ArrayList<String> equation) {
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();

        StringBuilder constructedNumber = new StringBuilder();
        for (int i = 0; i < equation.size(); i++) {
            String nextToken = equation.get(i);
            EZ.println(nextToken);

            if (Character.isDigit(nextToken.charAt(0))) {
                constructedNumber.append(nextToken);
            } else {
                operators.add(nextToken);
                if (!constructedNumber.isEmpty()) {
                    numbers.add(constructedNumber.toString());
                    constructedNumber = new StringBuilder();
                }
            }
        }

        numbers.add(constructedNumber.toString());

        EZ.println(numbers.toString());
        EZ.println(operators.toString());

        ArrayList<String> parsed = new ArrayList<>();

        parsed.addAll(numbers);
        parsed.addAll(operators);

        return parsed;

    }

    public static ASTNode buildASTree(ArrayList<String> tokenizedEquation) {
        EZ.print("Generating ASTree from: %s\n", tokenizedEquation.toString());

        String nodeValue = tokenizedEquation.removeFirst();
        ASTNode constructedNode = new ASTNode(nodeValue);
        constructedNode.right = buildNextNode(tokenizedEquation.removeFirst(), constructedNode);

        return constructedNode;
    }

    public static ASTNode buildNextNode(String value, ASTNode prev) {
        EZ.print("Building node from: %s\n", value);
        ASTNode constructedNode = new ASTNode(value);
        constructedNode.left = prev;
        return constructedNode;
    }
}