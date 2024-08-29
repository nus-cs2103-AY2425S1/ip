import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Velma {
    private static final String FILE_PATH = "./data/velma.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Velma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public enum Command {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        UNKNOWN
    }

    public static Command getCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }


    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File("/Users/zeonchew04/ip/data/velma.txt");
        if (!file.exists()) {
            System.out.println("No previous tasks found.");
            return list;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ", 3);
                Task task;
                switch (parts[0].charAt(1)) { // Extract task type from the string
                    case 'T':
                        task = new Todo(parts[2]);
                        break;
                    case 'D':
                        String[] deadlineParts = parts[2].split(" \\(by: ", 2);
                        String deadlineDescription = deadlineParts[0];
                        String byString = deadlineParts[1].substring(0, deadlineParts[1].length() - 1);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");// Remove closing parenthesis
                        LocalDateTime by = LocalDateTime.parse(byString, formatter);
                        task = new Deadline(deadlineDescription, by);
                        break;
                    case 'E':
                        String[] eventParts = parts[2].split(" \\(from: | to: ", 3);
                        String eventDescription = eventParts[0];
                        String startTimeString = eventParts[1];
                        String endTimeString = eventParts[2].substring(0, eventParts[2].length() - 1);
                        task = new Event(eventDescription, startTimeString, endTimeString);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + parts[0]);
                }
                if (parts[1].equals("[X]")) {
                    task.changeIsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks.");
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        Velma velma = new Velma(FILE_PATH);
        velma.run();
    }

    public void run() {
        ui.showWelcome();
        Scanner req = new Scanner(System.in);
        boolean end = false;

        while (!end) {
            String request = req.nextLine();
            Command command = getCommand(request);

            try {
                switch (command) {
                    case TODO:
                        String todoDescription = request.replaceFirst("todo\\s*", "").trim();
                        if (todoDescription.isEmpty()) {
                            throw new VelmaException("Sorry boss! Where is your todo description?");
                        }
                        System.out.println("Got it. I've added this task:");
                        Task newTodo = new Todo(todoDescription);
                        tasks.addTask(newTodo);
                        ui.showTaskAdded(newTodo, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case DEADLINE:
                        String[] parts = request.replaceFirst("deadline\\s*", "").split(" /by ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Your deadline task needs a deadline!");
                        }
                        String description = parts[0];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime deadline;
                        try {
                            deadline = LocalDateTime.parse(parts[1], formatter);
                        } catch (DateTimeParseException e) {
                            throw new VelmaException("Sorry boss! The date format is incorrect. Please use d/M/yyyy HHmm.");
                        }

                        Task newDeadline = new Deadline(description, deadline);
                        tasks.addTask(newDeadline);
                        ui.showTaskAdded(new Deadline(description, deadline), tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case EVENT:
                        parts = request.replaceFirst("event\\s+", "").split(" /from | /to ");
                        if (parts.length < 3) {
                            throw new VelmaException("Sorry boss! An event needs a valid start time and end time!");
                        }
                        description = parts[0];
                        String startTime = parts[1];
                        String endTime = parts[2];
                        Task newEvent = new Event(description, startTime, endTime);
                        tasks.addTask(newEvent);
                        ui.showTaskAdded(new Event(description, startTime, endTime), tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case LIST:
                        parts = request.split(" ");
                        if (parts.length == 2) {
                            String dateString = parts[1];
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date;
                            try {
                                date = LocalDate.parse(dateString, dateFormatter);
                            } catch (DateTimeParseException e) {
                                throw new VelmaException("Sorry boss! The date format is incorrect. Please use yyyy-MM-dd.");
                            }
                            ui.showTasksOnDate(tasks.getTasks(), date);
                        } else {
                            ui.showAllTasks(tasks.getTasks());
                        }
                        break;

                    case MARK:
                    case UNMARK:
                        parts = request.split(" ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Please specify which task.");
                        }
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.getTask(taskNumber);
                        task.changeIsDone();
                        if (command == Command.MARK) {
                            System.out.println("Nice! I have marked this task as done:");
                        } else {
                            System.out.println("OK! I have marked this task as not done yet:");
                        }
                        ui.showMarkUnmarkTask(task, task.isDone);
                        storage.save(tasks.getTasks());
                        break;

                    case DELETE:
                        parts = request.split(" ");
                        if (parts.length < 2) {
                            throw new VelmaException("Sorry boss! Please specify which task to delete.");
                        }
                        taskNumber = Integer.parseInt(parts[1]) - 1;
                        Task deletedTask = tasks.getTask(taskNumber);
                        tasks.deleteTask(taskNumber);
                        ui.showTaskDeleted(deletedTask, tasks.getSize());
                        storage.save(tasks.getTasks());
                        break;

                    case BYE:
                        ui.showGoodbye();
                        break;

                    case UNKNOWN:
                    default:
                        throw new VelmaException("Sorry boss! What are you talking about?");
                }
            } catch (VelmaException e) {
               ui.showError(e.getMessage());
            }


        }
    }
}
