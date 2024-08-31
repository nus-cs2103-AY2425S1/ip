package wansbot.ui;

import wansbot.tasks.Task;
import wansbot.tasks.TaskList;

public class UI {
    private static final String HR = "----------------------------------------------------------------------";

    public UI() {
    }

    // Method that abstracts Bot message when bot first starts
    public void introduceToUser() {
        String logo = "                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        System.out.println(HR + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + HR);
    }

    // method that tells user that bot does not understand
    public void handleUnrecognisedInput(String userInput) {
        System.out.println(HR + "\nWans: \n"
                + "I'm sorry I'm not that useful I don't know what "
                + userInput + " means!!!" + "\n" + HR);
    }

    // handle when wrong date format is used
    public void handleDateTimeException() {
        System.out.println(HR + "\nWans:\n"
                + "Your date needs to be in the form YYYY-MM-DD! It needs to be a valid \ndate too please!"
                + "\n" + HR);
    }

    // handles wrong deadline format
    public void handleDeadlineFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input deadline, followed by /by, then the deadline!"
                + "\n" + HR);
    }

    // handles wrong event format
    public void handleEventFormat() {
        System.out.println(HR + "\nWans:\n"
                + "You need to input event, followed by /from, then your start time, then /to, then " +
                "your end time!"
                + "\n" + HR);
    }

    // lists task to user
    public void handleListingTask(TaskList taskList) {
        System.out.println(HR + "\nWans:"
                + "\nHere are your tasks!\n"
                + taskList.toString());
            System.out.println("You have "+ taskList.numOfTasks() +" tasks!"+"\n"+HR);
    }

    // says goodbye to user and exits the program
    public void handleGoodbye() {
        String exit = "|  _ \\ \\   / /  ____|"
                + "\n| |_) \\ \\_/ /| |__"
                + "\n|  _ < \\   / |  __|"
                + "\n| |_) | | |  | |____"
                + "\n|____/  |_|  |______";
        System.out.println(HR + "\nWans: \n"
                + exit
                + "\nI'll miss you :( (I really wanna go home)\n" + HR);
        System.exit(0);
    }
}
