import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;

public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
        String fileName = "savedTasks.txt";
        File file = new File(fileName); // delete the existing file
        if (file.exists()) {
            file.delete();
        }
    }

    public void delete(String indexString) throws SocchatException {
        try {
//            String taskIndexString = scanner.nextLine().trim();
            int taskIndex = Integer.parseInt(indexString);
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            System.out.println("Deleted "  + "\"" +  task.toString() + "\"");
            System.out.println("Now you have " + tasks.size() + " task(s).");
            updateFile(false);
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
            updateFile(true);
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
            LocalDate formattedFrom = LocalDate.parse(from);
            LocalDate formattedTo = LocalDate.parse(to);

            Task t = new Event(des, formattedFrom, formattedTo);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");
            updateFile(true);
        } catch (IndexOutOfBoundsException e) {

            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        }
    }
    public void addDeadline(String[] strToken) throws SocchatException {
        try {
            String des = strToken[0].substring("deadline ".length());
            String by = strToken[1].substring("by ".length());
            LocalDate formattedBy = LocalDate.parse(by);

            Task t = new Deadline(des, formattedBy);
            tasks.add(t);
            System.out.print("added: ");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " task(s).");
            updateFile(true);
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
            updateFile(false);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
        }
    }

    private void updateFile(Boolean needAppend) {
        String file = "savedTasks.txt";
        ArrayList<Task> content = new ArrayList<>();
        if (needAppend) {
            content.add(tasks.get(tasks.size() - 1)); // Append only the last added task
        } else {
            content = tasks; // Rewrite the tasks
        }
        try(FileWriter writer = new FileWriter(file, needAppend)) {
            for (Task t : content) {
                writer.write(t.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
