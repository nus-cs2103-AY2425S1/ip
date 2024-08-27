import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Colress {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_CHECK = "check";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_UNCHECK = "uncheck";
    private static final String COMMAND_LIST = "list";
    private static final String MESSAGE_ADD_CONFIRMATION
            = "Okay. I have added this task to your list:";
    private static final String MESSAGE_CHECK_CONFIRMATION
            = "Sure. I have marked this task on your list as done:";
    private static final String MESSAGE_DELETE_CONFIRMATION
            = "Got it. I have removed the task from your list.";
    private static final String MESSAGE_FAREWELL = "Well then, I'll see you soon.";
    private static final String MESSAGE_FILE_CREATED_CONFIRMATION = "New task list file created.";
    private static final String MESSAGE_FILE_EXISTS = "Task list file exists. Retrieving file.";
    private static final String MESSAGE_FILE_CORRUPTED_ERROR
            = "The task file seems to be corrupted.";
    private static final String MESSAGE_FILE_CREATION_ERROR
            = "I could not get the task file for you. Try again.";
    private static final String MESSAGE_FILE_DOES_NOT_EXIST_ERROR
            = "The task file does not exist. Try again.";
    private static final String MESSAGE_FILE_WRITER_CREATION_ERROR
            = "I could not create a file writer for you. Try again.";
    private static final String MESSAGE_FILE_WRITING_ERROR
            = "I could not write the task file for you. Try again.";
    private static final String MESSAGE_GREETING = "Hello. My name is Colress.\n"
            + "What brings you here?";
    private static final String MESSAGE_LIST_EMPTY
            = "Your list is empty.";
    private static final String MESSAGE_NOT_A_NUMBER_ERROR
            = "You did not seem to have entered a number. Try Again.";
    private static final String MESSAGE_NOT_A_VALID_NUMBER_ERROR
            = "You did not seem to have entered a valid number. Try Again.";
    private static final String MESSAGE_UNCHECK_CONFIRMATION
            = "Right. I have marked this task on your list as not done:";
    private static final String PROMPT_DEADLINE = "Enter the deadline.";
    private static final String PROMPT_EVENT_DATE = "Enter the date of the event.";
    private static final String PROMPT_EVENT_DESCRIPTION = "Enter the description of the event.";
    private static final String PROMPT_EVENT_END_TIME = "Enter the ending time of the event.";
    private static final String PROMPT_EVENT_START_TIME = "Enter the starting time of the event.";
    private static final String PROMPT_TASK_DESCRIPTION = "Enter the description of the task.";
    private static final String PROMPT_TASK_NUMBER = "Enter the task number.";
    private static final String PROMPT_TASK_TYPE = "Enter the type of task you wish to add to your list.";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPACER = "____________________________________________________________________\n";
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static String input = "";
    private static File taskList = null;
    private static FileWriter writer = null;

    public static void print(String s) {
        System.out.println(Colress.SPACER + s + "\n" + Colress.SPACER);
    }

    public static void getInput() {
        Colress.input = Colress.SCANNER.nextLine().toLowerCase();
    }

    public static void retrieveFile() {
        File file = new File("task.txt");
        try {
            if (file.createNewFile()) {
                print(Colress.MESSAGE_FILE_CREATED_CONFIRMATION);
            } else {
                print(Colress.MESSAGE_FILE_EXISTS);
            }
        } catch (IOException e) {
            print(Colress.MESSAGE_FILE_CREATION_ERROR);
        }
        Colress.taskList = file;
    }

    public static void initialiseFileWriter() {
        try {
            Colress.writer = new FileWriter(Colress.taskList, false);
        } catch (IOException e) {
            print(Colress.MESSAGE_FILE_WRITER_CREATION_ERROR);
        }
    }

    public static void makeList() {
        retrieveFile();
        repopulateTasks();
        Colress.print(Colress.printTasks());

        Colress.getInput();
        while (!Colress.input.equals(Colress.COMMAND_EXIT)) {
            try {
                switch (Colress.input) {
                case Colress.COMMAND_ADD:
                    Colress.addToTasks();
                    break;
                case Colress.COMMAND_CHECK, Colress.COMMAND_UNCHECK, Colress.COMMAND_DELETE:
                    Colress.editTasks(Colress.input);
                    break;
                case Colress.COMMAND_LIST:
                    Colress.print(Colress.printTasks());
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException e) {
                print(String.valueOf(e));
            }
            writeToTaskFile();
            Colress.getInput();
        }
    }

    public static void addToTasks() {
        Task currTask = null;
        String description;
        String date;
        String from;
        String to;

        while (currTask == null) {
            try {
                print(Colress.PROMPT_TASK_TYPE);
                Colress.getInput();

                switch (Colress.input) {
                case "todo":
                    Colress.print(Colress.PROMPT_TASK_DESCRIPTION);
                    description = Colress.SCANNER.nextLine();

                    currTask = new ToDo(description);
                    Colress.TASKS.add(currTask);
                    Colress.print(Colress.MESSAGE_ADD_CONFIRMATION
                            + "\n"
                            + Colress.TASKS.size()
                            + ". "
                            + currTask);
                    break;
                case "deadline":
                    Colress.print(Colress.PROMPT_TASK_DESCRIPTION);
                    description = Colress.SCANNER.nextLine();

                    Colress.print(Colress.PROMPT_DEADLINE);
                    date = Colress.SCANNER.nextLine();

                    currTask = new Deadline(description, date);
                    Colress.TASKS.add(currTask);
                    Colress.print(Colress.MESSAGE_ADD_CONFIRMATION
                            + "\n"
                            + Colress.TASKS.size()
                            + ". "
                            + currTask);
                    break;
                case "event":
                    Colress.print(Colress.PROMPT_EVENT_DESCRIPTION);
                    description = Colress.SCANNER.nextLine();

                    Colress.print(Colress.PROMPT_EVENT_DATE);
                    date = Colress.SCANNER.nextLine();

                    Colress.print(Colress.PROMPT_EVENT_START_TIME);
                    from = Colress.SCANNER.nextLine();

                    Colress.print(Colress.PROMPT_EVENT_END_TIME);
                    to = Colress.SCANNER.nextLine();

                    currTask = new Event(description, date, from, to);
                    Colress.TASKS.add(currTask);
                    Colress.print(Colress.MESSAGE_ADD_CONFIRMATION
                            + "\n"
                            + Colress.TASKS.size()
                            + ". "
                            + currTask);
                    break;
                default:
                    throw new UnknownTaskTypeException();
                }
            } catch (UnknownTaskTypeException e) {
                print(String.valueOf(e));
            }
        }
    }

    public static void editTasks(String action) throws UnknownCommandException {
        if (Colress.TASKS.isEmpty()) {
            Colress.print(Colress.MESSAGE_LIST_EMPTY);
        } else {
            int index = -1;
            Task currTask = null;
            while (currTask == null) {
                Colress.print(Colress.PROMPT_TASK_NUMBER);
                try {
                    index = Integer.parseInt(Colress.SCANNER.nextLine());
                    currTask = Colress.TASKS.get(index - 1);
                } catch (NumberFormatException e) {
                    print(Colress.MESSAGE_NOT_A_NUMBER_ERROR);
                } catch (IndexOutOfBoundsException e) {
                    print(Colress.MESSAGE_NOT_A_VALID_NUMBER_ERROR);
                }
            }

            switch (action) {
            case "check":
                currTask.check();
                Colress.print(Colress.MESSAGE_CHECK_CONFIRMATION + "\n" + index + "." + currTask);
                break;
            case "uncheck":
                currTask.uncheck();
                Colress.print(Colress.MESSAGE_UNCHECK_CONFIRMATION + "\n" + index + "." + currTask);
                break;
            case "delete":
                Colress.TASKS.remove(index - 1);
                Colress.print(Colress.MESSAGE_DELETE_CONFIRMATION + "\n" + printTasks());
                break;
            default:
                throw new UnknownCommandException();
            }
        }
    }

    public static String printTasks() {
        String result = "";
        for(int i = 0; i < Colress.TASKS.size(); i++) {
            result += String.format("\n%d. " + Colress.TASKS.get(i), i + 1);
        }
        if(result.isEmpty()) {
            return Colress.MESSAGE_LIST_EMPTY;
        }
        return "Here is your list:" + result;
    }

    public static void writeToTaskFile() {
        try {
            String result = "";
            for(int i = 0; i < Colress.TASKS.size(); i++) {
                result += String.format(Colress.TASKS.get(i) + "\n", i + 1);
            }
            initialiseFileWriter();
            Colress.writer.write(result);
            Colress.writer.close();
        } catch (IOException e) {
            print(Colress.MESSAGE_FILE_WRITING_ERROR);
        }
    }

    public static void repopulateTasks() {
        try {
            Scanner reader = new Scanner(Colress.taskList);
            String[] strings;
            String currLine;

            while (reader.hasNextLine()) {
                currLine = reader.nextLine();
                strings = currLine.split(" \\| ");
                boolean isChecked;
                if (Objects.equals(strings[0], "[X]")) {
                    isChecked = true;
                } else if (Objects.equals(strings[0], "[ ]")) {
                    isChecked = false;
                } else {
                    Colress.print(Colress.MESSAGE_FILE_CORRUPTED_ERROR);
                    continue;
                }

                switch (strings[1]) {
                case "To-Do":
                    Colress.TASKS.add(new ToDo(strings[2], isChecked));
                    break;
                case "Deadline":
                    Colress.TASKS.add(new Deadline(strings[2], strings[3], isChecked));
                    break;
                case "Event":
                    String[] times = strings[4].split(" to ");
                    Colress.TASKS.add(new Event(strings[2], strings[3], times[0], times[1], isChecked));
                    break;
                default:
                    Colress.print(Colress.MESSAGE_FILE_CORRUPTED_ERROR);
                }
            }
        } catch (FileNotFoundException e) {
            Colress.print(Colress.MESSAGE_FILE_DOES_NOT_EXIST_ERROR);
        }
    }

    public static void main(String[] args) {
        Colress.print(Colress.MESSAGE_GREETING);
        Colress.makeList();
        Colress.print(Colress.MESSAGE_FAREWELL);
    }
}
