import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> tasks;
    private Storage storage;

    public enum TaskType {
        TASK_TODO, TASK_DEADLINE, TASK_EVENT;
    }

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.readFromStorage(storage);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTaskByIdx(int i) {
        return this.tasks.get(i);
    }

    public void markTaskCompleted(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            Task taskMarked = this.tasks.get(idx);
            taskMarked.setCompleted();
            Ui.printTaskMarkedCompleteMessage(taskMarked);
        }
    }

    public void markTaskIncomplete(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            Task taskMarked = this.tasks.get(idx);
            taskMarked.setUncompleted();
            Ui.printTaskMarkedIncompleteMessage(taskMarked);
        }
    }

    public void addTask(Matcher matcher, TaskType tt) throws PhenexException {
        String taskName = matcher.group(1);
        String emptyNameRegex = "\\s*";
        Pattern emptyNamePattern = Pattern.compile(emptyNameRegex);
        Matcher emptyNameMatcher = emptyNamePattern.matcher(taskName);

        Task taskToAdd;

        switch (tt) {
        case TASK_TODO:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid todo name");
            }
            taskToAdd = new ToDo(taskName);
            break;
        case TASK_DEADLINE:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid deadline name");
            }
            String deadlineBy = matcher.group(2);
            try {
                LocalDate localDate = LocalDate.parse(deadlineBy);
                taskToAdd = new Deadline(taskName, localDate);
            } catch (DateTimeParseException e) {
                throw new PhenexException(e.getMessage());
            }
            break;
        case TASK_EVENT:
            if (emptyNameMatcher.matches()) {
                throw new PhenexException("Error, invalid event name");
            }
            try {
                LocalDate fromDate = LocalDate.parse(matcher.group(2));
                LocalDate toDate = LocalDate.parse(matcher.group(3));
                taskToAdd = new Event(taskName, fromDate, toDate);
                break;
            } catch (DateTimeParseException e) {
                throw new PhenexException(e.getMessage());
            }

        default:
            Ui.printInvalidInputMessage();
            return;
        }

        this.tasks.add(taskToAdd);
        Ui.printTaskAddedMessage(taskToAdd, this.tasks.size());
    }

    public void deleteTask(int idx) throws PhenexException {
        if (idx >= this.tasks.size()) {
            throw new PhenexException("Error, no such mission exists");
        }
        Task taskToDelete = this.tasks.get(idx);
        this.tasks.remove(idx);
        Ui.printTaskDeletedMessage(taskToDelete, this.tasks.size());
    }

    private void readFromStorage(Storage storage) {
        try {
            Scanner scanner = new Scanner(storage.file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                this.addTaskFromMemoryLine(line);
            }
        } catch (Exception e) {
            // reset memory if invalid input
            Ui.printExceptionMessage(e);
            this.tasks = new ArrayList<>();
        }
    }

    private void addTaskFromMemoryLine(String data) throws PhenexException {
        String[] taskDetails = data.split(", ");
        if (taskDetails.length <= 1) {
            throw new PhenexException("Error, corrupted memory.");
        }

        String symbol = taskDetails[0];
        String status = taskDetails[1];
        String name;
        Task taskToAdd;

        switch (symbol) {
        case "T":
            if (taskDetails.length != 3) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            taskToAdd = new ToDo(name);
            break;
        case "D":
            if (taskDetails.length != 4) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            String byDate = taskDetails[3];
            LocalDate date = LocalDate.parse(byDate);
            taskToAdd = new Deadline(name, date);
            break;
        case "E":
            if (taskDetails.length != 5) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            try {
                LocalDate fromDate = LocalDate.parse(taskDetails[3]);
                LocalDate toDate = LocalDate.parse(taskDetails[4]);
                taskToAdd = new Event(name, fromDate, toDate);
                break;
            } catch (DateTimeParseException e) {
                throw new PhenexException(e.getMessage());
            }
        default:
            throw new PhenexException("Error, corrupted memory");
        }

        if (status.equals("1")) {
            taskToAdd.setCompleted();
        }
        this.tasks.add(taskToAdd);
    }
}
