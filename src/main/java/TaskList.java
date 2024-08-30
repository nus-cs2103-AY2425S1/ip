import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.PrintWriter;

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

    public void markTask(String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum).markStatus();
        printer.print("NYA! Good job on this task:" + "\n" +
                tasks.get(currNum).toString() + "\n");
        printer.flush();
    }

    public void unmarkTask(String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum2).unmarkStatus();
        printer.print("MRAWWW! Don't forget to return to this task:" + "\n" +
                tasks.get(currNum2).toString() + "\n");
        printer.flush();
    }

    public void addTask(String taskType, String command, String[] splitCommand) throws TiraException {
            if (taskType.equals("todo")) {
                this.addToDo(splitCommand);
            } else {
                if (taskType.equals("deadline")) {
                    this.addDeadline(command, splitCommand);
                } else {
                    if (taskType.equals("event")) {
                        this.addEvent(command, splitCommand);
                    } else {
                        throw new TiraException ("No such command exists");
                    }
            }
        }
    }

    public void addToDo(String[] splitCommand) throws TiraException {
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
            LocalDate endDate = LocalDate.parse(dateCommands[1].substring(3).trim(), IN_FORMATTER);
            Task deadlineTask = new Deadline(dateCommands[0], endDate);
            tasks.add(deadlineTask);
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

    }

}
