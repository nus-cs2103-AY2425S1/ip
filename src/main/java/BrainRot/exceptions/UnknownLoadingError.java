package BrainRot.exceptions;

import java.io.IOException;

public class UnknownLoadingError extends IOException{
    public UnknownLoadingError(IOException message) {
        super(message);
    }
}
