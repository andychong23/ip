# Bob User Guide

Example of Bob: <br>
!(https://github.com/andychong23/ip/tree/master/docs/Ui.png "Ui Screenshot")

// Product intro goes here
Have you ever gone crazy about keeping track of your todos, deadlines and events?
Fret not! Bob is here to help you. Similar to how Bob helps Gru, Bob can help you to solve your problems. 
Let Bob keep track of your todos, deadlines and events for you.

## Adding deadlines

With this command, you can add a deadline, with its due date of course and the details of the deadline

Command usage : `deadline <deadline_details> /by <end_datetime/end_date>`

Example usage : `deadline submit homework /by 04/04` <br>
Bob's output:
```
Got it. I've added this deadline:
[D] [ ] submit homework (by: 04 Apr 2025)
Now you have 1 tasks in your list
```

## Adding events
With this command, you can add an event to track, with the details of the event as well as the start and end date of the event!

Command usage : `event <event_details> /from <start_datetime/start_date> /to <end_datetime/end_date>`

Example usage : `event networking /from 10/03 /to 14/03 22:00` <br>
Bob's output:
```
Got it. I've added this todo task:
[T] [ ] grocery shopping
Now you have 1 tasks in your list
```

## Adding todos
With this command, you can add a todo task, with the details of the todo task

Command usage : `todo <task_details>`

Example usage : `todo grocery shopping` <br>
Bob's output:
```
Got it. I've added this :
[D] [ ] submit homework (by: 04 Apr 2025)
Now you have 1 tasks in your list
```

## Finding tasks with a specific string
With this command, you can find todo tasks, deadlines and events that matches the string provided

Command usage : `find <string>`

Example usage : `find home` <br>
Bob's output:
```
Here are the matching tasks in your list:
1. [D] [ ] submit homework (by: 04 Apr 2025)
```

## Mark/Unmark tasks
With this command, you can mark/unmark tasks, deadlines and events to be done

Mark command usage : `mark <task_number>`
Unmark command usage : `unmark <task_number>`

Example usage : `mark 1` / `unmark 1`

## Cheer
With this command, you can get a motivation message from Bob when you are feeling down

Cheer command usage : `cheer`

## Deleting tasks
With this command, you can delete tasks, deadlines and events that you no longer want to track

Delete command usage : `delete <task_number>`

Example usage : `delete 4`

## List
With this command, you can list all the tasks, deadlines and events that you are tracking

List command usage : `list`

## Bye
With this command, you will say goodbye to Bob and Bob will leave you D:

Bye command usage : `bye`