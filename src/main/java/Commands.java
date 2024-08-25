public class Commands {
    public enum Types {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        BYE,
        HELP,
    }

    public static Types checkType(String command) {
        for(Types t : Types.values()) {
            if (t.name().equalsIgnoreCase(command)) return t;
        }
        throw new RuntimeException();
    }

    public static void runCommand(Types t, String input, TaskList taskList) {
        try {
            if (input == null) {
                switch (t) {
                    case LIST:
                        list(taskList);
                        break;
                    case BYE:
                        bye();
                        break;
                    case HELP:
                        help();
                        break;
                    default:
                        System.out.println("Missing argument/ Function not implemented");
                        break;
                }
            } else {
                switch (t) {
                    case TODO:
                        todo(taskList, input);
                        break;
                    case DEADLINE:
                        deadline(taskList, input);
                        break;
                    case EVENT:
                        event(taskList, input);
                        break;
                    case MARK:
                        mark(taskList, input);
                        break;
                    case UNMARK:
                        unmark(taskList, input);
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Command not correctly implemented yet");
        }
    }

    public static void run(String input, TaskList taskList) {
        // Process input
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputContent = inputArray.length == 1 ? null: inputArray[1];
        try {
            runCommand(checkType(inputCommand), inputContent, taskList);
        } catch (RuntimeException e){
            System.out.println("Command don't exist. Please retry. Type 'Help' for help");
        }

    }

    //Command Logic
    public static void todo(TaskList taskList, String input) {
        Task newTask = Task.of(input, Task.TaskType.TODO);
        taskList.add(newTask);
        System.out.printf("New Task: [%s] has been added.\n", newTask);
        System.out.print(taskList);
    }
    public static void deadline(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.DEADLINE);
            taskList.add(newTask);
            System.out.printf("New Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }

    }
    public static void event(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.EVENT);
            taskList.add(newTask);
            System.out.printf("New Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }
    }
    public static void list(TaskList taskList) {
        System.out.println("TaskList: ");
        System.out.print(taskList);
    }
    public static void mark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Task %d: [%s] has been marked", i, taskList.get(i - 1));
            taskList.mark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }
    public static void unmark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Task %d: [%s] has been unmarked", i, taskList.get(i - 1));
            taskList.unmark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }
    public static void bye() {
        String byeMessage = """
                            Bye. Hope to see you again soon!
                            ________________________________________________________________
                            """;
        System.out.println(byeMessage);
        System.exit(0);
    }
    public static void help() {
        String helpMessage = """
                             Commands:
                             -TODO
                             -DEADLINE
                             -EVENT
                             -LIST
                             -MARK
                             -UNMARK
                             -BYE
                             -HELP
                             """;
        System.out.print(helpMessage);
    }
}
