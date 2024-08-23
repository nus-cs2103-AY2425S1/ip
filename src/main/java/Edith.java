import java.util.*;

public class Edith {
    private static final String horizontal = "____________________________________________________________";
    private static final String greeting = " heyyy im edith!\n what can I do for you?";
    private static final String farewell = " bye!! see you soon love <3";
    private static final String linebreak = "\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList();

        // print out greeting when bot first starts up
        System.out.println(horizontal + linebreak +
                greeting + linebreak +
                horizontal);

        String command = scanner.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(command, "bye")) {
            List<String> words = Arrays.asList(command.split(" "));

            if (Objects.equals(command, "list")) { // check if user wants the todo list
                System.out.println(horizontal + linebreak +
                        " tasks in your todo list!" + linebreak +
                        todoList.toString() +
                        horizontal);
                command = scanner.nextLine();
            }

            else if (Objects.equals(words.get(0), "mark") || Objects.equals(words.get(0), "unmark")) { // check if user wants to mark a task
                try {
                    changeTaskStatus(words, todoList);
                } catch (InvalidTaskNumberException | MissingTaskNumberException e) {
                    System.out.println(horizontal + linebreak +
                            e.getMessage() + linebreak +
                            horizontal);
                } finally {
                    command = scanner.nextLine();
                }
            }

            else if (Objects.equals(words.get(0), "todo") || Objects.equals(words.get(0), "deadline") ||
                    Objects.equals(words.get(0), "event")) {
                try {
                    addTask(command, words.get(0), todoList);
                } catch (MissingTaskNameException | MissingDeadlineException | MissingEventDurationException e) {
                    System.out.println(horizontal + linebreak +
                            e.getMessage() + linebreak +
                            horizontal);
                } finally {
                    command = scanner.nextLine();
                }
            }

            else {
                try {
                    otherCommand();
                } catch (InvalidCommandException e) {
                    System.out.println(horizontal + linebreak +
                            e.getMessage() + linebreak +
                            horizontal);
                } finally {
                    command = scanner.nextLine();
                }
            }

        }

        System.out.println(horizontal + linebreak + farewell + linebreak + horizontal);
    }

    public static void changeTaskStatus(List<String> commands, ToDoList toDoList) throws InvalidTaskNumberException,
            MissingTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(commands.get(1));
            if (Objects.equals(commands.get(0), "mark")) { // check if user wants to mark a task
                toDoList.mark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(horizontal + linebreak +
                        " " + "yay! i've marked this task as done #productive:" + linebreak +
                        " " + toDoList.getTask(taskNumber) + linebreak +
                        horizontal);
            }

            else { // unmarking a task
                toDoList.unmark(taskNumber); // may throw InvalidTaskNumberException
                System.out.println(horizontal + linebreak +
                        " " + "aw, i've marked this task as undone:" + linebreak +
                        " " + toDoList.getTask(taskNumber) + linebreak +
                        horizontal);
            }
        } catch (ArrayIndexOutOfBoundsException e) { // if there is a missing task number
            throw new MissingTaskNumberException();
        }
    }

    public static void addTask(String fullCommand, String firstCommand, ToDoList toDoList)
            throws MissingTaskNameException, MissingDeadlineException, MissingEventDurationException {
        // System.out.println("addTask block running");
        Task task = null;

        if (Objects.equals(firstCommand, "todo")) {
            // System.out.println("todo block running");
            List<String> commandToArray = Arrays.asList(fullCommand.split("todo "));
            // System.out.println(commandToArray);
            try {
                String taskName = commandToArray.get(1);
                task = new ToDoTask(taskName);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingTaskNameException();
            }
        }

        if (Objects.equals(firstCommand, "deadline")) {
            // System.out.println("deadline block running");
            List<String> commandToArray = Arrays.asList(fullCommand.split("deadline "));
            // System.out.println(commandToArray);
            try {
                String taskAndDeadline = commandToArray.get(1);
                try {
                    String taskName = taskAndDeadline.split(" /by ")[0];
                    String deadline = taskAndDeadline.split(" /by ")[1];
                    task = new DeadlineTask(taskName, deadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingDeadlineException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingTaskNameException();
            }
        }

        if (Objects.equals(firstCommand, "event")) {
            // System.out.println("event block running");
            List<String> commandToArray = Arrays.asList(fullCommand.split("event "));
            // System.out.println(commandToArray);
            try {
                String taskAndDuration = commandToArray.get(1);
                try {
                    List<String> taskAndDurationArray = Arrays.asList(taskAndDuration.split(" /from "));
                    // System.out.println(taskAndDurationArray);
                    String taskName = taskAndDurationArray.get(0);
                    String duration = taskAndDurationArray.get(1);
                    List<String> durationArray = Arrays.asList(duration.split(" /to "));
                    // System.out.println(durationArray);
                    String start = durationArray.get(0);
                    String end = durationArray.get(1);
                    task = new EventTask(taskName, start, end);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingEventDurationException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingTaskNameException();
            }
        }

        toDoList.add(task);
        System.out.println(horizontal + linebreak +
                " " + "nice! i've added this task:" + linebreak +
                " " + task.toString() + linebreak +
                " there are currently " + toDoList.getNumberofTasks() + " tasks in your todo list." + linebreak +
                horizontal);

    }

    public static void otherCommand() throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
