import java.util.ArrayList;
import java.util.Scanner;

public class Blitz {
    private static void printInDivider(String divider, String cont) {
        System.out.print(divider + cont + divider);
    }

    private static void printTaskAddedWithDivider(String divider, String type, int size, Task task) {
        String toPrint = "    Got it. I've added this task:\n" +
                "      [" + type + "][ ] " + task + "\n" +
                "    " + "Now you have " + size + " tasks in the list.\n";
        printInDivider(divider, toPrint);
    }

    public static void main(String[] args) {
        ArrayList<Task> db = new ArrayList<>();
        String tab = "    ";
        String divider = tab + "-----------------------------------------------\n";
        String greet = tab + "Hello! I'm Blitz.\n" +
                tab + "What can I do for you?\n";
        String end = tab + "Bye. Hope to see you again soon!\n";

        printInDivider(divider, greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("bye")) {
                break;
            }

            if (inp.equals("list")) {
                System.out.println(divider +
                        tab + "Here are the tasks in your list:");
                for (int i = 0; i < db.size(); i++) {
                    Task curr = db.get(i);
                    System.out.println(tab + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr);
                }
                System.out.print(divider);
            } else {
                String[] cont = inp.split(" ", 2);
                String command = cont[0];

                switch (command) {
                    case "mark" -> {
                        int ind = Integer.parseInt(cont[1]) - 1;
                        Task task = db.get(ind);

                        task.markDone();
                        String toPrint = tab + "Nice! I've marked this task as done:\n" +
                                tab + "  [" + task.getType() + "]" + "[X] " + task + "\n";
                        printInDivider(divider, toPrint);
                    }
                    case "unmark" -> {
                        int ind = Integer.parseInt(cont[1]) - 1;
                        Task task = db.get(ind);

                        task.unmarkDone();
                        String toPrint = tab + "Ok, I've marked this task as not done yet:\n" +
                                tab + "  [" + task.getType() + "]" + "[ ] " + task + "\n";
                        printInDivider(divider, toPrint);
                    }
                    case "todo" -> {
                        Task temp = new Todo(cont[1], "T", false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, "T", db.size(), temp);
                    }
                    case "deadline" -> {
                        String[] str = cont[1].split(" /by ");
                        Task temp = new Deadline(str[0], "D", str[1], false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, "D", db.size(), temp);
                    }
                    case "event" -> {
                        String[] str1 = cont[1].split(" /from ");
                        String[] str2 = str1[1].split(" /to ");
                        Task temp = new Event(str1[0], "E", str2[0], str2[1], false);

                        db.add(temp);
                        printTaskAddedWithDivider(divider, "E", db.size(), temp);
                    }
                    default -> {
                        String toPrint = tab + "Invalid command\n";

                        printInDivider(divider, toPrint);
                    }
                }
            }
        }

        printInDivider(divider, end);
    }
}
