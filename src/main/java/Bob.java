import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class Bob {
    private static final List<Task> taskList = new ArrayList<>();
    private static int numTasks = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter responses = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)));

       responses.println("Hello! I'm Bob the bot!\nHow can I help you?");

        while (true) {
            String userInput = br.readLine();

            // Exit program
            if (userInput.equalsIgnoreCase("bye")) {
                responses.println("Bye! Hope to see you again :)");
                break;
            }

            // Get input history
            if (userInput.equalsIgnoreCase("list")) {
                responses.println(actionResponse(userInput) + getTaskList());

            // Mark task as done
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]);
                Task currTask = taskList.get(taskNum - 1);
                currTask.markAsDone();
                responses.println(actionResponse(parts[0]) + currTask);

            // Mark task as undone
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]);
                Task currTask = taskList.get(taskNum - 1);
                currTask.markAsUndone();
                responses.println(actionResponse(parts[0]) + currTask);

            // Create Task
            } else {
                Task task = createTask(userInput);
                addTask(task);
                String word = "tasks";
                if (numTasks == 1) {
                    word = "task";
                }
                responses.println(actionResponse(userInput) + task
                        + "\nNow you have " + numTasks + " " + word + " in your list.");
            }
        }
        responses.flush();
    }

    static void addTask(Task task) {
        taskList.add(task);
        numTasks++;
    }

    static String actionResponse(String userInput) {
        if (userInput.startsWith("list")) {
            return "Your list of tasks:\n";
        } else if (userInput.startsWith("mark")) {
            return "Good Job! Marking this task as done:\n ";
        } else if (userInput.startsWith("unmark")) {
            return "Okay, marking this task as not done yet:\n ";
        } else {
            return "Adding this task:\n ";
        }
    }

    static String getTaskList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = taskList.get(i - 1);
            if (i == numTasks) {
                tasks.append(i).append(". ").append(currTask); // currTask.toString() ?
                continue;
            }
            tasks.append(i).append(". ").append(currTask).append("\n");
        }
        return tasks.toString();
    }

    static Task createTask(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String taskType = parts[0];
        String remaining = parts[1];

        String[] params = remaining.split(" /");
        String description = params[0];
        if (taskType.equalsIgnoreCase("todo")) {
            return new ToDo(description);
        }
        if (taskType.equalsIgnoreCase("deadline")) {
            String by = params[1].split(" ", 2)[1];
            return new Deadline(description, by);
        }
        if (taskType.equalsIgnoreCase("event")) {
            String from = params[1].split(" ", 2)[1];
            String to = params[2].split(" ", 2)[1];
            return new Event(description, from, to);
        }
        return new Task(userInput);
    }
}
