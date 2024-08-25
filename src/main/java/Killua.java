import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Killua {
    private static final String LINE = "______________________________________________________________________________";

    private static void showUserPage() {
        printLine();
        System.out.println("Welcome to Killua Task Manager!");
        System.out.println("Here are some commands you can use:");
        System.out.println("  bye - Exit the application");
        System.out.println("  list - List all tasks");
        System.out.println("  mark <task number> - Mark a task as done");
        System.out.println("  unmark <task number> - Mark a task as not done yet");
        System.out.println("  delete <task number> - Delete a task");
        System.out.println("  todo <description> - Add a new todo task");
        System.out.println("  deadline <description> /by <yyyy-mm-dd> OR ");
        System.out.println("        deadline <description> /by <yyyy-mm-dd hh:mm> - Add a new deadline task");
        System.out.println("  event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> OR ");
        System.out.println("        event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm> - Add a new event task");
        System.out.println("  on <yyyy-mm-dd> - Show tasks occurring on a specific date");
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void add(ArrayList<Task> tasks, Task task){
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
        printLine();
    }

    private static void delete(ArrayList<Task> tasks, int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);

        printLine();
        System.out.println("OK, I've deleted this task:");
        System.out.println("  " + task);
        printLine();
    }

    private static void bye(){
        printLine();
        System.out.println("Bye!");
        printLine();
    }

    private static void listTask(ArrayList<Task> tasks){
        printLine();

        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }

        printLine();
    }

    private static void markTaskDone(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).markAsDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void unmarkTask(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).unmark();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void saveList(ArrayList<Task> tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter fw = new FileWriter("./data/tasklist.txt");
        for (Task task : tasks) {
            fw.write(task.toSave() + System.lineSeparator());
        }
        fw.close();
    }

    private static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("./data/tasklist.txt");

        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                tasks.add(task);
            }
            scanner.close();
        }

        return tasks;
    }

    private static Task parseTask(String line) {
        char taskType = line.charAt(0);
        boolean isDone = line.charAt(4) == 1;
        String argument = line.substring(8);

        Task task;

        if (taskType == 'T') {
            task = new Todo(argument);
        } else if (taskType == 'D') {
            String[] strs = argument.split("\\|", 2);
            task = getDeadline2(strs[0].strip(), strs[1].strip());
        } else if (taskType == 'E') {
            String[] strs = argument.split("\\|", 3);
            task = getEvent2(strs[0].strip(), strs[1].strip(), strs[2].strip());
        } else {
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private static Task getDeadline(String argument) {
        Task deadline;
        String[] strs = argument.split("/", 2);
        String by = strs[1].substring(3).strip();
        String[] dateAndTime = by.split(" ", 2);
        if (dateAndTime.length == 2) {
            LocalDateTime dataTime = LocalDateTime.parse(dateAndTime[0] + "T" + dateAndTime[1]);
            deadline = new Deadline(strs[0].strip(), dataTime);
        } else {
            deadline = new Deadline(strs[0].strip(), LocalDate.parse(dateAndTime[0]));
        }
        return deadline;
    }

    private static Task getEvent(String argument) {
        Task event;
        String[] strs = argument.split("/", 3);
        String from = strs[1].substring(5).strip();
        String to = strs[2].substring(3).strip();
        String[] dateAndTimeFrom = from.split(" ", 2);
        String[] dateAndTimeTo = to.split(" ", 2);
        if (dateAndTimeFrom.length == 2) {
            LocalDateTime dataTimeFrom = LocalDateTime.parse(dateAndTimeFrom[0] + "T" + dateAndTimeFrom[1]);
            LocalDateTime dataTimeTo = LocalDateTime.parse(dateAndTimeTo[0] + "T" + dateAndTimeTo[1]);
            event = new Event(strs[0].strip(), dataTimeFrom, dataTimeTo);
        } else {
            LocalDate dateFrom = LocalDate.parse(dateAndTimeFrom[0]);
            LocalDate dateTo = LocalDate.parse(dateAndTimeTo[0]);
            event = new Event(strs[0].strip(), dateFrom, dateTo);
        }
        return event;
    }

    private static Task getDeadline2(String description, String dateTimeString) {
        Task deadline;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            deadline = new Deadline(description, dateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, dateFormatter);
                deadline = new Deadline(description, date);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: " + dateTimeString);
            }
        }

        return deadline;
    }

    private static Task getEvent2(String description, String dateTimeStringFrom, String dateTimeStringTo) {
        Task event;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            LocalDateTime fromDateTime;
            LocalDateTime toDateTime;

            try {
                fromDateTime = LocalDateTime.parse(dateTimeStringFrom, dateTimeFormatter);
                toDateTime = LocalDateTime.parse(dateTimeStringTo, dateTimeFormatter);
                event = new Event(description, fromDateTime, toDateTime);
            } catch (DateTimeParseException e1) {
                LocalDate fromDate = LocalDate.parse(dateTimeStringFrom, dateFormatter);
                LocalDate toDate = LocalDate.parse(dateTimeStringTo, dateFormatter);
                event = new Event(description, fromDate, toDate);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format in event: from '" + dateTimeStringFrom + "' to '" + dateTimeStringTo + "'", e);
        }

        return event;
    }

    private static void showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        printLine();
        boolean hasTasks = false;
        System.out.println("Tasks on " + date + ":");

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) {
                    System.out.println("  " + task);
                    hasTasks = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getStartDate().isAfter(date) && !event.getEndDate().isBefore(date)) {
                    System.out.println("  " + task);
                    hasTasks = true;
                }
            }
        }

        if (!hasTasks) {
            System.out.println("No tasks found for this date.");
        }

        printLine();
    }

    public static void main(String[] args) {
        showUserPage();

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = loadTasks();
        } catch (IOException e) {
            printLine();
            System.out.println("Error loading tasks: " + e.getMessage());
            printLine();
        }

        while (isRunning) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String commandStr = parts[0];
            String argument = (parts.length > 1) ? parts[1] : "";

            try {
                Command command = Command.fromString(commandStr);

                switch (command) {
                case BYE:
                    bye();
                    isRunning = false;
                    break;
                case LIST:
                    listTask(tasks);
                    break;
                case MARK, UNMARK, DELETE:
                    try {
                        int taskNumber = Integer.parseInt(argument);

                        if (command == Command.MARK) {
                            markTaskDone(tasks, taskNumber - 1);
                        } else if (command == Command.UNMARK) {
                            unmarkTask(tasks, taskNumber - 1);
                        } else {
                            delete(tasks, taskNumber - 1);
                        }
                    } catch (NumberFormatException e) {
                        throw new KilluaException("Please provide a valid number! e.g., " + command + " 1");
                    } catch (IndexOutOfBoundsException e) {
                        throw new KilluaException("Task not found: Task " + argument);
                    }
                    saveList(tasks);
                    break;
                case TODO:
                    if (Objects.equals(argument, "")) {
                        throw new KilluaException("Todo description cannot be empty!");
                    }

                    Task todo = new Todo(argument);
                    tasks.add(todo);
                    add(tasks, todo);
                    saveList(tasks);
                    break;
                case DEADLINE:
                    if (Objects.equals(argument, "")) {
                        throw new KilluaException("Deadline description cannot be empty!");
                    }
                    try {
                        Task deadline = getDeadline(argument);
                        tasks.add(deadline);
                        add(tasks, deadline);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new KilluaException("Please use the correct format for deadlines!");
                    } catch (DateTimeParseException e1) {
                        throw new KilluaException("Please use the correct format for dates: yyyy-mm-dd");
                    }
                    saveList(tasks);
                    break;
                case EVENT:
                    if (Objects.equals(argument, "")) {
                        throw new KilluaException("Event description cannot be empty!");
                    }
                    try {
                        Task event = getEvent(argument);
                        tasks.add(event);
                        add(tasks, event);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new KilluaException("Please use the correct format for events!");
                    } catch (DateTimeParseException e1) {
                        throw new KilluaException("Please use the correct format for dates: yyyy-mm-dd");
                    }
                    saveList(tasks);
                    break;
                case ON:
                    try {
                        LocalDate date = LocalDate.parse(argument);
                        showTasksOnDate(tasks, date);
                    } catch (DateTimeParseException e) {
                        throw new KilluaException("Please use the correct format for dates: yyyy-mm-dd");
                    }
                    break;
                }
            } catch (KilluaException | IOException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

}
