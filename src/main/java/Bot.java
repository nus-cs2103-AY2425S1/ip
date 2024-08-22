import java.util.*;

public class Bot {
    private final String INDENT = "  ";
    private final String DIVIDER = "____________________________________________________________\n";
    private final List<String> TASK_TYPES = List.of(new String[]{"todo", "event", "deadline"});
    private ArrayList<Task> taskList = new ArrayList<>();

    public void taskNotFound(int taskNumber) {
        System.out.println(DIVIDER
                + "task " + taskNumber + " doesn't exist...try another number!\n"
                + DIVIDER);
    }

    public void acceptCommand() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            List<String> inputList = Arrays.asList(input.split(" "));
            String command = inputList.get(0).strip();
//            System.out.println("inputList size: " + inputList.size());
            String args = String.join(" ", inputList.subList(1, inputList.size())).strip();
//            System.out.println("command: " + command);
//            System.out.println("args: " + args);
//            Consider implementing the 1010X approach to calling functions
//            Watch the data directed programming lecture
            if (Objects.equals(command, "bye")) {
                System.out.println("""
                        ____________________________________________________________
                        yeah bye bye to you too human being
                        ____________________________________________________________
                        """);
                System.exit(0);
            } else if (Objects.equals(command, "list")) {
                showList();
            } else if (Objects.equals(command, "mark")
                    || Objects.equals(command, "unmark")) {
                // need to handle the case where a non-integer is passed to mark (NumberFormatException)
                int taskToMark = Integer.parseInt(args);
                try {
                    markTask(taskToMark, command);
                } catch (IndexOutOfBoundsException e) {
                    taskNotFound(taskToMark);
                }
            } else if (Objects.equals(command, "delete")) {
                int taskToDelete = Integer.parseInt(args);
                try {
                    deleteTask(taskToDelete);
                } catch (IndexOutOfBoundsException e) {
                    taskNotFound(taskToDelete);
                }
            } else {
                try {
                    addToList(command, args);
                } catch (UnknownCommandException e) {
                    System.out.println(DIVIDER
                            + "hmmm i didn't quite understand what you said. try again?\n"
                            + DIVIDER);
                } catch (NoDescriptionException e) {
                    String aOrAn = (Objects.equals(command, "event"))
                            ? " an "
                            : " a ";
                    System.out.println(DIVIDER
                            + "uhhh the description of" + aOrAn
                            + command + " can't be empty :( try again?\n"
                            + DIVIDER);
                }
            }
        }
    }

    public String listSizeUpdateMessage() {
        int listSize = taskList.size();
        if (listSize == 1) {
            return "your list has " + listSize + " item now.\n";
        } else {
            return "your list has " + listSize + " items now.\n";
        }
    }
    public void addToList(String command, String args) throws NoDescriptionException, UnknownCommandException {
        if (!(TASK_TYPES.contains(command))) {
            throw new UnknownCommandException();
        }
        if (Objects.equals(args, "")) {
            throw new NoDescriptionException();
        }
        if (Objects.equals(command, "todo")) {
            Todo todo = new Todo(args);
            taskList.add(todo);
            System.out.println(DIVIDER
                + "i've thrown this to-do into your task list:\n"
                + INDENT + todo.taskDescription() + "\n"
                + listSizeUpdateMessage()
                + DIVIDER);
        } else if (Objects.equals(command, "deadline")) {
            String[] taskAndDeadline = args.split(" /by ");
            String taskName = taskAndDeadline[0];
            String deadline = taskAndDeadline[1];
            Deadline dl = new Deadline(taskName, deadline);
            taskList.add(dl);
            System.out.println(DIVIDER
                    + "the new deadline's been added to your task list:\n"
                    + INDENT + dl.taskDescription() + "\n"
                    + listSizeUpdateMessage()
                    + DIVIDER);
        } else if (Objects.equals(command, "event")) {
            String[] taskAndTimings = args.split(" /from | /to ");
//            System.out.println(Arrays.toString(taskAndTimings));
            String taskName = taskAndTimings[0];
            String from = taskAndTimings[1];
            String to = taskAndTimings[2];
            Event event = new Event(taskName, from, to);
            taskList.add(event);
            System.out.println(DIVIDER
                    + "aaaaand this event is now in your task list:\n"
                    + INDENT + event.taskDescription() + "\n"
                    + listSizeUpdateMessage()
                    + DIVIDER);
        }
        acceptCommand();
    }

    public void showList() {
        System.out.println(DIVIDER
            + "here's everything that's in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i+1) + ". " + task.taskDescription());
        }
        System.out.println(DIVIDER);
        acceptCommand();
    }

    public void markTask(int taskToMark, String command) {
        Task task = taskList.get(taskToMark-1);
        task.changeMark();
        if (Objects.equals(command, "mark")) {
            System.out.println(DIVIDER
                + "ok i've marked this task as done:\n"
                + INDENT + task.taskDescription() + "\n"
                + DIVIDER);
        } else {
            System.out.println(DIVIDER
                    + "ok i've unmarked this task:\n"
                    + INDENT + task.taskDescription() + "\n"
                    + DIVIDER);
        }
    }

    public void deleteTask(int taskToDelete) throws IndexOutOfBoundsException{
        Task task = taskList.remove(taskToDelete-1);
        System.out.println(DIVIDER
                + "alright i've purged this task for you:\n"
                + INDENT + task.taskDescription() + "\n"
                + listSizeUpdateMessage()
                + DIVIDER);
    }
}
