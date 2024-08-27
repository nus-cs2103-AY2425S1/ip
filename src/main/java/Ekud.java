import java.io.*;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Scanner;

public class Ekud {
    public static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";
    public static final String OUTPUT_PREFIX = "\t ";
    public static final String END_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMNAD = "mark";
    public static final String DELETE_COMMAND = "delete";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String TASK_SAVE = "data/tasks.txt";

    private final TaskList tasks = new TaskList();
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public void loadTasks() {
        try {
            FormatPrinter.printIndent("Give a sec, I'm trying to find your tasks...", OUTPUT_PREFIX);
            File save = new File("data/tasks.txt");
            if (save.isFile()) {
                FormatPrinter.printIndent("Great! I've found your tasks!!", OUTPUT_PREFIX);
                try (BufferedReader reader = new BufferedReader(new FileReader(save))) {
                    String currLine = reader.readLine();
                    while (currLine != null) {
                        String taskSave = currLine.trim();
                        Task task = Task.getTaskFromSave(taskSave);
                        if (task != null) {
                            tasks.addTask(task);
                        }
                        currLine = reader.readLine();
                    }
                }
            } else {
                save.createNewFile();
                FormatPrinter.printIndent(
                        "Looks like your save doesn't exists... I've created a new one just for you!!",
                        OUTPUT_PREFIX);
            }
        } catch (IOException e) {
            // print error message and end program
            setRunning(false);
            FormatPrinter.printIndent(
                    String.format("""
                            Something went wrong when trying to load your save!
                            ERROR: %s
                            Proceeding with System shutdown!!!""",
                            e),
                    OUTPUT_PREFIX);
        } finally {
            printLineSeparator();
        }
    }

    public void greet() {
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        printLineSeparator();
        FormatPrinter.printIndent(greeting, OUTPUT_PREFIX);
        printLineSeparator();
    }

