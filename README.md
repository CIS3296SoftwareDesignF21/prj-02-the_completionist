# The Completionist
## TJ Nguyen, Tuan Lam, Adam Lang, Andrew Pari, Sameera Rachakonda, Matt Stasiak 

### Project Description 
The Completionist is a desktop application coded primarily in Java with a command-line user interface that is designed to help increase productivity for students and professionals. 

The key features of this software include:
1. To-Do List
2. Calendar
2. Pomodoro Timer
3. Notes

These features are able to communicate and interact with each other. For example, when the user adds tasks to the to-do list, those tasks will also be added to the calendar. The calendar is able to import the tasks of the day into a daily to-do list. Besides the calendar and to-do list, The Completionist has a toolbox assortment of features to improve productivity. The Pomodoro Timer is derived from the Pomodoro Technique, a time management method that uses a timer to break work into intervals of 25 minutes, separated by short breaks. We implement our own version of the Pomodoro Timer in The Completionist to help users stay on top of their work. Additionally, the Notes tool will allow users to quickly jot down ideas to help sort through their thoughts. 

### Installation Instructions

macOS: Download the latest release and unzip contents. Knowing the file path to the download, navigate to prj-02-the_completionist in the terminal. From here, change the directory to prj-02-the_completionist -> out -> artifacts -> mainTest -> artifacts -> prj_02_the_completionist_jar. This filepath contains a file called prj-02-the_completionist.jar. Proceed with the following command, `java --module-path $PATH_TO_DOWNLOADED_PROGRAM/prj-02-the_completionist-0.1.4-alpha/lib --add-modules javafx.controls -jar prj-02-the_completionist.jar`. Example, `java --module-path /Users/tj/IdeaProjects/prj-02-the_completionist/lib --add-modules javafx.controls -jar prj-02-the_completionist.jar`. The .jar file should execute successfully. Current v0.1.6-alpha includes the functionality to add tasks within the main menu of the application. Tasks should populate into the sections "To Do Today" and "To Do In The Next 7 Days Of Current Month". Pomodoro Timer tool and Notepad tool functionality are working in the sense that a timer can be started and reset, and the notepad allows users to make changes to a standalone document. The Completionist is an open-source project that can be modified accordingly. Future modifications and permissions subject to change.
