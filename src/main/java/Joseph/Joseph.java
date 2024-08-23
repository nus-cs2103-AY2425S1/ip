package Joseph;

import jdk.jfr.Event;

import java.util.Scanner;
public class Joseph {
    public static void main(String[] args) {
        final String NAME = "Joseph";
        final String EXIT = "bye";
        final String LIST = "list";
        final String HELP = "help";
        final String MARK = "mark "; // note that it includes a space character
        final String UNMARK = "unmark "; // note that it includes a space character
        final String TODO = "todo ";
        final String DEADLINE = "deadline ";
        final String EVENT = "event ";
        Task[] list = new Task[100];
        int tracker = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Hello, I'm " + NAME + "!");
        System.out.println("How can I help you today? (type help for all commands)");
        System.out.println("----------------------------------");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(EXIT)) {
                System.out.println("----------------------------------");
                System.out.println("Bye! Have a nice day :)");
                System.out.println("----------------------------------");
                scanner.close();
                break;
            }

            else if (input.equals(LIST)) {
                System.out.println("----------------------------------");
                for (int i = 1; i < tracker + 1; i++) {
                    String done = "[" + list[i-1].getDone() + "] ";
                    System.out.println(i + ". " + done + list[i-1].getDesc());
                }
                System.out.println("----------------------------------");
            }

            else if (input.equals(HELP)) {
                System.out.println("----------------------------------");
                System.out.println("help: prints all available commands");
                System.out.println("list: prints the current list");
                System.out.println("mark X, where X is any number: " +
                        "marks task X on the list as completed");
                System.out.println("unmark X, where X is any number: " +
                        "unmarks task X on the list as uncompleted.");
                System.out.println("todo desc, " +
                        "where desc is any string: " +
                        " adds a todo to the list)");
                System.out.println("deadline desc /due, " +
                        "where desc and due are any string: " +
                        " adds a deadline to the list with its due date)");
                System.out.println("event desc /start /end, " +
                        "where desc, start and end are any string: " +
                        " adds an event to the list with its start and end)");
                System.out.println("bye: closes the chatbot");
                System.out.println("----------------------------------");

            }

            else if (input.startsWith(MARK)) {
                int num = Integer.parseInt(input.substring(5).trim());
                list[num-1].setDone();
                System.out.println("Great, I've marked " + list[num-1].getDesc()+
                        " as done!");
            }

            else if (input.startsWith(UNMARK)) {
                int num = Integer.parseInt(input.substring(7).trim());
                list[num-1].unDone();
                System.out.println("Okay, I've unmarked " + list[num-1].getDesc()+
                        " as not done!");
            }

            else if (input.startsWith(TODO)) {
                input = input.substring(5).trim();
                ToDo todo = new ToDo(input);
                list[tracker] = todo;
                System.out.println("----------------------------------");
                System.out.println("I've added the todo: " + input);
                System.out.println("----------------------------------");
                tracker++;
            }

            else if (input.startsWith(DEADLINE)) {
                input = input.substring(9).trim();
                String[] details = input.split("/");
                String task = details[0];
                String due = details[1];
                Deadline deadline = new Deadline(task, due);
                list[tracker] = deadline;
                System.out.println("----------------------------------");
                System.out.println("I've added the deadline: " + input);
                System.out.println("----------------------------------");
                tracker++;
            }

            else if (input.startsWith(EVENT)) {
                input = input.substring(6).trim();
                String[] details = input.split("/");
                String task = details[0];
                String start = details[1];
                String end = details[2];
                JEvent event = new JEvent(task, start, end);
                list[tracker] = event;
                System.out.println("----------------------------------");
                System.out.println("I've added the event: " + input);
                System.out.println("----------------------------------");
                tracker++;
            }

            else {
                System.out.println("That is not a recognised command!");
            }
        }
    }
}