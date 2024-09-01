package elysia.command;

import elysia.dateparser.DateParser;
import elysia.ui.Ui;

import java.util.ArrayList;

/**
 * Provides command to user.
 **/
public class TaskList {

    /**
     * Adds ToDo task to the list.
     **/
    public void addToDos(ArrayList<Task> arrayLists, String s) {
        Ui ui = new Ui();

        if (s.isEmpty()) {
            ui.showEmptyDescriptionMessage("todo");
        } else {
            Task task = new ToDos(s);
            arrayLists.add(task);
            ui.handleAddedMessage(arrayLists, task);
        }
    }

    /**
     * Adds Deadline task to the list.
     **/
    public void addDeadline(ArrayList<Task> arrayLists, String s) {
        Ui ui = new Ui();
        if (s.isEmpty()) {
            ui.showEmptyDescriptionMessage("deadline");
        } else {
            String[] str = s.split("/by ");
            Task task = new Deadline(str[0].trim(), DateParser.parseDate(str[1]));
            arrayLists.add(task);
            ui.handleAddedMessage(arrayLists, task);
        }
    }

    /**
     * Adds Event task to the list.
     **/
    public void addEvent(ArrayList<Task> arrayLists, String s) {
        Ui ui = new Ui();

        if (s.isEmpty()) {
            ui.showEmptyDescriptionMessage("event");
        } else {
            String[] str = s.split("/from | /to ");
            Task task = new Event(str[0].trim(), str[1], str[2]);
            arrayLists.add(task);
            ui.handleAddedMessage(arrayLists, task);
        }
    }

    /**
     * Marks the task as done.
     **/
    public void markAsDone(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
        Task curr = arrayLists.get(index);
        curr.markAsDone();

        Ui ui = new Ui();
        ui.showMarkAsDoneMessage(curr);
    }

    /**
     * Unmarks the completed task.
     **/
    public void unmarkAsDone(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        Task curr = arrayLists.get(index);
        curr.unmarkAsDone();

        Ui ui = new Ui();
        ui.showUnmarkAsDoneMessage(curr);
    }

    /**
     * Deletes the task from list.
     **/
    public void deleteTask(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = arrayLists.get(index);
        arrayLists.remove(index);

        Ui ui = new Ui();
        ui.showDeleteTaskMessage(arrayLists, task);
    }

    public void findTask(ArrayList<Task> arrayLists, String searchContent) {
        ArrayList<Task> result = new ArrayList<>();

        for (int i = 0; i < arrayLists.size(); i++) {
            Task task = arrayLists.get(i);
            String taskDescription = task.getDescription().toLowerCase();

            if (taskDescription.contains(searchContent)) {
                result.add(task);
            }
        }

        Ui ui = new Ui();
        ui.printList(result);
    }
}
