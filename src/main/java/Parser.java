public class Parser {
    public String[] parse(String userInput) {
        return userInput.split(" ", 2); // Splits input into command and the rest
    }
}
