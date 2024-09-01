public class AddEventTaskScene {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void back() {
        sceneManager.showHomeScreenScene();
    }

    public void switchToTodo() {
        sceneManager.showAddTodoTaskScene();
    }

    public void switchToDeadLine() {
        sceneManager.showAddDeadLineTaskScene();
    }

    public void save() {
        //TODO
    }
}
