WikiaTest
=========

WikiaTest-Login-youtube


"Program Wikia.com Test Version v1.0 Dated 8/7/2014"

General Usage Notes
------------------------------------------------------------------------------------------------------------------------------------------
Pre-condition

-You have previously created an account and user name for Wikia.com precondition

-User is logged out of Wikia.com

-Credentials username:singhgurpreet123 and password:p@ssw0rD 

-The project is written in java programming language and Framework is used as junit and test is written in Selenium Web Driver.

-User can run test against chrome and fire fox.

-Project is written in SingleTon patten instead of Page Object and Page Factory.

-The google chrome version used is Version 36.0.1985.125 m

-The firefox version used is firefox 31.0

-Download and install Jars for Selenium web driver

------------------------------------------------------------------------------------------------------------------------------------------
Introduction: 

    The Project is about testing of webpage Wikia.com. Two major tasks are involved as Login with credentials and check and updated you tube video link. 

Table of Content:

    Candidate Name: Gurpreet Singh Sambhy

    Candidate Email: sambhy@kth.se

    Project demo:  Testing two tasks for Wikia website 

-------------------------------------------------------------
Scenario- LOGIN

Steps: Action

    1.Navigate to http://testhomework.wikia.com/

    2.Hover mouse over the “Log in” label

    3.Enter username and password, left click the login button


Expected result:

    1.User  redirected to the home page http://testhomework.wikia.com/wiki/Testhomework_Wiki

    2.The Login form  visible

    3.User logged in, username is displayed instead of the “Log in” label



Scenario 2- ADD VIDEO

Steps: Action

    1.Navigate to http://testhomework.wikia.com/

    2.Left click the “Contribute” button

    3.Left click the “Add a video” button

    4.Type URL to video from you tube into the “Video URL” field and left click the “Add” button e.g. url http://www.youtube.com/watch?v=h9tRIZyTXTI

    5.Left click the link to file on the flash message

    6.Verify that the filename is the same as on the flash message (note spaces may be shown as underscores) 

Steps: Expected result:

    1.User is redirected to the home page http://testhomework.wikia.com/wiki/Testhomework_Wiki

    2.The Contribute dropdown expanded

    3.User is redirected to the WikiaVideoAddPage http://testhomework.wikia.com/wiki/Special:WikiaVideoAdd

    4.Flash message with text: “Video page File:FILENAME successfully added.” is displayed near the top of the page.

    5.User is redirected to the http://testhomework.wikia.com/wiki/File:FILENAME page

    6.Confirmed

----------------------------------------------------------------------------
 
--------------------------------------------------------
TestNG plug-in for Eclipse

    To run this program user should install TestNG (TestNG plug-in for Eclipse).
    Go to eclipse.
    Click on Help Menu.
    Click on Install New Software.
    Available software :- click on Add button.
    Add Repository.
    Name:- TestNG.
    Location:- http://beust.com/eclipse.
    I accept the terms of the licence conditions click Next:- Finish and    eclipse re-start.
Instalation steps from
    
    http://www.uipress.com/how-to-install-apache-ant-on-windows-7/#.U-Obg6Mg4Zs

Jars for Selenium web driver

-Download Jars files from internet.

    http://docs.seleniumhq.org/download/
-Language: Java 	|Client Version: 2.42.2 |Release Date: 2014-06-03 |Click on Download
-Save file on local machine and install them for current project.
    
    To run this program user should install jar files for eclipse, Download from internet
    Go to Eclipse
    Right Click on proect select properties.
    Click on Java Build Path under section Libraries click on Add External JARs
    Ok
Or Download from Gurpreet Dropbox

    https://www.dropbox.com/sh/0zxgn67mczzhkp6/AADKEl2g3qc0Izpc9J7QE50Ha?n=63047246

End Steps of executing programe in command prompt and pre-conditions









