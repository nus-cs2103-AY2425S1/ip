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
                         Ui.getListOfCommandsMessage();
        return message;
    }
}
