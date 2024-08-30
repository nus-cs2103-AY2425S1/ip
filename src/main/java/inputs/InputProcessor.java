package inputs;

public class InputProcessor {

    public InputProcessor() {};

    public ProcessedInput processInput(String input) {
        String[] inputArray = input.split(" ", 2);
        String action = inputArray[0];
        String details = inputArray.length > 1 ? inputArray[1].trim() : "";
/*        for (int i = 1; i < inputArray.length; i++) {
            details = details.concat(inputArray[i] + " ");
        }

        details = details.trim();*/

        return new ProcessedInput(action, details);

    }
}
