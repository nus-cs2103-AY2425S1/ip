public class AddTodoTaskScene {
    private SceneManager sceneManager;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void back() {
        sceneManager.showHomeScreenScene();
    }

    public void switchToEvent() {
        sceneManager.showAddEventTaskScene();
    }

    public void switchToDeadLine() {
        sceneManager.showAddDeadLineTaskScene();
    }

    public void save() {
        //TODO
    }
}
