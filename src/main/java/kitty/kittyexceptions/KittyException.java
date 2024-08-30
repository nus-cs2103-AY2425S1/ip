package kitty.kittyexceptions;

/**
 * Provides Exception for ChatBot.
 */
public class KittyException extends Exception{

    /**
     * Returns the String message for the Exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}


