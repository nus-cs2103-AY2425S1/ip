package com.chatbaby;

import java.io.File;
import java.util.Locale;

public class ChatBaby {
    private static final String FILE_PATH = "." + File.separator +
            "data" + File.separator + "chatBaby.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ChatBaby() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String curCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(curCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBabyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        try {
            storage.save(tasks);
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new ChatBaby().run();
    }
}


//public class ChatBaby {
//
//
//    public static void main(String[] args) {
//        // Set the default locale to English
//        Locale.setDefault(Locale.ENGLISH);
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<Task> tasks = loadTasks();
//        greet();
//
//        while (true) {
//            String input = scanner.nextLine().trim();
//
//            if (input.equals("bye")) {
//                bye();
//                break;
//            } else if (input.equals("list")) {
//                printTasks(tasks);
//            } else if (input.startsWith("mark")) {
//                markTask(input, tasks);
//            } else if (input.startsWith("unmark")) {
//                unmarkTask(input, tasks);
//            } else if (input.startsWith("list that ends on")) {
//                listTasksOn(input.substring(18), tasks);
//            }else if (input.startsWith("todo")) {
//                handleTaskCommand(tasks, TaskType.TODO, input, 5);
//            } else if (input.startsWith("deadline")) {
//                handleTaskCommand(tasks, TaskType.DEADLINE, input, 9);
//            } else if (input.startsWith("event")) {
//                handleTaskCommand(tasks, TaskType.EVENT, input, 6);
//            } else if (input.startsWith("delete")) {
//                deleteTask(input, tasks);
//            } else {
//                printUnknownCommandError();
//            }
//            saveTasks(tasks);
//        }
//        scanner.close();
//    }

//    private static void saveTasks(ArrayList<Task> tasks) {
//        try {
//            File directory = new File(DIRECTORY_PATH);
//            if (!directory.exists()) {
//                directory.mkdirs();  // Create the directory if it doesn't exist
//            }
//
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//                for (Task task: tasks) {
//                    writer.write(task.toFileText());
//                    writer.newLine();
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error while saving tasks: " + e.getMessage());
//        }
//    }

//    private static ArrayList<Task> loadTasks() {
//        ArrayList<Task> tasks = new ArrayList<>();
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            return tasks; // Return an empty list if the file doesn't exist
//        }
//
//        try (Scanner scanner = new Scanner(file)) {
//            String line;
//            while (scanner.hasNextLine()) {
//                line = scanner.nextLine();
//                try {
//                    tasks.add(Task.fromFileText(line)); // Handle corrupted data inside the method
//                } catch (Exception e) {
//                    System.out.println("Error while reading line: " + line);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Error while loading tasks: " + e.getMessage());
//        }
//
//        return tasks;
//    }

