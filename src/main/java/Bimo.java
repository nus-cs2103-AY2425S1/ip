public class Bimo {
    public static String NAME = "Bimo";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Bimo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (BimoException e) {
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greetUser(NAME);
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.getIsQuit();
            } catch (BimoException e) {
                ui.showErrorMessage();
            } finally {
                ui.showLine();
            }
        }

    }
    public static void main(String[] args) {
        new Bimo("ip/data/Bimo.txt").run();
    }
}
//    public static void main(String[] args) {
//        try {
//            File dataFile = new File("ip/data/Bimo.txt");
//            Scanner fileReader = new Scanner(dataFile);
//            while (fileReader.hasNext()) {
//                String text = fileReader.nextLine();
//                Task task = convertTextToTask(text);
//                tasks.add(task);
//            }
//        } catch (InvalidPathException e) {
//            System.out.println("File path not found, unable to run chatbot due to error");
//            return;
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//            System.out.println("File not found, unable to run chatbot due to error");
//            return;
//        } catch (DateTimeParseException e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//
//        System.out.println(LINE);
//        System.out.println("    " + String.format("Hello! I'm %s", NAME));
//        System.out.println("    " + "What can I do for you?");
//        System.out.println(LINE);
//        String input = scanner.nextLine();
//
//        boolean botIsActive = true;
//        while (botIsActive) {
//            Command command = null;
//            String action = input.split(" ")[0].toLowerCase();
//            try {
//                command = getCommand(action);
//            } catch (InvalidCommandException e) {
//                System.out.println(LINE);
//                System.out.println(e.getMessage());
//                System.out.println(LINE);
//                input = scanner.nextLine();
//                continue;
//            }
//            System.out.println(LINE);
//            switch (command) {
//                case LIST:
//                    System.out.println("    Here are the tasks in your tasks:");
//                    for (int i = 0; i < tasks.size(); i++) {
//                        String message = String.format("    %d. %s", i + 1, tasks.get(i).toString());
//                        System.out.println(message);
//                    }
//                    break;
//                case MARK:
//                    //need to handle if no index given
//                    //need to handle if already marked
//                    try {
//                        int index1 = updateList(true, input);
//                        updateFile();
//                        System.out.println("    Good job! I've marked this task as done:");
//                        System.out.println("       " + tasks.get(index1).toString());
//                    } catch(IllegalArgumentException e) {
//                        System.out.println("    Task selected not found in tasks");
//                    }
//                    break;
//                case UNMARK:
//                    try {
//                        int index2 = updateList(false, input);
//                        updateFile();
//                        System.out.println("    OK, I've marked this task as not done yet:");
//                        System.out.println("       " + tasks.get(index2).toString());
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("    Task selected not found in tasks");
//                    }
//                    break;
//                case TODO:
//                    try {
//                        Task task = new ToDo(getDetails(input));
//                        printTaskInfo(task);
//                        appendToFile(task);
//                    } catch (MissingDescriptionException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case DEADLINE:
//                    String[] temp = input.split("/by ");
//                    try {
//                        String details = getDetails(temp[0]);
//                        String dueDate = processDate(true, true, temp);
//                        LocalDate ld = LocalDate.parse((dueDate));
//                        Task task = new Deadline(details, ld);
//                        printTaskInfo(task);
//                        appendToFile(task);
//                    } catch (MissingDescriptionException e) {
//                        System.out.println(e.getMessage());
//                    } catch (InvalidDateException e) {
//                        System.out.println(e.getMessage());
//                    } catch (DateTimeParseException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case EVENT:
//                    try {
//                        String[] temp2 = input.split("/from ");
//                        String details = getDetails(temp2[0]);
//                        String start = processDate(false, false, temp2);
//                        String end = processDate(false, true, temp2);
//                        Task task = new Event(details, LocalDate.parse(start),
//                                LocalDate.parse(end));
//                        printTaskInfo(task);
//                        appendToFile(task);
//                    } catch (MissingDescriptionException e) {
//                        System.out.println(e.getMessage());
//                    } catch (InvalidDateException e) {
//                        System.out.println(e.getMessage());
//                    } catch (DateTimeParseException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case DELETE:
//                    int index3 = Integer.valueOf(input.split(" ")[1]) - 1;
//                    try {
//                        Task task = delete(index3);
//                        System.out.println("    Noted. I've removed this task:");
//                        System.out.println("        " + task.toString());
//                        String word = tasks.size() == 1 ? "task" : "tasks";
//                        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.size(), word));
//                        updateFile();
//
//                    } catch (IllegalArgumentException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case BYE:
//                    botIsActive = false;
//                    System.out.println("    " +"Bye!!! Thanks for chatting!");
//                    break;
//            }
//            System.out.println(LINE);
//            if (botIsActive) {
//                input = scanner.nextLine();
//            }
//        }
//    }
//
//    /**
//     *
//     * @param complete boolean value that represents whether task is completed
//     * @param input String value of input typed by user
//     * @throws IllegalArgumentException
//     */
//    public static int updateList(boolean complete, String input) throws IllegalArgumentException {
//        int index = Integer.valueOf(input.toLowerCase().split(" ")[1]) - 1;
//        if (index < 0 || index >= tasks.size()) {
//            throw new IllegalArgumentException();
//        } else if(complete) {
//            tasks.get(index).markCompleted();
//        } else {
//            tasks.get(index).markUncompleted();
//        }
//        return index;
//    }
//    /**
//     *
//     * @param mixed String that consists of action and description
//     * @return Only the description but with white space in front
//     * @throws MissingDescriptionException
//     */
//    public static String getDetails(String mixed) throws MissingDescriptionException {
//        String[] temp = mixed.split(" ");
//        if (temp.length <= 1) {
//            throw new MissingDescriptionException("    Please key in description for your task");
//        }
//        temp[0] = "";
//        return removeSpace(temp);
//    }
//
//    /**
//     *
//     * @param isDeadline To indicate whether it has one or two dates
//     * @param isEnd To indicate whether to return first date or second
//     * @param array An array of strings split using delimter
//     * @return the corresponding date portion of string
//     * @throws InvalidDateException
//     */
//    public static String processDate(boolean isDeadline, boolean isEnd,
//        String[] array) throws InvalidDateException {
//        if (array.length <= 1) {
//            String type = isDeadline ? " /by" : " /from .... /to";
//            throw new InvalidDateException("    Please provide a date using" + type);
//        } else if (!isDeadline) {
//            array = array[1].split(" /to ");
//            if (array.length <= 1) {
//                throw new InvalidDateException("    Please provide a deadline using /to");
//            }
//        }
//        return isEnd ? array[1] : array[0];
//    }
//
//    /**
//     *
//     * @param index Position of element to remove
//     * @throws IllegalArgumentException
//     */
//    public static Task delete(int index) throws IllegalArgumentException {
//        if (tasks.size() <= 0 || index < 0 || index > tasks.size()) {
//            throw new IllegalArgumentException("Task not found in tasks");
//        }
//        Task task = tasks.remove(index);
//        return task;
//    }
//
//    /**
//     *
//     * @param action String representation of commands
//     * @return A command enum object
//     * @throws InvalidCommandException
//     */
//    public static Command getCommand(String action) throws InvalidCommandException {
//        String cmd = action.toLowerCase();
//        if (cmd.equals("list")) {
//            return Command.LIST;
//        } else if (cmd.equals("mark")) {
//            return Command.MARK;
//        } else if (cmd.equals("unmark")) {
//            return Command.UNMARK;
//        } else if (cmd.equals("todo")) {
//            return Command.TODO;
//        } else if (cmd.equals("event")) {
//            return Command.EVENT;
//        } else if (cmd.equals("deadline")) {
//            return Command.DEADLINE;
//        } else if (cmd.equals("delete")) {
//            return Command.DELETE;
//        } else if (cmd.equals("bye")){
//            return Command.BYE;
//        } else {
//            throw new InvalidCommandException("    Sorry, I do not understand you \n" +
//                    "    as this is not a valid command");
//        }
//    }
//
//    /**
//     * Abstraction to remove duplicated code
//     * @param task Task to be added into tasks
//     */
//    public static void printTaskInfo(Task task) {
//        task.toString();
//        System.out.println("    Got it. I've added this task:");
//        Bimo.tasks.add(task);
//        System.out.println("        " + task.toString());
//        String word = tasks.size() == 1 ? "task" : "tasks";
//        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.size(), word));
//    }
//
//    /**
//     * Format the type of task, status, description and dates into
//     * text format type|status|description|date1/date2
//     * Split by | , if length == 2 means todo,
//     * if length == 3 split by /, if length == 2 means event
//     * @param task Task object inside tasks
//     * @return Formatted text that can be written into data file
//     */
//    public static String convertTaskToText(Task task) {
//        String text = "";
//        String description = task.getTaskText();
//        if (task instanceof ToDo) {
//            text = "T|" + description;
//        } else if (task instanceof Deadline) {
//            //safe to cast since type checking is done
//            text = "D|" + description + ((Deadline) task).getDateAsText();
//        } else {
//            text = "E|" + description + ((Event) task).getDatesAsText();
//        }
//        return text;
//    }
//
//    /**
//     *
//     * @param input Array of words formed from splitting by " "
//     * @return String with empty space removed in front
//     */
//    public static String removeSpace(String[] input) {
//        String [] temp = new String[input.length - 1];
//        for (int i = 1; i < input.length; i++) {
//            temp[i - 1] = input[i];
//        }
//        return String.join(" ", temp);
//    }
//
//    /**
//     * Converts description of task to text in the form
//     * type|status|description|date1|date2
//     * @param task Task inside the tasks
//     */
//    public static void appendToFile(Task task) {
//        String text = convertTaskToText(task) + System.lineSeparator();
//        try {
//            FileWriter writer = new FileWriter("ip/data/Bimo.txt", true);
//            writer.write(text);
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("Enable to write to file for task " + task.toString());
//        }
//    }
//
//    /**
//     * To re-write file when user marks or unmarks a task
//     */
//    public static void updateFile() {
//        try {
//            FileWriter writer = new FileWriter("ip/data/Bimo.txt");
//            for (int i = 0; i < tasks.size(); i++) {
//                String text = convertTaskToText(tasks.get(i));
//                writer.write(text + System.lineSeparator());
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("Enable to write to file");
//        }
//    }
//
//    /**
//     * Converts line of text in Bimo.txt to its corresponding Task object
//     * @param text line of code inside Bimo.txt
//     * @return Task object
//     */
//    public static Task convertTextToTask(String text) {
//        String[] details = text.split("\\|");
//        String type = details[0];
//        Task task= null;
//
//        boolean status = details[1].equals("0") ? false : true;
//        if (type.equals("T")) {
//            task = new ToDo(details[2]);
//        } else if (type.equals("D")) {
//            task = new Deadline(details[2], LocalDate.parse(details[3]));
//        } else if (type.equals("E")) {
//            String[] dates = details[3].split("/");
//            task = new Event(details[2], LocalDate.parse(dates[0]), LocalDate.parse(dates[1]));
//        }
//       if (status) {
//           task.markCompleted();
//       }
//       return task;
//    }
//}
