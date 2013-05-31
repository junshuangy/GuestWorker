Directorys instruction

C:/GuestWorker/Jason
  My root directory, contains sub-directorys like projects, libs and publishes.

Projects
  This directory contains all projects. Also the workspace folder of Eclipse.

Libs
  This directory contains public external jar libs.

Publishes
  This directory contains all publishes correspond to projects.

My steps to sign jar files

1. 
keytool -genkey -keystore myKeystore -alias TestKey (123456, 1...)

2.
jarsigner -keystore myKeystore ../../Libs/jchempaint-2.2.1-jason.jar TestKey

3.
jarsigner -keystore myKeystore ../../Libs/mysql-connector-java-5.1.24-bin.jar TestKey

4.
jarsigner -keystore myKeystore ChemViewer.jar TestKey


