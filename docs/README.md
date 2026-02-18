# Jane Eyre - User Guide

Jane Eyre is a powerful CLI (Command Line Interface) application for managing your daily schedule.

---

## Command Format Notes

> [!IMPORTANT]
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.  
    >   *Example:* In `todo TASK`, `TASK` is a parameter: `todo read book`.
> * Items in square brackets are optional.  
    >   *Example:* `todo TASK [#TAG]` can be used as `todo read book #hobby` or just `todo read book`.
> * Items with `…` after them can be used multiple times including zero times.  
    >   *Example:* `[#TAG]…` can be used as `#urgent`, `#work #urgent`, or not at all.
> * Extraneous parameters for commands that do not take parameters (such as `help`, `list`, `exit`) will be ignored.

---

## Command Summary

| Action | Format | Example |
| :--- | :--- | :--- |
| **Todo** | `todo TASK [#TAG]…` | `todo buy milk #grocery` |
| **Deadline** | `deadline TASK /by yyyy-M-d HHmm [#TAG]…` | `deadline return book /by 2023-12-01 1800` |
| **Event** | `event TASK /from yyyy-M-d HHmm /to yyyy-M-d HHmm [#TAG]…` | `event meeting /from 2023-10-10 1400 /to 10-10 1600` |
| **Mark** | `mark INDEX` | `mark 1` |
| **Unmark** | `unmark INDEX` | `unmark 1` |
| **Find** | `find KEYWORD [MORE_KEYWORDS]…` | `find book` |
| **Delete** | `delete INDEX` | `delete 2` |
| **List** | `list` | `list` |
| **Exit** | `exit` | `exit` |

---

## Features

### 1. Add a To-Do: `todo`
Adds a task without any date or time.
* **Format:** `todo TASK [#TAG]…`
* **Example:** `todo read book #leisure`

### 2. Add a Deadline: `deadline`
Adds a task that must be completed by a specific time.
* **Format:** `deadline TASK /by yyyy-M-d HHmm [#TAG]…`
* **Example:** `deadline submit report /by 2024-05-20 2359 #work`

### 3. Add an Event: `event`
Adds a task with a specific start and end time.
* **Format:** `event TASK /from yyyy-M-d HHmm /to yyyy-M-d HHmm [#TAG]…`
* **Example:** `event wedding /from 2024-06-12 1200 /to 2024-06-12 1800`

### 4. Tagging Tasks
You can categorize tasks by adding one or more hashtags.
* **Usage:** Append `#TAGNAME` to the end of any adding command.
* **Example:** `todo gym #health #fitness`

### 5. Mark Task as Done: `mark`
Marks a task at a specific index as completed.
* **Format:** `mark INDEX`
* **Example:** `mark 1`

### 6. Unmark Task: `unmark`
Reverts a completed task back to "not done".
* **Format:** `unmark INDEX`
* **Example:** `unmark 1`

### 7. Find Tasks: `find`
Searches for tasks containing the specified keyword(s).
* **Format:** `find KEYWORD [MORE_KEYWORDS]…`
* **Example:** `find project`

### 8. Delete a Task: `delete`
Removes a task permanently from the list.
* **Format:** `delete INDEX`
* **Example:** `delete 3`

### 9. List All Tasks: `list`
Displays every task currently saved in the application.
* **Format:** `list`

### 10. Exit Program: `bye`
Safely closes the application.
* **Format:** `bye`

---

 