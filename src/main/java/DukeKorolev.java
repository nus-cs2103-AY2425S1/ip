import java.util.Scanner;
public class DukeKorolev {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String newLogo = "Hello! I'm DukeKorolev\n"
                + "What can I do for you?\n";
        String input = "";
        String end = "Bye. Hope to see you again soon!";
        String divider = "--------------------";

        System.out.println(newLogo);
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
        }
        System.out.println(end);
    }
}
