package tayoo;

import tayoo.Storage;
import tayoo.exception.TayooException;
import tayoo.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class StorageStub extends Storage {

    public StorageStub() {
    }

    @Override
    public void updateTxt(int taskNumber, boolean isCompleted) throws TayooException {
    }

    @Override
    public List<Task> returnTaskListFromTxt() throws TayooException {
        return new ArrayList<>();
    }

    @Override
    public void deleteTxt(int taskNumber) throws TayooException{
    }

    @Override
    public boolean createTxt() throws TayooException {
        return true;
    }

}
