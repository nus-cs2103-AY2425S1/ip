package monique.exception;

/**
 * The <code>ParseException</code> class represents an exception that is thrown
 * when there is an error parsing a command, usually due to missing arguments.
 * It extends from <code>MoniqueException</code>.
 */
public class ParseException extends MoniqueException {

    /**
     * The <code>ParseException</code> class represents an exception that is thrown
     * when there is an error parsing a command, usually due to missing arguments.
     * It extends from <code>MoniqueException</code>.
     */
    public ParseException() {
        super("Error parsing, missing arguments");
    }

    /**
     * Provides advice on how to handle this exception.
     * This method prints a message informing the user to re-enter commands using the correct template
     * and suggests entering '/commands' to find out command templates.
     */
    @Override
    public void advice() {
        System.out.println("Parsing Exception. Please re-enter commands using the correct template.");
        System.out.println("To find out command templates, please enter '/commands' ");
    }
}
