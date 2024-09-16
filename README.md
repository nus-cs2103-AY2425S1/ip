# Personal task manager: Kotori

> “ Even newborn baby birds
>
> Will someday soar through the sky
>
> They'll fly with great, strong wings
> .” – µ’s ([source](https://genius.com/Genius-english-translations-s-start-dash-english-translation-lyrics))

Kotori Minami is now in your computer to help you keep track of your task list
It's,

- text-based
- simple to use, can be learned by ***monkeys*** 🐵
- completely **FREE**!
- coded in Java

All you need to do to enjoy this bot is,

1. download it from [here](https://nus-cs2103-ay2425s1.github.io/website/schedule/week4/project.html).
2. double-click it.
3. add your tasks.
4. let Kotori manage your tasks for you 😉

Features:

- [x]  Managing tasks
- [x]  A better GUI
- [x]  Dialog reflex the Personality of ***Kotori Minami***

## How to use the bot:
### 1. Basic Features commands:
1. `Add` a task:
   1. `Todo`: todo `description`
   2. `Deadline`: deadline `description` /by `YYYY-MM-DD`
   3. `Event`: event `description` /from  `YYYY-MM-DD` /to `YYYY-MM-DD`
2. `View` all task: list
3. `Mark/Unmark` a task: mark/unmark `task index`
4. `Exit` the bot: bye
5. `Delete` a task : delete `task index`

### 2. Advanced Features commands
1. `Find` tasks related to a keyword: find `keyword`
2. `Search` tasks that need to be done before a date:
   - search `date`
3. `Sort` all tasks based on due time: sort

```java
String meetKotori = """
        .:----::.
        ::--===---==-.
        .=++++++========-.:--=          ......
        :**==============+=====:      .------------:.
        .+##========+++=++=======--:::.-==++++===-------:.
        .-=+*##+===============+++++++=========++***++=-------:.
        :==+*##+===----==+*****++++++++*++==------=+****+=------:.
        :==++**+=====+****+=-::::::::-====--------====+***+=-------
        :****+===++****+-::..:::::...=-:==--------====-=+***=----==-:
        -#*+==++++===:............::=-.=====-------====--=***+--==-==.
        :++==+++=:--:..............:-:===========-======--=+**+--++-=:
        :+++++=:.=*+##******+=:....::.===================---+**=-=*+=-
        :++++-.:=%#=:..  .:-=*#*=:....--=================----+*+--+*+=.
        :+++:...==  =#=   .*#=:+%*:.......:--======++===+=---=+=-==+*+.
        :==-.....  :#+=*#*=+#%-.=*............:-===++======---====-+*+
        ::=........:*++*##==*#. .:...............:--+=======-===--=++:
        ..=....:.......:==+==.......................:-=-=========-==-=.
        ..:..:::::::....................................=======+*=----=
        ......:::::::...........................--:......-=====+*=----=:
        :.....................................:==+*#+-:....-===+*+---==-
        :-....................................  .  .-*#=....-==-*+---===
        .=:.......................:...........-*=   .:-##:...:--*==---==
        .:-:..................................#++*+:.*+:##:....++===-===
        .............-+###=..................:*+*%%#**#--%*-:.:+=======-
        .:::.......-*######*-. ................:+**=-+#::%#...++++==-=+:
        .::::::..:+**++++**###*+===..............:+*++: :%*-:+++*===-=+
        .::::::::-===========+#####+:.........::...::  .==-:++**+====+:
        .::::::::==============*####:........::::::.......-+=+*+====+-
        .::::::::===============+##=..........::::::....:-:-**++===+*.
        .::::::::-===============#=.............:::......:+*+++++++++
        :=-::::::::-=============-.....................-+**+++++**++=                   """;
```