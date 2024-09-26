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
}
