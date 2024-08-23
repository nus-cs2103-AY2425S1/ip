/**
 * InvalidInstructionsContentException is a class that encapsulates errors relating to instruction
 */
public class InvalidInstructionException extends Exception {
    String instruction;
    public InvalidInstructionException(String instruction) {
        super();
        this.instruction = instruction;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public String toString() {
        return this.getInstruction() + " is not a valid instruction!";
    }
}
