# Giphy
#It is a very simple application which contains a activity with viewpager which contains 2 fragments.

#Fragment1
It contains a search field and a recyclerview to display result.If search field is empty it will display
trending gifs otherwise serach results will be displayed.
Recyclerview displays items in the form of list and itemview displays a image with a heart which
represents it is favourite or not.

#Fragment2
It shows the list of items added to favourite which is stored in local database
Itemview also contains a heart to remove from favourite press on item.

#The app is also compatible with orientation changes.

#Architecture
I have used MVVM architecture combined with various design patterns such as repository,datasource,observer,DI etc.

#Dependency Injection
In this project i have used Koin DI

#Pagination
For pagination Android Paging Libarary has been used which is architecture component

#Api
For api calling i have used retrofit.

#Persistancy
As in app favourites needs to be stored locally so i have used Room Persistancy libray for it.

#Communication
For communication between fragments the concept of shared Viewmodel has been used.

#Unit Testing
Espresso is used for ui tests and Junit and Mockito is also used for testing

#Threading
Kotlin Coroutines have been used for api calls as well as database inserstions or wherever required.

