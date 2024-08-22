import java.util.Scanner;
public class meowmeow {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm meowmeow\n" + "What can I do for you?\n");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
