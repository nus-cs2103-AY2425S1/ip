public class Astra {
    private static String formatMsg(String msg) {
        return "____________________________________________________________\n" +
                msg + "\n" +
                "____________________________________________________________\n";
    }

    public static void greet() {
        String msg = " Hello! I'm Astra. \n" +
                     " What can I do for you?";
        System.out.println(formatMsg(msg));
    }

    public static void goodbye() {
        String msg = " Bye. Hope to see you again soon!";
        System.out.println(formatMsg(msg));
    }

    public static void main(String[] args) {
        greet();
        goodbye();
    }
}
