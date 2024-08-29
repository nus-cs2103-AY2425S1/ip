import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private Parser parser;
    private Storage storage;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        parser = new Parser();
        String fileName = "savedTasks.txt";
        File file = new File(fileName); // delete the existing file
        if (file.exists()) {
            file.delete();
        }
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void delete(String indexString) throws SocchatException {
        try {
//            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(indexString);
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            System.out.println("Deleted "  + "\"" +  task.toString() + "\"");
            System.out.println("Now you have " + tasks.size() + " task(s).");

            storage.update(tasks, false);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
        }
    }
    public void list() {
        System.out.println("Your task list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.print((i + 1) + ": ");
            System.out.println(curr.toString());
        }
    }

    public void addTodo(String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("todo ".length());
            Task t = new Todo(des);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");

            storage.update(tasks, true);
        } catch (IndexOutOfBoundsException e) {
            // scanner.nextLine();
            throw new SocchatException("Invalid Todo format: Description is empty");

        }
    }
    public void addEvent(String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("event ".length());
            String from = strToken[1].substring("from ".length());
            String to = strToken[2].substring("to ".length());
            LocalDateTime formattedFrom = parser.parseDate(from);
            LocalDateTime formattedTo = parser.parseDate(to);

            Task t = new Event(des, formattedFrom, formattedTo);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");

            storage.update(tasks, true);
        } catch (IndexOutOfBoundsException e) {

            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        }
    }
    public void addDeadline(String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("deadline ".length());
            String by = strToken[1].substring("by ".length());
            LocalDateTime formattedBy = parser.parseDate(by);

            Task t = new Deadline(des, formattedBy);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");

            storage.update(tasks, true);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Deadline format: deadline <description> /by <deadline>");
        }

    }

    public void setMark(String indexString, Boolean mark) throws SocchatException {
        try {
//            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(indexString);
            if (mark) {
                tasks.get(taskIndex - 1).mark();
            } else {
                tasks.get(taskIndex - 1).unmark();
            }
            storage.update(tasks, false);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
        }
    }



}
