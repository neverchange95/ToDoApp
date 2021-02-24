# ToDoApp
A simple ToDoList app for android smartphones. The application was written with android studio in the programming language Java.
All ToDo elements are saved in a sql-database. The connection to this database is realized with java database connectivity (jdbc).
The sourcecode of this application you can find in this repository under ***Sourcecode/app/src/main/java/com/application*** and the design xml files under ***Sourcecode/app/src/main/res/layout***

### Application functions
* A overview over all days and their ToDo´s of the actual month
* Input a ToDo for a specific day and it will saved into a SQL-Database
* Set a notification for a ToDo by setting a specific time in minutes and hours
* Delete a ToDo from the database
* Set a ToDo as done -> The text will be crossed out
* Change the date using a calendar view from android and check all ToDo´s in the past

### Screenshots of the application
* First Picture shows the overview of all specific days in a month. By clicking on a day, the application switches to the activity like picture two
* Picture two shows the activity where you can input and save a todo, delete a todo or set a todo as done. The menu button brings you to the activity before and the bell will show you the activity like picture three
* Picture three shows the notification activity. Here you can set a notification for a specific todo
* If you are clicking on the the date, which is shown in all activities, the application switches to the date change activity like picture four. Here you can choose a date on the calendar and get with the "Zum ToDo" button to the todo-list for the choosed date. Picture five shows a example for the todo-list of a choosed date in the calendar
<div>
  <img src="Screenshots/Screenshot_20210224-222852.jpg" width="200" alt="" style="margin:3px" align="left">
  <img src="Screenshots/Screenshot_20210224-223359.jpg" width="200" alt="" style="margin:3px" align="left">
  <img src="Screenshots/Screenshot_20210224-223334.jpg" width="200" alt="" style="margin:3px" align="left">
  <img src="Screenshots/Screenshot_20210224-223206.jpg" width="200" alt="" style="margin:3px" align="left">
  <img src="Screenshots/Screenshot_20210224-223212.jpg" width="200" alt="" style="margin:3px" align="left">
