import java.sql.Array;
import java.util.Scanner;
import  java.util.ArrayList;

public class Rasputin {

    private static void printText(String text) {
        System.out.println(lineBreak);
        System.out.println(text);
        System.out.println(lineBreak + "\n");
    }

    private static String lineBreak = "____________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String name = "Rasputin";
        ArrayList<Task> ls = new ArrayList<>();



        Scanner scanner = new Scanner(System.in);
        printText("Hello, I'm " + name + "!\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                int index = 1;
                System.out.println(lineBreak);
                for (Task item: ls) {
                    System.out.println(index + "." + item.toString());
                    index++;
                }
                System.out.println(lineBreak + "\n");
                continue;
            }

            if (input.contains("unmark")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                int index = (input.charAt(7) - '0' - 1);
                System.out.println(index);
                ls.get(index).markAsNotDone();
                String output = "Unmarked that as done for you. \n " +
                        ls.get(index).toString();
                printText(output);
                continue;
            }

            if (input.contains("mark")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                int index = (input.charAt(5) - '0' - 1);
                System.out.println(index);
                ls.get(index).markAsDone();
                String output = "Marked that as done for you. \n " +
                        ls.get(index).toString();
                printText(output);
                continue;
            }

            ls.add(new Task(input));
            printText("added: " + input);

        }

        printText("Bye. Hope to see you soon!");
    }


}
