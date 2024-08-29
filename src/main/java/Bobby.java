import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Bobby {
    private static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    private static String exit = "Bye. Hope to see you again soon!";
    private static Scanner scan = new Scanner(System.in);
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;
    private static final String FILE_PATH = "/Users/zhiyi/Desktop/CS2103T/Chatbot/src/main/task.txt";

    private enum SimpleType {
        T, D, E
    }

    public enum ActionType {
        bye, list, delete, mark, unmark, todo, deadline, event, retry
    }



    public static Task constructTask(String desc) {
        String details[] = desc.split("/");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
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
                task = new Deadlines(details[2],
                        LocalDateTime.parse(details[3], formatter));
                if (details[1].equals("1")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                return task;
            case E:
                task = new Events(details[2],
                        LocalDateTime.parse(details[3], formatter),
                        LocalDateTime.parse(details[4], formatter));
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
     * Handles the "bye" command by printing the task list and
     * writes all task into task.txt to be saved in disk
     */
    private static void handle_bye() throws Exception {
        if (scan.nextLine().isEmpty()) {
            storage.writeToFile(taskList);
            System.out.println(exit);
        } else {
            check_action(ActionType.retry);
        }
    }

    /**
     * Handles the "list" command by printing the task list and
     * processing the next user action.
     */
    private static void handle_list() throws Exception {
        if (scan.nextLine().isEmpty()) {
            taskList.print_list();
            check_action(parser.getActionType(scan.next()));
        } else {
            check_action(ActionType.retry);
        }
    }

    /**
     * Handles the "delete" command by deleting the specified task
     * and processing the next user action.
     */
    private static void handle_delete() throws Exception {
        int curr_task_index = scan.nextInt();
        taskList.delete_task(curr_task_index);
        check_action(parser.getActionType(scan.next()));
    }

    /**
     * Handles the "mark" command by marking the specified task as done
     * and processing the next user action.
     */
    private static void handle_mark() throws Exception {
        Task curr_task;
        if (scan.hasNextInt()) {
            curr_task = taskList.retrive_task(scan.nextInt());
        } else {
            System.out.println("OOPS! Integer not found! Please input mark x. (e.g mark 1)\n");
            scan.nextLine();
            check_action(parser.getActionType(scan.next()));
            return;
        }
        if (!scan.nextLine().isEmpty()) {
            System.out.println("OOPS! Too much information! Please input mark x. (e.g mark 1)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        if (curr_task == null) {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " mark by keying in list! \n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        curr_task.markAsDone();
        check_action(parser.getActionType(scan.next()));
    }

    /**
     * Handles the "unmark" command by unmarking the specified task
     * and processing the next user action.
     */
    private static void handle_unmark() throws Exception {
        Task curr_task;
        if (scan.hasNextInt()) {
            curr_task = taskList.retrive_task(scan.nextInt());
        } else {
            System.out.println("OOPS! Integer not found! Please input unmark x. (e.g unmark 1)\n");
            scan.nextLine();
            check_action(parser.getActionType(scan.next()));
            return;
        }
        if (!scan.nextLine().isEmpty()) {
            System.out.println("OOPS! Too much information! Please input unmark x. (e.g unmark 1)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        if (curr_task == null) {
            System.out.println("I can't find this task," +
                    " please check which task you want to" +
                    " unmark by keying in list! \n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        curr_task.unMark();
        check_action(parser.getActionType(scan.next()));
    }

    /**
     * Handles the "todo" command by adding a new ToDo task
     * and processing the next user action.
     */
    private static void handle_todo() throws Exception {
        String input = scan.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("OOPS!!! The description of a todo is empty. (todo xxx)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        ToDos curr_ToDos = new ToDos(input.trim());
        taskList.add_task(curr_ToDos);
        System.out.println("Got it. I've added this task:\n" +
                curr_ToDos.toString() + "\n" +
                "Now you have " + taskList.getSize() + " tasks in the list.\n");
        check_action(parser.getActionType(scan.next()));
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task
     * and processing the next user action.
     */
    private static void handle_deadline() throws Exception {
        String input = scan.nextLine();
        String details[] = input.split("/by");
        if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of deadline should be " +
                    "(deadline xxx /by yyyy-MM-dd HHmm)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime by = LocalDateTime.parse(details[1].trim(), formatter);
            Deadlines curr_deadlines = new Deadlines(details[0].trim(), by);
            taskList.add_task(curr_deadlines);
            System.out.println("Got it. I've added this task:\n" +
                    curr_deadlines.toString() + "\n" +
                    "Now you have " + taskList.getSize() + " tasks in the list.\n");
            check_action(parser.getActionType(scan.next()));
        }catch (DateTimeParseException e) {
            System.out.println("OOPS!!! Format of deadline should be " +
                    "(deadline xxx /by yyyy-MM-dd HHmm)\n");
            check_action(parser.getActionType(scan.next()));
        }
    }

    /**
     * Handles the "event" command by adding a new Event task
     * and processing the next user action.
     */
    private static void handle_event() throws Exception {
        String input = scan.nextLine();
        String details[] = input.split("/from");
        if (details.length < 2 || details[0].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of events should be " +
                    "(event xxx /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        String duration[] = details[1].split("/to");
        if (duration.length < 2 || duration[0].trim().isEmpty()
                || duration[1].trim().isEmpty()) {
            System.out.println("OOPS!!! Format of events should be " +
                    "(event xxx /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)\n");
            check_action(parser.getActionType(scan.next()));
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            try {
                LocalDateTime from = LocalDateTime.parse(duration[0].trim(), formatter);
                LocalDateTime to = LocalDateTime.parse(duration[1].trim(), formatter);
                Events curr_events = new Events(details[0].trim(), from, to);
                taskList.add_task(curr_events);
                System.out.println("Got it. I've added this task:\n" +
                        curr_events.toString() + "\n" +
                        "Now you have " + taskList.getSize() + " tasks in the list.\n");
                check_action(parser.getActionType(scan.next()));
            } catch (DateTimeParseException e) {
                System.out.println("OOPS!!! Format of event should be " +
                        "(event xxx /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)\n");
                check_action(parser.getActionType(scan.next()));
            }
    }

    /**
     * Determines and executes the action based on user input.
     *
     * @param action The type of action to perform, as determined by user input.
     */
    private static void check_action(ActionType action) throws Exception {
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
            case retry:
            default:
                System.out.println("HELP!! I do not recognise this action as of now.\n" +
                        "You can try: todo xxx, event xxx /from xxx /to xxx, " +
                        "deadline xxx /by xxx, unmark x, mark x, list, bye\n");
                check_action(parser.getActionType(scan.next()));
                break;
        }
    }
    public static void main(String[] args) throws Exception {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage.loadFile());
        parser = new Parser();
        System.out.println(greeting);
        String input = scan.next();
        check_action(parser.getActionType(input));
    }
}
