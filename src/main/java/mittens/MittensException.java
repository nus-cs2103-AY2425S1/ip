package mittens;

/**
 * Represents an exception specific to the Mittens program.
 */
public class MittensException extends Exception {

    /** The message to be displayed by the cute cat Mittens */
    private final String mittensMessage;
    
    /** The message to be displayed to help the user */
    private final String helpMessage;

    /**
     * Constructs a MittensException with the specified message.
     * 
     * @param message The message which describes the technical details of the exception.
     * @param mittensMessage The message to be displayed by the cute cat Mittens.
     * @param helpMessage The message to be displayed to help the user.
     */
    public MittensException(String message, String mittensMessage, String helpMessage) {
        super(message);
        this.mittensMessage = mittensMessage;
        this.helpMessage = helpMessage;
    }
    
    public String getMittensMessage() {
        return this.mittensMessage;
    }

    public String getHelpMessage() {
        return this.helpMessage;
    }

    public void echo() {
        String message = """
                                
                 /\\_/\\     %s
                 >x.x<    ( %s )
                  / \\      %s
                 (___)_/
                                
                Error: %s
                %s
                """.formatted("_".repeat(this.getMittensMessage().length() + 2),
                this.getMittensMessage(), "-".repeat(this.getMittensMessage().length() + 2),
                this.getMessage(), this.helpMessage);

        System.out.println(message);
    }
}
