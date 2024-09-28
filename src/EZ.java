public class EZ {

    // A collection of useful helper functions; Mostly because I'm too lazy to type System.out.println

    public static void print(String str, Object... objects) {
        System.out.printf(str, objects);
    }

    public static void println(String str, Object... objects) {
        print(str + "\n", objects);
    }

    public static void print(int num) {
        print("%d", num);
    }

    public static void printAny(Object obj) {
        print(obj.toString());
    }

    public static void printAnyln(Object obj) {
        println(obj.toString());
    }

    public static void println(int num) {
        println("%d", num);
    }

    public static void print(int... nums) {
        print(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            print(", %d", nums[i]);
        }
        print("\n");
    }

    public static void println(int... nums) {
        for (int i = 0; i < nums.length; i++) {
            println("%d", nums[i]);
        }
    }

    public static void printResult(double result, int maxPrecision) {
        if (Math.floor(result) == result) {
            System.out.printf("Result: %d\n", (int) result);
        } else {
            int precision = Math.min(decimalLength(result), maxPrecision);
            String formatString = "Result: %." + Integer.toString(precision) + "f\n";
            System.out.printf(formatString, result);
        }
    }

    // Converts number to string, splits at the decimal, returns length of post-decimal string
    private static int decimalLength(double num) {
        return Double.toString(num).split("\\.")[1].length();
    }
}
