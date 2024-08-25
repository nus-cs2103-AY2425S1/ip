import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ProYapper {
    private static final String FILE_PATH = "./data/ProYapper.txt";
    List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        ProYapper proYapper = new ProYapper();
        proYapper.loadTasks();
        proYapper.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String greeting = "Hello! I am Pro Yapper!\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";
        String errorMessage = "Please type in a command!\n" +
                "list: shows a list of your tasks\n" +
                "mark <task number>: mark the task in your list as done\n" +
                "unmark <task number>: mark the task in your list as not done\n" +
                "todo <task>: add task without any date/time attached to it\n" +
                "deadline <task> /by <when>: add task that needs to be done before a specific date/time\n" +
                "event <task> /from <when> /to <when>: add a task that starts at a specific date/time and ends at a specific date/time";

        System.out.println(greeting);

        while (true) {
            String userInput = scanner.nextLine();
            // Bye command
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(goodbye);
                break;

                // List command
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int lstNum = i + 1;
                    Task next = taskList.get(i);
                    System.out.println(lstNum + ". " + next.toString());
                }

                // Mark command
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    System.out.println("mark WHAT???");
                } else {
                    try {
                        int lstNum = Integer.parseInt(parts[1]);
                        if (lstNum < 1 || lstNum > taskList.size()) {
                            System.out.println("OI WRONG NUMBER.");
                        } else {
                            Task marked = taskList.get(lstNum - 1);
                            marked.markAsDone();

                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(marked.toString());
                            saveTasks();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("THIS ONE NOT INTEGER!!!");
                    }
                }

                // Unmark command
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    System.out.println("unmark WHAT???");
                } else {
                    try {
                        int lstNum = Integer.parseInt(parts[1]);
                        if (lstNum < 1 || lstNum > taskList.size()) {
                            System.out.println("OI WRONG NUMBER.");
                        } else {
                            Task unmarked = taskList.get(lstNum - 1);
                            unmarked.markAsUndone();

                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(unmarked.toString());
                            saveTasks();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("THIS ONE NOT INTEGER!!!");
                    }
                }

                // Delete command
            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    System.out.println("delete WHAT???");
                } else {
                    try {
                        int lstNum = Integer.parseInt(parts[1]);
                        if (lstNum < 1 || lstNum > taskList.size()) {
                            System.out.println("OI WRONG NUMBER.");
                        } else {
                            Task deleted = taskList.get(lstNum - 1);
                            taskList.remove(deleted);
                            int numTasks = taskList.size();

                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + deleted.toString());
                            System.out.println("Now you have " + numTasks + " tasks in the list");
                            saveTasks();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("THIS ONE NOT INTEGER!!!");
                    }
                }

                // To Do command
            } else if (userInput.startsWith("todo")) {
                String[] parts = userInput.split(" ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("todo WHAT????");
                } else {
                    String taskName = parts[1].trim();
                    Task newTask = new ToDo(taskName);
                    taskList.add(newTask);
                    int numTasks = taskList.size();

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask.toString());
                    System.out.println("Now you have " + numTasks + " tasks in the list");
                    saveTasks();
                }

                // Deadline
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("WHEN IS THE DEADLINE???");
                } else {
                    String taskName = parts[0].replaceFirst("deadline", "").trim();
                    if (taskName.isEmpty()) {
                        System.out.println("deadline WHAT???");
                    } else {
                        String dueWhen = parts[1].trim();
                        Task newTask = new Deadline(taskName, dueWhen);
                        taskList.add(newTask);
                        int numTasks = taskList.size();

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask.toString());
                        System.out.println("Now you have " + numTasks + " tasks in the list");
                        saveTasks();
                    }
                }

                // Event
            } else if (userInput.startsWith("event")) {
                String[] partsFrom = userInput.split("/from", 2);
                if (partsFrom.length < 2 || partsFrom[1].trim().isEmpty()) {
                    System.out.println("WHEN DOES IT START???");
                } else {
                    String[] partsTo = partsFrom[1].split("/to", 2);
                    if (partsTo.length < 2 || partsTo[1].trim().isEmpty()) {
                        System.out.println("WHEN DOES IT END???");
                    } else {
                        String taskName = partsFrom[0].replaceFirst("event", "").trim();
                        if (taskName.isEmpty()) {
                            System.out.println("event WHAT???");
                        } else {
                            String startWhen = partsTo[0].trim();
                            String endWhen = partsTo[1].trim();
                            Task newTask = new Event(taskName, startWhen, endWhen);
                            taskList.add(newTask);
                            int numTasks = taskList.size();

                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask.toString());
                            System.out.println("Now you have " + numTasks + " tasks in the list");
                            saveTasks();
                        }
                    }
                }

            } else {
                System.out.println(errorMessage);
            }
        }
        scanner.close();
    }
    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*\\|\\s*");
                if (parts.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        ToDo newToDo = new ToDo(description);
                        if (isDone) {
                            newToDo.markAsDone();
                        }
                        taskList.add(newToDo);
                        break;
                    case "D":
                        if (parts.length < 4) {
                            System.out.println("Skipping invalid Deadline line: " + line);
                            continue;
                        }
                        Deadline newDeadline = new Deadline(description, parts[3]);
                        if (isDone) {
                            newDeadline.markAsDone();
                        }
                        taskList.add(newDeadline);
                        break;
                    case "E":
                        if (parts.length < 4) {
                            System.out.println("Skipping invalid Event line: " + line);
                            continue;
                        }

                        String[] timeParts = parts[3].split(" to ");
                        if (timeParts.length != 2) {
                            System.out.println("Skipping invalid Event time format: " + line);
                            continue;
                        }
                        String from = timeParts[0].trim();
                        String to = timeParts[1].trim();
                        Event newEvent = new Event(description, from, to);
                        if (isDone) {
                            newEvent.markAsDone();
                        }
                        taskList.add(newEvent);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }




    private void saveTasks() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure the directory exists

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}


