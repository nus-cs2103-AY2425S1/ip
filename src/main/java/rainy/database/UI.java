package rainy.database;

/**
 * Consolidates and provides the list of responses the Rainy chatbot provides for user inputs.
 */
public class UI {
    public UI() {

    }

    /**
     * Displays message when the chatbot starts.
     */
    public void welcomeMessage() {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("I am a digital assistant designed to help you keep track of your day.");
        System.out.println("So, what can I do for you today?");
    }

    /**
     * Displays message after welcome message is first displayed.
     */
    public void startTracking() {
        System.out.println("Use me to track your ToDos/Deadlines/Events!");
    }

    /**
     * Displays message if user does not specify the category of the task to the chabot.
     */
    public void noCategoryDeclared() {
        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) "
            + "before providing a description of it.");
    }

    /**
     * Displays message if user only provides the todo category and does not specify anything else.
     */
    public void noToDoDescription() {
        System.out.println("You need to provide a description of your ToDo task, please try again!");
    }

    /**
     * Displays message if user only provides the deadline category and does not specify anything else.
     */
    public void noDeadlineDescription() {
        System.out.println("You need to provide a description of your Deadline, please try again!");
    }

    /**
     * Displays message when the user does not provide an end date for the deadline.
     */
    public void noEndDateDeadline() {
        System.out.println("Please provide an end date for your Deadline!");
    }

    /**
     * Displays message when the user provides a date in the wrong format.
     */
    public void invalidDateDeadline() {
        System.out.println("Please input a date in the format MM/DD/YYYY!");
    }

    /**
     * Displays message if user only provides the event category and does not specify anything else.
     */
    public void noEventDescription() {
        System.out.println("You need to provide a description of your Event, please try again!");
    }

    /**
     * Displays message if the user provides an event date in an invalid format.
     */
    public void invalidEventDate() {
        System.out.println("Please provide a proper date in MM/DD/YYYY format, "
            + "as well as a start time and end time in HH:MM format for your Event!");
    }

    /**
     * Displays message when the user provides the sort command to a non-empty tasklist.
     */
    public void sortDone() {
        System.out.println("The task list has been sorted according to date!");
    }

    /**
     * Displays message when the user issues a bye command.
     */
    public void goodbyeMessage() {
        System.out.println("Goodbye! Have a nice day ahead!!");
    }

    /**
     * Displays message when user tries to mark or unmark tasks to an empty tasklist.
     */
    public void noTasksAdded() {
        System.out.println("You have currently added ZERO tasks to your list! "
                + "Try telling me some of your tasks before marking/unmarking them.");
    }

    /**
     * Displays message when user marks an unmarked task.
     */
    public void markedTask() {
        System.out.println("Well Done Champ! I've marked this task as done!");
    }

    /**
     * Displays message when user unmarks a marked task.
     */
    public void unmarkedTask() {
        System.out.println("I've marked this task as undone! Please remember to complete it!");
    }

    /**
     * Displays message when user issues an invalid task number.
     * @return  This messags is returned as a string due to the possubility of a thrown exception.
     */
    public String invalidTask() {
        return "You have entered an invalid task number! Please try again.";
    }

    /**
     * Displays message when user adds task.
     */
    public void addedTask() {
        System.out.println("Gotcha!! Added this task to your list:");
    }


    /**
     * Displays message when user tries to delete a task from an empty task list.
     */
    public void noTasksBeforeDelete() {
        System.out.println("You have currently added ZERO tasks to your list! "
                + "Try telling me some of your tasks before deleting them.");
    }

    /**
     * Displays message when user removes task from non-empty task list.
     */
    public void removedTask() {
        System.out.println("Alright I have removed this task: ");
    }

    /**
     * Displays message when user marks a marked task.
     * @return  This messags is returned as a string due to the possibility of a thrown exception.
     */
    public String taskDone() {
        return "Task is already marked as done!";
    }

    /**
     * Displays message when user unmarked an unmarked task.
     * @return  Displays message when user marks a marked task.
     */
    public String taskNotDone() {
        return "Task is still undone!";
    }
}
