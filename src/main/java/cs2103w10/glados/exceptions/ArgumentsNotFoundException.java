package cs2103w10.glados.exceptions;

public class ArgumentsNotFoundException extends GladosException {
    /**
     * Constructs ArgumentsNotFoundException by calling the parent class.
     */
    public ArgumentsNotFoundException() {
        super( "Unknown arguments. Please try again.");
    }
}
