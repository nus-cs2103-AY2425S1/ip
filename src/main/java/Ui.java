public class Ui {
    private static final String DIVIDER = "---------------------------------------------------";
    private static  final String GREETING = "Hello! I'm Nebula, what can I do for you today?";
    private static  final String GOODBYE = "Bye! Hope to see you again soon :)";

    /**
     * Returns greeting message
     */
    public String greeting() {
        return DIVIDER + "\n" + GREETING + "\n" + DIVIDER;
    }

    /**
     * Returns goodbye message
     */
    public String goodbye() {
        return DIVIDER + "\n" + GOODBYE + "\n" + DIVIDER;
    }

    /**
     * Returns a copy of the message typed by the user
     */
    public String echo(String echoMessage) {
        return DIVIDER + "\n" + "added: " + echoMessage + "\n" + DIVIDER;
    }

    public String displayList() {
        String displayList = DIVIDER + "\n";
        int taskLength = TaskList.getTaskListLength();
        Task[] list = TaskList.getTaskList();
        if(taskLength == 0) {
            return displayList + "Your task list is empty!" + "\n" + DIVIDER;
        }
        for (int i = 0; i < taskLength; i++) {
            displayList += (i + 1) + ". " + list[i].getDescription() + "\n";
            if(i == taskLength - 1) {
                displayList += DIVIDER;
                break;
            }
        }
        return displayList;
    }
}
