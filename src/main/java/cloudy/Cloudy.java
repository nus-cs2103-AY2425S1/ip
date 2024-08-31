package cloudy;

import java.time.LocalDate;

import java.util.Scanner;

public class Cloudy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Cloudy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.loadTasksFromFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        Scanner echo = new Scanner(System.in);
        String userInput;

        // main program loop
        while (true) {
            userInput = echo.nextLine();
            Command command = parser.parseCommand(userInput);

            switch (command.getType()) {
            case "bye":
                ui.showBye();
                return;
            case "list":
                ui.showList(tasks);
                break;
            case "mark":
                handleMarkCommand(command);
                break;
            case "unmark":
                handleUnmarkCommand(command);
                break;
            case "todo":
                handleTodoCommand(command);
                break;
            case "deadline":
                handleDeadlineCommand(command);
                break;
            case "event":
                handleEventCommand(command);
                break;
            case "delete":
                handleDeleteCommand(command);
                break;
            case "invalid":
                ui.showInvalidCommand();
                break;
            case "invalidTaskNum":
                ui.showInvalidTaskNum();
                break;
            case "invalidTaskFormat":
                ui.showInvalidTaskFormat();
                break;
            case "invalidDeadline":
                ui.showInvalidDeadline();
                break;
            case "invalidEvent":
                ui.showInvalidEvent();
                break;
            default:
                ui.showInvalidCommand();
            }
        }
    }

    private void handleMarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToMark = tasks.getTask(taskNumber - 1);
                taskToMark.markTask();
                storage.saveTasksToFile(tasks.getAllTasks());
                ui.showMark(taskToMark);
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }

    private void handleUnmarkCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToUnmark = tasks.getTask(taskNumber - 1);
                taskToUnmark.unmarkTask();
                ui.showUnmark(taskToUnmark);
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }

    private void handleTodoCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        Task newTask = new Todo(taskDescription, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleDeadlineCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate deadline = command.getDeadline();
        Task newTask = new Deadline(taskDescription, deadline, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleEventCommand(Command command) {
        String taskDescription = command.getTaskDescription();
        LocalDate startTime = command.getStartTime();
        LocalDate endTime = command.getEndTime();
        Task newTask = new Event(taskDescription, startTime, endTime, false);
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showAddTask(newTask, tasks.size());
    }

    private void handleDeleteCommand(Command command) {
        try {
            int taskNumber = command.getTaskNumber();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task taskToDelete = tasks.getTask(taskNumber - 1);
                tasks.removeTask(taskNumber - 1);
                storage.saveTasksToFile(tasks.getAllTasks());
                ui.showDeleteTask(taskToDelete, tasks.size());
            } else {
                ui.showInvalidTaskNum();
            }
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNum();
        }
    }




    public static void main(String[] args) {
        new Cloudy("data/tasks.txt").run();
    }

    /*
    public void run() {
        ui.showGreeting();
        Scanner echo = new Scanner(System.in);
        String userInput;

        // main program loop
        while (true) {
            userInput = echo.nextLine();

            // if user types 'bye', program will end
            if (Objects.equals(userInput, "bye")) {
                ui.showBye();
                break;

            // if user types 'list', user can see the tasks
            } else if (Objects.equals(userInput, "list")) {
                ui.showList(tasks);

            // if user marks task
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length >= 1) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber <= tasks.size()) {
                            Task taskToMark = tasks.getTask(taskNumber - 1);
                            taskToMark.markTask();
                            storage.saveTasksToFile(tasks.getAllTasks());
                            ui.showMark(taskToMark);

                        } else {
                            ui.showInvalidCommand();
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNum();
                    }
                } else {
                    ui.showInvalidTaskFormat();
                }

                // if user unmarks task
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber <= tasks.size()) {
                            Task taskToUnmark = tasks.getTask(taskNumber - 1);
                            taskToUnmark.unmarkTask();
                            ui.showUnmark(taskToUnmark);

                        } else {
                            ui.showInvalidTaskNum();
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNum();
                    }
                } else {
                    ui.showInvalidCommand();
                }

            // user adds to do task
            } else if (userInput.startsWith("todo")) {
                if (userInput.trim().length() <= 4) {
                    ui.showInvalidCommand();
                } else {
                    String taskDescription = userInput.substring(5).trim();
                    Task newTask = new Todo(taskDescription, false);
                    tasks.addTask(newTask);
                    storage.saveTasksToFile(tasks.getAllTasks());

                    ui.showAddTask(newTask, tasks.size());
                }

                // user adds Deadline task
            } else if (userInput.startsWith("deadline")) {

                String[] parts = userInput.split("/by ");
                String taskDescription = parts[0].substring(9).trim();
                String inputDeadline = parts.length > 1 ? parts[1].trim() : "";

                String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
                Pattern pattern = Pattern.compile(datePattern);
                Matcher matcher = pattern.matcher(inputDeadline);

                if (matcher.matches()) {
                    try {
                        String[] deadlineParts = inputDeadline.split("/");
                        LocalDate deadline = LocalDate.parse(deadlineParts[2] + "-" + deadlineParts[1] + "-" + deadlineParts[0]);

                        Task newTask = new Deadline(taskDescription, deadline, false);
                        tasks.addTask(newTask);
                        storage.saveTasksToFile(tasks.getAllTasks());

                        ui.showAddTask(newTask, tasks.size());
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDeadline();
                    }
                } else {
                    ui.showInvalidDeadline();
                }

                // user adds Event task
            } else if (userInput.startsWith("event")) {

                String[] partsFrom = userInput.split("/from");
                String taskDescription = partsFrom[0].substring(6).trim();
                String startTime = "", endTime = "";
                if (partsFrom.length > 1) {
                    String[] partsTo = partsFrom[1].split("/to");
                    startTime = partsTo[0].trim();
                    if (partsTo.length > 1) {
                        endTime = partsTo[1].trim();
                    }
                }

                String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
                Pattern pattern = Pattern.compile(datePattern);
                Matcher matcherStartTime = pattern.matcher(startTime);
                Matcher matcherEndTime = pattern.matcher(endTime);

                if (matcherStartTime.matches() && matcherEndTime.matches()) {
                    try {
                        String[] startTimeParts = startTime.split("/");
                        String[] endTimeParts = startTime.split("/");
                        LocalDate startTimeFinal =
                                LocalDate.parse(startTimeParts[2] + "-" + startTimeParts[1] + "-" + startTimeParts[0]);
                        LocalDate endTimeFinal =
                                LocalDate.parse(endTimeParts[2] + "-" + endTimeParts[1] + "-" + endTimeParts[0]);

                        Task newTask = new Event(taskDescription, startTimeFinal, endTimeFinal, false);
                        tasks.addTask(newTask);
                        storage.saveTasksToFile(tasks.getAllTasks());

                        ui.showAddTask(newTask, tasks.size());

                    } catch (DateTimeParseException e) {
                        ui.showInvalidEvent();
                    }
                } else {
                    ui.showInvalidEvent();
                }


            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber <= tasks.size()) {
                            Task taskToDelete = tasks.getTask(taskNumber - 1);
                            tasks.removeTask(taskNumber - 1);
                            storage.saveTasksToFile(tasks.getAllTasks());

                            ui.showDeleteTask(taskToDelete, tasks.size());

                        } else {
                            ui.showInvalidTaskNum();
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNum();
                    }
                } else {
                    ui.showInvalidCommand();
                }

                // if user types invalid command
            } else {
                ui.showInvalidCommand();

            }
        }

    }
     */

    /*
    private static final String FILE_PATH = "./data/cloudy.txt";


    public static void main(String[] args) {
        Scanner echo = new Scanner(System.in);
        ArrayList<Task> userList = loadTasksFromFile();

        // Starting prompt
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Cloudy.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Asking for user input
        String userInput = echo.nextLine();

        // main program loop
        while (true) {
            // if user types 'bye', program will end
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            // if user types 'list', user can see the tasks
            } else if (Objects.equals(userInput, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i + 1) + ". " + userList.get(i).printTaskOnList());
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // if user marks task
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToMark = userList.get(taskNumber - 1);
                            taskToMark.markTask();
                            saveTasksToFile(userList);

                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskToMark.printTaskOnList());
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();


            // if user unmarks task
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                        Task taskToMark = userList.get(taskNumber - 1);
                        taskToMark.unmarkTask();

                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskToMark.printTaskOnList());
                        System.out.println("____________________________________________________________");

                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }

                // prompt for input
                userInput = echo.nextLine();

            // user adds to do task
            } else if (userInput.startsWith("todo")) {
                System.out.println("____________________________________________________________");
                if (userInput.trim().length() <= 4) {
                    System.out.println("No task detected.");
                } else {
                    String taskDescription = userInput.substring(5).trim();
                    Task newTask = new Todo(taskDescription, false);
                    userList.add(newTask);
                    saveTasksToFile(userList);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.printTaskOnList());
                    System.out.println("Now you have " + userList.size() + " tasks in the list.");
                }
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();

            // user adds Deadline task
            } else if (userInput.startsWith("deadline")) {

                String[] parts = userInput.split("/by ");
                String taskDescription = parts[0].substring(9).trim();
                String inputDeadline = parts.length > 1 ? parts[1].trim() : "";

                String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
                Pattern pattern = Pattern.compile(datePattern);
                Matcher matcher = pattern.matcher(inputDeadline);

                if (matcher.matches()) {
                    try {
                        String[] deadlineParts = inputDeadline.split("/");
                        LocalDate deadline = LocalDate.parse(deadlineParts[2] + "-" + deadlineParts[1] + "-" + deadlineParts[0]);

                        Task newTask = new Deadline(taskDescription, deadline, false);
                        userList.add(newTask);
                        saveTasksToFile(userList);

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask.printTaskOnList());
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid format. Please enter a deadline with the format [task] /by dd/mm/yyyy.");
                    }
                } else {
                    System.out.println("Invalid format. Please enter a deadline with the format [task] /by dd/mm/yyyy.");
                }

                // prompt for input
                userInput = echo.nextLine();

            // user adds Event task
            } else if (userInput.startsWith("event")) {


                String[] partsFrom = userInput.split("/from");
                String taskDescription = partsFrom[0].substring(6).trim();
                String startTime = "", endTime = "";
                if (partsFrom.length > 1) {
                    String[] partsTo = partsFrom[1].split("/to");
                    startTime = partsTo[0].trim();
                    if (partsTo.length > 1) {
                        endTime = partsTo[1].trim();
                    }
                }

                String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
                Pattern pattern = Pattern.compile(datePattern);
                Matcher matcherStartTime = pattern.matcher(startTime);
                Matcher matcherEndTime = pattern.matcher(endTime);

                if (matcherStartTime.matches() && matcherEndTime.matches()) {
                    try {
                        String[] startTimeParts = startTime.split("/");
                        String[] endTimeParts = startTime.split("/");
                        LocalDate startTimeFinal =
                                LocalDate.parse(startTimeParts[2] + "-" + startTimeParts[1] + "-" + startTimeParts[0]);
                        LocalDate endTimeFinal =
                                LocalDate.parse(endTimeParts[2] + "-" + endTimeParts[1] + "-" + endTimeParts[0]);

                        Task newTask = new Event(taskDescription, startTimeFinal, endTimeFinal, false);
                        userList.add(newTask);
                        saveTasksToFile(userList);

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask.printTaskOnList());
                        System.out.println("Now you have " + userList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid format. Please enter an event with the format [event] /from dd/mm/yyyy /to dd/mm/yyyy");
                    }
                } else {
                    System.out.println("Invalid format. Please enter an event with the format [event] /from dd/mm/yyyy /to dd/mm/yyyy");
                }


                // prompt for input
                userInput = echo.nextLine();

            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskNumber > 0 && taskNumber < userList.size() + 1) {
                            Task taskToDelete = userList.get(taskNumber - 1);

                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskToDelete.printTaskOnList());
                            userList.remove(taskNumber - 1);
                            saveTasksToFile(userList);
                            System.out.println("Now you have " + userList.size() + " tasks in the list.");
                            System.out.println("____________________________________________________________");

                        } else {
                            System.out.println("____________________________________________________________");
                            System.out.println("This task does not exist. Try again.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please enter a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid format.");
                    System.out.println("____________________________________________________________");
                }
                userInput = echo.nextLine();

            // if user types invalid command
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command, try again.");
                System.out.println("____________________________________________________________");

                // prompt for input
                userInput = echo.nextLine();
            }
        }
    }


    public static void checkFileExists() {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred when creating the file.");
            e.printStackTrace();
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        checkFileExists();

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Skipping invalid task" + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found. Creating one...");
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks.");
            e.printStackTrace();
        }
        return tasks;
    }


    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String typeOfTask = parts[0];
        boolean isMarked = parts[1].equals("1");
        String taskDescription = parts[2];

        switch (typeOfTask) {
            case "T":
                return new Todo(taskDescription, isMarked);
            case "D":
                LocalDate deadline = LocalDate.parse(parts[3]);
                return new Deadline(taskDescription, deadline, isMarked);
            case "E":
                LocalDate startTime = LocalDate.parse(parts[3]);
                LocalDate endTime = LocalDate.parse(parts[4]);
                return new Event(taskDescription, startTime, endTime, isMarked);
            default:
                return null;
        }
    }
     */


}
