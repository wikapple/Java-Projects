Name: Java Bank Management System
Course: INFO-C210 Project
Last Updated: 12/16/2021
Author: William Applegate

*********************************************************************************************************************************************************************************************

Description: 
  Bank Management System is a java programming project designed to simulate a banking system software. I oringinally made this to be run in the terminal, then re-designed the program to be run as a Java FX Application. Methods utilyzing scanners and terminal user input were deleted and some classes were adjusted. I placed the FX classes in a separate package so layout was easier to understand. 

*********************************************************************************************************************************************************************************************

Installation: 
  BankManagement System requires the Java Development Kit, and imports from standard Java libraries and the Java FX SDK. Program was created using JDK 11 and JavaFX 17.0.1. BankManagement system was created and tested using the Eclipse IDE.

*********************************************************************************************************************************************************************************************

Usage:
  Program currenlty runs in a Java FX GUI. The program instantiates three accounts as an example and creates one default employee login before the GUI is shown. The application then asks the user to login. The user can simply login using the default login auto-filled if desired, or create a sign in. This leads to a main menu scene with a pane of buttons for all bank operator tasks. 

*********************************************************************************************************************************************************************************************

Bugs:
  -Error pop ups on the AccountCreation Scene cause the buttons to move. Max limit of five error pop ups. 

  -TextFields allow for longer numeric user input than what the variables allow. Even using a long variable, the TextField can go past the max value for long variables. Currently this sets the user input back to 0 and pops up an error mesage in the window if the input is too long.
*********************************************************************************************************************************************************************************************

Design Notes:

  bankmanagementsystem Package:
    All backend files for this project are contained in the bankmanagementsystem package.

  Account.java:
    Abstract class that provides a baseline for the CheckingAccount, GoldAccount and RegularAccount to extend from. Account balances are made using the BigDecimal class. This is to provide decimal point precision without the floating point precision issues that double would have provided. In order to maintain the most accuracy possible, the balance is never rounded aside from what BigDecimal 32-bit integer scale will round. The toString output of balance rounds what the user reads to the second decimal place to replicate dollars and cents. All Accounts have a composition relationship with AccountLedger.java, as they are contained within AccountLedger objects. Implements Java comparable interface as well as the local copeable interface.

    copeable.java
    interface that requires a class to have a copy method. Being an interface, it is up to the programmer writing the @override method to ensure the code actually copies the instance of a class without making a shallow copy. This was created to promote the Account class being more immutable.

  CheckingAccount.java, GoldAccount.java and RegularAccount.java:
    All three classes extend from Account.java. They are designed to meet their individual requirements outlines in the project instructions. CheckingAccount also implements the ChargesTransactionFee interface.

    ChargesTransactionFee.java
    Interface that requires methods to be find if a transactionfee should be applied and to apply it when needed. 

  AccountLedger.java:
    The AccountLedger class contains an ArrayList of Account objects. The AccountLedger class is responsible for retrieving information from that ArrayList and manipulating the list when called to do so. The account ledger only takes calls from a BankManagementSystem class and has local access control so it can not be called from outside the package. There is unfortunately redundant method calls from BankManagementSystem to the AccountLedger. AccountLedger was designed to manipulate Accounts array while also protecting the array by ensuring it the array itself is immutable. Any reference variables passed from the AccountLedger should only be from a full/deep copy of objects and no reference variables from the Account array should be passed. 

  BankOperator.java:
    Since moving to the FX GUI, the BankOperator class has lost some of its methods. It originally had a console menu. Now, the bank operator holds some simple employee information. The employee information could be expanded to include such things as access levels or mroe detailed employee information, though that goes off track from the objectives of this project.

  BankData.java
    BankData class contains a list of global data field values to be passed over to the GUI so that it can then output bank analysis information. It is not required, but I find it is easier to pass the an instance of this class as a whole instead of individually pulling each value from the AccountLedger individually.

  BankManagementSystem.java
    This class is designed to be the central hub of this program. Any expansion of this program expands outward from here. The BankManagementSystem instances take method calls and send data to and from the GUI. This is the top/parent of the backend bankmanagmentsystem package. It holds all relevant information for the program.
****
  
bankuserinterface Pacakge:
  This package holds all Java FX GUI classes as well as images used in the application

InterfaceLauncher.java
  Launches JavaFX Application. I also added some code to close the program. I have read mixed things on whether the program will shut down fully when I close the program, so I added this as a test. I noticed the more I test this program, the slower my computer got. This led me to suspect the program was still running in the background causing the slowdown. 

BankFXController.java
  This class controls what scene is displayed on the Stage/GUI. The GUI changes from scene to scene via button action events.
****

bankuserinterface.bankpanes
  These classes extend from JavaFX classes to create custom Panes for the GUI. The constructors are long as they store most of the information on each of the Panes.
****

bankuserinterface/Images 
  This folder contains any image files for the program, which as right now includes the following two images:

    Bank.jpg
    Image of bank used for GUI background. Obtained from Phillymag.com at https://www.phillymag.com/news/2019/01/19/first-bank-philadelphia/

    MoneyIcon.png
    Image/graphic of dollar bills used for Stage icon. Obtained from istock.com at https://www.istockphoto.com/vector/dollar-money-icon-cash-sign-bill-symbol-flat-payment-dollar-currency-icon-gm1148591344-310227132




