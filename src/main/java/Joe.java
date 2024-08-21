import java.util.Scanner;

public class Joe {
    private static final String line =
            "____________________________________________________________";

    public static void bye() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public static void echo() {
        Scanner reader = new Scanner(System.in);
        String userCmd = reader.nextLine();
        while (!userCmd.equalsIgnoreCase("bye")) {
            System.out.println(line + "\n" + userCmd + "\n" + line);
            userCmd = reader.nextLine();
        }
        bye();
    }

    public static void main(String[] args) {
        System.out.println(line +
                "\nHello! I'm Joe\n" +
                "What can I do for you?\n"+
                line);
        echo();
    }
}
