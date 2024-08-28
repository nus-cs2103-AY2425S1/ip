package Exceptions;

import Exceptions.DelphiException;

public class InvalidListItemException extends DelphiException {
    public InvalidListItemException(int i) {
        super("sorry, your task list doesn't have " + i + " items");
    }
}
