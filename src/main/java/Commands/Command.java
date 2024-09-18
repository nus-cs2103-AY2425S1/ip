package Commands;

import Data.StoreList;
import Exceptions.InvalidIndexException;
import Exceptions.UnknownCommandException;

/**
 * Parent class for all types of commands
 */

public abstract class Command {
    protected StoreList storeList;

    public void setData(StoreList storeList) {

        this.storeList = storeList;
    }

    public abstract String execute() throws InvalidIndexException, UnknownCommandException;
    public abstract boolean isExit();
}
