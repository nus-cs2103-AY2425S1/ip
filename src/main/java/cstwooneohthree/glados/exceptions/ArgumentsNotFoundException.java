package cstwooneohthree.glados.exceptions;

/**
 * ArgumentsNotFoundException class when argument does not exist.
 *
 * @author jayjay19630
 */
public class ArgumentsNotFoundException extends GladosException {
    /**
     * Constructs ArgumentsNotFoundException by calling the parent class.
     */
    public ArgumentsNotFoundException() {
        super("Unknown arguments. Please try again.");
    }
}
