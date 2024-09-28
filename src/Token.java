public class Token {
    private final String value;

    public Token(String value) {
        this.value = value;
    }

    public boolean isOperator() {
        return "+-*/^".contains(value);
    }

    public boolean isLeftBracket() {
        return value.equals("(");
    }

    public boolean isRightBracket() {
        return value.equals(")");
    }

    private boolean isBracket() {
        return "()".contains(value);
    }

    public boolean isNumber() {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isLegal() {
        return isOperator() || isNumber() || isBracket();
    }

    public char toOperator() throws Exception {
        if (this.isOperator() || this.isBracket()) {
            return value.charAt(0);
        } else {
            throw new Exception(String.format("%s is not an operator!", value));
        }
    }

    public Double toNumber() throws Exception {
        if (this.isNumber()) {
            return Double.parseDouble(value);
        } else {
            throw new Exception(String.format("%s is not a number!", value));
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getType() {
        if (isOperator())
            return "Operator";
        else if (isNumber())
            return "Numeric";
        else
            return "Illegal";

    }

    public void display() {
        EZ.println("%sToken(%s)", getType(), value);
    }
}
