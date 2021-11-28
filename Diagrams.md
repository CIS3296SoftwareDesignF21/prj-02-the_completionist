# UML Diagrams and Sequence Diagrams

## Week 3 UML Diagram
![completionist_uml drawio](https://user-images.githubusercontent.com/77748463/142042774-3db60c30-ce4f-495e-b5d0-bb48ec89fa68.png)

## Sequence Diagram 1
![image](https://user-images.githubusercontent.com/60636600/141896080-eda9cd0a-a07d-4b9e-a1f0-000985b974de.png)
Sarah wants to add a new task, so from the main menu pane, she goes to the 'add task pane'. She enters the new task information and hits the 'add task' button. This will create a new Task object which will get passed to the Calendar object via the addTask() method. When successful, the method returns and the new information gets propagated to the main_menu_pane.

## Sequence Diagram 2
![image](https://user-images.githubusercontent.com/61302705/141896567-7a4068dd-aef6-49a6-8eea-c0412a75503a.png)
Timmy would like to use the Completionist's integrated Pomodoro Timer. He wants to make efficient use of his time while completing some studies and homework. He opens the desktop application which initializes the main menu interface. On the backend, the readFromFile() function populates the daily tasks and upcoming tasks of the main menu. From the main menu, Timmy clicks on the Pomodoro Timer feature to initialize the tool. Within the tool, he uses setTimer(), startTimer(), and finally resetTimer(). All methods return values to the Pomodoro Timer pane. 

