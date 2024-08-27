public class Parser {
    
    public static String extractCommand(String input) {
        return input.split(" ")[0];
    }
    
    public static String extractDescription(String input) {
        return input.split("/")[0].trim();
    }
    
    public static String extractFirstDate(String input) {
        String processing = input.split("/")[1];
        processing.replace("by ", "").trim();
        return processing.replace("from ", "").trim();
    }
    
    public static String extractSecondDate(String input) {
        String processing = input.split("/")[2];
        return processing.replace("to", "").trim();
    }
}
