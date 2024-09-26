public class ASTNode {
    Token value;
    ASTNode left;
    ASTNode right;
    public ASTNode(Token value) {
        this.value = value;
    }

    public int Compute() {
        if (this.isOperator()) {
            return calculate(this.left.Compute(), this.right.Compute(), value.toString().charAt(0));
        } else {
            return Integer.parseInt(value.toString());
        }
    }

    private int calculate(int a, int b, char operator) {
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
        if (this.left != null) {
            return String.format("ASTNode(%s)", left.value.getValue());
        } else {
            return "LEAF";
        }
    }

    public String getRightValue() {
        if (this.right != null) {
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
                this.getValue(),
                this.getLeftValue(),
                this.getRightValue() );
    }
}
