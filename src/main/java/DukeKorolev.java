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
                + "What can I do for you?\n\n";
        String input = "";
        String end = "Bye. Hope to see you again soon\n";

        System.out.println(newLogo);
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(input);
        }
        System.out.println(end);
    }
}
