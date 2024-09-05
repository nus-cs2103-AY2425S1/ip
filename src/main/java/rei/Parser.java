package rei;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static boolean isAllWhitespace(String input) {
        return input.replaceAll("\\s+","").isEmpty();
    }
    public static boolean parse(TaskList tasks, String prompt) {

        List<String> prompts = Arrays.asList(prompt.split(" "));
        int id;
        switch (prompts.get(0)) {
            case "list":
                tasks.printTasks();
                return false;
            case "mark":
                // Read the rest of the line after "mark"
                prompt = prompt.substring(4).trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    Ui.print("State the task number.");
                }

                id = Integer.parseInt(prompt);
                tasks.markTask(id);
                return false;
            case "unmark":
                // Read the rest of the line after "unmark"
                prompt = prompt.substring(6).trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    Ui.print("State the task number.");
                }

                id = Integer.parseInt(prompt);
                tasks.unmarkTask(id);
                return false;
            case "todo":
                if (isAllWhitespace(prompt.substring(4))) {
                    Ui.print("Task is empty. Please state the task name.");
                }
                tasks.addTask(Task.createToDo(prompt.substring(5)));
                return false;
            case "deadline":
                if (isAllWhitespace(prompt.substring(8))) {
                    Ui.print("Task is empty. Please state the task and deadline.");
                }

                if (prompt.indexOf("/by") == -1) {
                    Ui.print("When is the deadline? Please state the task with the deadline.");
                }

                try {
                    tasks.addTask(Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4))));
                } catch (DateTimeParseException e) {
                    Ui.print("Wrong date format");
                }
                return false;
            case "event":
                if (isAllWhitespace(prompt.substring(5))) {
                    Ui.print("Event is empty. Please state the event and time range.");
                }

                if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
                    Ui.print("State the START and FINISH time of the event");
                }

                try {
                    tasks.addTask(Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1)),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4))));
                } catch (DateTimeParseException e) {
                    Ui.print("Wrong date format");
                }
                return false;

            case "delete":
                // Read the rest of the line after "delete"
                prompt = prompt.substring(6).trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    Ui.print("State the task number.");
                }

                id = Integer.parseInt(prompt);
                tasks.deleteTask(id);
                return false;
            case "annyeong":
                Ui.print("Annyeong. Hope to see you soon.");
                return true;
            default:
                Ui.print("I don't understand what you want me to do.");
                return false;
        }
    }

}
