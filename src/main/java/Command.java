public record Command(int commandCode, String[] commandDetails) {

    // Initialise method
    public static Command initialise(String[] input, int commandCode) {
        // Return a new Commands object with commandCode and commandDetails
        return new Command(commandCode, input);
    }
}
