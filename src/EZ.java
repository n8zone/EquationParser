public class EZ {

    // A collection of useful helper functions

    public static void print(String str, Object... objects) {
        System.out.printf(str, objects);
    }

    public static void println(String str, Object... objects) {
        print(str + "\n", objects);
    }

    public static void print(int num) {
        print("%d", num);
    }

    public static void println(int num) {
        println("%d", num);
    }
}
