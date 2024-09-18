package greetbot;

import java.time.format.DateTimeParseException;

//the design of the class is referring to
// https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Duke.java#L150

/**
 * A class which functions as main control unit of the chatbot.
 */
public class GreetBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning;

    /**
     * The constructor which creates the GreetBot instance.
     * And sets up all corresponding elements.
     * @param filePath Specifies the file path of the database.
     */
    public GreetBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        tasks = new TaskList(storage.load());
    }

    // run and getResponse method is adapted from
    // https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Duke.java

    /**
     * Runs the chatbot.
     */
    public void run() {
        System.out.println(this.ui.greetUser());

        while (this.isRunning) {
            try {
                String input = this.ui.readInput().strip();
                System.out.println(this.getResponse(input));
            } catch (RandomInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Returns a response string from ui directly or from the subprocess classes.
     * @param input Input string from user keyboard.
     * @return the string that needs to be printed.
     * @throws RandomInputException exception when non-command input passes in through keyboard.
     * @throws EmptyDescriptionException exception when nothing follows the command.
     */
    public String getResponse(String input) throws RandomInputException,
            EmptyDescriptionException, NumberFormatException, ArrayIndexOutOfBoundsException {
        final int keywordPart = 0;
        final int markUnmarkDeletePosition = 1;
        final int argumentsPart = 1;

        String[] segment = Parser.parseCommand(input);
        String keyword = segment[keywordPart];
        if (keyword.equals("BYE")) {
            isRunning = false;
            ui.closeInput();
            storage.saveData(tasks);
            return ui.farewellUser();
        } else if (keyword.equals("LIST") || keyword.equals("L")) {
            return ui.showList(this.tasks);
        } else if (keyword.equals("MARK") || keyword.equals("M")) {
            checkEmptyDescription(segment, "mark");
            return this.markAsDone(Parser.parseMarkUnmarkDelete(segment[markUnmarkDeletePosition]) - 1);
        } else if (keyword.equals("UNMARK") || keyword.equals("UM")) {
            checkEmptyDescription(segment, "unmark");
            return this.markAsNotDone(Parser.parseMarkUnmarkDelete(segment[markUnmarkDeletePosition]) - 1);
        } else if (keyword.equals("TODO") || keyword.equals("T")) {
            checkEmptyDescription(segment, "todo");
            return this.addTodo(Parser.parseTodo(segment[argumentsPart]));
        } else if (keyword.equals("EVENT") || keyword.equals("E")) {
            checkEmptyDescription(segment, "event");
            return this.addEvent(Parser.parseEvent(segment[argumentsPart]));
        } else if (keyword.equals("DEADLINE") || keyword.equals("D")) {
            checkEmptyDescription(segment, "deadline");
            return this.addDeadline(Parser.parseDeadline(segment[argumentsPart]));
        } else if (keyword.equals("DELETE") || keyword.equals("DEL")) {
            checkEmptyDescription(segment, "delete");
            return this.deleteTask(Parser.parseMarkUnmarkDelete(segment[argumentsPart]));
        } else if (keyword.equals("FIND")) {
            checkEmptyDescription(segment, "find");
            return this.findTask(Parser.parseFind(segment[argumentsPart]));
        } else if (keyword.isEmpty()) {
            return "";
        } else {
            throw new RandomInputException("何のことを言っているのか分かりません");
        }
    }

    private void checkEmptyDescription(String[] segment, String currentKeyword) throws EmptyDescriptionException {
        final int onlyHaveKeyword = 1;
        if (segment.length == onlyHaveKeyword) {
            throw new EmptyDescriptionException(
                    String.format("OOPS!!! The description of %s cannot be empty.", currentKeyword));
        }
    }

    /**
     * Return response of ui for marking a task.
     * @param position The position in task list which needs to be marked.
     * @return A response from ui.
     */
    private String markAsDone(int position) throws IndexOutOfBoundsException {
        try {
            this.tasks.get(position).mark();
            return ui.showMarked(tasks.get(position), tasks.getLength());
        } catch (IndexOutOfBoundsException e) {
            return String.format("You are manipulating position out of bound!");
        }
    }

    private String markAsNotDone(int position) throws IndexOutOfBoundsException {
        try {
            this.tasks.get(position).unmark();
            return ui.showUnmarked(tasks.get(position), tasks.getLength());
        } catch (IndexOutOfBoundsException e) {
            return String.format("You are manipulating position out of bound!");
        }
    }

    /**
     * Return response of ui for deleting a task.
     * @param position The position in task list which needs to be deleted.
     * @return A response from ui.
     */
    private String deleteTask(int position) throws IndexOutOfBoundsException {
        try {
            Task deletedTask = tasks.get(position - 1);
            this.tasks.delete(position);
            return ui.showDelete(deletedTask, tasks.getLength());
        } catch (IndexOutOfBoundsException e) {
            return String.format("You are manipulating position out of bound!");
        }
    }

    private String addTodo(String description) {
        Task todo = new Task.Todo(description);
        this.tasks.add(todo);
        return this.ui.showAdd(todo, this.tasks.getLength());
    }

    private String addEvent(String[] args) {
        final int descriptionPart = 0;
        final int fromPart = 1;
        final int toPart = 2;
        Task event = new Task.Event(args[descriptionPart], args[fromPart], args[toPart]);
        this.tasks.add(event);
        return this.ui.showAdd(event, this.tasks.getLength());
    }

    private String addDeadline(String[] args) {
        try {
            final int descriptionPart = 0;
            final int timePart = 1;
            Task deadline = new Task.Deadline(args[descriptionPart], args[timePart]);
            this.tasks.add(deadline);
            return this.ui.showAdd(deadline, this.tasks.getLength());
        } catch (DateTimeParseException e) {
            return "wrong time format for deadline task!";
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getBotResponse(String input) {
        try {
            return this.getResponse(input.trim());
        } catch (RandomInputException e) {
            return (e.getMessage());
        } catch (EmptyDescriptionException e) {
            return (e.getMessage());
        } catch (NumberFormatException e) {
            return String.format("Oops! There is something wrong with your argument! "
                    + "Please make sure it is a number and the format is correct");
        } catch (ArrayIndexOutOfBoundsException e) {
            return "The number of arguments for create a task is not enough!!!";
        } catch (StringIndexOutOfBoundsException e) {
            return "Please check if your /from or /to or /by argument head is written correctly!!!";
        }
    }

    private String findTask(String description) {
        return this.ui.showFind(description, tasks);
    }
    public static void main(String[] args) {
        new GreetBot("data/greetbot.txt").run();
    }
}
