package joe;

import joe.command.Command;
import joe.task.TaskList;

public class Joe {
//    private static final ArrayList<Task> tasks = new ArrayList<>();
//    private static final String DATA_PATH = "./data/joe.txt";
//    private static void saveData() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(Joe.DATA_PATH));
//            for (Task task : Joe.tasks) {
//                writer.write(task.serialize());
//                writer.newLine();
//            }
//            writer.close();
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        }
//    }

//    private static void loadData() {
//        try {
//            File file = new File(Joe.DATA_PATH);
//            if (!file.exists() || !file.canRead()) {
//                return;
//            }
//            BufferedReader reader = new BufferedReader(new FileReader(Joe.DATA_PATH));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                String[] lineArr = line.split("\\|");
//                String taskType = lineArr[0];
//                boolean isDone = Boolean.parseBoolean(lineArr[1]);
//                String taskDesc = lineArr[2];
//                switch (taskType) {
//                    case "T" -> {
//                        Joe.tasks.add(new Todo(taskDesc, isDone));
//                    }
//                    case "D" -> {
//                        String deadlineBy = lineArr[3];
//                        Joe.tasks.add(new Deadline(taskDesc, deadlineBy, isDone));
//                    }
//                    case "E" -> {
//                        String eventFrom = lineArr[3];
//                        String eventTo = lineArr[4];
//                        Joe.tasks.add(new Event(taskDesc, eventFrom, eventTo, isDone));
//                    }
//                }
//            }
//        } catch (IOException x) {
//            System.err.format("IOException: %s%n", x);
//        }
//    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Joe(String filePath) {
       this.ui = new Ui();
       this.storage = new Storage(filePath);
       try {
           this.tasks = new TaskList(this.storage.loadData());
       } catch (JoeException e) {
           this.ui.printLoadingError();
           this.tasks = new TaskList();
       }
    }
    private void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JoeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Joe("./data/joe.txt").run();
    }
//    public static void mai(String[] args) {
////        Joe.loadData();
////        System.out.println("\t" + "Hello! I'm Joe");
////        System.out.println("\t" + "What can I do for you?");
//        new Joe("./data/joe.txt").run();
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("\t____________________________________________________________");
//            String userInput = scanner.nextLine();
//            String[] inputArr = userInput.split(" ");
//            if (inputArr.length == 0) continue;
//            String commandStr = inputArr[0].toUpperCase();
//            Commands command;
//            try {
//                command = Commands.valueOf(commandStr);
//            } catch (IllegalArgumentException e) {
//                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
//                continue;
//            }
//            switch (command) {
//                case BYE:
//                    Joe.saveData();
//                    System.out.println("\t" + "Bye. Hope to see you again soon!");
//                    return;
//                case LIST:
//                    if (tasks.isEmpty()) System.out.println("\t\uD83C\uDF89 No tasks yet! Looks like you've got a clean slate. Time to add some tasks and conquer the day! \uD83D\uDE80");
//                    for (int i = 0; i < tasks.size(); i++) {
//                        Task task = tasks.get(i);
//                        String msg = String.format("%d. %s", i + 1, task.toString());
//                        System.out.println("\t" + msg);
//                    }
//                    break;
//                case MARK:
//                case UNMARK:
//                case DELETE:
//                    int idx = Integer.parseInt(inputArr[1]); // gets the task index to mark or unmark
//                    if (idx > tasks.size() || idx < 1) { // check that task index is valid
//                        System.out.println("\t" + "Please input a valid task index");
//                        break;
//                    }
//                    Task task = tasks.get(idx - 1);
//                    switch (command) {
//                        case MARK:
//                            task.markDone();
//                            System.out.printf("\tNice! I've marked this task as done:\n\t  %s\n", task);
//                            break;
//                        case UNMARK:
//                            task.unmarkDone();
//                            System.out.printf("\tOK, I've marked this task as not done yet:\n\t  %s\n", task);
//                            break;
//                        case DELETE:
//                            tasks.remove(idx - 1);
//                            System.out.printf("\tNoted. I've removed this task:\n\t  %s\n", task);
//                            break;
//                    }
//                    break;
//                case TODO:
//                case DEADLINE:
//                case EVENT:
//                    Task newTask = null;
//                    switch (command) {
//                        case TODO:
//                            if (inputArr.length == 1) {
//                                System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
//                                continue;
//                            }
//                            newTask = new Todo(String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length)));
//                            break;
//                        case DEADLINE:
//                            int byIdx = Arrays.asList(inputArr).indexOf("/by");
//                            if (byIdx == -1) {
//                                System.out.println("\tOops! Try adding it like this: deadline {task description} /by {duedate}");
//                                continue;
//                            }
//                            String taskDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, byIdx));
//                            String taskBy = String.join(" ", Arrays.copyOfRange(inputArr, byIdx + 1, inputArr.length));
//                            newTask = new Deadline(taskDesc, taskBy);
//                            break;
//                        case EVENT:
//                            int fromIdx = Arrays.asList(inputArr).indexOf("/from");
//                            int toIdx = Arrays.asList(inputArr).indexOf("/to");
//                            if (fromIdx == -1) {
//                                System.out.println("\tOops! Let's try again with this format: event {task description} /from {start date} /to {end date}");
//                                continue;
//                            }
//                            String eventDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, fromIdx));
//                            if (toIdx != -1) {
//                                // to date exists
//                                String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, toIdx));
//                                String eventTo = String.join(" ", Arrays.copyOfRange(inputArr, toIdx + 1, inputArr.length));
//                                newTask = new Event(eventDesc, eventFrom, eventTo);
//                            } else {
//                                String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, inputArr.length));
//                                newTask = new Event(eventDesc, eventFrom, "MAX_DATE");
//                            }
//                            break;
//                    }
//                    tasks.add(newTask);
//                    System.out.println("\tGot it. I've added this task:\n\t  " + newTask);
//                    System.out.printf("""
//                                Now you have %d tasks in the list.
//                            %n""", tasks.size());
//            }
//        }
//    }
}
