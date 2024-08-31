package chatterboxexceptions;

/**
 * Exception class that includes subclasses if each of the possible exceptions
 */
public class ChatterboxExceptions {
    /**
     * basic ChatterboxError class
     */
    public static class ChatterBoxError extends Exception {
        public ChatterBoxError (String message) {
            super(message);
        }
    }

    /**
     * Exception class for missing input
     */
    public static class ChatterBoxNoInput extends ChatterBoxError {
        public ChatterBoxNoInput (String message) {
            super(message);
        }
    }

    /**
     * Exception class for unknown commands
     */
    public static class ChatterBoxUnknownCommand extends ChatterBoxError {
        public ChatterBoxUnknownCommand (String message) {
            super(message);
        }
    }

    /**
     * Exception class for missing parameters
     */
    public static class ChatterBoxMissingParameter extends ChatterBoxError {
        public ChatterBoxMissingParameter(String para) {
            super("Missing parameter: " + para);
        }
    }
    public static void checkMessage(String msg) throws ChatterBoxUnknownCommand{
        System.out.println("message is " + msg);
        throw new ChatterBoxUnknownCommand("Error: Unknown command");
    }
}