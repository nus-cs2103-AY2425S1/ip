package Commands;

import Data.StoreList;

public abstract class Command {
    protected StoreList storeList;

    public void setData(StoreList storeList) {
        this.storeList = storeList;
    }

    public abstract void execute();
    public abstract boolean isExit();
}
