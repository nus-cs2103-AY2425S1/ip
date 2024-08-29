import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Talker {
    private static final String NAME = "Talker";
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH ="./data/talker.txt";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static ArrayList<Task> list = new ArrayList<>();


    private static Ui ui = new Ui(NAME);
    private static Storage storage = new Storage(DIRECTORY_PATH, FILE_PATH);

    public static void main(String[] args) {

        try {
            list = storage.readFile();
        } catch (TalkerException e) {
            ui.printError(e);
        }

        ui.printWelcome();
        Scanner reader = new Scanner(System.in);
        // read user input
        String input = reader.nextLine();
        ui.printLine();

        // if command "bye" entered, exit
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input);
                storage.writeFile(list);
            } catch (TalkerException e) {
                ui.printError(e);
            } finally {
                ui.printLine();
                input = reader.nextLine();
                ui.printLine();
            }
        }
        ui.printGoodBye();
    }

    public static void printTasksOn(String date) throws TalkerException {
        LocalDate targetDate;
        try {
            String[] parsed = date.split(" ");
            targetDate = LocalDate.parse(parsed[1], INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new TalkerException("Incorrect date format. Try again with: yyyy/MM/dd");
        }
        ui.printTasksOn(targetDate.format(OUTPUT_FORMAT));
        for (Task task: list) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline().toLocalDate();
                if (targetDate.isBefore(deadline) || targetDate.isEqual(deadline)) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                LocalDate start = eventTask.getFrom().toLocalDate();
                LocalDate end = eventTask.getTo().toLocalDate();
                if ((targetDate.isAfter(start) || targetDate.isEqual(start))
                        && (targetDate.isBefore(end) || targetDate.isEqual(end))) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            }
        }
    }

    public static void listTasks() throws TalkerException {
        if (list.isEmpty()) {
            throw new TalkerException("List is empty!");
        }
        ui.printTaskList(list);
    }

    public static void markTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            ui.printTaskMarked(list.get(index).mark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Mark format wrong. Try again with: mark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
        throw new TalkerException("Task not found!");
        }
    }

    public static void unmarkTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            ui.printTaskUnmarked(list.get(index).unmark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Unmark format wrong. Try again with: unmark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    public static void deleteTask(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            Task removed = list.remove(index);
            ui.printTaskDelete(removed, list.size());
        } catch (NumberFormatException e) {
            throw new TalkerException("Delete format wrong. Try again with: delete <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    public static void createToDo(String input) throws TalkerException {
        try {
            String desc = input.substring(5);
            Task newTask = new ToDo(desc);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("ToDo format wrong. Try again with: todo <description>");
        }
    }

    public static void createDeadline(String input) throws TalkerException {
        try {
            String contents = input.substring(9);

            String[] parsed = contents.split(" /by ", 2);
            String desc = parsed[0];
            String by = parsed[1];

            Task newTask = new Deadline(desc, by);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Deadline format wrong. Try again with: deadline <description> /by <dd-MM-yyyy HH:mm>");
        }
    }

    public static void createEvent(String input) throws TalkerException {
        try {
            String contents = input.substring(6);

            String[] parsed1 = contents.split(" /from ", 2);
            String[] parsed2 = parsed1[1].split(" /to ", 2);
            String desc = parsed1[0];
            String from = parsed2[0];
            String to = parsed2[1];

            Task newTask = new Event(desc, from, to);

            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Event format wrong. Try again with: event <description> " +
                            "/from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
        }
    }
}

