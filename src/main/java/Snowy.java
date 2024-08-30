import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Snowy {
    private static final String LINE ="____________________________________________________________";

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return this.description;
        }

        public void toggleStatus() {
            this.isDone = !this.isDone;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s",this.getStatusIcon(), this.getDescription());
        }

    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[E]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected LocalDateTime by;

        public Deadline(String description, String by) {
            super(description);
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        @Override
        public String toString() {
            String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        }
    }

    public static class Event extends Task {
        protected LocalDateTime from;
        protected LocalDateTime to;

        public Event(String description, String from, String to) throws SnowyException {
            super(description);
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

            if (!this.from.isBefore(this.to)) {
                throw new SnowyException("The 'from' date must be before the 'to' date.");
            }

        }

        @Override
        public String toString() {
            String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
            String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
            return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
        }
    }

    public static class ToDoList {
        private ArrayList<Task> taskList;
        private static final String FILE_PATH = "../ip/src/main/data";
        private static final String FILENAME = "snowy.txt";

        public ToDoList() {
            taskList = new ArrayList<Task>();
        }

        private void writeToFile(Task t) {
            Path dataDirectoryPath = Paths.get(FILE_PATH);
            Path taskFilePath = dataDirectoryPath.resolve(FILENAME);

            try {
                if (Files.notExists(dataDirectoryPath)) {
                    Files.createDirectories(dataDirectoryPath);
                } if (Files.notExists(taskFilePath)) {
                    Files.createFile(taskFilePath);
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter(taskFilePath.toString(), true));
                writer.write(t.toString());
                writer.newLine();
                writer.close();

            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        private void deleteFromFile(Task t) {
            Path dataDirectoryPath = Paths.get(FILE_PATH);
            Path taskFilePath = dataDirectoryPath.resolve(FILENAME);
            Path tempFilePath = dataDirectoryPath.resolve("temp.txt");
            String search = t.toString();

            try (
                    BufferedReader br = new BufferedReader(new FileReader(taskFilePath.toString()));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath.toString()))
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.equals(search)) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
                return;
            }

            // Replace the original file with the temporary file
            try {
                Files.delete(taskFilePath);
                Files.move(tempFilePath, taskFilePath);
                System.out.println("Task deleted successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while replacing the file: " + e.getMessage());
            }
        }


        public void addTask(Task t) {
            taskList.add(t);
            String str = String.format(" %s\nNow you have %d task(s) in your list\n" + LINE, t, this.taskList.size());
            System.out.println(str);
            writeToFile(t);
        }

        public boolean isTaskDone(int i) {
            Task task = taskList.get(i);
            return task.isDone;
        }

        public void toggleTask(int i) throws SnowyException {
            if (i >= 0 && i < taskList.size()) {
                Task task = taskList.get(i);
                task.toggleStatus();
                String str = String.format("%d. %s", i+1, task);
                System.out.println(str + "\n" + LINE + "\n");
            } else {
                throw new SnowyException("Invalid index.");
            }
        }
        public void displayList() throws SnowyException {
            if (taskList.isEmpty()) {
                throw new SnowyException("No tasks, make a list first.");
            }
            System.out.println(LINE +"\nYour list of tasks");
            for (int i = 0; i < taskList.size(); i++) {
                String str = String.format("%d. %s",i+1, taskList.get(i));
                System.out.println(str);
            }
            System.out.println(LINE);
        }

        public void deleteTask(int index) throws SnowyException {
            if (taskList.isEmpty()) {
                throw new SnowyException("No tasks in list.");
            }
            if (index >=0 && index <= taskList.size()) {
                Task task = taskList.get(index - 1);
                System.out.println("Removed task:\n " + task);
                taskList.remove(index - 1);
                deleteFromFile(task);
                System.out.printf("\nNow you have %d task(s) in your list.\n", this.taskList.size());

            } else {
                throw new SnowyException("Invalid index.");
            }
        }

    }

    public static class SnowyException extends Exception {
        protected String formatted;
        public SnowyException(String message) {
            super(message);
            formatted = LINE + "\nmessage\n" + LINE;
        }
    }

    public static void main(String[] args) {
        System.out.println(LINE + "\n Hello I'm Snowy\n What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ToDoList taskList = new ToDoList();
        boolean isBye = false;

        while (!isBye) {
            try {
                String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                isBye = true;

            } else if (input.equalsIgnoreCase("list")) {
                taskList.displayList();

            } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number");
                }
                int index = Integer.parseInt(parts[1]);
                if (taskList.isTaskDone(index - 1)) {
                    throw new SnowyException("Task is already done");

                }
                System.out.println(LINE + "\nMarked as done\n");
                taskList.toggleTask(index - 1);

            } else if (input.split(" ")[0].equalsIgnoreCase("unmark")){
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number");
                }
                int index = Integer.parseInt(parts[1]);
                if (!taskList.isTaskDone(index - 1)) {
                    throw new SnowyException("Cannot unmark task as it is not done");

                }
                System.out.println(LINE + "\nUnmarked task\n");
                taskList.toggleTask(index - 1);

            } else if (input.startsWith("todo")){
                String description = input.substring(4).trim();
                System.out.println(LINE);
                if (description.isEmpty() || description.equals(" ")) {
                    throw new SnowyException("Please provide a description");

                }
                ToDo todo = new ToDo(description);
                System.out.println("Added a to do to your list of tasks");
                taskList.addTask(todo);

            }  else if (input.startsWith("deadline")) {
                String after = input.substring(8);
                boolean hasDate = input.contains("/by");
                System.out.println(LINE);

                if (!hasDate) {
                    throw new SnowyException("Please provide a deadline");
                }

                String[] parts = after.split(" /by ", 2);

                if (parts.length < 2) {
                    throw new SnowyException("Please provide a deadline.");
                }

                String description = parts[0].trim();
                String date = parts[1].trim();


                if (description.isEmpty()) {
                    throw new SnowyException("Please provide a description");
                }
                if (date.isEmpty()) {
                    throw new SnowyException("Please provide a deadline");
                }

                Deadline deadline = new Deadline(description, date);
                System.out.println("Added a task with deadline to your list of tasks");
                taskList.addTask(deadline);

            } else if (input.startsWith("event")) {
                String after = input.substring(5);
                boolean hasFrom = input.contains("/from");
                boolean hasTo = input.contains("/to");

                System.out.println(LINE);

                if (!hasFrom || !hasTo) {
                    throw new SnowyException("Please provide both from and to");
                }

                String description = after.split(" /from ", 2)[0];
                String afterDesc = after.split(" /from ", 2)[1];

                String from = afterDesc.split("/to", 2)[0].trim();
                String to = afterDesc.split("/to", 2)[1].trim();

                if (description.isEmpty() || description.equals(" ")) {
                    throw new SnowyException("Please provide a description");
                }
                if (from.isEmpty() || from.equals(" ")) {
                    throw new SnowyException("Please provide a from");
                }
                if (to.isEmpty() || to.equals(" ")) {
                    throw new SnowyException("Please provide a to");
                }

                Event event = new Event(description, from, to);
                System.out.println("Added an event to your list of tasks");
                taskList.addTask(event);

            } else if (input.startsWith("delete")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new SnowyException("Please provide a task number.");
                }
                int index = Integer.parseInt(parts[1]);
                taskList.deleteTask(index);
                System.out.println(LINE);
            } else {
                throw new SnowyException("Sorry, I do not understand.");
            }
        } catch (SnowyException e) {
                System.out.println(e.getMessage());
            }
        }  System.out.println("Bye. See you next time!");


    }
}
