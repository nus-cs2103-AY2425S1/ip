package echoa;

/**
 * InvalidInstructionsContentException is a class that encapsulates errors relating to instruction.
 * It extends from class EchoaException.
 */

public class InvalidInstructionException extends EchoaException {
    String instruction;
    public InvalidInstructionException(String instruction) {
        super();
        this.instruction = instruction;
    }

    public String getInstruction() {
        return this.instruction;
    }

    @Override
    public String getMessage() {
        String message = this.getInstruction() + " is not a valid command!\n" +
                            "Here are the valid instructions: \n";
        for (String i : Ui.INSTRUCTION_LIST) {
            message = message + "- " + i + "\n";
        }
        return message;
    }
}
