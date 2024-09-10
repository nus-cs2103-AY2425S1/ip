package ai.exception;

/**
 * Creates exception when command has empty arguments.
 */
public class EmptyArgumentAiException extends AiException {
    public EmptyArgumentAiException(String command, String suggestion) {
        super(String.format("Whoopsies... %s cannot be empty >.< \n Try something like \"%s\" instead!!!\n",
                command, suggestion));
    }
}
