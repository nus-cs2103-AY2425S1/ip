import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Echo {
    public static final String DOCS_TASKS_TXT = "docs/tasks.txt";

    public static void main(String[] args) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        //load saved
        Scanner saveScanner = new Scanner(DOCS_TASKS_TXT);
        loadTasks(tasks);

        Scanner myObj = new Scanner(System.in);
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
        String input = null;

        while (true) {
            input = myObj.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            try {
                switch (command) {
                    case "bye":
                        exitProgram(tasks);
                        return;
                    case "list":
                        listTasks(tasks);
                        break;
                    case "mark":
                        if (parts.length == 2) {
                            markTask(tasks, parts[1]);
                        } else {
                            throw new EchoException("Please specify the task number to mark.");
                        }
                        break;
                    case "unmark":
                        if (parts.length == 2) {
                            unmarkTask(tasks, parts[1]);
                        } else {
                            throw new EchoException("Please specify the task number to mark.");
                        }
                        break;
                    case "todo":
                        if (parts.length == 2 && !parts[1].isEmpty()) {
                            addTodo(tasks, parts[1]);
                        } else {
                            throw new EchoException(" The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        addDeadline(tasks, input);
                        break;
                    case "event":
                        addEvent(tasks, input);
                        break;
                    case "delete":
                        if (parts.length == 2) {
                            deleteTask(tasks, parts[1]);
                        } else {
                            throw new EchoException("Please specify the task number to delete.");
                        }
                        break;
                    default:
                        throw new EchoException(" I'm sorry, but I don't know what that means :-(");

                }
            } catch (EchoException e) {
                System.out.println("OOPS!!!" + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadTasks(List<Task> tasks) throws FileNotFoundException {
        File file = new File(DOCS_TASKS_TXT);
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
    }

    private static void addDeadline(List<Task> tasks, String input) throws EchoException {
        String[] details = input.split(" /by ", 2);
        if (details.length == 2) {
            String[] des = details[0].split(" ", 2);
            Deadline deadlineTask = new Deadline(des[1], details[1]);
            tasks.add(deadlineTask);
            System.out.println("added: " + deadlineTask);
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    private static void addEvent(List<Task> tasks, String input) throws EchoException {
        String[] details = input.split(" /from ", 2);
        if (details.length == 2) {
            String[] times = details[1].split(" /to ", 2);
            if (times.length == 2) {
                String[] des = details[0].split(" ", 2);
                Events eventTask = new Events(des[1], times[0], times[1]);
                tasks.add(eventTask);
                System.out.println("added: " + eventTask);
            }
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    private static void exitProgram(List<Task> tasks) throws IOException {
        String filePath = "docs/tasks.txt";
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        }
        FileWriter fw = new FileWriter(filePath);

        String listToSave = listToString(tasks);
        fw.write(listToSave);
        fw.close();

        System.out.println("Bye bye...");
    }

    private static void listTasks(List<Task> tasks) {
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

    private static String listToString(List<Task> tasks) {
        StringBuilder listString = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }

    private static void markTask(List<Task> tasks, String input) {
        try {
            int num = Integer.parseInt(input);
            if (num > 0 && num <= tasks.size()) {
                Task doneTask = tasks.get(num - 1);
                doneTask.setDone();
                System.out.println("Nice! I've marked this task as done:\n" + doneTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void unmarkTask(List<Task> tasks, String input) {
        int num = Integer.parseInt(input);
        if (num > 0 && num <= tasks.size()) {
            Task undoneTask = tasks.get(num - 1);
            undoneTask.setUndone();
            System.out.println("OK, I've marked this task as not done yet:\n" + undoneTask);
        }
    }

    /*private static void addTask(List<Task> tasks, String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        System.out.println("added: " + taskDescription);
    }*/

    private static void addTodo(List<Task> tasks, String taskDescription) {
        Todo todo = new Todo(taskDescription);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:\n" + todo.toString() + "Now you have " + tasks.size() + " tasks in the list");
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

}
