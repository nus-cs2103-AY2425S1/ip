package joe;

import joe.controller.Controller;
import joe.ui.Ui;
import java.util.ArrayList;

public class ControllerStub extends Controller {

    public ControllerStub() {
      super(new Ui("Test"));
    }
    private boolean isCalled = false;
    private ArrayList<String> params = new ArrayList<>();

    @Override
    public void handleList() {
      isCalled = true;
    }
    
    @Override
    public void handleDone(int index) {
      isCalled = true;
      params.add(String.valueOf(index));
    }
    
    @Override
    public void handleUndone(int index) {
      isCalled = true;
      params.add(String.valueOf(index));
    }
    
    @Override
    public void handleTodo(String task) {
      isCalled = true;
      params.add(task);
    }
    
    @Override
    public void handleDeadline(String task, String by) {
      isCalled = true;
      params.add(task);
      params.add(by);
    }
    
    @Override
    public void handleEvent(String task, String from, String to) {
      isCalled = true;
      params.add(task);
      params.add(from);
      params.add(to);
    }

    public boolean isCalled() {
      return isCalled;
    }

    public ArrayList<String> getParams() {
      return params;
    }

    public void reset() {
      isCalled = false;
      params.clear();
    }
}
