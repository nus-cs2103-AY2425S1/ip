package seedu.maxine.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.maxine.*;
import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

public class Command {
    private MaxineStorage storage;
    private MaxineUi ui;
    private MaxineList list;
    private static boolean isRunning;
    public Command(MaxineStorage storage, MaxineUi ui, MaxineList list) {
        isRunning = true;
        this.storage = storage;
        this.ui = ui;
        this.list = list;
    }
    /**
     *
     * @return
     */
    public String handleBye() {
        isRunning = false;
        return ui.goodbye();
    }
    public String handleList() {
        return ui.showList(list);
    }
    public String handleMark(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: mark [task no.]");
            }
            String answer = inputArray[1];
            int mark = Integer.parseInt(answer) - 1;
            Task task = list.get(mark);
            task.markDone();
            storage.refreshStorage(list);
            return ui.mark(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleUnmark(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: unmark [task no.]");
            }
            String answer = inputArray[1];
            int mark = Integer.parseInt(answer) - 1;
            Task task = list.get(mark);
            task.markUndone();
            storage.refreshStorage(list);
            return ui.unmark(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleTodo(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("todo ");
            String regex = "todo";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 2 || !matcher.find()) {
                throw new MaxineException("Please follow this "
                        + "format: todo [enter maxine.task]");
            }
            String description = answer[1];
            Todo task = new Todo(description);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - todo task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleDeadline(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("deadline | /by ");
            String regex = "deadline.*?/by";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 3 || !matcher.find()) {
                throw new MaxineException("Please follow this format: deadline "
                        + "[enter maxine.task] /by [enter deadline]");
            }
            String description = answer[1];
            String deadline = answer[2];
            Deadline task = new Deadline(description, deadline);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - deadline task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleEvent(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("event | /from | /to ");
            String regex = "event.*?/from.*?/to";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 4 || !matcher.find()) {
                throw new MaxineException("Please follow this format: event [enter event] "
                        + "/from [start date] /to [end date]");
            }
            String description = answer[1];
            String startTime = answer[2];
            String endTime = answer[3];
            Event task = new Event(description, startTime, endTime);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - event added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleDelete(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: delete [task no.] "
                        + "or delete all");
            }
            String answer = inputArray[1];
            if (answer.equals("all")) {
                return handleDeleteAll();
            }
            int key = Integer.parseInt(answer) - 1;
            Task task = list.get(key);
            list.delete(key);
            storage.refreshStorage(list);
            return ui.delete(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    public String handleFind(String input) {
        ArrayList<Task> currList = storage.load();
        if (currList == null) {
            // currList should not be null
            return "Oops, current list is empty!";
        }
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : currList) {
            String keywords = input.substring(5);
            if (task.toString().contains(keywords)) {
                tasks.add(task);
            }
        }
        if (tasks.isEmpty()) {
            return "Oh no! I can't find anything on this...";
        }
        return ui.search(tasks);
    }
    public String handleDeleteAll() {
        list.deleteAll();
        storage.refreshStorage(list);
        return ui.deleteAll();
    }
    public boolean getStatus() {
        return isRunning;
    }
}
