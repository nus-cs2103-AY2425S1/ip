package processes;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }


    public void addTodo (String arg) {
        Ui.printLine();
        try {
            ToDo newToDo = new ToDo(arg);
            taskList.add(newToDo);
            System.out.println("I have added the task " + newToDo.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void addDeadline (String arg) {
        Ui.printLine();
        try {
            Task newDeadline = new DeadLine(arg);
            taskList.add(newDeadline);
            System.out.println("I have added the task " + newDeadline.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException | InvalidDateException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void addEvent (String arg) {
        Ui.printLine();
        try {
            Task newEvent = new Event(arg);
            taskList.add(newEvent);
            System.out.println("I have added the task " + newEvent.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException | InvalidDateException ex) {
            System.out.println(ex.getMessage());
            return;
        }
    }

    public void deleteTask(int index) {
        if (index < 1 || index > taskList.size()) {
            System.out.println("Task " + index + " does not exist!");
            return;
        }
        index--;
        Task currTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("The task\n  " + currTask.toString() + "\nhas been removed! \nYou now have "
                + taskList.size() + " tasks left.");
    }

    public void markAndUnmark(int index, boolean isMark) {
        if (index == Integer.MAX_VALUE) {
            throw new RuntimeException();
        } else if (index < 1 || index > taskList.size()) {
            System.out.println("task " + index + " does not exist");
        } else {
            index--;
            Task curr = taskList.get(index);
            if (isMark) {
                curr.mark();
                System.out.println("task\n  " + curr.toString() + "\nis marked!");
            } else {
                curr.unMark();
                System.out.println("task\n  " + curr.toString() + "\nis unmarked!");
            }
        }
    }

    public void find (String prompt) {
        Ui.printLine();
        ArrayList<Task> output = new ArrayList<>();
        for (Task item: this.taskList) {
            if (item.getName().contains(prompt)) {
                output.add(item);
            }
        }
        if (output.size() == 0) {
            System.out.println("There are no tasks in your list that match " + "'prompt'");
        } else {
            System.out.println("Here are the matching task(s) in your list: ");
            for (int i = 0; i < output.size(); i++) {
                System.out.println((i + 1) + ". " + output.get(i).toString());
            }
        }
    }
}
