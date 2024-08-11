package sigma.exception;

import sigma.Sigma;

public class SigmaFileFormatException extends SigmaException {
    @Override
    public String toString() {
        return String.format("%s Unknown Task type.", super.toString());
    }
}
