# Personal task manager: Kotori

> “ Even newborn baby birds
>
> Will someday soar through the sky
>
> They'll fly with great, strong wings
> .” – µ’s ([source](https://genius.com/Genius-english-translations-s-start-dash-english-translation-lyrics))

## How to use the bot:
### 1. Basic Features commands:
1. `Add` a task:
    1. `Todo`
       - Format : todo `description`
       - Example : todo wash clothes
       - Example output : 
       ```
       Now you have 1 tasks in list
       1. [T][ ] wash clothes
       ```
   2. `Deadline`: 
      - Format : deadline `description` /by `YYYY-MM-DD`
      - Example : deadline CS2103T ip /by 2024-09-26
      - Example output :
       ```
       Now you have 1 tasks in list
       1. [D][ ] CS2103T ip (by: Sep 26 2024)
       ```
   3. `Event`: 
      - Format : event `description` /from  `YYYY-MM-DD` /to `YYYY-MM-DD`
      - Example : event CS2103T ip /from 2024-08-16 /to 2024-09-26
      - Example output :
       ```
       Now you have 1 tasks in list
       1. [E][ ] CS2103T ip (from: Aug 16 2024 to: Sep 26 2024)
       ```
2. `View` all tasks
   - Format : list
   - Example output:
   ```
   Now you have 1 tasks in list
   1. [T][ ] CS2103T weekly work
   ```
3. `Mark/Unmark` a task: 
   - Format : mark/unmark `task index`
   - Index should be positive and not exceed the list
   - Mark/Unmark a marked/not marked task is not allowed
   - Example : mark 1
   - Example output: 
   ```
   Nice Job, Job 1 has been marked as done!
   [T][X]  CS2103T weekly work
   ```
4. `Delete` a task : 
   - Format : delete `task index`
   - Index should be positive and not exceed the list
   - Example : delete 1
   - Example output:
   ``` 
   OK~. I've deleted this task: 
   [T][X] me
   Now you have 0 tasks in the list
   ```
5. `Exit` the bot:
   - 
   - Format : bye
   - Example output:
   ``` 
   Bye! Hope to see you again soon.
   ```
### 2. Advanced Features commands
1. `Find` tasks related to a keyword: 
   - Format : find `keyword`
   - Example : find CS2103T
   - Example output: 
   ```
   These are(is) the task(s) that match the description
   1. [D][ ] CS2103T weekly work  (by: Oct 11 2020)
   2. [E][ ] project meeting for CS2103T (from: Oct 08 2020 to: Oct 26 2020)
   ```
2. `Search` tasks that need to be done before a date:
    - search `date`
    - Example search 2020-10-10
    - Example output :
    ```
    These are the tasks related to this date 2020-10-10
    1. [D][ ] something  (by: 2020-10-11)
    2. [E][ ] project meeting  (from: Oct 08 2020 to: Oct 26 2020)
   ```
3. `Sort` all tasks based on due time, the most urgent task in front:
   - Sort is done based on by time of Deadline and to time of Event
   - Format : sort
   - Example output :
   ```
   I have sorted the list for you~ ^_^
   This is your new task list.
    1. [D][ ] something  (by: Oct 11 2020)
    2. [E][ ] project meeting  (from: Oct 10 2020 to: Oct 26 2020)
    3. [T][X] wash clothes
   ```

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