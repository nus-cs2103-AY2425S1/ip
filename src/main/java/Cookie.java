import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.Scanner;
import java.util.ArrayList;

public class Cookie {
    enum Commands {
        list,
        bye,
        todo,
        deadline,
        event,
    }
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private void addTask(Task task) {
        taskArrayList.add(task);
    }
    private void delete(int index) {
        Task toRemove = taskArrayList.get(index - 1);
        taskArrayList.remove(index - 1);
        System.out.println("Cookie has removed the following task from your list:\n" +
                toRemove.toString() + "\n" + this.printNoTasks());
    }
    private String markDone(int index) {
        return taskArrayList.get(index - 1).markDone();
    }
    private String unmarkDone(int index) {
        return taskArrayList.get(index - 1).unmarkDone();
    }
    private String printTasks() {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for(Task task: this.taskArrayList) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return list.toString();
    }
    private String printNoTasks() {
        if (taskArrayList.size() == 1) {
            return "\nNow you have " + taskArrayList.size() + " task in the list.";
        } else {
            return "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        }
    }
    private String printLatestTask() {
        return "Got it. Cookie has added this task:\n  " + taskArrayList.get(taskArrayList.size() - 1);
    }
    public String printLogo() {
        return "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
    }
    public String printGreet() {
        return "Hello! I'm Cookie \n"
                + "How can I help you?\n"
                + "Here are some commands you can use:\n"
                + "todo, deadline, event, mark, unmark, delete, list";
    }
    public String printQuit() {
        return "Bye. See you soon!";
    }
    public void handleInput(String input) throws CookieException {
        String[] parseInput = input.split(" ", 2);
        String command = parseInput[0];
        String description = (parseInput.length > 1) ? parseInput[1] : "";

        switch (command) {
            case "list":
                System.out.println(this.printTasks());
                break;

            case "delete":
                int deleteIndex = Integer.parseInt(description);
                if (description.isEmpty()) {
                    throw new CookieException("Cookie does not know which task to delete.\n " +
                            "(Please enter an integer after \"delete\")");
                } else if (deleteIndex <= 0 || deleteIndex > taskArrayList.size()) {
                    throw  new CookieException("The task you want to delete does not exist");
                }
                this.delete(deleteIndex);
                break;

            case "mark":
                int markIndex = Integer.parseInt(description);
                if (description.isEmpty()) {
                    throw new CookieException("Cookie does not know which task to mark.\n " +
                            "(Please enter an integer after \"mark\")");
                } else if (markIndex <= 0 || markIndex > taskArrayList.size()) {
                    throw  new CookieException("The task you want to mark does not exist");
                }
                System.out.println("Cookie has marked this as done! Good job! \n" +
                        this.markDone(markIndex));
                break;

            case "unmark":
                int unmarkIndex = Integer.parseInt(description);
                if (description.isEmpty()) {
                    throw new CookieException("Cookie does not know which task to unmark.\n" +
                            "(Please enter an integer after \"unmark\")");
                } else if (unmarkIndex <= 0 || unmarkIndex > taskArrayList.size()) {
                    throw  new CookieException("The task you want to mark does not exist");
                }
                System.out.println("Cookie has unmarked this task! \n" +
                        this.unmarkDone(unmarkIndex));
                break;

            case "todo":
                if (description.isEmpty()) {
                    throw new CookieException("Please enter a task for you to do.");
                }
                addTask(new ToDo(description));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
                    throw new CookieException("Deadlines must include a task todo and a due date \n" +
                            "[task] /by [deadline]");
                }
                addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            case "event":
                String[] eventDetails = description.split(" /from | /to ");
                if (eventDetails.length < 2 || eventDetails[0].isEmpty() ||
                        eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
                    throw new CookieException("Events must include a task, a start time and an end time \n" +
                            "[task] /from [start] /to [end]");
                }
                addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            default:
                throw new CookieException("Cookie does not understand this command. I'm sorry!!");
        }
    }

    public void fetchFile() {
        File file = new File("./data/cookie.txt");
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                loadFile(file);
            }
        } catch (IOException | CookieException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFile(File file) throws FileNotFoundException, CookieException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();;
            parseFileContent(line);
        }
    }

    public void parseFileContent(String string) throws CookieException {
        String[] parts = string.split(" \\| ");
        String command = parts[0];
        boolean isDone = parts[1].equals("1");
        String task = parts[2];
        String details = parts.length > 3 ? parts[3] : "";

        switch (command) {
            case "T":
                addTask(new ToDo(isDone, task));
                break;
            case "D":
                addTask(new Deadline(isDone, task, details));
                break;
            case "E":
                String[] eventDetails = details.split("-");
                addTask(new Event(isDone, task, eventDetails[0], eventDetails[1]));
                break;
            default:
                throw new CookieException("File contains unknown command");
        }
    }

    public void saveFile() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/cookie.txt"))) {
            for (Task task : taskArrayList) {
                StringBuilder saveIntoFile = new StringBuilder();

                if (task instanceof ToDo) {
                    saveIntoFile.append("T | ");
                } else if (task instanceof Deadline) {
                    saveIntoFile.append("D | ");
                } else if (task instanceof Event) {
                    saveIntoFile.append("E | ");
                }

                saveIntoFile.append(task.getStatus().equals("[X]") ? "1 | " : "0 | ");
                saveIntoFile.append(task.getDescription());

                if (task instanceof Deadline) {
                    saveIntoFile.append(" | ").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    saveIntoFile.append(" | ").append(((Event) task).getFrom())
                            .append("-").append(((Event) task).getTo());
                }

                writer.write(saveIntoFile.toString());
                writer.write(System.lineSeparator());
            }
        }
    }


    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        cookie.fetchFile();
        System.out.println(cookie.printLogo() + cookie.printGreet());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                cookie.handleInput(input);
            } catch (CookieException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }

        scanner.close();
        try {
            cookie.saveFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cookie.printQuit());

    }
}
