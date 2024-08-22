import java.util.Scanner;

public class Bro {
    final static String GREETING_MESSAGE = """
                 Hello! I'm Bro
                 What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String ADD_TODO_COMMAND = "todo";
    final static String ADD_DEADLINE_COMMAND = "deadline";
    final static String ADD_EVENT_COMMAND = "event";

    public static void main(String[] args) {
        reply(GREETING_MESSAGE);
        TaskList taskList = new TaskList();
        // Start conversation
        Scanner sc = new Scanner(System.in);
        boolean isConversing = true;
        while (isConversing) {
            // Read from standard input
            String input = sc.nextLine();
            String[] inputArgs = input.split(" ", 2);
            String cmd = inputArgs[0];
            String secondArg = "";
            if (inputArgs.length > 1) {
                secondArg = inputArgs[1];
            }

            switch (cmd) {
                case EXIT_COMMAND:
                    isConversing = false;
                    break;
                case LIST_COMMAND:
                    taskList.printAllTasks();
                    break;
                case MARK_COMMAND:
                    try {
                        int taskId = Integer.parseInt(secondArg);
                        Task task = taskList.markTask(taskId);
                        reply("Nice bro! I've marked this task as done:\n" + task);
                        break;
                    } catch (Exception e) {
                        reply("Error: " + e.getMessage());
                        break;
                    }
                case UNMARK_COMMAND:
                    try {
                        int taskId = Integer.parseInt(secondArg);
                        Task task = taskList.unmarkTask(taskId);
                        reply("Ok bro! I've marked this task as undone:\n" + task);
                        break;
                    } catch (Exception e) {
                        reply("Error: " + e.getMessage());
                        break;
                    }
                case ADD_TODO_COMMAND:
                    if (secondArg.isEmpty()) {
                        reply("Bro I can't add a empty task");
                        break;
                    }
                    Task todoTask = taskList.addTask(new TodoTask(secondArg));
                    addTaskReply(todoTask, taskList.getNumberOfTask());
                    break;
                case ADD_DEADLINE_COMMAND:
                    // Input validation
                    if (secondArg.isEmpty()) {
                        reply("Bro I Can't add a empty task");
                        break;
                    }
                    if (!secondArg.contains("/by")) {
                        reply("Wrong usage of deadline command");
                        break;
                    }
                    String[] deadlineInputs = secondArg.split("/by");
                    String taskContent = deadlineInputs[0].trim();
                    String deadline = deadlineInputs[1].trim();

                    Task deadlineTask = taskList.addTask(new DeadlineTask(taskContent, deadline));
                    addTaskReply(deadlineTask, taskList.getNumberOfTask());
                    break;
                case ADD_EVENT_COMMAND:
                    // Input validation
                    if (secondArg.isEmpty()) {
                        reply("Bro I Can't add a empty task");
                        break;
                    }
                    if (!secondArg.contains("/from") || !secondArg.contains("/to")) {
                        reply("Wrong usage of event command");
                        break;
                    }

                    try {
                        String[] eventInputs = secondArg.split("/from");
                        String eventName = eventInputs[0].trim();

                        String[] durationInputs = eventInputs[1].split("/to");
                        String startTime = durationInputs[0].trim();
                        String endTime = durationInputs[1].trim();
                        Task eventTask = taskList.addTask(new EventTask(eventName, startTime, endTime));
                        addTaskReply(eventTask, taskList.getNumberOfTask());
                        break;
                    } catch (Exception e) {
                        reply("Error: " + e.getMessage());
                        break;
                    }
                default:
                    reply("I don't respond to that.");
            }
        }
        reply(GOODBYE_MESSAGE);
    }

    // Prints an adds to list on standard output
    public static void reply(String content) {
        String replyStr = String.format("""
                ____________________________________________________________
                %s
                Bro
                ____________________________________________________________
                """, content);
        System.out.print(replyStr);
    }

    // Prints an add task reply
    public static void addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
    }
}
