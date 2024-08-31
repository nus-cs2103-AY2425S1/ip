import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Muller {
    private static final String FILE_PATH = "./data/muller.txt";
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static void main(String[] args) {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = loadTasksFromFile();

        while (true) {
            try {
                String in = sc.nextLine();
                String[] inputs = in.split(" ", 2);
                String command = inputs[0].toLowerCase();

                if (command.equals("bye")) {
                    break;
                } else if (command.equals("mark")) {
                    handleMarkCommand(inputs, list);
                } else if (command.equals("unmark")) {
                    handleUnmarkCommand(inputs, list);
                } else if (command.equals("todo")) {
                    handleTodoCommand(inputs, list);
                } else if (command.equals("deadline")) {
                    handleDeadlineCommand(inputs, list);
                } else if (command.equals("event")) {
                    handleEventCommand(inputs, list);
                } else if (command.equals("list")) {
                    handleListCommand(list);
                } else if (command.equals("delete")) {
                    handleDeleteCommand(inputs, list);
                } else if (command.equals("on")) {
                    handleOnCommand(inputs, list);
                } else {
                    handleUnknownCommand(in, list);
                }
            } catch (MullerException e) {
                System.out.println(logo + "\n" + e.getMessage() + "\n" + logo);
            }
        }

        System.out.println(logo + "\nBye. Hope to see you again soon!\n" + logo);
        sc.close();
    }

    // Handler methods for various commands
    private static void handleMarkCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a task number!");
        }
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new MullerException("Wrong task number!");
        }
        list.get(index).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + list.get(index));
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleUnmarkCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a task number!");
        }
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new MullerException("Wrong task number!");
        }
        list.get(index).markAsNotDone();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + list.get(index));
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleTodoCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Todo what?");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        Task t = new Task(inputs[1]);
        t.setType("T");
        list.add(t);
        System.out.println("  " + t);
        System.out.println("\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleDeadlineCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Deadline for what?");
        }
        String[] detail = inputs[1].split("/by", 2);
        if (detail.length < 2) {
            throw new MullerException("Oops, you didn't specify the deadline!");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        Task t = new Task(detail[0].trim());
        t.setType("D");
        t.setDate(parseDate(detail[1].trim()));
        list.add(t);
        System.out.println("  " + t);
        System.out.println("\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleEventCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Event for what?");
        }
        String[] detail = inputs[1].split("/from", 2);
        if (detail.length < 2) {
            throw new MullerException("Oops, you didn't specify the start date!");
        }
        String[] timing = detail[1].split("/to", 2);
        if (timing.length < 2) {
            throw new MullerException("You missed out either the start or end date!");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        Task t = new Task(detail[0].trim());
        t.setType("E");
        t.setDateRange(parseDate(timing[0].trim()), parseDate(timing[1].trim()));
        list.add(t);
        System.out.println("  " + t);
        System.out.println("\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleListCommand(ArrayList<Task> list) throws MullerException {
        if (list.isEmpty()) {
            throw new MullerException("No tasks found.");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ": " + list.get(i - 1));
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleDeleteCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a task number!");
        }
        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new MullerException("Invalid task number!");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(index));
        list.remove(index);
        System.out.println("\nNow you have " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTasksToFile(list);
    }

    private static void handleOnCommand(String[] inputs, ArrayList<Task> list) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Specify a date (e.g., 'on 2019-10-15')!");
        }
        LocalDate date;
        try {
            date = LocalDate.parse(inputs[1].trim(), INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
        printTasksOnDate(list, date);
    }

    private static void handleUnknownCommand(String input, ArrayList<Task> list) {
        System.out.println("____________________________________________________________");
        System.out.println("Added: " + input);
        Task t = new Task(input);
        list.add(t);
        saveTasksToFile(list);
        System.out.println("____________________________________________________________");
    }

    // Parsing methods
    private static LocalDate parseDate(String dateStr) throws MullerException {
        try {
            return LocalDate.parse(dateStr, INPUT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }

    private static void printTasksOnDate(ArrayList<Task> list, LocalDate date) {
        boolean found = false;
        System.out.println("____________________________________________________________");
        System.out.println("Tasks on " + date.format(OUTPUT_DATE_FORMATTER) + ":");
        for (Task task : list) {
            if (task.isOnDate(date)) {
                System.out.println("  " + task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No tasks found.");
        }
        System.out.println("____________________________________________________________");
    }

    // File I/O methods
    private static void saveTasksToFile(ArrayList<Task> list) {
        try {
            File file = new File(FILE_PATH);
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : list) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return list;  // No tasks to load, return empty list
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                try {
                    if (parts.length < 3) {
                        throw new MullerException("File is corrupted at line " + lineNumber + ".");
                    }
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String name = parts[2];
                    Task task = new Task(name);
                    task.setType(type);
                    task.isDone = isDone;
                    if (type.equals("[D]")) {
                        if (parts.length < 4) {
                            throw new MullerException("File is corrupted at line " + lineNumber + ": Missing deadline date.");
                        }
                        LocalDate date = LocalDate.parse(parts[3], INPUT_DATE_FORMATTER);
                        task.setDate(date);
                    } else if (type.equals("[E]")) {
                        if (parts.length < 5) {
                            throw new MullerException("File is corrupted at line " + lineNumber + ": Missing event dates.");
                        }
                        LocalDate startDate = LocalDate.parse(parts[3], INPUT_DATE_FORMATTER);
                        LocalDate endDate = LocalDate.parse(parts[4], INPUT_DATE_FORMATTER);
                        task.setDateRange(startDate, endDate);
                    }
                    list.add(task);
                } catch (DateTimeParseException | MullerException e) {
                    System.out.println("Error loading task at line " + lineNumber + ": " + e.getMessage());
                    // Optionally, skip corrupted entries instead of halting
                }
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return list;
    }

    // Utility method to check if a string is numeric
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    // Exception class
    public static class MullerException extends Exception {
        public MullerException(String message) {
            super(message);
        }
    }

    // Task class with full implementation using LocalDate
    public static class Task {
        private String name;
        private boolean isDone;
        private String type = "[ ]";  // [T], [D], [E]
        private LocalDate date; // For Deadline or Event start date
        private LocalDate endDate;   // For Event end date

        public Task(String name) {
            this.name = name;
            this.isDone = false;
        }

        public void setType(String type) {
            this.type = "[" + type + "]";
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public void setDateRange(LocalDate startDate, LocalDate endDate) {
            this.date = startDate;
            this.endDate = endDate;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public boolean isOnDate(LocalDate date) {
            if (type.equals("[T]")) {
                // For Todo tasks, no date associated
                return false;
            } else if (type.equals("[D]")) {
                if (this.date == null) return false;
                return this.date.equals(date);
            } else if (type.equals("[E]")) {
                if (this.date == null || this.endDate == null) return false;
                // Check if the event occurs on the specified date
                return (this.date.equals(date) || this.endDate.equals(date)
                        || (date.isAfter(this.date) && date.isBefore(this.endDate)));
            }
            return false;
        }

        public String DoneIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        @Override
        public String toString() {
            String dateStr = "";
            if (type.equals("[D]") && date != null) {
                dateStr = " (by: " + date.format(OUTPUT_DATE_FORMATTER) + ")";
            } else if (type.equals("[E]") && date != null && endDate != null) {
                dateStr = " (from: " + date.format(OUTPUT_DATE_FORMATTER) + " to: " + endDate.format(OUTPUT_DATE_FORMATTER) + ")";
            }
            return this.type + DoneIcon() + " " + name + dateStr;
        }

        public String toFileString() {
            StringBuilder sb = new StringBuilder();
            // Type: 'T', 'D', 'E'
            sb.append(type.charAt(1)).append(" | ").append(isDone ? "1" : "0").append(" | ").append(name);
            if (type.equals("[D]")) {
                sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER));
            } else if (type.equals("[E]")) {
                sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER))
                        .append(" | ").append(endDate.format(INPUT_DATE_FORMATTER));
            }
            return sb.toString();
        }
    }
}



