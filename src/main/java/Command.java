public record Command(CommandTypes commandType, String[] commandDetails) {
    // Initialise method
    public static Command initialise(CommandTypes commandType, String[] input) {
        // Return a new Commands object with commandType and commandDetails
        return new Command(commandType, input);
    }
}
