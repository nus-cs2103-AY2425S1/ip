package mysutong;

import mysutong.SutongException;

class UnknownCommandException extends SutongException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
