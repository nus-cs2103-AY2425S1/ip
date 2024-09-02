/*
import java.util.ArrayList;
import java.util.List;
*/

/**
 * Enum showing user actions in the Task Manager.
 */
/*
public enum UserInput {
    ADD_TODO,
    ADD_DEADLINE,
    ADD_EVENT,
    DELETE_TASK,
    LIST,
    MARK,
    UNMARK,
    BYE;
}
*/

/**
 * This class manages the tasks and processes user actions.
 */
/*
public class TaskManager {
    private static final List<Task> THINGS_TO_DO = new ArrayList<>();
*/
    /**
     * This method adds a horizontal separator line consisting of underscores.
     */
/*
    private static void addLine() {
        System.out.println("______________________________________");
    }

    public static void processUserInput(UserInput userInput, String taskDescription) {
        switch (userInput) {
            case ADD_TODO:
                // Add the TodoTask to the thingsToDo list
                Todo newTodo = new Todo(userInput.toString());
                THINGS_TO_DO.add(newTodo);

                // Inform the user that the task has been added
                addLine();
                System.out.println("Got it, I've added this task:\n  " + newTodo.toString() + "\nNow you have " +
                        THINGS_TO_DO.size() + " tasks in the list.");
                addLine();
                break;
            case ADD_DEADLINE:
                // Add the DeadlineTask to the thingsToDo list
                Deadline newDeadline = new Deadline(userInput);
                THINGS_TO_DO.add(newDeadline);

                // Inform the user that the task has been added
                addLine();
                System.out.println("Got it, I've added this task:\n  " + newDeadline.toString() + "\nNow you have " +
                        THINGS_TO_DO.size() + " tasks in the list.");
                addLine();
                break;
            case ADD_EVENT:
                // Add the EventTask to the thingsToDo list
                Todo newEvent = new Event(userInput);
                THINGS_TO_DO.add(newEvent);

                // Inform the user that the task has been added
                addLine();
                System.out.println("Got it, I've added this task:\n  " + newEvent.toString() + "\nNow you have " +
                        THINGS_TO_DO.size() + " tasks in the list.");
                addLine();
                break;
            case DELETE_TASK:
                // Find the Task in the thingsToDo list
                int taskNum = Character.getNumericValue(userInput.charAt(7));

                // Inform the user that the task has been deleted
                addLine();
                System.out.println("Alright, I've removed this task:\n  " + THINGS_TO_DO.get(taskNum - 1).toString() + "\nNow you have " +
                        (THINGS_TO_DO.size() - 1) + " tasks in the list.");
                addLine();

                // Delete the Task from the thingsToDo list
                THINGS_TO_DO.get(taskNum - 1).delete();
                break;
            case LIST:
                addLine();
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task : tasks) {
                    System.out.println(i + "." + task.toString());
                    i++;
                }
                addLine();
                break;
            case MARK:
                // Mark the Task in the thingsToDo list as done
                int taskNum = Character.getNumericValue(userInput.charAt(5));
                THINGS_TO_DO.get(taskNum - 1).done();

                // Inform the user that the task has been marked
                addLine();
                System.out.println("Great! I've marked it as done:\n  " + THINGS_TO_DO.get(taskNum - 1).toString());
                addLine();
                break;
            case UNMARK:
                // Unmark the Task in the thingsToDo list
                int taskNum = Character.getNumericValue(userInput.charAt(7));
                THINGS_TO_DO.get(taskNum - 1).notDone();

                // Inform the user that the task has been marked
                addLine();
                System.out.println("Alright, I've unchecked this task:\n  " + THINGS_TO_DO.get(taskNum - 1).toString());
                addLine();
                break;
            case BYE:
                // Closes the program and says goodbye to the user

                break;
            default:
                throw new IllegalArgumentException("Sorry, I'm unable to perform this action: " + userInput);
        }
    }
}
*/