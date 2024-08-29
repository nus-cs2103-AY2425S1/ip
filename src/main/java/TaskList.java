import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    private void printErrorMessage(Exception e) {
        printLineSeparator();
        System.out.println(e);
        printLineSeparator();
    }

    public int getTaskCount() {
        return taskList.size();
    }

    private String formatDateTime(String dateTime) throws DateTimeParseException {
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        DateTimeFormatter formatter 
            = DateTimeFormatter.ofPattern(
                    "d MMM yyyy HH:mm:ss a");
    
        return ldt.format(formatter);
    }

    private Task createToDo(String[] taskInfo) throws EmptyToDoException {
        if (taskInfo.length == 1) {
            throw new EmptyToDoException();
        }
        taskInfo[0] = "";
        String taskDescription = String.join(" ", taskInfo);
        taskDescription = taskDescription.substring(1);
        return new ToDoTask(taskDescription);
    }

    private Task createDeadline(String task) {
        String[] taskInfo = task.split(" /by ");
        //Prefix of taskInfo[0] is "deadline "
        String taskDescription = taskInfo[0].substring(9);
        String taskDeadline = formatDateTime(taskInfo[1]);
        return new DeadlineTask(taskDescription, taskDeadline);
    }

    private Task createEvent(String task) {
        String[] taskInfo = task.split(" /", 3);
        //Prefix of taskInfo[0] is "event "
        String taskDescription = taskInfo[0].substring(6);
        //Prefix of taskInfo[1] is "from "
        String taskStart = formatDateTime(taskInfo[1].substring(5));
        //Prefix of taskInfo[2] is "to "
        String taskEnd = formatDateTime(taskInfo[2].substring(3));
        return new EventTask(taskDescription, taskStart, taskEnd);
    }

    private Task createTask(String task) throws EmptyCommandException, EmptyToDoException, UnknownCommandException {
        String[] taskInfo = task.split(" ");
        String taskType = taskInfo[0];
        if (taskInfo[0].equals("")) {
            throw new EmptyCommandException();
        } else if (taskType.equals("todo")) {
            try {
                return createToDo(taskInfo);
            } catch (EmptyToDoException e) {
                throw e;
            }
        } else if (taskType.equals("deadline")) {
            return createDeadline(task);
        } else if (taskType.equals("event")) {
            return createEvent(task);
        } else {
            throw new UnknownCommandException();
        }
    }

    public Task addToTaskList(String task) {
        try {
            Task curr = createTask(task);
            taskList.add(curr);
            printLineSeparator();
            System.out.println("Got it. I have added the following task:\n\t" + curr);
            System.out.println("You have a grand total of " + getTaskCount() + " task(s)");
            printLineSeparator();
            return curr;
        } catch (EmptyCommandException e) {
            printErrorMessage(e);
        } catch (EmptyToDoException e) {
            printErrorMessage(e);
        } catch (UnknownCommandException e) {
            printErrorMessage(e);
        } catch (DateTimeParseException e) {
            printLineSeparator();
            System.out.println("Warning! Invalid dateTime format detected!");
            System.out.println("Please use the following representation for dateTime strings:");
            System.out.println("\tyyyy-MM-ddTHH:mm:ss");
            printLineSeparator();
        }
        return null;
    }

    public void printTaskList() {
        printLineSeparator();
        for (int i = 0; i < getTaskCount(); i++) {
            System.out.println(String.format("%d. %s", i+1, this.taskList.get(i)));
        }
        printLineSeparator();
    }

    public void markAsDone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0) {
            printLineSeparator();
            System.out.println("Warning! Task numbering starts from 1!");
            printLineSeparator();
            return;
        } else if (taskNumber > getTaskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
        } else {
            Task curr = this.taskList.get(taskNumber-1);
            curr.markAsDone();
        }
    }

    public void markAsUndone(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber <= 0) {
            printLineSeparator();
            System.out.println("Warning! Task numbering starts from 1!");
            printLineSeparator();
            return;
        } else if (taskNumber > getTaskCount()) {
            throw new InvalidTaskNumberException(taskNumber);
        } else {
            Task curr = this.taskList.get(taskNumber-1);
            curr.markAsUndone();
        }
    }

    public void delete(int taskNumber) throws InvalidTaskNumberException {
        try {
            this.taskList.remove(taskNumber-1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException(taskNumber);
        }
    }

    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber-1);
    }
}