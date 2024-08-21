import java.sql.Array;
import java.util.*;
import java.util.ArrayList;

import static java.lang.System.exit;
import java.util.regex.*;

public class Momo {

    public static String horizontalLine = "____________________________________________________________";

    public enum Command {
        BYE,
        LIST,
        DELETE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT
    }

    static ArrayList<Task> list = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws MomoException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Momo\nWhat can I do for you?");
        String input = sc.nextLine();

        while (true) {
            try {
                Command command = checkValidInput(input);
                if (command == Command.BYE) {
                    MomoBye();
                }
                else if (command == Command.LIST) {
                    printList();
                }
                else if (command == Command.MARK) {
                    changeCompletion(Integer.parseInt(input.split(" ")[1]) - 1, Command.MARK);
                }
                else if (command == Command.UNMARK) {
                    changeCompletion(Integer.parseInt(input.split(" ")[1]) - 1, Command.UNMARK);
                }
                else if (command == Command.DELETE) {
                    deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
                }
                else {
                    if (command == Command.TODO) {
                        addToDo(input);
                    }
                    else if (command == Command.DEADLINE) {
                        addDeadline(input);

                    }

                    else if (command == Command.EVENT) {
                        addEvent(input);
                    }

                    printTaskAdded();
                    count++;

                }

            }
            catch (MomoException e){
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
    }

    public static Command checkValidInput(String input) throws MomoException {

        // Check if input is empty
        if (Objects.equals(input, "")) {
            throw new MomoException("I dare you to send an empty command again...");
        }

        // Check if input is bye
        if (Objects.equals(input, "bye")) {
            return Command.BYE;
        }

        // Check if input is list
        if (Objects.equals(input, "list")) {
            return Command.LIST;
        }

        // Check if input is delete
        if (input.startsWith("delete")) {
            if (Pattern.matches("delete\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only delete a task of a number your task list contains");
                }
                return Command.DELETE;
            }

            throw new MomoException("You're supposed to indicate the task number you want to delete!");

        }

        // Checking for mark and unmark input
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            // Check if input has format "mark x" or "unmark x"
            if (Pattern.matches("mark\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only mark a number your task list contains");
                }
                return Command.MARK;
            }

            else if (Pattern.matches("unmark\\s\\d", input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                if (index >= count || index < 0) {
                    throw new MomoException("You can only unmark a number your task list contains");
                }
                return Command.UNMARK;
            }

            throw new MomoException("Mark my words - You must enter a number x next to mark or unmark in the exact format `mark x`.");
        }

        // Checking for proper task input
        String taskType = input.split(" ")[0];

        switch (taskType) {
            case "todo" -> {
                if (input.split(" ").length < 2) {
                    throw new MomoException("Where's the description of your task??");
                }
                return Command.TODO;
            }
            case "deadline" -> {

                try {
                    String desc = input.split(" ", 2)[1];
                    String task = desc.split("/", 2)[0];
                    String by = desc.split("/", 2)[1];
                    return Command.DEADLINE;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your deadline properly\nIt should be in the format `deadline task /by date`");
                }
            }
            case "event" -> {
                try {
                    String desc = input.split(" ",2)[1];
                    String task =  desc.split("/",2)[0];
                    String from = desc.split("/",3)[1];
                    String to = desc.split("/",3)[2];
                    return Command.EVENT;
                }
                catch (Exception e) {
                    throw new MomoException("You likely did not format your event properly\nIt should be in the format `event task /from date time /to time`");
                }
            }
            default -> {
                throw new MomoException("You did not properly specify the type of task (todo/deadline/event) or command (bye/list)");
            }
        }
    }

    public static void MomoBye() {
        String logo =
                        "⣿⣿⣿⡉⢀⣾⣿⡟⣩⣭⣭⡈⠙⢿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡇⠄\n" +
                        "⣿⣿⡗⠄⣼⣿⣿⢸⡿⠉⠉⢻⡆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠄\n" +
                        "⣿⡻⠁⢠⣿⣿⣿⣦⡛⠢⠴⠛⠁⣸⣿⣿⣿⣿⡿⠛⢉⣉⣉⡙⢻⣿⣿⣗⠄⠄\n" +
                        "⠷⠁⠄⢰⣿⣿⣿⣷⣬⣭⣼⣷⣿⣿⣿⣿⣿⡏⢀⣾⠟⠛⢿⣿⣄⣿⣿⡏⠄⠄\n" +
                        "⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⠳⢀⣀⡼⢟⣼⣿⡟⠄⠄⠄\n" +
                        "⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣒⣲⣶⣾⣿⣿⠏⠄⠄⠄⢠\n" +
                        "⠄⠄⠄⠸⣿⣽⣿⣿⣿⣿⣉⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⢠⣷\n" +
                        "⠄⠄⠄⠄⢻⣷⢻⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⠄⠄⠄⢀⣾⣿\n" +
                        "⠄⠄⠄⠄⠄⢻⣧⡙⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄⢠⣿⣿⣿\n" +
                        "⠄⠄⠄⡀⠄⠈⣿⣿⣶⣭⣭⣭⣿⣾⡿⠟⠋⠁⠄⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⣿\n" +
                        "⠄⠄⠎⠄⠄⣨⣿⣿⣿⣿⣿⣿⠋⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⡲⣿⣿⣿⣿";

        System.out.println(horizontalLine);
        System.out.println("Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n" + logo);
        System.out.println(horizontalLine);
        System.exit(0);
    }

    public static void printList() {
        System.out.println(horizontalLine);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println(horizontalLine);
    }

    public static void changeCompletion(int index, Command command) {
        System.out.println(horizontalLine);
        Task task = list.get(index);

        if (command == Command.MARK) {
            task.markComplete();
            return;
        }
        task.unmark();
        System.out.println(horizontalLine);

    }

    public static void addToDo(String input) {
        String task = input.split(" ",2)[1];
        list.add(new Todo(task));
    }

    public static void addDeadline(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0];
        String by = desc.split("/",2)[1];
        list.add(new Deadline(task, by));

    }

    public static void addEvent(String input) {
        String desc = input.split(" ",2)[1];
        String task =  desc.split("/",2)[0];
        String from = desc.split("/",3)[1];
        String to = desc.split("/",3)[2];
        list.add(new Event(task, from, to));

    }
    public static void printTaskAdded() {
        System.out.println(horizontalLine);
        System.out.println("Noted. I've added this task:\n " + list.get(count));
        System.out.println(String.format("Now you have %d task(s) in the list", count + 1));
        System.out.println(horizontalLine);
    }

    public static void deleteTask(int index) {
        count--;
        System.out.println("Noted. I've removed this task:\n " + list.get(index));
        list.remove(index);
        System.out.println(String.format("Now you have %d task(s) in the list", count));
        System.out.println(horizontalLine);

    }
}




