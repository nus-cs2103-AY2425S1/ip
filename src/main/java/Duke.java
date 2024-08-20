import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        duke.readData();
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
                writeData();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void readData() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            File filedir = new File("data/");
            if (!filedir.exists()) {
                boolean success = filedir.mkdir();
            }
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split("\\s*|\\s*");
                String taskType = line[0];
                Task task = switch (taskType) {
                    case "T" -> new Todo(line[2]);
                    case "D" -> new Deadline(line[2], LocalDateTime.parse(line[3].trim(), dtf));
                    case "E" -> new Event(line[2], LocalDateTime.parse(line[3].trim(), dtf), LocalDateTime.parse(line[4].trim(), dtf));
                    default -> throw new DukeException("Invalid Task type provided.");
                };
                if (line[1].equals("1")) task.markAsDoneNonVerbose();
                this.taskStore.add(task);
            }
            scan.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeData() {
        try {
            FileWriter filewriter = new FileWriter("data/duke.txt");
            for (Task t : this.taskStore) {
                filewriter.write(t.saveFormat() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String[] preprocess(String input) {
        String[] raw = input.split(" ", 2);
        String[] split = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            split[i] = raw[i].trim();
        }
        split[0] = split[0].toLowerCase();
        return split;
    }

    private void createTask(String type, String input) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            if (input.isEmpty()) throw new DukeException("Empty Task description provided.");
            System.out.println("Got it. I've added this task:");
            Task task;
            switch (type) {
                case "todo":
                    task = new Todo(input);
                    break;
                case "deadline":
                    if (!input.contains("/by") || input.indexOf("/by") == input.length() - 3) throw new DukeException("Invalid deadline description provided.");
                    String[] deadlineInput = input.split("/by", 2);
                    task = new Deadline(deadlineInput[0].trim(), LocalDateTime.parse(deadlineInput[1].trim(), dtf));
                    break;
                case "event":
                    if (
                            !input.contains("/from") ||
                            !input.contains("/to") ||
                            input.indexOf("/from") == input.length() - 5 ||
                            input.indexOf("/to") == input.length() - 2
                    ) throw new DukeException("Invalid event description provided.");
                    String[] eventInput = input.split("/from", 2);
                    String[] eventTimeInput = eventInput[1].trim().split("/to", 2);
                    task = new Event(eventInput[0].trim(), LocalDateTime.parse(eventTimeInput[0].trim(), dtf), LocalDateTime.parse(eventTimeInput[1].trim(), dtf));
                    break;
                default:
                    throw new DukeException("Invalid Task type.");
            }
            this.taskStore.add(task);
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
        } catch (DateTimeParseException | DukeException e) {
            System.out.println(e.getMessage());
        }
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
