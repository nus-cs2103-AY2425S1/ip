package elysia.command;

import java.util.ArrayList;

public class TaskList {
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

    public void markAsDone(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
        Task curr = arrayLists.get(index);
        curr.markAsDone();

        Ui ui = new Ui();
        ui.showMarkAsDoneMessage(curr);
    }

    public void unmarkAsDone(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
        Task curr = arrayLists.get(index);
        curr.unmarkAsDone();

        Ui ui = new Ui();
        ui.showUnmarkAsDoneMessage(curr);
    }

    public void deleteTask(ArrayList<Task> arrayLists, String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = arrayLists.get(index);
        arrayLists.remove(index);

        Ui ui = new Ui();
        ui.showDeleteTaskMessage(arrayLists, task);
    }
}
