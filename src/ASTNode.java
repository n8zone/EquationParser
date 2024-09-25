public class ASTNode {
    String value;
    ASTNode left;
    ASTNode right;
    public ASTNode(String value) {
        this.value = value;
    }

    public int Compute() {
        if (this.isOperand()) {
            return calculate(this.left.Compute(), this.right.Compute(), this.value.charAt(0));
        } else {
            return Integer.parseInt(this.value);
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

    private boolean isOperand() {
        return (this.value.equals("*") ||
                this.value.equals("/") ||
                this.value.equals("+") ||
                this.value.equals("-")   );
    }

    public ASTNode getLeft() {
        return this.left;
    }

    public String getLeftValue() {
        if (this.left != null) {
            return this.left.value;
        } else {
            return "CAP";
        }
    }

    public String getRightValue() {
        if (this.right != null) {
            return this.right.value;
        } else {
            return "CAP";
        }
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return String.format("""
                        Value: %s
                        Left: %s
                        Right: %s""",
                this.getValue(),
                this.getLeftValue(),
                this.getRightValue() );
    }
}
