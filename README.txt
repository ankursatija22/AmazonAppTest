1) TEST FRAMEWORK: It's a combination of Data Driven + TestNG framework.

2) Used PAGE OBJEECT MODEL design pattern.

3) Excel file: It can be found under XLFile folder.   
    a) Login Credentials are taken  from excel file.  Put your credential while login into the APP.
    
 4) Device on which TC will be running is coming from properties file. Please change the device name when running on your real device.
 
 
5) TestCase execution is manage from TestNG.xml file. TC can be included excluded from that file.

6) In method "selectRandomSearchProductFunctionality" from "ProductSearchPage" class , Explicit wait time is given inorder to Bypass the payment page.


SYSOUT REQUIRMENT:

a) Java JDK (with JAVA_HOME and PATH configured)
b) IDE (and import this project)
c) Android Studio (with ANDROID_HOME and PATH configured)
d) Android AVD working
e) Appium installed Desktop version prefered.