//    public static void listTasksOn(String time, ArrayList<Task> tasks) {
//        System.out.println("____________________________________________________________");
//        try {
//            // Parse the input date
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate givenDate = LocalDate.parse(time, formatter);
//            System.out.println("Tasks that end on " + givenDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
//
//            boolean hasTasks = false;
//            for (Task task : tasks) {
//                if (task instanceof Deadline) {
//                    Deadline deadlineTask = (Deadline) task;
//                    if (deadlineTask.getDeadline().toLocalDate().equals(givenDate)) {
//                        System.out.println(task.toString());
//                        hasTasks = true;
//                    }
//                } else if (task instanceof Event) {
//                    Event eventTask = (Event) task;
//                    if (eventTask.getTo().toLocalDate().equals(givenDate)) {
//                        System.out.println(task.toString());
//                        hasTasks = true;
//                    }
//                }
//            }
//
//            if (!hasTasks) {
//                System.out.println("No tasks ending on this date.");
//            }
//        } catch (Exception e) {
//            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    public static void greet() {
//        System.out.println("____________________________________________________________\n"
//                + "Hello! I'm ChatBaby\n"
//                + "What can I do for you?\n"
//                + "____________________________________________________________");
//    }
//
//    public static void bye() {
//        System.out.println("____________________________________________________________\n"
//                + "Bye. Hope to see you again soon!\n"
//                + "____________________________________________________________");
//    }
//
//    public static void printTasks(ArrayList<Task> tasks) {
//        System.out.println("____________________________________________________________\n"
//                + "Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + ". " + tasks.get(i).toString());
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    private static void markTask(String input, ArrayList<Task> tasks) {
//        try {
//            int index = Integer.parseInt(input.substring(5).trim()) - 1;
//            if (index >= 0 && index < tasks.size()) {
//                tasks.get(index).markAsDone();
//                System.out.println("____________________________________________________________\n"
//                        + "Nice! I've marked this task as done:\n"
//                        + tasks.get(index).toString() + "\n"
//                        + "____________________________________________________________");
//            } else {
//                printInvalidTaskIndexError();
//            }
//        } catch (NumberFormatException e) {
//            printInvalidTaskIndexError();
//        }
//    }
//
//    private static void unmarkTask(String input, ArrayList<Task> tasks) {
//        try {
//            int index = Integer.parseInt(input.substring(7).trim()) - 1;
//            if (index >= 0 && index < tasks.size()) {
//                tasks.get(index).unMarkAsDone();
//                System.out.println("____________________________________________________________\n"
//                        + "OK, I've marked this task as not done yet:\n"
//                        + tasks.get(index).toString() + "\n"
//                        + "____________________________________________________________");
//            } else {
//                printInvalidTaskIndexError();
//            }
//        } catch (NumberFormatException e) {
//            printInvalidTaskIndexError();
//        }
//    }
//
//    private static void handleTaskCommand(ArrayList<Task> tasks, TaskType type, String input, int prefixLength) {
//        if (input.length() <= prefixLength) {
//            printEmptyDescriptionError(type);
//            return;
//        }
//
//        String description = input.substring(prefixLength).trim();
//        if (description.isEmpty()) {
//            printEmptyDescriptionError(type);
//            return;
//        }
//
//        Task newTask;
//        switch (type) {
//            case TODO:
//                newTask = new ToDo(description);
//                break;
//            case DEADLINE:
//                String[] deadlineParts = description.split("/by ");
//                if (deadlineParts.length == 2) {
//                    try {
//                        String taskDescription = deadlineParts[0].trim();
//                        String deadline = deadlineParts[1].trim();
//                        newTask = new Deadline(taskDescription, deadline);
//                    } catch (DateTimeParseException e) {
//                        printInvalidTimeFormatError();
//                        return;
//                    }
//                } else {
//                    printEmptyDescriptionError(type);
//                    return;
//                }
//                break;
//            case EVENT:
//                String[] eventParts = description.split("/from ");
//                if (eventParts.length == 2) {
//                    String name = eventParts[0].trim();
//                    String[] eventTimes = eventParts[1].split("/to ");
//                    if (eventTimes.length == 2) {
//                        try {
//                            String from = eventTimes[0].trim();
//                            String to = eventTimes[1].trim();
//                            newTask = new Event(name, from, to);
//                        } catch (DateTimeParseException e) {
//                            printInvalidTimeFormatError();
//                            return;
//                        }
//                    } else {
//                        printEmptyDescriptionError(type);
//                        return;
//                    }
//                } else {
//                    printEmptyDescriptionError(type);
//                    return;
//                }
//                break;
//
//            default:
//                printUnknownCommandError();
//                return;
//        }
//
//        tasks.add(newTask);
//        System.out.println("____________________________________________________________\n"
//                + "Got it. I've added this task:\n"
//                + newTask.toString() + "\n"
//                + "Now you have " + tasks.size() + " tasks in the list.\n"
//                + "____________________________________________________________");
//    }
//
//    private static void deleteTask(String input, ArrayList<Task> tasks) {
//        try {
//            int index = Integer.parseInt(input.substring(7).trim()) - 1;
//            if (index >= 0 && index < tasks.size()) {
//                Task removedTask = tasks.remove(index);
//                System.out.println("____________________________________________________________\n"
//                        + "Noted. I've removed this task:\n"
//                        + removedTask.toString() + "\n"
//                        + "Now you have " + tasks.size() + " tasks in the list.\n"
//                        + "____________________________________________________________");
//            } else {
//                printInvalidTaskIndexError();
//            }
//        } catch (NumberFormatException e) {
//            printInvalidTaskIndexError();
//        }
//    }
//
//    private static void printInvalidTaskIndexError() {
//        System.out.println("____________________________________________________________\n"
//                + "Oh no!!! The task index is invalid.\n"
//                + "____________________________________________________________");
//    }
//
//    private static void printEmptyDescriptionError(TaskType type) {
//        System.out.println("____________________________________________________________\n"
//                + "Oh no!!! The description of a " + type.name().toLowerCase() + " cannot be empty.\n"
//                + "____________________________________________________________");
//    }
//
//    private static void printUnknownCommandError() {
//        System.out.println("____________________________________________________________\n"
//                + "Oh no!!! I'm sorry, but I don't understand that command.\n"
//                + "____________________________________________________________");
//    }
//
//    private static void printInvalidTimeFormatError() {
//        System.out.println("____________________________________________________________\n"
//                + "Invalid date format. Please use yyyy-MM-dd HH:mm.\n"
//                + "____________________________________________________________");
//    }
//}
