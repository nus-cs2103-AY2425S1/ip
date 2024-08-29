import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class streams {
    private static final String FILE_PATH = "data" + File.separator + "streams.txt";
    private static ArrayList<Task> todo = new ArrayList<>();
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        loadTasks();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from Streams!");
        System.out.println("What can I do for you?");
        String cmd = "";
        do {
            cmd = sc.nextLine().trim();
            try {
                if (cmd.equals("list")) {
                    listTasks();
                } else if (cmd.startsWith("mark")) {
                    handleMarkUnmark(cmd, true);
                } else if (cmd.startsWith("unmark")) {
                    handleMarkUnmark(cmd, false);
                } else if (cmd.startsWith("todo")) {
                    handleToDo(cmd);
                } else if (cmd.startsWith("deadline")) {
                    handleDeadline(cmd);
                } else if (cmd.startsWith("event")) {
                    handleEvent(cmd);
                } else if (cmd.startsWith("delete")) {
                    handleDelete(cmd);
                } else if (cmd.startsWith("list-date")) {
                    handleListDate(cmd);
                } else if (cmd.equals("list-week")) {
                    handleListWeek();
                } else if (cmd.equals("sort-deadline")) {
                    handleSortDeadline();
                } else if (cmd.equals("bye")) {
                    System.out.println("bye. hope to see you again soon!");
                } else {
                    System.out.println("incorrect command: " + cmd);
                    System.out.println("take help from the guideline for prompt");
                }
            } catch (Exception e) {
                System.out.println("an unexpected error occurred: " + e.getMessage());
            }
        } while (!cmd.equals("bye"));
        sc.close();
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("created directory: " + directory.getAbsolutePath());
            } else {
                System.out.println("failed to create directory: " + directory.getAbsolutePath());
                return;
            }
        }

        if (!file.exists()) {
            System.out.println("oops!!! no existing task file has been found :) starting with an empty task list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDoTask(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        LocalDateTime by = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        task = new DeadlineTask(description, by);
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        LocalDateTime from = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], INPUT_FORMATTER);
                        task = new EventTask(description, from, to);
                        break;
                    default:
                        continue;
                }

                if (isDone) task.markAsDone();
                todo.add(task);
            }
            System.out.println("tasks have been loaded successfully");
        } catch (IOException | DateTimeParseException e) {
            System.out.println("an error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : todo) {
                String taskType = task instanceof ToDoTask ? "T" :
                        task instanceof DeadlineTask ? "D" :
                                task instanceof EventTask ? "E" : "";
                String isDone = task.isDone ? "1" : "0";
                String taskString = taskType + " | " + isDone + " | " + task.description;

                if (task instanceof DeadlineTask) {
                    taskString += " | " + ((DeadlineTask) task).by.format(INPUT_FORMATTER);
                } else if (task instanceof EventTask) {
                    taskString += " | " + ((EventTask) task).from.format(INPUT_FORMATTER) + " | " + ((EventTask) task).to.format(INPUT_FORMATTER);
                }

                writer.write(taskString);
                writer.newLine();
            }
            System.out.println("tasks saved successfully");
        } catch (IOException e) {
            System.out.println("an error occurred while saving the tasks: " + e.getMessage());
        }
    }

    private static void listTasks() {
        if (todo.isEmpty()) {
            System.out.println("your task list is empty!!");
        } else {
            for (int i = 0; i < todo.size(); i++) {
                System.out.println((i + 1) + ". " + todo.get(i));
            }
        }
    }

    private static void handleMarkUnmark(String cmd, boolean isDone) {
        try {
            int no = Integer.parseInt(cmd.substring(cmd.indexOf(' ') + 1).trim()) - 1;
            if (no < 0 || no >= todo.size()) {
                System.out.println("invalid task number");
            } else {
                if (isDone) {
                    todo.get(no).markAsDone();
                    System.out.println("marked task done: " + todo.get(no));
                } else {
                    todo.get(no).markAsNotDone();
                    System.out.println("marked task not done: " + todo.get(no));
                }
                saveTasks();
            }
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("error parsing task number");
        }
    }

    private static void handleToDo(String cmd) {
        if (cmd.length() <= 5) {
            System.out.println("the description of a todo cannot be empty");
            return;
        }
        String description = cmd.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("the description of a todo cannot be empty");
        } else {
            todo.add(new ToDoTask(description));
            System.out.println("added todo: " + description);
            saveTasks();
        }
    }

    private static void handleDeadline(String cmd) {
        try {
            if (cmd.length() <= 9) {
                System.out.println("the description of a deadline cannot be empty");
                return;
            }
            String[] sub = cmd.substring(9).trim().split(" /by ");
            if (sub.length != 2) {
                System.out.println("the format for deadlines is 'deadline [description] /by yyyy-MM-dd HH:mm'");
                return;
            }
            String description = sub[0].trim();
            String byString = sub[1].trim();
            if (description.isEmpty()) {
                System.out.println("the description of a deadline cannot be empty");
            } else {
                try {
                    LocalDateTime by = LocalDateTime.parse(byString, INPUT_FORMATTER);
                    todo.add(new DeadlineTask(description, by));
                    System.out.println("added deadline: " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")");
                    saveTasks();
                } catch (DateTimeParseException e) {
                    System.out.println("invalid date format. Please use yyyy-MM-dd HH:mm");
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("the format for deadlines is 'deadline [description] /by yyyy-MM-dd HH:mm'");
        }
    }

    private static void handleEvent(String cmd) {
        try {
            if (cmd.length() <= 6) {
                System.out.println("the description of an event cannot be empty");
                return;
            }
            String[] sub = cmd.substring(6).trim().split(" /from ");
            if (sub.length != 2) {
                System.out.println("the format for events is 'event [description] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
                return;
            }
            String description = sub[0].trim();
            String[] time = sub[1].split(" /to ");
            if (time.length != 2) {
                System.out.println("the format for events is 'event [description] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
                return;
            }
            String fromString = time[0].trim();
            String toString = time[1].trim();
            if (description.isEmpty()) {
                System.out.println("the description of an event cannot be empty");
            } else {
                try {
                    LocalDateTime from = LocalDateTime.parse(fromString, INPUT_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(toString, INPUT_FORMATTER);
                    todo.add(new EventTask(description, from, to));
                    System.out.println("added event: " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")");
                    saveTasks();
                } catch (DateTimeParseException e) {
                    System.out.println("invalid date format. Please use yyyy-MM-dd HH:mm");
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("the format for events is 'event [description] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
        }
    }

    private static void handleDelete(String cmd) {
        try {
            int no = Integer.parseInt(cmd.substring(cmd.indexOf(' ') + 1).trim()) - 1;
            if (no < 0 || no >= todo.size()) {
                System.out.println("invalid task number");
            } else {
                Task removedTask = todo.remove(no);
                System.out.println("okkieee..i've removed this task: " + removedTask);
                System.out.println("yayyayayy!!!! now you have " + todo.size() + " tasks in the list");
                saveTasks();
            }
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("error parsing task number");
        }
    }

    private static void handleListDate(String cmd) {
        try {
            String dateString = cmd.substring(10).trim();
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            List<Task> tasksOnDate = todo.stream()
                    .filter(task -> {
                        if (task instanceof DeadlineTask) {
                            return ((DeadlineTask) task).by.toLocalDate().equals(date);
                        } else if (task instanceof EventTask) {
                            return ((EventTask) task).from.toLocalDate().equals(date) ||
                                    ((EventTask) task).to.toLocalDate().equals(date);
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

            if (tasksOnDate.isEmpty()) {
                System.out.println("no tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } else {
                System.out.println("tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    System.out.println((i + 1) + ". " + tasksOnDate.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("invalid date format. Please use yyyy-MM-dd");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("the format for listing tasks on a date is 'list-date yyyy-MM-dd'");
        }
    }

    private static void handleListWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusDays(7);

        List<Task> tasksThisWeek = todo.stream()
                .filter(task -> {
                    if (task instanceof DeadlineTask) {
                        LocalDate deadlineDate = ((DeadlineTask) task).by.toLocalDate();
                        return !deadlineDate.isBefore(today) && !deadlineDate.isAfter(oneWeekLater);
                    } else if (task instanceof EventTask) {
                        LocalDate eventStartDate = ((EventTask) task).from.toLocalDate();
                        LocalDate eventEndDate = ((EventTask) task).to.toLocalDate();
                        return !(eventEndDate.isBefore(today) || eventStartDate.isAfter(oneWeekLater));
                    }
                    return false;
                })
                .sorted(Comparator.comparing(task -> {
                    if (task instanceof DeadlineTask) {
                        return ((DeadlineTask) task).by;
                    } else if (task instanceof EventTask) {
                        return ((EventTask) task).from;
                    }
                    return LocalDateTime.MAX;
                }))
                .collect(Collectors.toList());

        if (tasksThisWeek.isEmpty()) {
            System.out.println("no tasks in the upcoming week");
        } else {
            System.out.println("tasks in the upcoming week:");
            for (int i = 0; i < tasksThisWeek.size(); i++) {
                System.out.println((i + 1) + ". " + tasksThisWeek.get(i));
            }
        }
    }

    private static void handleSortDeadline() {
        List<Task> sortedTasks = todo.stream()
                .filter(task -> task instanceof DeadlineTask)
                .sorted(Comparator.comparing(task -> ((DeadlineTask) task).by))
                .collect(Collectors.toList());

        if (sortedTasks.isEmpty()) {
            System.out.println("no deadline tasks to sort");
        } else {
            System.out.println("deadline tasks sorted by due date:");
            for (int i = 0; i < sortedTasks.size(); i++) {
                System.out.println((i + 1) + ". " + sortedTasks.get(i));
            }
        }
    }

    static abstract class Task {
        protected boolean isDone;
        protected String description;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + description;
        }
    }

    static class ToDoTask extends Task {
        public ToDoTask(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class DeadlineTask extends Task {
        protected LocalDateTime by;

        public DeadlineTask(String description, LocalDateTime by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
        }
    }

    static class EventTask extends Task {
        protected LocalDateTime from;
        protected LocalDateTime to;

        public EventTask(String description, LocalDateTime from, LocalDateTime to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
        }
    }
}