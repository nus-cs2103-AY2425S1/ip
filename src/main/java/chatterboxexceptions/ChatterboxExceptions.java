package chatterboxexceptions;

public class ChatterboxExceptions {
    public static class ChatterBoxError extends Exception {
        public ChatterBoxError (String message) {
            super(message);
        }
    }
    public static class ChatterBoxNoInput extends ChatterBoxError {
        public ChatterBoxNoInput (String message) {
            super(message);
        }
    }

    public static class ChatterBoxUnknownCommand extends ChatterBoxError {
        public ChatterBoxUnknownCommand (String message) {
            super(message);
        }
    }

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