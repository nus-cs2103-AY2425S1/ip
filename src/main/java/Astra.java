import java.util.Scanner;

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

    public static void echo(String text) {
        System.out.println(formatMsg(text));
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String text = "";
        greet();
        while (!text.equals("bye")) {
            text = inp.nextLine();
            echo(text);
        }
        goodbye();
    }
}
