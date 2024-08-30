import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> tasks;
    private final PrintWriter printer = new PrintWriter(System.out);
    private static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void getList() {
        printer.println("HERE ARE THE CURRENT TASKS:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            printer.print((i + 1) + ". " + currTask + "\n");
        }
        printer.flush();
    }

    public void markTask(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum).markStatus();
        printer.print("NYA! Good job on this task:" + "\n" +
                tasks.get(currNum).toString() + "\n");
        printer.flush();
    }

    public void unmarkTask(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum2).unmarkStatus();
        printer.print("MRAWWW! Don't forget to return to this task:" + "\n" +
                tasks.get(currNum2).toString() + "\n");
        printer.flush();
    }

    public void modifyTask(String taskType, String command, String[] splitCommand) throws TiraException {
        if (taskType.equals("mark")) {
            this.markTask(command, splitCommand);
        } else if (taskType.equals("unmark")) {
            this.unmarkTask(command, splitCommand);
        } else if (taskType.equals("todo")) {
            this.addToDo(command, splitCommand);
        } else if (taskType.equals("deadline")) {
            this.addDeadline(command, splitCommand);
        } else if (taskType.equals("event")) {
            this.addEvent(command, splitCommand);
        } else if (taskType.equals("delete")) {
            this.delete(splitCommand);
        } else
            throw new TiraException("No such command exists");
    }



    public void addToDo(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String description = "";
        for (int i = 1; i < splitCommand.length; i++) {
            description += (splitCommand[i]);
            if (i != splitCommand.length - 1) {
                description += " ";
            }
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                newTask.toString() + "\nNow you have " + tasks.size() + " task(s) in the list!");

    }

    public void addDeadline(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands = command.split("/");
        try {
            LocalDate endDate = LocalDate.parse(dateCommands[1].substring(3).trim(), IN_FORMATTER);
            Task deadlineTask = new Deadline(dateCommands[0], endDate);
            tasks.add(deadlineTask);
            System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                    deadlineTask.toString() + "\nNow you have " + tasks.size() + " task(s) in the list!");
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addEvent(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands = command.split("/");
        try {
            LocalDate startDate = LocalDate.parse(dateCommands[1].substring(5).trim(), IN_FORMATTER);
            LocalDate endDate = LocalDate.parse(dateCommands[2].substring(3).trim(), IN_FORMATTER);
            Task eventTask = new Event(dateCommands[0], startDate, endDate);
            tasks.add(eventTask);
            System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                    eventTask.toString() + "\nNow you have " + tasks.size() + " task(s) in the list!");
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String[] splitCommand) throws TiraException{
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int indexToDelete = Integer.parseInt(splitCommand[1]);
        Task taskToRemove = tasks.get(indexToDelete - 1);
        tasks.remove(indexToDelete - 1);
        printer.println("Noted, Miao! I've removed this task:\n" + taskToRemove +
                "\nNow you have " + tasks.size() + " task(s) in the list!");
        printer.flush();

    }

}
