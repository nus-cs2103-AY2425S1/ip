package maga.task;

import maga.Command;
import maga.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public String listTasks() {
        StringBuilder message = new StringBuilder("Take a look, all the tasks you have here, so many, yuuuuuuge\n");
        for (int i = 0; i < taskList.size(); i++) {
            int temp = i + 1;
            message.append(temp).append(". ").append(taskList.get(i).printTask());
        }

        return message.toString();
    }

    public Task getTask(int id) {
        return taskList.get(id);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public String deleteTask(int taskNumber) {
        try {
            Task tempTask = taskList.get(taskNumber);
            taskList.remove(taskNumber - 1);
            return "I've deleted this task:\n" + tempTask.getTaskType() + tempTask.getStatusIcon() +
                    tempTask.getDescription() + "\nYou have " + taskList.size() + " task(s) now!\n";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "Invalid task specified!";
        }
    }

    public String markTask(int taskNumber) {
        Task temp = taskList.get(taskNumber);
        if (temp == null) {
            return "You're trying to mark a task that DOESN'T EXIST, like bad people on JAN 6. " +
                    "Some of the kindest and most lovely souls I've met";
        } else {
            temp.markAsDone();
            return temp.getTaskType() + temp.getStatusIcon() + temp.getDescription() +
                    "Ya boi Donald took the LIBERTY to mark this done:\n";
        }
    }

    public String unmarkTask(int taskNumber) {
        Task temp = taskList.get(taskNumber);;
        if (temp == null) {
            return "Stop trying to unmark tasks like ILLEGAL ALIENS after" +
                    " I'm president: NOT HERE!";
        } else {
            temp.markAsUndone();
            return "Here's the task promised but not completed, just like the DEMS\n" +
                    temp.getStatusIcon() + temp.getDescription();
        }
    }

    public String findTask(String content) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list: \n");
        int count = 1;
        for (Task task : taskList) {
            if (task.getDescription().contains(content)) {
                message.append(count + ". ").append(task.toString()).append("\n");
            }
            count++;
        }

        return message.toString();
    }

    public String addTask(Command<LocalDate[]> command) {
        Task task = null;
        if (Objects.equals(command.getCommandType(), "todo")) {
            task = new TodoTask(false, command.getDescription());
        } else if (Objects.equals(command.getCommandType(), "event")) {
            task = new EventTask(false, command.getCommandType(), command.getContent()[0]);
        } else if (Objects.equals(command.getCommandType(), "deadline")) {
            task = new DeadlineTask(false,
                    command.getCommandType(), command.getContent()[0], command.getContent()[1]);
        }

        try {
            taskList.add(task);
            return "Another task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskList.size() + " task(s) now!";
        } catch (IndexOutOfBoundsException e) {
            return "Tasklist is full!";
        }
    }


    public String addTask(Task task) {
        try {
            taskList.add(task);
            return "Another maga.task for the American people added:\n" + task.getTaskType()
                    + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskList.size() + " task(s) now!";
        } catch (IndexOutOfBoundsException e) {
            return "Tasklist is full!";
        }
    }
}
