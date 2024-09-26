public class Token {
    private final String value;

    public Token(String value) {
        this.value = value;
    }

    public boolean isOperator() {
        return "+-*/".contains(value);
    }

    public boolean isNumber() {
        try {
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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
}
