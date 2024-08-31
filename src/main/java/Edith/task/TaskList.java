package task;

import Edith.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public void deleteTask(int index) {
        listOfTasks.remove(index);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    public void listTasks(Ui ui) {
        if (listOfTasks.isEmpty()) {
            ui.showIndentedMessage("Great news, you have no outstanding tasks! Have a break!");
        } else {
            ui.showIndentedMessage("Here are the tasks in your list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                ui.showIndentedMessage((i + 1) + ") " + listOfTasks.get(i));
            }
            ui.showLineBreak();
        }
    }

    public void listTasksOnDate(String date, Ui ui) {
        int index = 1;
        boolean isDue = false;
        boolean isStartingOn = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        ui.showIndentedMessage("Here are your tasks due by " + date + ":");
        for (Task task : listOfTasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDueDate().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + deadline);
                    index++;
                    isDue = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getEndTime().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + event);
                    index++;
                    isDue = true;
                }
            }
        }

        if (!isDue) {
            ui.showIndentedMessage("NOTHING\n");
        } else {
            System.out.println("\n");
        }

        ui.showIndentedMessage("Here are your events starting on " + date + ":");
        for (Task task : listOfTasks) {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + event);
                    index++;
                    isStartingOn = true;
                }
            }
        }

        if (!isStartingOn) {
            ui.showIndentedMessage("NOTHING");
            ui.showLineBreak();
        } else {
            ui.showLineBreak();
        }
    }
}
