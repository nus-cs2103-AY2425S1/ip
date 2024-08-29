import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Bobby {
    private static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    private static String exit = "Bye. Hope to see you again soon!";
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Task> listToPrint = new ArrayList(100);
    private static ArrayList<String> listForDisk = new ArrayList(100);
    private static final String FILE_PATH = "/Users/zhiyi/Desktop/CS2103T/Chatbot/src/main/task.txt";
    private enum ActionType {
        bye, list, delete, mark, unmark, todo, deadline, event
    }
    private enum SimpleType {
        T, D, E
    }

    /**
     * Determines the action type based on the user's input string.
     *
     * @param input The string representing the action to perform.
     * @return The corresponding ActionType enum value, or null if the input does not match any ActionType.
     */
    private static ActionType getActionType(String input) {
        try {
            return ActionType.valueOf(input);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Writes the given task description to the file.
     */
    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        for (int x = 0; x < listToPrint.size(); x++) {
            Task curr_task = listToPrint.get(x);
            listForDisk.add(curr_task.toStore());
        }

        for (int x = 0; x < listForDisk.size(); x++) {
            fw.write(listForDisk.get(x) + System.lineSeparator());
        }
        fw.close();
    }

    private static void loadTaskList() throws IOException {
        File file = new File(FILE_PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = constructTask(line);
                if (task != null) {
                    listToPrint.add(task);
                }
            }
        }
        FileWriter fw = new FileWriter(FILE_PATH, false);
    }
    private static Task constructTask(String desc) {
        String details[] = desc.split("/");
        switch(SimpleType.valueOf(details[0])) {
            case T:
                Task task = new ToDos(details[2]);
                if (details[1].equals("1")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                return task;
            case D:
                task = new Deadlines(details[2], details[3]);
                if (details[1].equals("1")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                return task;
            case E:
                task = new Events(details[2], details[3], details[4]);
                if (details[1].equals("1")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                return task;
            default:
                System.out.println("GG!! your code is buggy");
                return null;
        }
    }

    /**
     * Adds a task to the list and writes the task description to the file.
     *
     * @param input The Task object to add.
     */
    private static void add_task(Task input) {
        listToPrint.add(input);
    }

    /**
     * Deletes a task from both lists by its index.
     *
     * @param idx The index of the task to delete (1-based index).
     */
    private static void delete_task(int idx) {
        if (idx <= listToPrint.size()) {
            Task deleted_task = retrive_task(idx);
            listToPrint.remove(idx - 1);
            listForDisk.remove(idx - 1);
            System.out.println("Noted. I've removed this task:\n" +
                    deleted_task.toString() + "\nNow you have "
                    + listToPrint.size() + " tasks in the list.\n");
        } else {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " delete by keying in list!");
        }
    }

//    public static Task check_task(String description) {
//        for (int x = 0; x < counter; x++) {
//            Task t = list[x];
//            if(t.description.equals(description)) {
//                return t;
//            }
//        }
//        Task new_t = new Task(description);
//        return new_t;
//    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param idx The index of the task to retrieve (1-based index).
     * @return The Task object at the specified index, or null if not found.
     */
    private static Task retrive_task(int idx) {
        if (idx <= listToPrint.size()) {
            return listToPrint.get(idx - 1);
        }
        return null;
    }


    /**
     * Prints all tasks in the list, each on a new line.
     * User-friendly mode.
     */
    private static void print_list() {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < listToPrint.size(); x++) {
            System.out.println(x + 1 + "." + listToPrint.get(x).toString());
        }
        System.out.println();
    }

    /**
     * Handles the "bye" command by printing the task list and
     * writes all task into task.txt to be saved in disk
     */
    private static void handle_bye() throws IOException {
        writeToFile();
        System.out.println(exit);
    }

    /**
     * Handles the "list" command by printing the task list and
     * processing the next user action.
     */
    private static void handle_list() throws IOException {
        print_list();
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "delete" command by deleting the specified task
     * and processing the next user action.
     */
    private static void handle_delete() throws IOException {
        int curr_task_index = scan.nextInt();
        delete_task(curr_task_index);
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "mark" command by marking the specified task as done
     * and processing the next user action.
     */
    private static void handle_mark() throws IOException {
        Task curr_task;
        if (scan.hasNextInt()) {
            curr_task = retrive_task(scan.nextInt());
        } else {
            System.out.println("OOPS! Integer not found! Please input mark x. (e.g mark 1)\n");
            scan.nextLine();
            check_action(getActionType(scan.next()));
            return;
        }
        if (!scan.nextLine().isEmpty()) {
            System.out.println("OOPS! Too much information! Please input mark x. (e.g mark 1)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        if (curr_task == null) {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " mark by keying in list! \n");
            check_action(getActionType(scan.next()));
            return;
        }
        curr_task.markAsDone();
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "unmark" command by unmarking the specified task
     * and processing the next user action.
     */
    private static void handle_unmark() throws IOException {
        Task curr_task;
        if (scan.hasNextInt()) {
            curr_task = retrive_task(scan.nextInt());
        } else {
            System.out.println("OOPS! Integer not found! Please input unmark x. (e.g unmark 1)\n");
            scan.nextLine();
            check_action(getActionType(scan.next()));
            return;
        }
        if (!scan.nextLine().isEmpty()) {
            System.out.println("OOPS! Too much information! Please input unmark x. (e.g unmark 1)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        if (curr_task == null) {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " unmark by keying in list! \n");
            check_action(getActionType(scan.next()));
            return;
        }
        curr_task.unMark();
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "todo" command by adding a new ToDo task
     * and processing the next user action.
     */
    private static void handle_todo() throws IOException {
        String input = scan.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("OOPS!!! The description of a todo is empty. (todo xxx)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        ToDos curr_ToDos = new ToDos(input.trim());
        add_task(curr_ToDos);
        System.out.println("Got it. I've added this task:\n" +
                curr_ToDos.toString() + "\n" +
                "Now you have " + listToPrint.size() + " tasks in the list.\n");
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task
     * and processing the next user action.
     */
    private static void handle_deadline() throws IOException {
        String input = scan.nextLine();
        String details[] = input.split("/by");
        if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of deadline should be (deadline xxx /by xxx)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        Deadlines curr_deadlines = new Deadlines(details[0].trim(), details[1].trim());
        add_task(curr_deadlines);
        System.out.println("Got it. I've added this task:\n" +
                curr_deadlines.toString() + "\n" +
                "Now you have " + listToPrint.size() + " tasks in the list.\n");
        check_action(getActionType(scan.next()));
    }

    /**
     * Handles the "event" command by adding a new Event task
     * and processing the next user action.
     */
    private static void handle_event() throws IOException {
        String input = scan.nextLine();
        String details[] = input.split("/from");
        if (details.length < 2 || details[0].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of events should be (event xxx /from xxx /to xxx)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        String duration[] = details[1].split("/to");
        if (duration.length < 2 || duration[0].trim().isEmpty() || duration[1].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of events should be (event xxx /from xxx /to xxx)\n");
            check_action(getActionType(scan.next()));
            return;
        }
        Events curr_events = new Events(details[0].trim(), duration[0].trim(), duration[1].trim());
        add_task(curr_events);
        System.out.println("Got it. I've added this task:\n" +
                curr_events.toString() + "\n" +
                "Now you have " + listToPrint.size() + " tasks in the list.\n");
        check_action(getActionType(scan.next()));
    }

    /**
     * Determines and executes the action based on user input.
     *
     * @param action The type of action to perform, as determined by user input.
     */
    private static void check_action(ActionType action) throws IOException {
        switch (action) {
        case bye:
            handle_bye();
            break;
        case list:
            handle_list();
            break;
        case delete:
            handle_delete();
            break;
        case mark:
            handle_mark();
            return;
        case unmark:
            handle_unmark();
            return;
        case todo:
            handle_todo();
            break;
        case deadline:
            handle_deadline();
            break;
        case event:
            handle_event();
            break;
        default:
            System.out.println("HELP!! I do not recognise this action as of now.\n" +
                    "You can try: todo xxx, event xxx /from xxx /to xxx, " +
                    "deadline xxx /by xxx, unmark x, mark x\n");
            check_action(getActionType(scan.next()));
            break;
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(greeting);
        loadTaskList();
        String input = scan.next();
        check_action(getActionType(input));
    }
}
