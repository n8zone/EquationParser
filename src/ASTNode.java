public class ASTNode {
    Token value;
    ASTNode left;
    ASTNode right;
    public ASTNode(Token value) {
        this.value = value;
    }

    public double Compute() {
        if (this.isOperator()) {
            return calculate(this.left.Compute(), this.right.Compute(), value.toString().charAt(0));
        } else {
            return Double.parseDouble(value.toString());
        }
    }

    private double calculate(double a, double b, char operator) {
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

    private boolean isOperator() {
        return value.isOperator();
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    public String getLeftValue() {
        if (left != null) {
            return String.format("ASTNode(%s)", left.value.getValue());
        } else {
            return "LEAF";
        }
    }

    public String getRightValue() {
        if (right != null) {
            return String.format("ASTNode(%s)", right.value.getValue());
        } else {
            return "[     ]";
        }
    }

    public String getValue() {
        return value.getValue();
    }

    public String toString() {
        return String.format("""
                        === NODE ===
                        Value: %s
                        Left: %s
                        Right: %s
                        === ==== ===
                        """,
                getValue(),
                getLeftValue(),
                getRightValue() );
    }
}
