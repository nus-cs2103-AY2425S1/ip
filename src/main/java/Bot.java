import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

public class Bot {
    private final String INDENT = "  ";
    private final String DIVIDER = "____________________________________________________________\n";
    private final List<String> TASK_TYPES = List.of(new String[]{"todo", "event", "deadline"});
    private ArrayList<Task> taskList = new ArrayList<>();

    public boolean loadData() {
        Path saveDataPath = Paths.get("./data/Luke.txt");
        try {
            if (Files.notExists(saveDataPath)) {
                throw new NoSaveDataFoundException();
            }
            List<String> lines = Files.readAllLines(saveDataPath);
            for (String line : lines) {
                List<String> inputList = Arrays.asList(line.split("\\|"));
                String mark = inputList.get(0).strip().toLowerCase();
                String command = inputList.get(1).strip();
                String args = String.join(" ", inputList.subList(2, inputList.size())).strip();
                // Debugging
                // System.out.println(line);
                try {
                    switch (mark) {
                    case "x" -> addToList(command, args, true, true);
                    case "-" -> addToList(command, args, false, true);
                    default -> {
                        System.out.println("your task '" + command + " " + args + "' was marked with a '"
                                + mark + "', which i don't quite understand :(\n"
                                + "try reopening Luke.txt and mark every completed task with a 'X' "
                                + "and every pending task with a '-'. \n"
                                + "i'll exit first, run me again when you're ready.");
                        return false;
                    }
                    }
                } catch (UnknownCommandException e) {
                    System.out.println("your task '" + command + " " + args + " has a strange command"
                            + " which i don't quite understand :(\n"
                            + "head to Luke.txt and fix the command.\n"
                            + "i'll exit first, run me again when you're ready.");
                    return false;
                } catch (NoDescriptionException e) {
                    System.out.println("your task '" + command + "' seems just A LITTLE incomplete..."
                            + command + " what?\n"
                            + "head to Luke.txt and give me more info.\n"
                            + "i'll exit first, run me again when you're ready.");
                    return false;
                }
            }
            return true;
        } catch (NoSaveDataFoundException e) {
            System.out.println("i couldn't find a saved task list. you will need to create one to continue using me.\n"
                    + "would you like to create one? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().toLowerCase();
                switch (input) {
                case "y", "yes" -> {
                    try {
                        Files.createFile(saveDataPath);
                        System.out.println("save file created! ok, i'm all ears now. tell me what you need.");
                        System.out.println(DIVIDER);
                        // Don't close scanner here as doing so would close the underlying input stream (System.in).
                        // Once it's closed, it can't be reopened later even if we call acceptCommand().
                        return true;
                    } catch (IOException e2) {
                        System.out.println("oof, i couldn't create the file. i'll exit first - try restarting me!");
                        return false;
                    }
                }
                case "n", "no" -> {
                    System.out.println("alright then. cya ;)");
                    scanner.close();
                    return false;
                }
                default -> System.out.println("didn't quite understand what you said there. try again?");
                }
            }
            scanner.close();
            return true;
        } catch (IOException e) {
            System.out.println("hmmm...i ran into an issue while setting up. try launching me again.");
            return false;
        }
    }

