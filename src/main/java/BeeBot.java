import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class MissingDeadlineException extends Exception {
    public MissingDeadlineException(String message) {
        super(message);
    }
}

class MissingEventTimeException extends Exception {
    public MissingEventTimeException(String message) {
        super(message);
    }
}

class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

public class BeeBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit_status = false;
        String greet = "Hello! I'm BeeBot\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again!\n";
        String filePath = "src/data/BeeBot.txt";
        ArrayList<Task> taskList = loadTaskListFromFile(filePath);

        speak(greet);
        while (!exit_status) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String cmd = parts[0];

            try {
                switch (cmd) {
                    case "bye":
                        speak(exit);
                        exit_status = true;
                        break;
                    case "list":
                        int size = taskList.size();
                        String listStr = "";
                        for (int i = 0; i < size; i++) {
                            int num = i + 1;
                            listStr += (num + "." + taskList.get(i).toString());
                        }
                        speak(listStr);
                        break;
                    case "mark":
                        int taskNum = Integer.parseInt(parts[1]);
                        Task currTask = getTask(taskList, taskNum);
                        currTask.markAsDone();
                        speak("Nice! I've marked this task as done:\n" + currTask.toString());
                        break;
                    case "unmark":
                        int taskNum2 = Integer.parseInt(parts[1]);
                        Task curr = getTask(taskList, taskNum2);
                        curr.markAsUndone();
                        speak("Nice! I've marked this task as not done yet:\n" + curr.toString());
                        break;
                    case "todo":
                        if (parts.length == 1) {
                            throw new EmptyDescriptionException("Enter a description for the Todo Task.\n");
                        }
                        String name = concatenate(parts, 1);
                        ToDo newToDo = new ToDo(name);
                        taskList.add(newToDo);
                        speakTaskAdded(newToDo, taskList.size());
                        break;
                    case "deadline":
                        if (parts.length == 1) {
                            throw new EmptyDescriptionException("Enter a description for the Deadline Task.\n");
                        }
                        String deadlineName = concatenateUntil(parts, "/by");
                        String deadlineDate = dateConverter(getFollowingDate(parts, "/by"));
                        Deadline newDeadline = new Deadline(deadlineName, deadlineDate);
                        taskList.add(newDeadline);
                        speakTaskAdded(newDeadline, taskList.size());
                        break;
                    case "event":
                        if (parts.length == 1) {
                            throw new EmptyDescriptionException("Enter a description for the Event Task.\n");
                        }
                        String eventName = concatenateUntil(parts, "/from");
                        String startTime = dateConverter(getFollowingDate(parts, "/from", "/to"));
                        String endTime = dateConverter(getFollowingDate(parts, "/to", ""));
                        Event newEvent = new Event(eventName, startTime, endTime);
                        taskList.add(newEvent);
                        speakTaskAdded(newEvent, taskList.size());
                        break;

                    case "delete":
                        int deletionNumber = Integer.parseInt(parts[1]) - 1;
                        if (deletionNumber >= taskList.size()) {
                            throw new TaskNotFoundException("Task does not exist.\n");
                        }
                        String deletedDescription = taskList.get(deletionNumber).toString();
                        taskList.remove(deletionNumber);
                        speakTaskRemoved(deletedDescription, taskList.size());
                        break;
                    default:
                        speak("Invalid command.\n");
                }

                saveTaskListToFile(filePath, taskList);
            } catch (EmptyDescriptionException | MissingDeadlineException | MissingEventTimeException | TaskNotFoundException e) {
                speak(e.getMessage());
            } catch (NumberFormatException e) {
                speak("Please enter a valid task number.\n");
            } catch (Exception e) {
                speak("An error occurred: " + e.getMessage() + "\n");
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }


    public static Task getTask(ArrayList<Task> taskList, int taskNum) throws TaskNotFoundException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new TaskNotFoundException("Task not found.\n");
        }
        return taskList.get(taskNum - 1);
    }

    public static String concatenate(String[] parts, int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < parts.length - 1; i++) {
            result.append(parts[i]).append(" ");
        }
        result.append(parts[parts.length - 1]);
        return result.toString();
    }

    public static String concatenateUntil(String[] parts, String delimiter) throws MissingDeadlineException {
        StringBuilder result = new StringBuilder();
        int i = 1;
        while (!parts[i].equals(delimiter)) {
            result.append(parts[i]).append(" ");
            i++;
            if (i == parts.length) {
                throw new MissingDeadlineException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        return result.toString().trim();
    }

    public static String getFollowingDate(String[] parts, String delimiter) throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++;
        if (i == parts.length) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        return concatenate(parts, i);
    }

    public static String getFollowingDate(String[] parts, String delimiter, String stopDelimiter) throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++; // Move to the part after the delimiter
        if (i == parts.length || parts[i].equals(stopDelimiter)) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        StringBuilder date = new StringBuilder();
        while (i < parts.length && !parts[i].equals(stopDelimiter)) {
            date.append(parts[i]).append(" ");
            i++;
        }
        return date.toString().trim();
    }

    public static void speakTaskAdded(Task task, int size) {
        String msg = "Got it. I've added this task:\n" + task.toString();
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        speak(msg);
    }

    public static void speakTaskRemoved(String description, int size) {
        String msg = "Noted. I've removed this task:\n" + description;
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        speak(msg);
    }

    public static ArrayList<Task> loadTaskListFromFile(String filePath) {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "ToDo":
                        ToDo todo = new ToDo(description);
                        if (isDone) todo.markAsDone();
                        taskList.add(todo);
                        break;
                    case "Deadline":
                        String by = parts[3];
                        Deadline deadline = new Deadline(description, by);
                        if (isDone) deadline.markAsDone();
                        taskList.add(deadline);
                        break;
                    case "Event":
                        String from = parts[3];
                        String to = parts[4];
                        Event event = new Event(description, from, to);
                        if (isDone) event.markAsDone();
                        taskList.add(event);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the task list: " + e.getMessage());
        }
        return taskList;
    }

    public static void saveTaskListToFile(String filePath, ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        }
    }

    public static String taskToString(Task task) {
        String type = "";
        String status = task.isDone() ? "1" : "0"; // 1 if the task is done, 0 if not done
        String description = task.getName();

        if (task instanceof ToDo) {
            type = "ToDo";
            return type + " | " + status + " | " + description;
        } else if (task instanceof Deadline) {
            type = "Deadline";
            String by = ((Deadline) task).getBy();
            return type + " | " + status + " | " + description + " | " + by;
        } else if (task instanceof Event) {
            type = "Event";
            String from = ((Event) task).getFrom();
            String to = ((Event) task).getTo();
            return type + " | " + status + " | " + description + " | " + from + " | " + to;
        }
        return "";
    }

    public static String dateConverter(String date) {
        try {
            String[] words = date.split("\\s+");
            LocalDate parsedDate = LocalDate.parse(words[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDate = parsedDate.format(formatter);
            words[0] = formattedDate;
            String finalDate = String.join(" ", words);
            return finalDate;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Enter date in YYYY-MM-DD format");
            return null;
        }
    }
}