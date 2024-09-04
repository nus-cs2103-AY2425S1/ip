package easton;

import java.io.IOException;
import java.util.ArrayList;

import easton.model.Deadline;
import easton.model.Event;
import easton.model.Task;
import easton.model.ToDo;
import easton.view.Ui;

/**
 * Represents the chatbot, easton.
 */
public class Easton {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;
    private Ui<Task> ui;

    /**
     * Constructs an instance of the chatbot.
     *
     * @param fileName Name of the file storing the records.
     */
    public Easton(String fileName) {
        try {

            storage = new Storage(fileName);
        } catch (IOException e) {
            Ui.displayText("Cannot connect to the storage.");
        }

        tasks = retrieveTasks();
        ui = new Ui<>();
    }

    /**
     * Executes the chatbot to run.
     */
    public void run() {
        Ui.welcome();
        boolean isFinished = false;
        Action action;
        String userInput;

        while (!isFinished) {
            userInput = ui.input();
            Ui.divider();

            try {
                action = getActionFromInput(userInput);
            } catch (IllegalActionException e) {
                Ui.displayText(e.getMessage());
                action = Action.INVALID;
            }

            switch (action) {
            case BYE:
                Ui.goodbye();
                isFinished = true;
                break;
            case LIST:
                Ui.displayText("Here are the tasks in your list:");
                ui.list(tasks, (x) -> true);
                break;
            case MARK:
                changeTaskStatus(userInput, true, "Nice! I've marked this task as done:");
                saveTasks();
                break;
            case UNMARK:
                changeTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
                saveTasks();
                break;
            case TODO:
                try {
                    addTask(createToDo(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException e) {
                    Ui.displayText(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    addTask(createDeadline(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException | InvalidFormatException | DateTimeFormatException e) {
                    Ui.displayText(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    addTask(createEvent(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException | InvalidFormatException | DateTimeFormatException e) {
                    Ui.displayText(e.getMessage());
                }
                break;
            case DELETE:
                deleteTask(userInput);
                saveTasks();
                break;
            case FIND:
                try {
                    findTasks(userInput);
                } catch (EmptyDescriptionException e) {
                    Ui.displayText(e.getMessage());
                }
                break;
            default:
                break;
            }

            Ui.divider();
        }

    }


    /**
     * Executes the program to start.
     * @param args Environment arguments.
     */
    public static void main(String[] args) {
        new Easton("task.csv").run();
    }

    /**
     * Changes the status of a given task.
     *
     * @param input Input from the prompt.
     * @param isDone Is the task done.
     * @param message Message displayed to the user interface.
     */
    public void changeTaskStatus(String input, boolean isDone, String message) {
        try {
            int index = getIndexFromInput(input);
            Task task = tasks.get(index - 1);
            task.setDone(isDone);
            Ui.displayText(message);
            ui.show(task);

        } catch (InvalidIndexException | EmptyDescriptionException e) {
            Ui.displayText(e.getMessage());
        }
    }

    /**
     * Deletes a task.
     *
     * @param input Input from the prompt.
     */
    public void deleteTask(String input) {
        try {
            int index = getIndexFromInput(input);
            Task task = tasks.remove(index - 1);
            Ui.displayText("Noted. I've removed this task:");
            ui.show(task);
            Ui.displayText("Now you have " + tasks.size() + " tasks in the list.");

        } catch (InvalidIndexException | EmptyDescriptionException e) {
            Ui.displayText(e.getMessage());
        }
    }

    /**
     * Returns the index from the input by the user.
     * If the index does not exist, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return Index that exist in the task list.
     * @throws InvalidIndexException If the index does not exist in the task list.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     */
    public int getIndexFromInput(String input) throws InvalidIndexException, EmptyDescriptionException {
        int index;
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        try {
            index = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(splitInput[1]);
        }

        if (0 < index && index <= tasks.size()) {
            return index;
        } else {
            throw new InvalidIndexException(splitInput[1]);
        }
    }

    /**
     * Creates a todo task from the input.
     * If the body of the input is empty, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return A new todo task.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     */
    public static ToDo createToDo(String input) throws EmptyDescriptionException {
        String[] splitInput = input.stripLeading()
                .stripTrailing()
                .split(" ", 2);
        if (splitInput.length == 2) {
            return new ToDo(splitInput[1]);
        } else {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Creates a deadline task from the input.
     * If the body of the input is empty or the format is invalid, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return A new deadline task.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     * @throws InvalidFormatException If the body is in the incorrect format.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public static Deadline createDeadline(String input) throws EmptyDescriptionException,
            InvalidFormatException,
            DateTimeFormatException {
        String[] splitInput = input.stripLeading()
                .stripTrailing()
                .split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        if (!splitInput[1].contains(" /by ")) {
            throw new InvalidFormatException();
        }

        String[] content = splitInput[1].split(" /by ", 2);
        return new Deadline(content[0], content[1]);
    }

    /**
     * Creates an event task from the input.
     * If the body of the input is empty or the format is invalid, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return A new event task.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     * @throws InvalidFormatException If the body is in the incorrect format.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public static Event createEvent(String input) throws EmptyDescriptionException,
            InvalidFormatException,
            DateTimeFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        if (!(splitInput[1].contains(" /from ") && splitInput[1].contains(" /to "))) {
            throw new InvalidFormatException();
        }

        String[] content = splitInput[1].split(" /from | /to ", 3);
        return new Event(content[0], content[1], content[2]);
    }

    /**
     * Returns an action that can be done/exist.
     * If the action cannot be handled, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return A valid action.
     * @throws IllegalActionException If the action given cannot be done.
     */
    public static Action getActionFromInput(String input) throws IllegalActionException {
        String action = input.split(" ", 2)[0];
        try {
            return Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalActionException();
        }
    }

    /**
     * Adds a given task to the list.
     *
     * @param task Task
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.displayText("Got it. I've added this task:");
        ui.show(task);
        Ui.displayText("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Retrieves the tasks from the given file/storage.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> retrieveTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        ArrayList<String> records = new ArrayList<>();
        Task task;
        try {
            records = storage.retrieve();
        } catch (IOException e) {
            Ui.displayText("Could not retrieve tasks from storage.");
        }

        for (String record : records) {
            String[] data = record.split(",");
            switch (data[0]) {
            case "T":
                task = new ToDo(data[2]);
                break;
            case "D":
                try {
                    task = new Deadline(data[2], data[3]);
                } catch (DateTimeFormatException e) {
                    Ui.displayText("easton.model.Deadline ("
                            + data[2]
                            + ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            case "E":
                try {
                    task = new Event(data[2], data[3], data[4]);
                } catch (DateTimeFormatException e) {
                    Ui.displayText("easton.model.Event ("
                            + data[2]
                            + ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            default:
                continue;
            }
            task.setDone(data[1].equals("1"));
            taskArrayList.add(task);
        }

        return taskArrayList;
    }

    /**
     * Saves the tasks to the file/storage.
     */
    public void saveTasks() {
        ArrayList<String> records = new ArrayList<>();

        for (Task task : tasks) {
            records.add(task.getCsvFormat() + "\n");
        }

        storage.save(records);
    }

    /**
     * Finds tasks with a matching keyword in their description.
     *
     * @param input Input from the prompt.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     */
    public void findTasks(String input) throws EmptyDescriptionException {
        String[] splitInput = input.stripLeading()
                .stripTrailing()
                .split(" ", 2);

        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        String keyword = splitInput[1].trim();
        if (keyword.contains(" ")) {
            Ui.displayText("Only one keyword please!");
        } else {
            boolean hasNoMatch = true;

            for (Task task : tasks) {
                if (task.hasKeyword(keyword)) {
                    hasNoMatch = false;
                    break;
                }
            }

            if (hasNoMatch) {
                Ui.displayText("No match was found.");
            } else {
                Ui.displayText("Here are the matching tasks in your list:");
                ui.list(tasks, (x) -> x.hasKeyword(keyword));
            }

        }

    }
}
