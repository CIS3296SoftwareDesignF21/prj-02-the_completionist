# The Completionist
## TJ Nguyen, Tuan Lam, Adam Lang, Andrew Pari, Sameera Rachakonda, Matt Stasiak 

### Project Description 
The Completionist is a desktop application coded primarily in Java with a JavaFX GUI interface that is designed to help increase productivity for students and professionals. 

The key features of this software include:
1. To-Do List
2. Calendar
3. Pomodoro Timer
4. Notepad
5. Motivational Quote

These features are able to communicate and interact with each other. For example, when the user adds tasks to the to-do list, those tasks will also be added to the calendar. The main menu interface features two dedicated areas: one for tasks on the current day, and tasks upcoming in the next 7 days of the current month. Besides the calendar and to-do lists, The Completionist has a toolbox assortment of features to improve productivity. The Pomodoro Timer is derived from the Pomodoro Technique, a time management method that uses a timer to break work into intervals of 25 minutes, separated by short breaks. We implement our own version of the Pomodoro Timer in The Completionist to help users stay on top of their work. Additionally, the Notepad tool will allow users to quickly jot down ideas to help sort through their thoughts. Changees to the notepad can be discarded by exiting the window and can be saved by clicking the corresponding button. Lastly, there is a small text area at the top of the application that features a motivational quoote to drive productivity.

### Installation Instructions

FIRST:  Download the latest release of the Completionist and unzip contents. Depending on how the file is unzipped, it may or may not create a duplicate folder. If there is a duplicate folder, please delete it. You should be left with one main folder named `prj-02-the_completionist-1.0.2-alpha` along with the numerous contents within the folder (source code and documents). Keep note of the absolute filepath to `prj-02-the_completionist-1.0.2-alpha`. Also create a `.txt` document in the directory that contains the Completionist called `completionistNotepad`. Make sure that the `.txt` file is not inside the Completionist folder that was created from unzipping. For example, if the relative filepath to the Completionist is `..\Downloads\prj-02-the_completionist-1.0.2-alpha`, the `.txt` document should be in `..\Downloads`.

MacOS: If you are on Mac, open the terminal. From here, change the directory to the directory containing the unzipped contents of the Completionist. 
For example, 
`/Users/tj/Downloads/prj-02-the_completionist-1.0.2-alpha/`. 
Once inside the directory of the Completionist, proceed with the following command, 
`java --module-path $PATH_TO_THE_COMPLETIONIST/prj-02-the_completionist-1.0.2-alpha/libMacOS --add-modules javafx.controls -jar prj-02-the_completionist.jar`. 
Example, 
`java --module-path /Users/tj/Downloads/prj-02-the_completionist-1.0.2-alpha/libMacOS --add-modules javafx.controls -jar prj-02-the_completionist.jar`. 
The .jar file should execute successfully. 

Windows: If you are on Windows, open the command line. From here, change the directory to the directory containing the unzipped contents of the Completionist. 
For example, 
`\Users\tj\Downloads\prj-02-the_completionist-1.0.2-alpha`. 
Once inside the directory of the Completionist, proceed with the following command, 
`java --module-path $PATH_TO_THE_COMPLETIONIST\prj-02-the_completionist-1.0.2-alpha\libWin\lib --add-modules javafx.controls -jar prj-02-the_completionist.jar`. 
Example, 
`java --module-path \Users\tj\Downloads\prj-02-the_completionist-1.0.2-alpha\libWin\lib --add-modules javafx.controls -jar prj-02-the_completionist.jar`. 
The .jar file should execute successfully.

The Completionist is still in pre-release stages. Bugs and errors potentially exist. Please give us time and patience as we are constantly looking to improve functionality and stability of the app.
