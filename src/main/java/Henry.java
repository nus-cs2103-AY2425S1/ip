import java.util.Scanner;

public class Henry {

    /**
     * Prints greetings
     *
     */
    public static void greetings() {
        String greetings = "Hello! I'm Henry\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    /**
     * Prints exit
     *
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }

    /**
     * Prints all the tasks recorded
     *
     * @param tasks array of tasks recorded
     * @param index number of tasks recorded
     */
    public static void printList(Task[] tasks, int index) throws HenryException {
        if (index == 0) {
            throw new HenryException("You do not have any tasks!");
        }
        System.out.println("\nHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1
                    +"."
                    + tasks[i].toString());
        }
        System.out.println();
    }

    /**
     * Adds new task into the array of tasks recorded and split them into todo, event and deadline
     *
     * @param tasks array of tasks recorded
     * @param index number of tasks recorded
     * @param input name of task
     */
    public static void addTask(Task[] tasks, int index, String input) throws HenryException  {
        String[] words = input.split(" ");
        String task = words[0].toLowerCase();
        String activityAndTime = input.replaceFirst(words[0] + " ", "");
        String[] activityAndTimeList = activityAndTime.split(" /");
        String activity = activityAndTimeList[0];
        if (task.equals("todo")) {
            if (words.length == 1 ) {
                throw new HenryException("The todo description is wrong!! " +
                        "Ensure that you have included the activity. " +
                        "Example: todo read book");
            }
            tasks[index] = new Todo(activity);
        } else if (task.equals("deadline")) {
            if (activityAndTimeList.length != 2 ) {
                throw new HenryException("The deadline description is wrong!! " +
                        "Ensure that you have included the activity, followed by the deadline. " +
                        "Example: deadline return book /by Sunday");
            }
            String time = activityAndTimeList[1]
                    .replaceFirst("by ", "");
            tasks[index] = new Deadline(activity, time);
        } else if (task.equals("event")) {
            if (activityAndTimeList.length != 3 ) {
                throw new HenryException("The event description is wrong!! " +
                        "Ensure that you have included the activity, " +
                        "followed by the start time and end time. " +
                        "Example: event project meeting /from Mon 2pm /to 4pm");
            }
            String startTime = activityAndTimeList[1]
                    .replaceFirst("from ", "");
            String endTime = activityAndTimeList[2]
                    .replaceFirst("to ", "");
            tasks[index] = new Event(activity, startTime, endTime);
        } else {
            throw new HenryException("This is not a task!! " +
                    "To write a task, start with "
                    + "\"" + "todo" +"\","
                    + " \"" + "deadline" +"\" or"
                    + " \"" + "event" +"\"");
        }
        System.out.println("\nGot it. I've added this task:\n"
                + tasks[index].toString()
                + "\nNow you have "
                + (index + 1)
                + (index + 1 <= 1 ? " task" : " tasks")
                + " in the list.\n");
    }

    /**
     * Changes status of task
     *
     * @param tasks array of tasks recorded
     * @param words user input
     */
    public static void changeTaskStatus(Task[] tasks, String[] words) throws HenryException {
        int number = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            if (tasks[number - 1].isDone()) {
                throw new HenryException("The task is already marked!");
            }
            tasks[number - 1].mark();
            System.out.println("\nNice! I've marked this task as done:\n"
                    + tasks[number - 1].toString()
                    + "\n");
        } else {
            if (!tasks[number - 1].isDone()) {
                throw new HenryException("The task is already unmarked!");
            }
            tasks[number - 1].unmark();
            System.out.println("\nOK, I've marked this task as not done yet:\n"
                    + tasks[number - 1].toString()
                    + "\n");
        }
    }

    public static void main(String[] args) {
        greetings();

        Task[] tasks = new Task[100];

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        do {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println();
                bye();
                break;
            } else if (input.equals("list")) {
                try{
                    printList(tasks, index);
                } catch (HenryException e) {
                    System.out.println("\nSorry! " + e.getMessage() + "\n");
                }
            } else {
                String[] words = input.split(" ");
                if (words[0].equals("mark") || words[0].equals("unmark")) {
                    try {
                        changeTaskStatus(tasks, words);
                    } catch (HenryException e) {
                        System.out.println("\nSorry! " + e.getMessage() + "\n");
                    }
                } else {
                    try {
                        addTask(tasks, index, input);
                        index++;
                    } catch (HenryException e) {
                        System.out.println("\nSorry! " + e.getMessage() + "\n");
                    }
                }
            }
        } while (true);
    }
}
