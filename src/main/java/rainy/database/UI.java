package rainy.database;

public class UI {
    public UI() {

    }

    public void welcomeMessage() {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("I am a digital assistant designed to help you keep track of your day.");
        System.out.println("So, what can I do for you today?");
    }

    public void startTracking() {
        System.out.println("Use me to track your ToDos/Deadlines/Events!");
    }

    public void noCategoryDeclared() {
        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) " +
                "before providing a description of it.");
    }

    public void noToDoDescription() {
        System.out.println("You need to provide a description of your ToDo task, please try again!");
    }

    public void noDeadlineDescription() {
        System.out.println("You need to provide a description of your Deadline, please try again!");
    }

    public void noEndDateDeadline() {
        System.out.println("Please provide an end date for your Deadline!");
    }

    public void invalidDateDeadline() {
        System.out.println("Please input a date in the format MM/DD/YYYY!");
    }

    public void noEventDescription() {
        System.out.println("You need to provide a description of your Event, please try again!");
    }

    public void invalidEventDate() {
        System.out.println("Please provide a proper date in MM/DD/YYYY format, as well as a start time and end time in HH:MM format for your Event!");
    }

    public void sortDone() {
        System.out.println("The task list has been sorted according to date!");
    }

    public void goodbyeMessage() {
        System.out.println("Goodbye! Have a nice day ahead!!");
    }

    public void noTasksAdded() {
        System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before marking/unmarking them.");
    }

    public void markedTask() {
        System.out.println("Well Done Champ! I've marked this task as done!");
    }

    public void unmarkedTask() {
        System.out.println("I've marked this task as undone! Please remember to complete it!");
    }

    public String invalidTask() {
        return "You have entered an invalid task number! Please try again.";
    }

    public void addedTask() {
        System.out.println("Gotcha!! Added this task to your list:");
    }

    public void noTasksBeforeDelete() {
        System.out.println("You have currently added ZERO tasks to your list! Try telling me some of your tasks before deleting them.");
    }

    public void removedTask() {
        System.out.println("Alright I have removed this task: ");
    }

    public String taskDone() {
        return "Task is already marked as done!";
    }

    public String taskNotDone() {
        return "Task is still undone!";
    }
}