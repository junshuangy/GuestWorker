My steps to sign jars
1. 
keytool -genkey -keystore myKeystore -alias TestKey (123456, 1...)
2.
jarsigner -keystore myKeystore ChemViewer.jar TestKey

Directorys instruction

C:/GuestWorker/Jason
  My root directory, contains sub-directorys like projects, libs and publishes.

Projects
  This directory contains all projects. Also the workspace folder of Eclipse.

Libs
  This directory contains public external jar libs.

Publishes
  This directory contains all publishes correspond to projects.

DB
  This directory contains all database related files.

06/09/2013
Add a new project (NewChemViewer), using jchempaint-3.3-1210.jar and add ojdbc.jar
Using arguments in jnlp