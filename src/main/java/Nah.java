import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Nah {
    private static String greetLine = "____________________________________________________________\n"
            + " Hello! I'm NAH \n"
            + " What can I do for you? \n"
            + "____________________________________________________________\n";
    private static String byeLine = " Bye. Hope to see you again soon!\n";

    private Task[] task = new Task[100];
    private int taskCount = 0;
    private void greet() {
        System.out.println(greetLine);
    }

    private void exit() {
        System.out.println(byeLine);
    }

    private void add(Task newTask) {
        task[taskCount] = newTask;
        taskCount ++;
        System.out.println(" Got it. I've added this task: \n"
                        + "   " + newTask.toString() + "\n"
                        + " Now you have " + taskCount + " tasks in the list.");
    }

    private void readTask() {
        System.out.println(" Here are the tasks in your list:\n");
        for (int i = 1; i <= taskCount; i ++) {
            System.out.println( " " + i + ". " + task[i - 1].toString() + "\n");
        }
    }

    private void mark(int i) {
        task[i - 1].mark();
        System.out.println(" Nice! I've marked this task as done:\n"
                        + "   " + task[i - 1].toString());
    }

    private void unMark(int i) {
        task[i - 1].unMark();
        System.out.println(" OK, I've marked this task as not done yet:\n"
                + "   " + task[i - 1].toString());
    }

    public static void main(String[] args) {
        String logo = "| \\   | |      /\\      | |  | | \n"
                + "| |\\  | |     / /\\     | |==| | \n"
                + "| | \\ | |    / /__\\    | |  | | \n"
                + "| |  \\  |   / /    \\   | |  | | \n";


        System.out.println("Hello from\n" + logo);

        Nah nah = new Nah();

        nah.greet();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________ \n");

            String[] command = input.split(" ", 2);
            switch (command[0]) {
                case "bye": {
                    nah.exit();
                    return;
                }
                case "list": {
                    nah.readTask();
                    break;
                }
                case "mark": {
                    int i = parseInt(command[1]);
                    nah.mark(i);
                    break;
                }
                case "unmark": {
                    int i = parseInt(command[1]);
                    nah.unMark(i);
                    break;
                }
                case "todo": {
                    nah.add(new ToDos(command[1]));
                    break;
                }
                case "deadline": {
                    String[] description = command[1].split("/by", 2);
                    nah.add(new Deadlines(description[0], description[1]));
                    break;
                }
                case "event": {
                    String[] start = command[1].split("/from", 2);
                    String[] end = start[1].split("/to", 2);
                    nah.add(new Events(start[0],end[0],end[1]));
                    break;
                }
                default:
                    nah.add(new Task(input));
                    break;

            }
            System.out.println("____________________________________________________________ \n");
        }

    }
}
