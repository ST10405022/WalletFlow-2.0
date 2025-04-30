# WalletFlow-2.0
These are the requiements for the log in screen, I have added a continue without login button since I didn't have the DB or authentication - I reckon we can keep this button since this part is described as a prototype:

Required features: 

1. Local DB for saved users.

2. Authentication of input based on the Email and Password that is input.

3. The sign in button is present in the .kt file but you will have to hook it up to your logic.

----------------------------------------------------------------------------------------------------------------------------
Secondly, The hub, these are the main menu of the app and all other pages and features are connected to this, hence, AndroidManifest, will have the activities for all to guide you guys and connect the pages:

Required features: 

1. Population of categories using local DB (It would be a good idea just to populate it with some hard-coded ones before working on the Create A Category function).

2. I have made the UI dynamically populate on the UI as it goes on - it will only work if the person who makes the DB codes for it (You really don't have to do this, do what is comfortable to you, but the option is there).

Note: Flow State (Graphic) and Achievements (gameification) is not working and doesnt have pages, that is because it isnt being marked or required for part 2 , thats part 3 work.

-----------------------------------------------------------------------------------------------------
Thirdly, Create a Category, This function is used to populate the DB with a category which has several parameters:

Requirements: 

1. The user can enter the name of a category and its saved to the local DB

2. The user can upload or take a picture which is uploaded to the DB and displayed on the open screen

3. This is all populated and finalized when clicking on the DONE button

-------------------------------------------------------------------------------------------------------------
Nr4, Add an Expense, This feature is similar to the 3rd feature, it requires the user to be able to add an expense to the DB.

Requirements: 

1. User can add the name of the expense.

2. The user can select the cost amount of the expense.

3. The user can select a Date for the Expense.

4. The user can create a description for the Expense.

5. The user can upload an image in relation to the expense.

6. The expense is saved to the DB/Locally.

-----------------------------------------------------------------------------------------------------------------------------------
Nr5, Expense List, This is a list which displays from the DB from the users inputted expenses:

Requirements: 

1. Displays from the DB a list of expenses.

2. The List dynamically indicates (The wireframe shows red highlight, but any indicator will do) when users have expenses that exceed their cost amount for the expense).

---------------------------------------------------------------------------------------------------------

Nr6, Category Activity, This is what happens when the user selects an already created category and allows them to add their category details 

Requirements: 

1. Displays the image the user chose for their category (placeholder is the green square which can be found in the code).

2. Set Monthly Total Budget Goal Function (This allows the user to set a FROM date to a TO date for their category).

3. The list of current expenses which the user can select for the category.

⚠️ SOMETHING VERY IMPORTANT ABOUT THIS PAGE ⚠️

1. I didn't Add the UI for the EXPENSE MAX AMOUNT for this page, the reason is, i didn't want to tunnel one of you into doing it my way.

2. This page requires the user to input their max expense amount for the category.

3. This amount is linked with the expenses list as this MAX AMOUNT will determine what item is going over budget
Image.

---------------THAT IS ALL FOR THE UI FUNCTION ADAPTATION--------------------------------------------------

References: 

AndroidDevelopers, 2021. Developers. [Online] Available at: https://developer.android.com/training/data-storage/room [Accessed 22 April 2025].

GeeksForGeeks, 2025. GeeksForGeeks. [Online] Available at: https://www.geeksforgeeks.org/how-to-create-and-add-data-to-sqlite-database-in-android/ [Accessed 15 April 2025].

Malone, 2024. Youtube. [Online] Available at: https://www.youtube.com/watch?v=saKrGCWlJDs&ab_channel=DJMalone [Accessed 20 April 2025]. 

MikeT, 2022. stackOverflow. [Online] Available at: https://stackoverflow.com/questions/74477964/android-studio-add-a-database [Accessed 28 April 2025].

Sekhon, S., 2020. Medium. [Online] Available at: https://medium.com/@sukhbirsekhon3939/how-to-create-a-login-application-on-android-studio-d664662578f8 [Accessed 20 April 2025].

Team, A., 2024. AndroidDeveloper. [Online] Available at: https://developer.android.com/codelabs/basic-android-kotlin-compose-first-app#0 [Accessed 17 April 2025].

Team, G., 2025. geeksforgeeks. [Online] Available at: https://www.geeksforgeeks.org/kotlin-android-tutorial/ [Accessed 2 April 2025].

Tuto, E., 2023. Youtube. [Online] Available at: https://www.youtube.com/watch?v=H2potb8pGDQ&ab_channel=EasyTuto [Accessed 17 April 2025].

Tutorialspoint, 2024. tutortialspoint. [Online] Available at: https://www.tutorialspoint.com/android/android_login_screen.htm [Accessed 7 April 2025].
