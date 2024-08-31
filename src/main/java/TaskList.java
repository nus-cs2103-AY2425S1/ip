import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getNoOfTasks() {
        return tasks.size();
    }

    public String addTaskToString() {
        return """
                I have added this task into the list for you and
                that brings your total number of tasks to""" + " " + String.valueOf(Task.taskCount);
    }

    public String addToDo(String description) {
        ToDo newToDo = new ToDo(description);
        tasks.add(newToDo);
        return addTaskToString() + "\n" +
                " ".repeat(5) + "[T] [ ] " + description + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    public String addDeadline(String description, String deadline) throws WinnerException {
        Deadline newDeadline;
        try {
            if (deadline.matches(".*\\bat\\b.*")) { //Parser
                String dateTime = deadline.trim(); //TaskList
                DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HHmm");
                LocalDateTime byDateTime = LocalDateTime.parse(dateTime, formattedDateTime);
                newDeadline = new Deadline(description, byDateTime);
            } else {
                String date = deadline.trim(); //TaskList
                DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate byDate = LocalDate.parse(date, formattedDate);
                newDeadline = new Deadline(description, byDate);
            }
            tasks.add(newDeadline);
            return addTaskToString() + "\n"
                    + " ".repeat(5) + "[D] [ ] " + description + " (by: " + newDeadline.formattedDeadline() + ")"
                    + "\n" + "\n"
                    + "You can use the command \"list\" to view your list of tasks :D";
        } catch (DateTimeParseException e) {
            throw new WinnerException("""
                    Expected format for date and time:
                    date - dd/mm/yyyy
                    time - 24 hour format""");
        }
    }

    public String addEvent(String description, String start, String end) {
        Event newEvent = new Event(description, start, end);
        tasks.add(newEvent);
        return addTaskToString() + "\n"
                + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")" + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    public String listTasks() {
        int counter = 1;
        StringBuilder list = new StringBuilder("Here are the tasks you have in your list:\n");
        for (Task i : tasks) {
            list.append(counter).append(". ").append(i.taskToString()).append("\n");
            counter++;
        }
        return list.toString();
    }

    public String markTaskAsDone(int taskNumber) throws WinnerException {
        Task task = tasks.get(taskNumber - 1);
        if (task.isDone) {
            throw new WinnerException("You have already marked this task as DONE :D");
        }
        return task.markDone();
    }

    public String unmarkDoneTask(int taskNumber) throws WinnerException {
        Task task = tasks.get(taskNumber - 1);
        if (!task.isDone) {
            throw new WinnerException("You cannot unmark this task as it has not been marked as done :(");
        }
        return task.unmarkDone();
    }

    public String deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task.deleteTask();
    }

}
