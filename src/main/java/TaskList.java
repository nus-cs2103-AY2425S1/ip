import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getAllTask() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void deleteTask(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                System.out.println("Noted. I've removed this task:");
                System.out.print(list.get(taskNumber - 1).toString() + "\n");
                list.remove(taskNumber - 1);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public void addTodo(String rest) {
        System.out.println("Got it. I've added this task:");
        Task newTask = new ToDo(rest);
        list.add(newTask);
        System.out.println(newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void addDeadline(String rest) throws XBotException {
        String[] parts = rest.split("/by", 2);
        if (parts.length == 2) {
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            if (!deadline.isEmpty() && isValidDateFormat(deadline)) {
                Task newTask = new Deadline(taskDescription, deadline);
                list.add(newTask);

                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'deadline <task> /by <date>'");
        }
    }

    public void addEvent(String rest) throws XBotException{
        String[] parts = rest.split("/from", 2);
        if (parts.length == 2) {
            String taskDescription = parts[0].trim();
            String time = parts[1].trim();
            String[] timeParts = time.split("/to", 2);
            if (timeParts.length == 2) {
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                if (isValidDateFormat(from) && isValidDateFormat(to)) {

                    Task newTask = new Event(taskDescription, from, to);
                    list.add(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
                }
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }
    }

    public boolean isValidDateFormat(String date) {
        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime.parse(date, formatter);
                } else {
                    LocalDate.parse(date, formatter);
                }
                return true;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return false;
    }


    public void markDone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsDone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public void markUndone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsUndone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }


}
