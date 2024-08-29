package inputs;

public class InputProcessor {

    public InputProcessor() {};

    public ProcessedInput processInput(String input) {
        String[] inputArray = input.split(" ");
        String action = inputArray[0];
        String details = "";

        for (int i = 1; i < inputArray.length; i++) {
            details = details.concat(inputArray[i] + " ");
        }

        details = details.trim();

        return new ProcessedInput(action, details);

    }
}
