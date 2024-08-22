import java.util.LinkedList;
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
        LinkedList<String> list = new LinkedList<>();
        Scanner s = new Scanner(System.in);
        int i = 0;
        String input = s.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.forEach(System.out::println);
            } else {
                System.out.println("added: " + input);
                i++;
                list.add(i + ". " + input);
            }
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
