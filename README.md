[logo]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Apoco.png
[global]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Global.png
[dating]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Dating.png
[business]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Business.png
[social]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Social.png
[minesweeper]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Minesweeper.png
[sysdesign]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/SystemDesign-Apoco.png
[ajax]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Ajax.jpg
[bootstrap]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Bootstrap.png
[eclipse]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Eclipse.png
[java]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Java.jpeg
[spring]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/Spring.png
[relational]: https://github.com/kalebbe/Apoco/blob/master/WebContent/assets/img/presentation/RelationalDiagram.png

# Apoco
Come visit us at https://apoco-app.herokuapp.com/
![Apoco][logo]

# Video Demonstration
[![Demo](http://img.youtube.com/vi/YYS6Z7Q2Dq4/0.jpg)](http://www.youtube.com/watch?v=YYS6Z7Q2Dq4)

https://youtu.be/YYS6Z7Q2Dq4
---
### Table of Contents
- [Directory](https://github.com/kalebbe/Apoco#directory)
- [System Design](https://github.com/kalebbe/Apoco#top-down-system-design-model)
- [Relational Diagram](https://github.com/kalebbe/Apoco#relational-diagram)
- [Design Tools](https://github.com/kalebbe/Apoco#design-tools)
- [Global](https://github.com/kalebbe/Apoco#global-loginregistrationlanding-page)
   * [Overview](https://github.com/kalebbe/Apoco#overview)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality)
- [Social Platform](https://github.com/kalebbe/Apoco#social-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality)
- [Business Platform](https://github.com/kalebbe/Apoco#business-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview-2)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose-2)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality-2)
- [Dating Platform](https://github.com/kalebbe/Apoco#dating-platform)
   * [Overview](https://github.com/kalebbe/Apoco#overview-3)
   * [Purpose](https://github.com/kalebbe/Apoco#purpose-3)
   * [Functionality](https://github.com/kalebbe/Apoco#functionality-3)
### Directory
- [Models](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/model)- Classes containing object definitions.
- [Controllers](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/controller)- Classes controlling the flow of the application.
- [Data services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/data)- Classes handling persistent data.
- [Business services](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/business)- Classes handling the business logic of the application.
- [Utilities](https://github.com/kalebbe/Apoco/tree/master/src/com/gcu/utilities)- Classes containing functions used throughout the application.
- [Views](https://github.com/kalebbe/Apoco/tree/master/WebContent/WEB-INF/pages)- Contains the visual pages of the website.
---

## Top-down System Design Model
![System Design][sysdesign]

## Relational Diagram
![Relational Diagram][relational]

This diagram is a general representation of the relationships used throughout our application design. Our controllers use different models and call methods from the business services. The business services each have an interface that they implement, and they also utilize the data services. Finally, most of the data services implement the DataAccessInterface.

## Design Tools
![Spring MVC][spring]

**Spring MVC**

Apoco was built using the Model, View, Controller design pattern and heavily leveraged the Spring MVC framework. During the early stages of Apoco, we wanted to build our website using the MEAN stack; however, after many hours of frustration, we determined that Spring MVC and Java would better match our needs and expertise. 

![Java][java]

**Java**

Java is one of the first programming languages we learn at Grand Canyon University and because of that, it's also one of our most concrete languages. Apoco was built using Java and further development will be continued in Java.

![Eclipse][eclipse]

**Eclipse**

I think we will always have a love-hate relationship with Eclipse as developers. Despite its many frustrations, Eclipse makes so many things easier than using a normal Notepad editor.

![Bootstrap][bootstrap]

**Bootstrap**

Pretty much our entire project is lined with bootstrap methods or leverages bootstrap in some way. Mick and I started using Bootstrap studios early in our college years and it has helped us immensely in easing the development of our front end web pages. That being said, as we've gotten better at development, most web pages are created manually, but still leverage bootstrap libraries.

![jQuery][ajax]

**jQuery and Ajax**

jQuery is utilized all throughout Apoco and some social areas of Apoco also utilize Ajax for form posting to prevent page refreshing. This is important in the Minesweeper module because it simulates a console-style gaming experience.

## Global (Login/Registration/Landing page)
![Global][global]
### Overview
This section will give a brief explanation of the global module of the Apoco web application. This module includes the login, registration, index, user home, and account editor pages.
### Purpose
The global module is the first interaction all users will have with the Apoco website. We tried our best to make a design and logo that would be visual appealing to all users and attract new users. All users that want to use any of the services Apoco offers are required to create a global account before they can make any other account. This gives our users the freedom to create profiles for the services they want to use rather than forcing them to use all of our services.
### Functionality
The core functionality included in the Global module of Apoco include account registration, login, and account editting. Users must follow validation rules regarding usernames, emails, and passwords when creating or updating their account. Passwords are hashed using the BCrypt Maven dependency.

**Registration with BCrypt password hash**
```java
@Override
public int register(User t) {
   String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt()); // Takes the plain text pass and encrypts it
   t.setPassword(hashPass); // Updating the model object for database insertion
   if (dao.create(t)) { // creates a new
      return dao.getId(t.getEmail()); // Returns the ID of the newly created user for session capture
   } else
      return 0; // 0 tells me that the user creation has failed
}
```

**Hashed password check for login**
```java
@Override
public User checkPass(String pass, int id) {
   User user = dao.findById(id); // Grabs User object with ID

   if (BCrypt.checkpw(pass, user.getPassword())) { // Checks user's pass with database password for match
      return user;
   } else
      return null;
}
```
---
## Social Platform
![Social][social]
### Overview
This section will give a brief explanation of the purpose of our social platform as well as the functionality found within the social platform. Some code snippets will also be included and diagrams showing some of the functionality.
### Purpose
The social platform is the biggest part of Apoco and has been the focus of most of the project and testing. Our social media allows our users to connect with each other and communicate with their closest friends. Users can also enable privacy mode so that other users can only search for them by their email if they choose to do so. This is just a shell of what our social media platform is going to become over time. More browser games can be developed and added to the website as requested and we can give users a way to compete with their friends.
### Functionality
Current functionaly includes profile creation, minesweeper, post creation/deletion, voting on posts, adding friends, and messaging friends. Our social platform is still in the early stages like the rest of our website and can only grow from here.

**Creating a social profile (Controller code)**
```java
@RequestMapping(path = "/submitSocial", method = RequestMethod.POST)
public ModelAndView submitSocial(@Valid @ModelAttribute("social") Social social, BindingResult result,
   HttpSession session) {
   if (result.hasErrors()) { //Binding result holds data validation errors from the model
      return new ModelAndView("socialProfile", "social", social); //Returns socialProfile with errors
   }
   social.setUserId((int)session.getAttribute("id"));
   if(ss.createSocial(social)) { //Successfully creates a social profile in the database
      //Session attribute that lets the user skip the profile creation in the future
      session.setAttribute("hasSocial", true); 
      return new ModelAndView("socialDash", "social", social);
   }
   else { //Social profile failed insertion into database.
      return new ModelAndView("socialProfile", "social", new Social());
   }
}
```
**Minesweeper**
![Minesweeper][minesweeper]

**Minesweeper board Generation**
```java
public void generateBoard(int size) {
   btnHolder = new Button[size][size];
		
   //first loops to check if each button will be a bomb and then set it
   for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
         btnHolder[i][j] = new Button(i, j);
	 if(isActive()) {
	    btnHolder[i][j].setLive(true);
	 }
      }
   }
		
   //Second loops to set live neighbors for each button
   for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
         countNeighbors(i, j, size);
      }
   }
}
```

**Minesweeper Recursive Cell Processing**
Cells around the clicked cell are checked recursively using this method. This allows the effect where all the cells that would equal 0 in minesweeper around the clicked cell automatically get cleared.
```java
public static void processCell(int x, int y, int size) {
   if(inBounds(x, y, size)) { //This makes sure we don't get an outofbounds exception
      if(btnHolder[x][y].isLive()) { //Kaboom
         setLose(size);
      }
      else if(btnHolder[x][y].getLiveNeighbors() != 0) {
         btnHolder[x][y].setVisited(true); //Button has now been visited
      }
      else {
         for(int i = -1; i < 1; i++) { //There's that loop around the neighbors again
	    for(int j = -1; j < 1; j++) {
		if(inBounds(x + i, y + j, size) && !btnHolder[x][y].isVisited()) {
		   btnHolder[x][y].setVisited(true);
		   processCell(x-1, y, size); //This is recursively calling
		   processCell(x+1, y, size); //this same method 8 times to check
		   processCell(x, y-1, size); //every cell around the clicked cell.
		   processCell(x, y+1, size); //recursion is a bit confusing though.
		   processCell(x+1, y+1, size);
		   processCell(x-1, y-1, size);
		   processCell(x+1, y-1, size);
		   processCell(x-1, y+1, size);
		}
	     }
          }
       }
    }
}
```
---
## Business Platform
![Business][business]
### Overview
This section will talk about the purpose of the business platform and some of the functionality included in the business portion as well as some planned future functionality. This is a section of the website that could see some huge growth with the addition of useful functionality.
### Purpose
The purpose of the business platform on Apoco is to give our users a place to connect with some of their colleagues and view job openings in their area. Most of the functionality is still in beta; however, we're looking to expand this section to provide a unique experience to our users.
### Functionality
The current functionality in the business platform includes profile creation, searching jobs, searching users, adding connections, viewing jobs, and messaging connections. We would like to expand this platform to include dynamic job creation by company accounts, free resume creation for our users, application creation for our company accounts, job recommendations for your connections and more! Stay tuned.

**Message Threads**
This functionality is used across all platforms, but we'll talk about it a little more here and provide a code snippet showing some of the backend logic. Message correspondence between 2 users is placed into a message thread to help them keep their conversation going.
```java
@Override
public List<Message> getThread(int id){
   List<Message> msgs = dao.getThread(id);
   msgs.add(0, dao.findById(id)); //Puts the parent message at the start of the list
		
   //Attach sender User to each message
   for(Message msg : msgs) {
      msg.setUser(uDao.findById(msg.getSenderId()));
   }
		
   return msgs;
}
```
---
## Dating Platform
![Dating][dating]
### Overview
This section will go into more detail about the purpose of the dating platform at Apoco and discuss some of the current and planned functionality in this section. Our dating platform is the newest addition to our website and is still in the very early stages of development. This platform will have more functionality very soon for the senior capstone showcase on December 5th.
### Purpose
Our goal with the dating platform of Apoco is to give users a place to meet with other people with similar interests. We understand that our users don't want their business colleagues or their friends to know about their dating life (at least not always), so we've taken steps to separate dating from the other platforms. Account first names and last names are not used in the dating portion of our website, but instead users are required to select a nickname when they create their dating profile.
### Functionality
The current functionality in the dating platform is unfortunately limited to profile creation. Once users create their dating profile, they will be sent to a dashboard and they will not be able to perform any other actions on the dating platform. Our intent is to drop a few more bits of functionality to the dating platform before the capstone showcase on December 5th. The current plan is to implement a matching system where users will be matched based upon their answers to 10 questions. These users will then be able to browse matches and message other users. Over time we intend to refine the matchmaking algorithm and provide additional feautures to the dating platform as they are requested.

**Dating Profile Age Verification (18+)**
We don't want minors using our dating platform, so we implemented age verification logic to ensure that our users are over the age of 18.
```java
@Override
public boolean checkAge(Dating t) {
   //Creates a date object with the data to check difference
   LocalDate birthDate = LocalDate.of(t.getBirthYear(), t.getBirthMonth(), t.getBirthDay());
		
   //Returns the year between birthDate and current
   int age = Period.between(birthDate, LocalDate.now()).getYears();
		
   if(age < 18) {
      return false;
   }
   else {
      return true;
   }
}
```