    public void rewriteData() {
        // clear file
        try {
            FileWriter fw = new FileWriter("./data/Luke.txt", false);
            // add all tasks in the current list into the file
            for (Task task : taskList) {
                fw.write(task.taskInSaveData());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("woops! i ran into an issue saving your list. your last saved list can be found "
                    + "at Luke.txt. try launching me again to continue.");
            System.exit(0);
        }
    }

    public void taskNotFound(int taskNumber) {
        System.out.println(DIVIDER
                + "task " + taskNumber + " doesn't exist...try another number!\n"
                + DIVIDER);
    }

    public void acceptCommand() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            String command = inputList.get(0).strip();
            String args = String.join(" ", inputList.subList(1, inputList.size())).strip();
            switch (command) {
            case "bye" -> {
                System.out.println("""
                        ____________________________________________________________
                        yeah bye bye to you too human being
                        ____________________________________________________________
                        """);
                return;
            }
            case "list" -> showList();
            case "mark", "unmark" -> {
                int taskToMark = Integer.parseInt(args);
                // also need to handle the case where a non-integer is passed to mark (NumberFormatException)
                try {
                    markTask(taskToMark, command);
                } catch (IndexOutOfBoundsException e) {
                    taskNotFound(taskToMark);
                }
            }
            case "delete" -> {
                int taskToDelete = Integer.parseInt(args);
                try {
                    deleteTask(taskToDelete);
                } catch (IndexOutOfBoundsException e) {
                    taskNotFound(taskToDelete);
                }
            }
            default -> {
                try {
                    addToList(command, args, false, false);
                } catch (UnknownCommandException e) {
                    System.out.println(DIVIDER
                            + "hmmm i didn't quite understand what you said. try again?\n"
                            + DIVIDER);
                } catch (NoDescriptionException e) {
                    String aOrAn = (Objects.equals(command, "event"))
                            ? " an "
                            : " a ";
                    System.out.println(DIVIDER
                            + "uhhh the description of" + aOrAn
                            + command + " can't be empty :( try again?\n"
                            + DIVIDER);
                }
            }
            }
        }
        scanner.close();
    }

    public String listSizeUpdateMessage() {
        int listSize = taskList.size();
        if (listSize == 1) {
            return "your list has " + listSize + " item now.\n";
        } else {
            return "your list has " + listSize + " items now.\n";
        }
    }
    public void addToList(String command, String args, boolean isMarked, boolean isLoadingFromDisk)
            throws NoDescriptionException, UnknownCommandException {
        if (!(TASK_TYPES.contains(command))) {
            throw new UnknownCommandException();
        }
        if (Objects.equals(args, "")) {
            throw new NoDescriptionException();
        }
        switch (command) {
        case "todo" -> {
            Todo todo = new Todo(args, isMarked);
            taskList.add(todo);
            if (!(isLoadingFromDisk)) {
                System.out.println(DIVIDER
                        + "i've thrown this to-do into your task list:\n"
                        + INDENT + todo.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + DIVIDER);
            }
        }
        case "deadline" -> {
            String[] taskAndDeadline;
            if (isLoadingFromDisk) {
                taskAndDeadline = args.split(" by ");
            } else {
                taskAndDeadline = args.split(" /by ");
            }
            String taskName = taskAndDeadline[0];
            String deadline = taskAndDeadline[1];
            Deadline dl = new Deadline(taskName, deadline, isMarked);
            taskList.add(dl);
            if (!(isLoadingFromDisk)) {
                System.out.println(DIVIDER
                        + "the new deadline's been added to your task list:\n"
                        + INDENT + dl.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + DIVIDER);
            }
        }
        case "event" -> {
            String[] taskAndTimings;
            if (isLoadingFromDisk) {
                taskAndTimings = args.split(" from | to ");
            } else {
                taskAndTimings = args.split(" /from | /to ");
            }
            String taskName = taskAndTimings[0];
            String from = taskAndTimings[1];
            String to = taskAndTimings[2];
            Event event = new Event(taskName, from, to, isMarked);
            taskList.add(event);
            if (!(isLoadingFromDisk)) {
                System.out.println(DIVIDER
                        + "aaaaand this event is now in your task list:\n"
                        + INDENT + event.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + DIVIDER);
            }
        }
        }
        rewriteData();
    }

    public void showList() {
        System.out.println(DIVIDER + "here's everything that's in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i+1) + ". " + task.taskDescription());
        }
        System.out.println(DIVIDER);
    }

    public void markTask(int taskToMark, String command) {
        Task task = taskList.get(taskToMark-1);
        task.changeMark();
        if (Objects.equals(command, "mark")) {
            System.out.println(DIVIDER
                + "ok i've marked this task as done:\n"
                + INDENT + task.taskDescription() + "\n"
                + DIVIDER);
        } else {
            System.out.println(DIVIDER
                    + "ok i've unmarked this task:\n"
                    + INDENT + task.taskDescription() + "\n"
                    + DIVIDER);
        }
        rewriteData();
    }

    public void deleteTask(int taskToDelete) throws IndexOutOfBoundsException{
        Task task = taskList.remove(taskToDelete-1);
        System.out.println(DIVIDER
                + "alright i've purged this task for you:\n"
                + INDENT + task.taskDescription() + "\n"
                + listSizeUpdateMessage()
                + DIVIDER);
    }
}
