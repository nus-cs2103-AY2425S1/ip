import java.util.*;
public class Duke {
    private List<Task> taskStore = new ArrayList<Task>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke duke = new Duke();
        duke.handleUserInput();
    }

    private void handleUserInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String[] input = preprocess(scan.nextLine());
            String cmd = input[0], args = input[1];
            try {
                switch (cmd) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        scan.close();
                        return;
                    case "list":
                        this.getTaskList();
                        break;
                    case "mark":
                        this.mark(args);
                        break;
                    case "unmark":
                        this.unmark(args);
                        break;
                    case "delete":
                        this.deleteTask(args);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        this.createTask(cmd, args);
                        break;
                    default:
                        throw new DukeException("Invalid command provided.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String[] preprocess(String input) {
        String[] raw = input.split(" ", 2);
        String[] split = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            split[i] = raw[i].trim();
        }
        return split;
    }

    private void createTask(String type, String input) throws DukeException {
        if (input.isEmpty()) throw new DukeException("Empty Task description provided.");
        System.out.println("Got it. I've added this task:");
        Task task;
        switch (type) {
            case "todo":
                task = new Todo(input);
                break;
            case "deadline":
                int byDate = input.indexOf("/by");
                task = new Deadline(input.substring(0, byDate - 1), input.substring(byDate + 4));
                break;
            case "event":
                int fromDate = input.indexOf("/from");
                int toDate = input.indexOf("/to");
                task = new Event(input.substring(0, fromDate - 1), input.substring(fromDate + 6, toDate - 1), input.substring(toDate + 4));
                break;
            default:
                throw new DukeException("Invalid Task type.");
        }
        this.taskStore.add(task);
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
    }

    private void deleteTask(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("Task list is already empty.");
        if (input.isEmpty()) throw new DukeException("No Task index provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        Task task = this.taskStore.get(id - 1);
        this.taskStore.remove(id - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
    }

    private void getTaskList() {
        if (taskStore.isEmpty()) {
            System.out.println("List is currently empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskStore.size(); ++i) {
            System.out.printf("%d. %s\n", i+1, taskStore.get(i));
        }
    }

    private void mark(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("List is empty, no tasks to mark.");
        if (input == null) throw new DukeException("No input provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        this.taskStore.get(id - 1).markAsDone();
    }

    private void unmark(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("List is empty, no tasks to unmark.");
        if (input == null) throw new DukeException("No input provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        this.taskStore.get(id - 1).markAsNotDone();
    }
}
