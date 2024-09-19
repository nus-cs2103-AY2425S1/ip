package chacha.command.stub;

import chacha.exception.WrongCommandFormatException;

public class WrongCommandFormatExceptionStub extends WrongCommandFormatException {
    public WrongCommandFormatExceptionStub(String type) {
        super(type);
    }
}
