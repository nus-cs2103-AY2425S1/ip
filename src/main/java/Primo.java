/*
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Primo {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean ended = false;
    private static ArrayList<Task> list = new ArrayList<>();
    private enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private String command;
        CommandType(String command) {
            this.command = command;
        }

        public static CommandType getCommandType(String s) throws PrimoException {
            for (CommandType type : values()) {
                if (type.command.equals(s)) {
                    return type;
                }
            }
            throw new PrimoException("Invalid command!\n(Expected Commands: todo, deadline, event, mark, unmark, delete, list, bye)\n");
        }
    }

    private static void printList() {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = String.valueOf(i + 1) + "." + list.get(i);
            System.out.println(output);
        }
    }

    private static String getList() {
        int len = list.size();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String output = String.valueOf(i + 1) + "." + list.get(i);
            data.append(output);
            data.append("\n");
        }
        return data.toString();
    }

    private static void sayBye() {
        String byeMessage = "\nEl Primo:\n" +
                            "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        ended = true;
    }

    private static void assessInput(String input) throws PrimoException{
        String[] words = input.split(" ");
        CommandType type = CommandType.getCommandType(words[0]);
        switch (type) {
            case BYE:
                sayBye();
                break;
            case LIST:
                System.out.println("\nEl Primo:");
                System.out.println("Here are the tasks in your list:");
                printList();
                break;
            case MARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("mark <integer> expected");
                }
                int markIndex = Integer.valueOf(words[1]) - 1;
                if (markIndex >= list.size() || markIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                list.get(markIndex).markAsDone();
                System.out.println("\nEl Primo:");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(markIndex));
                writeToData(getList());
                break;
            case UNMARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("unmark <integer> expected");
                }
                int unmarkIndex = Integer.valueOf(words[1]) - 1;
                if (unmarkIndex >= list.size() || unmarkIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                list.get(unmarkIndex).markAsUndone();
                System.out.println("\nEl Primo:");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(unmarkIndex));
                writeToData(getList());
                break;
            case TODO:
                int todoFromIndex = input.indexOf("todo ") + 5;
                String todoDescription = input.substring(todoFromIndex).trim();
                if (todoDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected: todo <string>");
                }
                Task newToDoTask = new ToDoTask(todoDescription);
                list.add(newToDoTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newToDoTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                writeToData(getList());
                break;
            case DEADLINE:
                if (!input.contains("/by")) {
                    throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
                }
                int deadlineFromIndex = input.indexOf("deadline ") + 9;
                int deadlineToIndex = input.indexOf("/by");
                String deadlineDescription = input.substring(deadlineFromIndex, deadlineToIndex).trim();
                if (deadlineDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
                }

                String dueTime = input.substring(deadlineToIndex + 3).trim();
                LocalDate parsedDate = null;
                try {
                    parsedDate = LocalDate.parse(dueTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Deadline not in the form of YYYY-MM-DD or invalid DATE");
                    dueTime = "";
                }
                if (dueTime.isEmpty()) {
                    throw new PrimoException("deadline time empty or wrong formatting! Expected deadline <string> " +
                            "/by YYYY-MM-DD");
                }
                Task newDeadlineTask = new DeadlineTask(deadlineDescription, parsedDate);
                list.add(newDeadlineTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadlineTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                writeToData(getList());
                break;
            case EVENT:
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
                }
                int eventFromIndex = input.indexOf("event ") + 6;
                int eventToIndex = input.indexOf("/from");
                int eventFinalIndex = input.indexOf("/to");
                String eventDescription = input.substring(eventFromIndex, eventToIndex).trim();
                if (eventDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
                }
                String from = input.substring(eventToIndex + 5, eventFinalIndex).trim();
                LocalDate parsedFromDate = null;
                LocalDate parsedToDate = null;
                try {
                    parsedFromDate = LocalDate.parse(from);
                } catch (DateTimeParseException e) {
                    System.out.println("\"From\" date not in the form of YYYY-MM-DD or invalid DATE");
                    from = "";
                }
                if (from.isEmpty()) {
                    throw new PrimoException("'From' parameter empty or wrong formatting! Expected event /from YYYY-MM-DD /to YYYY-MM-DD");
                }
                String to = input.substring(eventFinalIndex + 3).trim();
                try {
                    parsedToDate = LocalDate.parse(to);
                } catch (DateTimeParseException e) {
                    System.out.println("\"To\" date not in the form of YYYY-MM-DD or invalid DATE");
                    to = "";
                }
                if (to.isEmpty()) {
                    throw new PrimoException("'To' parameter cannot be empty! Expected deadline YYYY-MM-DD /by YYYY-MM-DD");
                }
                Task newEventTask = new EventTask(eventDescription, parsedFromDate, parsedToDate);

                list.add(newEventTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newEventTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                writeToData(getList());
                break;
            case DELETE:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("delete <integer> expected");
                }
                int deleteIndex = Integer.valueOf(words[1]) - 1;
                if (deleteIndex >= list.size() || deleteIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                System.out.println("\nEl Primo:");
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(deleteIndex));
                list.remove(deleteIndex);
                writeToData(getList());
                break;
        }
    }
    private static void readInput() {
        System.out.println("\nMe:");
        String input = scanner.nextLine();
        try {
            assessInput(input);
        } catch (PrimoException e) {
            System.out.println(e);
        }
    }

    private static void readData() throws IOException {
        try {
            Path filePath = Paths.get("./data/data.txt");
            List<String> lines = Files.readAllLines(filePath);
            for (String s : lines) {
                String[] words = s.split(" ");
                boolean isDone;
                String name = "";
                String deadline = "";
                String from = "";
                String to = "";
                switch (words[0].charAt(3)) {
                case 'T':
                    int todoFromIndex = 9;
                    String todoDescription = s.substring(todoFromIndex).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newToDoTask = new ToDoTask(todoDescription);
                    if (isDone) {
                        newToDoTask.markAsDone();
                    }
                    list.add(newToDoTask);
                    break;
                case 'D':
                    int deadlineFromIndex = 9;
                    int deadlineToIndex = s.indexOf("(by:");
                    String deadlineDescription = s.substring(deadlineFromIndex, deadlineToIndex).trim();
                    String dueTime = s.substring(deadlineToIndex + 4, s.indexOf(')')).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newDeadlineTask = new DeadlineTask(deadlineDescription, dueTime);
                    if (isDone) {
                        newDeadlineTask.markAsDone();
                    }
                    list.add(newDeadlineTask);
                    break;
                case 'E':
                    String eventDescription = s.substring(9, s.indexOf("(from:")).trim();
                    String eventFromTime = s.substring(s.indexOf("from: ") + 6, s.indexOf("to: ")).trim();
                    String eventToTime = s.substring(s.indexOf("to: ") + 4, s.indexOf(")")).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newEventTask = new EventTask(eventDescription, eventFromTime, eventToTime);
                    if (isDone) {
                        newEventTask.markAsDone();
                    }
                    list.add(newEventTask);
                    break;
                }
            }
        } catch (IOException e) {
            fixCorruptedFile();
        }
    }

    private static void writeToData(String s) {
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(s);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void fixCorruptedFile() throws IOException {
        Path directoryPath = Paths.get("./data");
        Path filePath = directoryPath.resolve("data.txt");
        Files.createDirectories(directoryPath);
        Files.createFile(filePath);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("""
                El Primo:
                Hello! I'm El Primo!!
                What can I do for you?""");

        readData();
        System.out.println("Current Tasks: ");
        printList();

        while (!ended) {
            readInput();
        }
    }
}
*/

public class Primo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Primo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PrimoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PrimoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Primo("data/tasks.txt").run();
    }
}