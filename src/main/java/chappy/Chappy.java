package chappy;

import java.io.File;
import java.io.IOException;

import chappy.util.Parser;
import chappy.util.TaskList;
import chappy.util.Ui;
import chappy.util.Storage;

import chappy.task.Deadline;
import chappy.task.Event;
import chappy.task.Todo;

import chappy.exception.CreateTaskException;

import org.json.simple.parser.ParseException;

public class Chappy {

    private Storage storage;
    private TaskList userTaskList;
    private Ui ui;
    private Parser parser;

    private static final String DATA_FILE =
            "." + File.separator + "data" + File.separator + "task_list.json";

    public Chappy() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE);
        this.parser = new Parser();
        try {
            this.userTaskList = new TaskList(storage.loadFromDisk());
        } catch (IOException | ParseException e) {
            System.out.println("oh SIR! There was an error loading data from the saved file!");
            System.out.println(e.getMessage());
            this.userTaskList = new TaskList();
        }
    }

    public void run() throws CreateTaskException, IOException, ParseException {

        ui.printIntro();
        String userInput;
        while (ui.hasNextUserInput()) {
            userInput = ui.loadNextUserInput();
            if (userInput == "") {
                continue;
            }

            assert userInput != "";

            ui.printLineSpacer();
            Parser.Command userCommand = parser.parseUserInput(userInput);

            assert userCommand != null;

            try {
                switch (userCommand) {
                    case BYE:
                        System.out.println("It's been a pleasure serving you! Farewell sir.");
                        return;

                    case LIST:
                        this.userTaskList.listTasks();
                        break;

                    case UNMARK:
                        String[] unmarkInput =
                                userInput.trim().split("(?i)" + Parser.Command.UNMARK.getKeyword());
                        if (unmarkInput.length < 2) {
                            throw new CreateTaskException(
                                    "Oh SIR! The index input of a Unmark command cannot be empty!");
                        }
                        int unmarkIndex = Integer.parseInt(unmarkInput[1].trim()) - 1;
                        this.userTaskList.markTaskAsNotDone(unmarkIndex, this.storage);
                        break;

                    case MARK:
                        String[] markInput =
                                userInput.trim().split("(?i)" + Parser.Command.MARK.getKeyword());
                        if (markInput.length < 2) {
                            throw new CreateTaskException(
                                    "Oh SIR! The index input of a Mark command cannot be empty!");
                        }
                        int markIndex = Integer.parseInt(markInput[1].trim()) - 1;
                        this.userTaskList.markTaskAsDone(markIndex, this.storage);
                        break;

                    case TODO:
                        String[] todoInput =
                                userInput.trim().split("(?i)" + Parser.Command.TODO.getKeyword());
                        if (todoInput.length < 2) {
                            throw new CreateTaskException(
                                    "Oh SIR! The description of a Todo cannot be empty!");
                        }
                        Todo todo = new Todo(todoInput[1].trim());
                        this.userTaskList.addTask(todo, this.storage);
                        break;

                    case DEADLINE:
                        Deadline deadline = Deadline.of(userInput);
                        if (deadline != null) {
                            this.userTaskList.addTask(deadline, this.storage);
                        }
                        break;

                    case EVENT:
                        Event event = Event.of(userInput);
                        if (event != null) {
                            this.userTaskList.addTask(event, this.storage);
                        }
                        break;

                    case DELETE:
                        String[] deleteInput =
                                userInput.trim().split("(?i)" + Parser.Command.DELETE.getKeyword());
                        if (deleteInput.length < 2) {
                            throw new CreateTaskException(
                                    "Oh SIR! The index input of a Delete command cannot be empty!");
                        }
                        int deleteIndex = Integer.parseInt(deleteInput[1].trim()) - 1;
                        this.userTaskList.removeTask(deleteIndex, this.storage);
                        break;
                    case FIND:
                        String[] findInput =
                                userInput.trim().split("(?i)" + Parser.Command.FIND.getKeyword());
                        if (findInput.length < 2) {
                            throw new CreateTaskException(
                                    "Oh SIR! The keyword input of a Find command cannot be empty!");
                        }
                        this.userTaskList.findTask(findInput[1].trim());
                        break;

                    default:
                        ui.printUnknownCommand();
                }
            } catch (CreateTaskException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) throws CreateTaskException, IOException {
        Parser.Command userCommand = parser.parseUserInput(userInput);

        assert userCommand != null;

        try {
            switch (userCommand) {
                case BYE:
                    return "It's been a pleasure serving you! Farewell sir.";

                case LIST:
                    return this.userTaskList.listTasks();

                case UNMARK:
                    String[] unmarkInput =
                            userInput.trim().split("(?i)" + Parser.Command.UNMARK.getKeyword());
                    if (unmarkInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! The index input of a Unmark command cannot be empty!");
                    }
                    int unmarkIndex = Integer.parseInt(unmarkInput[1].trim()) - 1;
                    return this.userTaskList.markTaskAsNotDone(unmarkIndex, this.storage);

                case MARK:
                    String[] markInput =
                            userInput.trim().split("(?i)" + Parser.Command.MARK.getKeyword());
                    if (markInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! The index input of a Mark command cannot be empty!");
                    }
                    int markIndex = Integer.parseInt(markInput[1].trim()) - 1;
                    return this.userTaskList.markTaskAsDone(markIndex, this.storage);

                case TODO:
                    String[] todoInput =
                            userInput.trim().split("(?i)" + Parser.Command.TODO.getKeyword());
                    if (todoInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! The description of a Todo cannot be empty!");
                    }
                    Todo todo = new Todo(todoInput[1].trim());
                    return this.userTaskList.addTask(todo, this.storage);

                case DEADLINE:
                    Deadline deadline = Deadline.of(userInput);
                    if (deadline != null) {
                        return this.userTaskList.addTask(deadline, this.storage);
                    }
                    return "";

                case EVENT:
                    Event event = Event.of(userInput);
                    if (event != null) {
                        return this.userTaskList.addTask(event, this.storage);
                    }
                    return "";

                case DELETE:
                    String[] deleteInput =
                            userInput.trim().split("(?i)" + Parser.Command.DELETE.getKeyword());
                    if (deleteInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! The index input of a Delete command cannot be empty!");
                    }
                    int deleteIndex = Integer.parseInt(deleteInput[1].trim()) - 1;
                    return this.userTaskList.removeTask(deleteIndex, this.storage);

                case FIND:
                    String[] findInput =
                            userInput.trim().split("(?i)" + Parser.Command.FIND.getKeyword());
                    if (findInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! The keyword input of a Find command cannot be empty!");
                    }
                    return this.userTaskList.findTask(findInput[1].trim());

                case FREE:
                    String[] freeInput =
                            userInput.trim().split("(?i)" + Parser.Command.FREE.getKeyword());
                    if (freeInput.length < 2) {
                        throw new CreateTaskException(
                                "Oh SIR! There input of a Free command cannot be empty! e.g. free 4 will find your nearest 4 free days!");
                    }
                    return this.userTaskList.findFreeTime(freeInput[1].trim());
                default:
                    return "Oh SIR! I can't understand what you are saying!";
            }
        } catch (CreateTaskException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws CreateTaskException, IOException, ParseException {
        new Chappy().run();
    }
}
