import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TaskHandler {
    private List<Task> tasks;
    // private List<Boolean> markedDone;
    private static final List<String> VALIDTASKS = List.of("T", "D", "E");

    public TaskHandler() {
        this.tasks = new ArrayList<Task>();
        // this.markedDone = new ArrayList<Boolean>();
    }

    /*
    public void addToDo(String task) {
        this.tasks.add(task);
        // this.markedDone.add(false);
    }
     */

    public void handleCommand(String command, String desc) {
        desc = desc.stripLeading();
        if (command.equals("mark")) {
            Task t = this.tasks.get(Integer.parseInt(desc) - 1).markAsDone();
            System.out.println("I've marked as done:\n" + t);
        } else if (command.equals("unmark")) {
            Task t = this.tasks.get(Integer.parseInt(desc) - 1).markAsNotDone();
            System.out.println("I've marked as not done:\n" + t);
        } else if (command.equals("list")) {
            System.out.println(this.getTasksString());
        } else if (command.equals("todo")) {
            try {
                Task t = new ToDoTask(desc);
                this.tasks.add(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + this.tasks.size() + " tasks in list");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (command.equals("deadline")) {
            try {
                String[] arr = desc.split("/by");
                Task t;
                try {
                    t = new Deadline(arr[0].strip(), arr[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("missing /by");
                    return;
                }
                this.tasks.add(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + this.tasks.size() + " tasks in list");
            } catch (EmptyDescException e) {
                System.out.println(e);
            }
        } else if (command.equals("event")) {
            String[] arr = desc.split("/from");
            String[] arr2;
            try {
                arr2 = arr[1].split("/to");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("missing /from");
                return;
            }
            Task t = null;
            try {
                try {
                    t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("missing /to");
                    return;
                }
                this.tasks.add(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + this.tasks.size() + " tasks in list");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (command.equals("delete")) {
            this.deleteTask(Integer.parseInt(desc));
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                System.out.println(e);
            }
            //System.out.println("Unknown command: " + command);
        }
    }

    private void deleteTask(int i) {
        if (i > this.tasks.size()) {
            System.out.println("index out of bounds");
            return;
        }
        this.tasks.remove(i - 1);
    }

    public String getTasksString() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += String.format("%d.", i + 1) +  this.tasks.get(i) + "\n";
        }
        return s.stripTrailing();
    }

    private void markDone(int i) {
        if (i > this.tasks.size()) {
            System.out.println("Error: Task number out of bounds");
            return;
        }
        // this.markedDone.set(i - 1, true);
        System.out.println("Marked done: " + i + "\n");
    }

    void writeToFile(String fileloc) {
        try {
            FileWriter myWriter = new FileWriter(fileloc);
            for (Task t : this.tasks) {
                System.out.println(t.toString());
                myWriter.write(t.toString());
                myWriter.write("\n");
            }
            myWriter.close();
            // myWriter.write("Files in Java might be tricky, but it is fun enough!");
            // myWriter.close();
            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void addPastTask(String s) {
        if (s.isEmpty()) {
            return;
        }
        String taskChar = s.substring(1, 2);
        if (!VALIDTASKS.contains(taskChar)) {
            System.out.println("unknown task stored: " + s);
            return;
        }
        int i = VALIDTASKS.indexOf(taskChar);
        
        Task t = new ToDoTask(desc);
    }
}
