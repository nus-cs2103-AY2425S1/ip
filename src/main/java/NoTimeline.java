public class NoTimeline extends ToothlessExceptions{

    public NoTimeline(String taskType, String instruction) {
        super("The timing for " + taskType +  "is not specified! :o\n" +
                "Please enter a valid timeline.\n\n" +
                "The format for " + taskType + " is: " + instruction + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }
}
