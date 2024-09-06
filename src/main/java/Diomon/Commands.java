package Diomon;

public class Commands {
    private boolean exit;
    public enum Types {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        BYE,
        HELP,
        DELETE,
        FIND,
    }

    public Commands() {
        this.exit = false;
    }

    public boolean isExit() {
        return exit;
    }

    public static Types checkType(String command) {
        for(Types t : Types.values()) {
            if (t.name().equalsIgnoreCase(command)) return t;
        }
        throw new RuntimeException();
    }

    public void runCommand(Types t, String input, TaskList taskList) {

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
                throw new RuntimeException("Missing argument/ Function not implemented");
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
            case DELETE:
                delete(taskList, input);
                break;
            case FIND:
                runFind(taskList, input);
                break;
            default:
                throw new RuntimeException("Unknown argument/ Function not implemented yet");
                }
            }
    }

    public void run(String input, TaskList taskList) {
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
    public void todo(TaskList taskList, String input) {
        Task newTask = Task.of(input, Task.TaskType.TODO);
        taskList.add(newTask);
        System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
        System.out.print(taskList);
    }
    public void deadline(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.DEADLINE);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }

    }
    public void event(TaskList taskList, String input) {
        try {
            Task newTask = Task.of(input, Task.TaskType.EVENT);
            taskList.add(newTask);
            System.out.printf("New Diomon.Task: [%s] has been added.\n", newTask);
            System.out.print(taskList);
        } catch (Exception e) {
            System.out.println("Incorrect/ missing details given");
        }
    }
    public void list(TaskList taskList) {
        System.out.println("Diomon.TaskList:");
        System.out.print(taskList);
    }
    public void mark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been marked", i, taskList.get(i - 1));
            taskList.mark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }
    public void unmark(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been unmarked\n", i, taskList.get(i - 1));
            taskList.unmark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }
    public void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        this.exit = true;
    }
    public void help() {
        String helpMessage = "Diomon.Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP";
        System.out.print(helpMessage);
    }
    public void delete(TaskList taskList, String input) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("Diomon.Task %d: [%s] has been deleted", i, taskList.get(i - 1));
            taskList.remove( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, please try again");
        }
    }

    public void runFind(TaskList taskList, String input) {
        try {
            System.out.println("Here is the search result:");
            System.out.println(taskList.fuzzyFind(input));
        } catch (RuntimeException e) {
            System.out.println("Something went wrong with the search");
        }
    }
}
