package easton;

import easton.model.Deadline;
import easton.model.Event;
import easton.model.Task;
import easton.model.ToDo;
import easton.view.Ui;

import java.io.*;
import java.util.ArrayList;

public class Easton {

    private ArrayList<Task> tasks = new ArrayList<>();;
    private Storage storage;
    private Ui<Task> ui;

    public Easton(String fileName) {
        try {
            storage = new Storage(fileName);
        } catch (IOException e) {
            Ui.displayText("Cannot connect to the storage.");
        }

        tasks = retrieveTasks();
        ui = new Ui<>();
    }

    public void run() {
        boolean isFinished = Ui.welcome();;
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
                isFinished = Ui.goodbye();
                break;
            case LIST:
                ui.list(tasks);
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
            }

            Ui.divider();
        }

    }

    public static void main(String[] args) {
        new Easton("task.csv").run();
    }

    private void changeTaskStatus(String input, boolean isDone, String message) {
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

    private void deleteTask(String input) {
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

    private int getIndexFromInput(String input) throws InvalidIndexException, EmptyDescriptionException {
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

    public static ToDo createToDo(String input) throws EmptyDescriptionException {
        String[] splitInput = input.stripLeading()
                .stripTrailing().
                split(" ", 2);
        if (splitInput.length == 2) {
            return new ToDo(splitInput[1]);
        } else {
            throw new EmptyDescriptionException();
        }
    }

    private static Deadline createDeadline(String input) throws EmptyDescriptionException,
            InvalidFormatException,
            DateTimeFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        if (!splitInput[1].contains(" /by ")) {
            throw new InvalidFormatException();
        }

        String[] content = splitInput[1].split(" /by ", 2);
        return new Deadline(content[0], content[1]);
    }

    private static Event createEvent(String input) throws EmptyDescriptionException,
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

    private static Action getActionFromInput(String input) throws IllegalActionException {
        String action = input.split(" ", 2)[0];
        try {
            return Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalActionException();
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        Ui.displayText("Got it. I've added this task:");
        ui.show(task);
        Ui.displayText("Now you have " + tasks.size() + " tasks in the list.");
    }

    private ArrayList<Task> retrieveTasks() {
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
                    Ui.displayText("easton.model.Deadline (" +
                            data[2] +
                            ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            case "E":
                try {
                    task = new Event(data[2], data[3], data[4]);
                } catch (DateTimeFormatException e) {
                    Ui.displayText("easton.model.Event ("+
                            data[2] +
                            ") is using the wrong DateTime Format, the record is voided.");
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

    private void saveTasks() {
        ArrayList<String> records = new ArrayList<>();

        for (Task task : tasks) {
            records.add(task.getCsvFormat() + "\n");
        }

        storage.save(records);
    }
}
