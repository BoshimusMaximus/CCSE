# CCSE
CCSE Coursework 1

In order to run the System MySQL must be installed on the host machine and running a server. Either the MySQL server username and password must be root or change 
the spring datasource username and password in /src/main/resources/application.properties

upon initialisation there will be no data in the database.
You can add regular users by registering normally.
to add an admin user edit the RegisterNewAccount() method to set the role as admin, then register an account.
afterwards edit it back.
