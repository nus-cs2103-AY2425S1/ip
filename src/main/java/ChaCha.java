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
                "     Hello! I'm ChaCha the ChatBot. \n" +
                "     What can I do for you? \n" +
                "     ____________________________________________________________ \n";

        String exit = "     ____________________________________________________________ \n" +
                "     Bye. Hope to talk to you again soon! \n" +
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

            else if (cmd.startsWith("todo")) {
                String description = cmd.substring(5);
                System.out.println("     ____________________________________________________________ \n" +
                        "     Got it. I've added this task:\n" +
                        "       " + list.addToDo(description).printTask() + "\n" +
                        "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                        "     ____________________________________________________________");
            }

            else if (cmd.startsWith("deadline")) {
                String temp = cmd.substring(9);
                String[] arr = temp.split(" /");
                String description = arr[0];
                String date = arr[1].substring(3);
                System.out.println("     ____________________________________________________________ \n" +
                        "     Got it. I've added this task:\n" +
                        "       " + list.addDeadline(description, date).printTask() + "\n" +
                        "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                        "     ____________________________________________________________");
            }

            else if (cmd.startsWith("event")) {
                String temp = cmd.substring(6);
                String[] arr = temp.split(" /");
                String description = arr[0];
                String startTime = arr[1].substring(5);
                String endTime = arr[2].substring(3);
                System.out.println("     ____________________________________________________________ \n" +
                        "     Got it. I've added this task:\n" +
                        "       " + list.addEvent(description, startTime, endTime).printTask() + "\n" +
                        "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                        "     ____________________________________________________________");
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
                System.out.println("     ____________________________________________________________ \n" +
                        "Hmmm... I don't seem to understand this. Please input another command! \n" +
                        "     ____________________________________________________________");
            }

            scanner = new Scanner(System.in);
        }

    }
}
