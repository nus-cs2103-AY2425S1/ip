import java.util.Scanner;
public class ChaCha {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greeting = "     ____________________________________________________________ \n" +
                "     Hello! I'm ChaCha \n" +
                "     What can I do for you? \n" +
                "     ____________________________________________________________ \n";

        String exit = "     ____________________________________________________________ \n" +
                "     Bye. Hope to see you again soon! \n" +
                "     ____________________________________________________________";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        ListOfTask list = new ListOfTask();

        while (scanner.hasNextLine()) {
            String cmd = scanner.nextLine();

            if (cmd.equals("bye")) {
                System.out.println(exit);
                break;
            }

            else if (cmd.equals("list")) {
                System.out.println(list.printList());
            }

            else if (cmd.startsWith("mark ")) {
                int index = Integer.parseInt(cmd.substring(5));
                System.out.println(list.markDone(index));
            }

            else if (cmd.startsWith("unmark ")) {
                int index = Integer.parseInt(cmd.substring(7));
                System.out.println(list.markUndone(index));
            }

            else {
                System.out.println(list.addTask(cmd));
            }

            scanner = new Scanner(System.in);
        }

    }
}
