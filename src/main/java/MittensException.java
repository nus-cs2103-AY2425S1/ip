public class MittensException extends Exception {
    private final String mittensMessage;
    private final String helpMessage;
    
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
