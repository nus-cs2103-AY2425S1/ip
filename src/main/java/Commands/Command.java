package Commands;

import Data.StoreList;
import Exceptions.InvalidFormatException;
import Exceptions.InvalidIndexException;

/**
 * Parent class for all types of commands
 */

public abstract class Command {
    protected StoreList storeList;

    public void setData(StoreList storeList) {

        this.storeList = storeList;
    }

    public abstract String execute() throws InvalidIndexException, InvalidFormatException;
    public abstract boolean isExit();
}
