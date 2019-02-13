# Everyday - android app to help people achieve their goals.
![Back](/pictures/background.png)
## About Everyday!
This is the v2 (2018) version of my DayOne (app created back in 2017). Both Apps share similar funcionality but different architecture. <br/> During development process of <b>Everyday!</b> I learned and practiced how to :<br/> 
* follow a basic Model-View-Presenter (MVP) architecture (when needed).
* practice writing <i>JUnit and Mockito tests</i> 
* implement Room persistance library to store repetitive, bigger amounts of data
* implement RecyclerView different way to ease displaying data 
* use AsyncTasks with weak references to do small tasks on the background thread
* use interfaces more complex way to ease development process
* use BroadcastRecievers and AlarmManagers to create and deliver simple local notifications at certain time of a day
## TO-DO
* Services for notifications to "load" them after booting device
* Notification channel to get notifications on Android 8+
* MediaPlayer focus-end sound to fix

## Future of Everyday!
Right now bugs are being solved. What I plan to do with Everyday!
* Create a fake data source so the <i>Repository patter</i> implemented within the app will finally do his thang
* Create tests both with JUnit and Mockito
* Publish App on the google play store
* Refactor everything to reduce boilprate code
