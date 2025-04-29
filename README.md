# WalletFlow-2.0
This is the log in screen, I have added a continue without login button since I didn't have the DB or authentication - I reckon we can keep this button since this part is described as a prototype. 

Required features: 
Local DB for saved users
Authentication of input based on the Email and Password that is input
the sign in button is present in the .kt file but you will have to hook it up to your logic

----------------------------------------------------------------------------------------------------------------------------
Secondly, The hub, this is the main menu of the app and all other pages and features are connected to this, hence, AndroidManifest, will have the activities for all to guide you guys and connect the pages. 

Required features: 
Population of categories using local DB (It would be a good idea just to populate it with some hard-coded ones before working on the Create A Category function)
I have made the UI dynamically populate on the UI as it goes on - it will only work if the person who makes the DB codes for it (You really don't have to do this, do what is comfortable to you, but the option is there)

Note: Flow State (Graphic) and Achievements (gameification) is not working and doesnt have pages, that is because it isnt being marked or required for part 2 , thats part 3 work.

-----------------------------------------------------------------------------------------------------
Thirdly, Create a Category, This function is used to populate the DB with a category which has several parameters:

Requirements: 

The user can enter the name of a category and its saved to the local DB

The user can upload or take a picture which is uploaded to the DB and displayed on the open screen

This is all populated and finalized when clicking on the DONE button

-------------------------------------------------------------------------------------------------------------
nr4, Add an Expense, This feature is similar to the 3rd feature, it requires the user to be able to add an expense to the DB

Requirements: 

User can add the name of the expense

The user can select the cost amount of the expense

The user can select a Date for the Expense

The user can create a description for the Expense

The user can upload an image in relation to the expense

The expense is saved to the DB/Locally

-----------------------------------------------------------------------------------------------------------------------------------
Nr5, Expense List, This is a list which displays from the DB from the users inputted expenses 

Requirements: 

Displays from the DB a list of expenses

The List dynamically indicates (The wireframe shows red highlight, but any indicator will do) when users have expenses that exceed their cost amount for the expense)

---------------------------------------------------------------------------------------------------------

Nr6, Category Activity, This is what happens when the user selects an already created category and allows them to add their category details 

Requirements: 
Displays the image the user chose for their category (placeholder is the green square which can be found in the code)
Set Monthly Total Budget Goal Function (This allows the user to set a FROM date to a TO date for their category)
The list of current expenses which the user can select for the category

⚠️ SOMETHING VERY IMPORTANT ABOUT THIS PAGE ⚠️

I didn't Add the UI for the EXPENSE MAX AMOUNT for this page, the reason is, i didn't want to tunnel one of you into doing it my way
This page requires the user to input their max expense amount for the category
This amount is linked with the expenses list as this MAX AMOUNT will determine what item is going over budget
Image
---------------THAT IS ALL FOR THE UI FUNCTION ADAPTATION--------------------------------------------------
