package echoa.exception;

import echoa.main.Echoa;

public abstract class UpdateException extends EchoaException {
    public UpdateException() {
        super();
    }

    @Override
    public abstract String getMessage();
}
