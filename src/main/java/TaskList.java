import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TaskList {
    List<Task> tasks;
    public TaskList(List<Task> tasks) throws EchoException, FileNotFoundException {
        this.tasks =tasks;

    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static String listToString(List<Task> tasks) {
        StringBuilder listString = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }

    /*private void loadTasks(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        if(file.exists()) {
            Scanner saveScanner = new Scanner(file);
            while (saveScanner.hasNextLine()) {
                String input = saveScanner.nextLine();
                String[] parts = input.split("\\|");

                String type = parts[0];
                String marked = parts[1];
                String des = parts[2];
                String info = parts[3];
                switch (type) {
                    case "T":

                        Todo todo = new Todo(info);
                        if (Objects.equals(marked, "1")) {
                            todo.setDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":

                        String[] details = input.split("/by ", 2);
                        if (details.length == 2) {
                            Deadline deadlineTask = new Deadline(des, details[1]);
                            if (Objects.equals(marked, "1")) {
                                deadlineTask.setDone();
                            }

                            tasks.add(deadlineTask);
                        }
                        break;
                    case "E":
                        String[] eventDetails = input.split(" /from ", 2);
                        if (eventDetails.length == 2) {
                            String[] times = eventDetails[1].split(" /to ", 2);
                            if (times.length == 2) {
                                Events eventTask = new Events(des, times[0], times[1]);
                                if (Objects.equals(marked, "1")) {
                                    eventTask.setDone();
                                }
                                tasks.add(eventTask);

                            }
                        }
                        break;
                    default:
                        System.out.println("Unknown task type: " + type);
                        break;
                }

            }
        }
    }*/

    public void listTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    private static void deleteTask(List<Task> tasks, String input) throws EchoException {
        try {
            int num = Integer.parseInt(input);
            if (num > 0 && num <= tasks.size()) {
                Task removedTask = tasks.remove(num - 1);
                System.out.println(" Noted. I've removed this task:\n   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new EchoException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new EchoException("Please enter a valid number.");
        }
    }

    public void markTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task doneTask = tasks.get(index - 1);
            doneTask.setDone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }

    public void unmarkTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task undoneTask = tasks.get(index - 1);
            undoneTask.setUndone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }


    private void addDeadline( String input) throws EchoException, DateTimeParseException {
        String[] details = input.split(" /by ", 2);
        if (details.length == 2) {
            String[] des = details[0].split(" ", 2);
            Deadline deadlineTask = new Deadline(des[1], details[1]);
            this.tasks.add(deadlineTask);
            System.out.println("added: " + deadlineTask);
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    private void addEvent( String input) throws EchoException, DateTimeParseException {
        String[] details = input.split(" /from ", 2);
        if (details.length == 2) {
            String[] times = details[1].split(" /to ", 2);
            if (times.length == 2) {
                String[] des = details[0].split(" ", 2);
                Events eventTask = new Events(des[1], times[0], times[1]);
                this.tasks.add(eventTask);
                System.out.println("added: " + eventTask);
            }
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }


    private void addTodo(String taskDescription) {
        Todo todo = new Todo(taskDescription);
        this.tasks.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo.toString() + "Now you have " + tasks.size() + " tasks in the list");
    }
}
