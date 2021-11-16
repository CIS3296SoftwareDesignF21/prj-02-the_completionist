# The Completionist
## TJ Nguyen, Tuan Lam, Adam Lang, Andrew Pari, Sameera Rachakonda, Matt Stasiak 

The Completionist is a desktop application coded primarily in Java with a command-line user interface that is designed to help increase productivity for students and professionals. 

The key features of this software include:
1. To-Do List
2. Calendar
2. Pomodoro Timer
3. Notes

These features are able to communicate and interact with each other. For example, when the user adds tasks to the to-do list, those tasks will also be added to the calendar. The calendar is able to import the tasks of the day into a daily to-do list. Besides the calendar and to-do list, The Completionist has a toolbox assortment of features to improve productivity. The Pomodoro Timer is derived from the Pomodoro Technique, a time management method that uses a timer to break work into intervals of 25 minutes, separated by short breaks. We implement our own version of the Pomodoro Timer in The Completionist to help users stay on top of their work. Additionally, the Notes tool will allow users to quickly jot down ideas to help sort through their thoughts. 

# How to Build
Download the latest release and unzip contents. Knowing the file path to the download, navigate to prj-02-the_completionist in the terminal. From here, change the directory to prj-02-the_completionist -> out -> artifacts -> mainTest. Within this directory, there exists a file called prj-02-the_completionist.jar. Proceed with the following command, java -jar prj-02-the_completionist.jar. The .jar file should execute successfully. Current v0.1.1-alpha functionality includes the ability to add tasks to the calendar and to the to-do list. One is able to add tasks to the calendar with each run of the .jar file. For the to-do list, one is able to add tasks to the to-do list by modifying lines of code. The Completionist is an open-source project that can be modified accordingly. Future modifications and permissions subject to change. 

# How to Install
Future instructions TBD upon further development.

# Project Proposal, Vision Statement, and User Personas
https://docs.google.com/document/d/1y7izQUvN6UzQYRuw_cb_xHtADV20pzqIIkr6MgNdVRU/edit?usp=sharing

# Dynamic Ideas, Insights, and Feedback
https://docs.google.com/document/d/1gTEzMWscNSvW-PFdMYA6CQFRUvlqC2HmPmQjqyizfhc/edit?usp=sharing

# Week1.md
https://drive.google.com/file/d/1ZA55OEslf93hhpxhqoJ5u4RYq3rrBt0C/view?usp=sharing

# Week2.md
https://github.com/CIS3296SoftwareDesignF21/prj-02-the_completionist/blob/main/Week2.md

# Week 2 UML Diagram
![completionist_uml drawio](https://user-images.githubusercontent.com/77748463/140963703-975170be-a4ce-4b34-8212-f9a6a77cdc53.png)

# Sequence Diagram 1
![image](https://user-images.githubusercontent.com/60636600/141896080-eda9cd0a-a07d-4b9e-a1f0-000985b974de.png)
The user wants to add a new task, so from the main menu pane, he goes to the 'add task pane'. He enters the new task information and hits the 'add task' button. This will create a new Task object which will get passed to the Calendar object via the addTask() method. When successful, the method returns and the new information gets propagated to the main_menu_pane.

# Sequence Diagram 2
![image](https://user-images.githubusercontent.com/61302705/141896567-7a4068dd-aef6-49a6-8eea-c0412a75503a.png)


