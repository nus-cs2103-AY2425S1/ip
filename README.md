# YappingBot
> _"Software is like s-x: it's better when it's free."_ - Linus Torvalds ([Source](https://x.com/Linus__Torvalds/status/296333253571387392), yes he actually said that.)

YappingBot **FREES** your mind of having to remember things you need to do.

- Text-based
- Questionable learning curve
- _SUPER_ FAST to ~~crash~~ use (I'm kidding, it's Java. You only get that* in C)
  (_* - in both speed and stability aspects_)

All you need to do is:
1. Download it from [here](https://github.com/yadobler/ip/releases) (DID YOU CHECK THE SITE LINK?).
2. Double click it (honestly doesn't work for me. You're better off running `java -jar yappingbot.java`).
3. ???
4. Add your tasks!

Supported flags include:
```
--savefile <FILEPATH> define an alternate savefile path
        -s <FILEPATH> same as --savefile
        -c            Run CLI mode (fallback)
```

Did I mention it's **FREE**? (Free of Charge, Free of Dom)

# Features implemented:
- [x] View all added tasks
- [x] Manages _to-do_ tasks
- [x] Manages _deadline_ tasks
- [x] Manages _event_ tasks
- [x] Task list persistence (automatic save to and retrieve from disk)
- [ ] Does your taxes
- [x] Search and filter tasks (iteratively too!)
- [x] GUI
- [x] CLI mode fallback (pass `-c`)
- [x] Change savefile path (pass `-s <filepath>`)
- [ ] More coming soon...
---
If you're bored and want to stress yourself, look no further!

Clone this, and feel free to run it like this:

**CLI Mode:**
```java
public static void main(String[] args) {
    // Feel free to change this!
    // The original main method checks if an argument is passed and uses it instead.
    String savefile = "./savefile"; 

    YappingBot yp = new YappingBot(savefile);
    yp.start();
}
```

**GUI Mode:** (requires javafx)
```java
public static void main(String[] args) {
    // MainGuiApplication currently looks in 
    // Launcher to retrieve the savefilePath
    //
    // To be updated in further commits
    Launcher.savefilePath = "./savefile";
    MainGuiApplication.launch(MainGuiApplication.class, args);
}
```
