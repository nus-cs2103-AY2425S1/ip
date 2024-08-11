package sigma.exception;

import sigma.Sigma;

public class SigmaFileException extends SigmaException {
    @Override
    public String toString() {
        return String.format("%s Cannot read from or write to the file.", super.toString());
    }
}
