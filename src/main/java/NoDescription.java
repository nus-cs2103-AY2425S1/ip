public class NoDescription extends ToothlessExceptions{

    public NoDescription(String taskType, String instruction) {
        super("You need to have decription for your " + taskType + " task!\n" +
                "Please enter a valid description.\n\n" +
                "The format for " + taskType + " is: " + instruction + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }
}
