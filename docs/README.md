# Alpha User Guide

![image](https://github.com/Brendan8899/ip/blob/master/docs/Ui.png)

> *"Are you a tired and lonely computing student? Need help to manage your tasks and deadlines in a smart and timely manner?  
> Fret Not! Alpha Chatbot is here for you."* :rocket:

Alpha Chatbot is your personal assistant to manage tasks efficiently. Use our CLI input to record all your tasks.

Alpha Chatbot will provide timely reminders of your weekly tasks whenever you open the app and offers natural language sorting and chronological sorting functions.

## How to get started? :bulb:

- [ ] Copy the JAR file `Alpha.jar` into a directory.
- [ ] `cd` to `your_directory_address`.
- [ ] Run `java -jar "Alpha.jar"`.

## Listing all tasks :clipboard:

Use the `list` command.

## Adding Deadlines :alarm_clock:

To add tasks with deadlines, follow this format:

**Deadline [DESCRIPTION] /by [DATE]**

This is an example Command to add Deadlines:
`Deadline Vitamins /by 2024-09-19`

*Note:** It's important that dates follow the `YYYY-MM-DD` format to ensure correct input.

Alpha Chatbot will respond to show you the deadline recorded:

![image](https://github.com/user-attachments/assets/3bcd9e28-fbc1-4ce3-ace8-283272adff80)

## Adding ToDos :memo:

To add ToDos, follow this format:

**Todo [DESCRIPTION]**

This is an example Command to add ToDo:
`Todo Wash the Toilet` 

Alpha Chatbot will respond to show you the ToDo recorded:

![image](https://github.com/user-attachments/assets/0e6220ed-311c-4100-9a70-8a047b082906)

## Adding Events :date:

To add events, follow this format:
**Event [DESCRIPTION] /from [DATE] /to [DATE]**

This is an example Command to add ToDo:
`Event CS2103 Lecture /from 2024-09-19 /to 2024-09-19`

**Note:** It's important that dates follow the `YYYY-MM-DD` format to ensure correct input.

Alpha Chatbot will respond to show you the event recorded:

![image](https://github.com/user-attachments/assets/ca1b351b-be2f-4ca2-a417-c75e2304e9ba)

## Alpha Find Function :mag_right:

Alpha has a built-in find function for you to quickly search relevant tasks by their description.

**Format of the command:** `Find [DESCRIPTION]`

This will output all relevant tasks with a partial or full match with the Description you keyed in

**Example:** `Find CS2103`

The image below shows the find interaction with Alpha Chatbot
![image](https://github.com/user-attachments/assets/2e5edaae-85d8-4146-9979-a3c33efb7467)


## Alpha Sort Function :twisted_rightwards_arrows:

Alpha has a built-in sorting function because we understand that all computing students are super busy with 100+ tasks. :sweat_smile:

The `sort` command will arrange your tasks by deadlines or event start dates. This allows you to stay focused on immediate tasks. ToDos are left as lowest priority as they do not have a start date or deadline.

**Example:** `Sort`


The image below shows the sort interaction with Alpha Chatbot:

![image](https://github.com/user-attachments/assets/6b4e4773-4b64-4134-a35f-ee79efecc45d)

## Alpha Reminder Function :bell:

Alpha Chatbot will automatically output the tasks due this week upon application start. Below is an example interaction:

![image](https://github.com/user-attachments/assets/e3f80982-4238-4b80-aad6-3dc39c3d6511)

---

We hope that you have as much fun using Alpha as we had building it. :smiley:
