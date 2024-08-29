package talker.task;

import talker.TalkerException;
import talker.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }


    public void setTasks(ArrayList<Task> list) {
        this.list = list;
    }

    public void writeToFile(FileWriter fileWriter) throws TalkerException {
        try {
            for (Task task: list) {
                fileWriter.write(task.getSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to write to file. Error occurred: " + e.getMessage());
        }
    }

    public void printTasksOn(String date, Ui ui) throws TalkerException {
        LocalDate targetDate;
        try {
            String[] parsed = date.split(" ");
            targetDate = LocalDate.parse(parsed[1], INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new TalkerException("Incorrect date format. Try again with: yyyy/MM/dd");
        }
        ui.printTasksOn(targetDate.format(OUTPUT_FORMAT));
        for (Task task: list) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline().toLocalDate();
                if (targetDate.isBefore(deadline) || targetDate.isEqual(deadline)) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                LocalDate start = eventTask.getFrom().toLocalDate();
                LocalDate end = eventTask.getTo().toLocalDate();
                if ((targetDate.isAfter(start) || targetDate.isEqual(start))
                        && (targetDate.isBefore(end) || targetDate.isEqual(end))) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            }
        }
    }

    public void listTasks(Ui ui) throws TalkerException {
        if (list.isEmpty()) {
            throw new TalkerException("List is empty!");
        }
        ui.printTaskList(list);
    }

    public void markTaskComplete(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            ui.printTaskMarked(list.get(index).mark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Mark format wrong. Try again with: mark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    public void unmarkTaskComplete(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            ui.printTaskUnmarked(list.get(index).unmark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Unmark format wrong. Try again with: unmark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    public void deleteTask(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            Task removed = list.remove(index);
            ui.printTaskDelete(removed, list.size());
        } catch (NumberFormatException e) {
            throw new TalkerException("Delete format wrong. Try again with: delete <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    public void createToDo(String input, Ui ui) throws TalkerException {
        try {
            String desc = input.substring(5);
            Task newTask = new ToDo(desc);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("ToDo format wrong. Try again with: todo <description>");
        }
    }

    public void createDeadline(String input, Ui ui) throws TalkerException {
        try {
            String contents = input.substring(9);

            String[] parsed = contents.split(" /by ", 2);
            String desc = parsed[0];
            String by = parsed[1];

            Task newTask = new Deadline(desc, by);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Deadline format wrong. Try again with: deadline <description> /by <dd-MM-yyyy HH:mm>");
        }
    }

    public void createEvent(String input, Ui ui) throws TalkerException {
        try {
            String contents = input.substring(6);

            String[] parsed1 = contents.split(" /from ", 2);
            String[] parsed2 = parsed1[1].split(" /to ", 2);
            String desc = parsed1[0];
            String from = parsed2[0];
            String to = parsed2[1];

            Task newTask = new Event(desc, from, to);

            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Event format wrong. Try again with: event <description> " +
                            "/from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
        }
    }

    /**
     * Finds certain keyword amongst descriptions of tasks
     *
     * @param keyword keyword to be searched through descriptions
     * @param ui ui object to print output
     */
    public void findTask(String keyword, Ui ui) throws TalkerException {
        ArrayList<Task> outputList = new ArrayList<>();
        for (Task task: list) {
            if (task.getDescription().contains(keyword)) {
                outputList.add(task);
            }
        }
        ui.printMatchingTasks(outputList);
    }
}
