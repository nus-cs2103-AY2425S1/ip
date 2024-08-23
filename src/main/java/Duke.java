import java.util.Scanner;

public class Duke {
    public static String addHorizontalLines(String dialog) {
        String res = "____________________________________________________________\n" +
                dialog + "\n" +
                "____________________________________________________________";
        return res;
    }

    public static void main(String[] args) {
        String hi = " Hello! I'm Foo\n" +
                " What can I do for you?";
        System.out.println(addHorizontalLines(hi));
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println(addHorizontalLines(line));
        }
        System.out.println(addHorizontalLines("Bye. Hope to see you again soon!"));
    }
}
