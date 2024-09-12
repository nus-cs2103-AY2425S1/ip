# Bee #
> [!TIP] 
> “Your mind is for having ideas, _not_ holding them.” – David Allen (source)

### Bee frees your mind of having to remember things you need to do. ###
- text-based
- easy to learn
- SUPER FAST to use

### Features: ###

- [x] Managing tasks
- [x] Managing deadlines 
- [x] Managing events

## All you need to do is, ##

1. download the `.jar` file from [here](https://github.com/celeschai/ip/releases).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉
5. And it is **FREE**!

### For developers ###
to start the application through CLI, download the codebase and run `./gradlew run`.
CLI version for UI is available before `branch-Level-10` was merged.

Entrypoint to GUI application:
```java
public class Main extends Application {
    private Bee bee = new Bee();

    @Override
    public void start(Stage stage) {
        try {
            //...
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```