    public void sayGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        FormatPrinter.printIndent(goodbye, OUTPUT_PREFIX);
        printLineSeparator();
    }

    public void addToList(Task task) {
        tasks.addTask(task);
        String confirmation = String.format("added: %s\nAnd another one; %d out of %d tasks to complete...",
                task,
                tasks.getIncompleteCount(),
                tasks.getCount());
        FormatPrinter.printIndent(confirmation, OUTPUT_PREFIX);
    }

    public void echoList() {
        if (tasks.isEmpty()) {
            System.out.printf("%sWould Ya look at: No tasks to be found. Shocking ain't it!\n",
                    OUTPUT_PREFIX);
        } else {
            System.out.println(OUTPUT_PREFIX + "Look at all these tasks:");
            int i = 1;
            for (Task task : tasks) {
                System.out.printf("%s%d. %s\n", OUTPUT_PREFIX, i, task);
                i++;
            }
        }
    }

    public int readInt(String input) throws EkudWrongInputFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            String message = String.format("""
                            Now look what you've done!!
                            I thought it was obvious... But '%s' is clearly not an Integer!""",
                    input);
            throw new EkudWrongInputFormatException(message);
        }
    }

    public void markList(int listIndex) throws TaskListIndexOutOfBoundsException {
        tasks.markComplete(listIndex);
        String listStatus = tasks.isAllComplete()
                            ? String.format("WOOHOO!! YOU DID IT! Everything is complete!! All %d of them",
                                    tasks.getCount())
                            : String.format("Woohoo!! Only %d out of %d tasks more to go",
                                    tasks.getIncompleteCount(),
                                    tasks.getCount());
        String message = String.format("""
                        Wowie!! You've completed your task!
                        I shall mark it as complete in celebration!
                          %s
                        %s!""",
                tasks.getTask(listIndex),
                listStatus);
        FormatPrinter.printIndent(message, OUTPUT_PREFIX);
    }

    public void unmarkList(int listIndex) throws TaskListIndexOutOfBoundsException {
        tasks.markIncomplete(listIndex);
        String message = String.format("""
                        Oh ho ho, did you perhaps forget something?
                        It's OK, I already noted down your incompetence...
                          %s
                        Tsk Tsk... Back to %d out of %d incomplete tasks you go!""",
                tasks.getTask(listIndex),
                tasks.getIncompleteCount(),
                tasks.getCount());
        FormatPrinter.printIndent(message, OUTPUT_PREFIX);
    }

    public void deleteList(int listIndex) throws TaskListIndexOutOfBoundsException {
        Task removed = tasks.removeTask(listIndex);
        String completeResponse = removed.isDone()
                                  ? "Great work on completing your task!"
                                  : "I'm going to assume that task wasn't meant to be there...";
        String listStatus;
        if (tasks.isEmpty()) {
            listStatus = "Well, looks like there is nothing left for you do!";
        } else if (tasks.isAllComplete()) {
            listStatus = String.format("I've ran the numbers, and it says that all %d tasks are complete!",
                    tasks.getCount());
        } else {
            listStatus = String.format("Now get a move on, "
                            + "you have %d out of %d incomplete tasks remaining!",
                   tasks.getIncompleteCount(),
                   tasks.getCount());
        }
        String message = String.format("""
                        %s
                        Proceeding with task removal directive...
                          deleted: %s
                        %s
                        """,
                completeResponse,
                removed,
                listStatus);
        FormatPrinter.printIndent(message, OUTPUT_PREFIX);
    }

    public void warnUnknownCommand() {
        String warning = "Quit yapping buddy, I have no clue what ya saying!";
        FormatPrinter.printIndent(warning, OUTPUT_PREFIX);
    }

    public static HashMap<String, String> parseTokens(String[] tokens) {
        // parses scanner into a map of tokens into a map of keywords and values
        // each line of commands has at least 2 keywords: command & argument
        StringBuilder tokenBuilder = new StringBuilder();
        HashMap<String, String> tokenMap = new HashMap<>();
        // get command
        tokenMap.put("command", tokens[0]);
        // get params
        String currToken = "argument";
        for (int i = 1; i < tokens.length; i++) {
            // encounter optional token
            if (!tokens[i].isEmpty() && tokens[i].charAt(0) == '/') {
                tokenMap.put(currToken, tokenBuilder.toString());
                currToken = tokens[i];
                tokenBuilder.setLength(0); // reset builder
            } else {
                if (!tokenBuilder.isEmpty()) { // add space in between words
                    tokenBuilder.append(" ");
                }
                tokenBuilder.append(tokens[i]);
            }
        }
        tokenMap.put(currToken, tokenBuilder.toString());
        return tokenMap;
    }

    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        Scanner sc = new Scanner(System.in);

        ekud.greet();
        ekud.loadTasks();
        while (ekud.isRunning()) {
            // get user command
            // assumes user puts correct format
            System.out.println();
            String input = sc.nextLine();
            String[] tokens = input.split("\\s"); // delimits input by space into array of String
            HashMap<String, String> tokenMap = parseTokens(tokens);
            String command = tokenMap.get("command");
            String argument = tokenMap.get("argument");
            printLineSeparator();

            // handle command
            try {
                switch (command) {
                case END_COMMAND:
                    ekud.setRunning(false);
                    break;
                case LIST_COMMAND:
                    ekud.echoList();
                    break;
                case MARK_COMMNAD:
                    ekud.markList(ekud.readInt(argument) - 1);
                    break;
                case UNMARK_COMMAND:
                    ekud.unmarkList(ekud.readInt(argument) - 1);
                    break;
                case DELETE_COMMAND:
                    ekud.deleteList(ekud.readInt(argument) - 1);
                    break;
                case TODO_COMMAND:
                    ekud.addToList(new TodoTask(argument));
                    break;
                case DEADLINE_COMMAND:
                    ekud.addToList(new DeadlineTask(argument, tokenMap.get("/by")));
                    break;
                case EVENT_COMMAND:
                    ekud.addToList(new EventTask(argument, tokenMap.get("/from"), tokenMap.get("/to")));
                    break;
                default:
                    ekud.warnUnknownCommand();
                }
            } catch (TaskArgumentMissingException | EkudWrongInputFormatException
                     | TaskListIndexOutOfBoundsException err) {
                FormatPrinter.printIndent(err.getMessage(), OUTPUT_PREFIX);
            } finally {
                if (ekud.isRunning()) {
                    printLineSeparator();
                }
            }
        }
        ekud.sayGoodbye();
        sc.close();
    }
}
