public class Utils {
    public static void printIndent(String item) {
        System.out.println(Config.INDENTATION + item);
    }

    public static void printItem(String item) {
        printIndent("____________________________________________________________");
        printIndent(item);
        printIndent("____________________________________________________________");
    }
}
