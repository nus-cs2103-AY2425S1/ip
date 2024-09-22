package hoodini;
import java.io.IOException;


/**
 * Handles the user inputs and file inputs.
 * Ensures that the inputs are valid
 */
public class Parser {

    private static final String TASK_ERROR = "Whoopsie! Please enter a task";
    private static final String NUMBER_ERROR = "Whoopsie! Please enter a number";

    private Storage store;
    private Ui ui;



    /**
     * Stores storage and ui objects
     * @param store Tasklist to store tasks
     * @param ui Handles messages to the user
     */
    public Parser(Storage store, Ui ui) {
        this.store = store;
        this.ui = ui;

    }

    /**
     * Scans for file on desktop and load the file
     */
    public void load() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home,
                "Desktop", "hoodini.txt");
        if (java.nio.file.Files.exists(path)) {
            try {
                readFromFile(path.toString());
            } catch (ReadException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void handleFileToDo(String str) {
        if (str.startsWith("[X]")) {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            toDo.markdone();
            store.add(toDo);

        } else {
            String str1 = "todo " + str.substring(4);
            ToDo toDo = new ToDo(str1);
            store.add(toDo);

        }
    }


    private void handleFileDeadline(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0] + " ",
                    str1.split("by: ")[1]
                            .replace(")", "")
                            .trim());
            deadline.markdone();
            store.add(deadline);

        } else {
            String str1 = str.substring(4);
            Deadline deadline = new Deadline(str1.split(" ")[0] + " ",
                    str1.split("by: ")[1]
                            .replace(")", "")
                            .trim());
            store.add(deadline);

        }
    }

    private void handleFileEvent(String str) {
        if (str.startsWith("[X]")) {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ", 2)[0] + " ",
                    str1.split("from:")[1].split(" to: ")[0],
                    str1.split("to:")[1].replace(")", ""));
            event.markdone();
            store.add(event);

        } else {
            String str1 = str.substring(4);
            Event event = new Event(str1.split(" ", 2)[0] + " ",
                    str1.split("from:")[1].split(" to: ")[0],
                    str1.split("to:")[1].replace(")", ""));
            store.add(event);

        }
    }

    /**
     * Reads from file and input content
     * to the chatbot
     * @param filepath the path of the file
     * @throws ReadException Handles invalid tasks in file
     */

    public void readFromFile(String filepath) throws ReadException {
        try {
            java.io.File file = new java.io.File(filepath);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("[T]")) {
                    handleFileToDo(str.substring(4));
                } else if (str.startsWith("[D]")) {
                    handleFileDeadline(str.substring(4));
                } else if (str.startsWith("[E]")) {
                    handleFileEvent(str.substring(4));
                } else {
                    throw new ReadException("Whoopsie! "
                            + "There are invalid tasks in the file");
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Whoopsie! File not found");

        }
    }


    /**
     * Ends the chatbot and writes to file
     */
    private String end() {
        try {
            String home = System.getProperty("user.home");
            String filePath = home + "/Desktop/hoodini.txt";
            store.writeToFile(filePath);
            return ui.showGoodbye();
        } catch (IOException e) {
            return "Whoopsie! There was an error writing to the file";
        }
    }

    /**
     * Handles the user inputs and identifies tasks to store
     * into the task list
     */
    public String handleInput(String input) {
        assert input != null : "Input should not be null";
        try {
            String command = input.split(" ")[0];
            Case cases = Case.getCase(command);
            switch (cases) {
            case BYE:
                return end();
            case LIST:
                return store.output();
            case TODO:
                return handleToDo(input);
            case DEADLINE:
                return handleDeadline(input);
            case EVENT:
                return handleEvent(input);
            case MARK:
                return handleMark(input);
            case UNMARK:
                return handleUnmark(input);
            case DELETE:
                return handleDelete(input);
            case FIND:
                return handleFind(input);
            case SORT:
                return store.sort();
            case ERROR:
            default:
                throw new InvalidInputException("Whoopsie! "
                        + "I am unable to understand your request!");
            }
        } catch (HandleException e) {
            return e.getMessage();
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles ToDo tasks by user
     * @param str Input string from user
     * @throws HandleException Handles invalid tasks
     */
    private String handleToDo(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("todo")) {
            throw new HandleException(TASK_ERROR);
        } else {
            ToDo toDo = new ToDo(str);
            return store.store(toDo);
        }
    }

    /**
     * Handles Deadline tasks by user
     * @param str Input string from user
     * @throws HandleException Handles invalid tasks
     */
    private String handleDeadline(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("deadline")) {
            throw new HandleException(TASK_ERROR);
        } else {
            try {
                Deadline deadline = new Deadline(str);
                return store.store(deadline);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please enter deadline in the format "
                        + "deadline <task> /by <date>";
            } catch (java.time.format.DateTimeParseException e) {
                return "Please enter the date "
                        + "in the format yyyy-mm-dd";
            }
        }
    }

    /**
     * Handles Event tasks by user
     * @param str Input string from user
     * @throws HandleException Handles invalid tasks
     */
    private String handleEvent(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("event")) {
            throw new HandleException(TASK_ERROR);
        } else {
            try {
                Event event = new Event(str);
                return store.store(event);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please enter event in the format "
                        + "event <task> /from <input> /to <input>";
            }

        }
    }

    private String handleDelete(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("delete")) {
            throw new HandleException(TASK_ERROR);
        } else {
            return store.delete(str);
        }
    }

    private String handleMark(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("mark")) {
            throw new HandleException(NUMBER_ERROR);
        } else {
            return store.mark(str);
        }
    }

    private String handleUnmark(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("unmark")) {
            throw new HandleException(NUMBER_ERROR);
        } else {
            return store.unmark(str);
        }
    }

    private String handleFind(String str) throws HandleException {
        assert str != null : "Input should not be null";
        if (str.trim().equalsIgnoreCase("find")) {
            throw new HandleException("Whoopsie! Please enter something to find");
        } else {
            return store.find(str);
        }
    }
}
