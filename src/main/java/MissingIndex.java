public class MissingIndex extends ToothlessExceptions{

    public MissingIndex(String taskType, String instruction) {
        super("The index for " + taskType + " is missing! :o" +
                "Please enter a valid index.\n\n" +
                "The format for " + taskType + " is: " + instruction + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }
}
