# CCSE
CCSE Coursework 1

In order to run the System MySQL must be installed on the host machine and running a server on port 3306. Either the MySQL server username and password must be root or change 
the spring datasource username and password in /src/main/resources/application.properties

upon initialisation there will be no data in the database.
You can add regular users by registering normally.
to add an admin user edit the RegisterNewAccount() method to set the role as admin, then register an account.
afterwards edit it back.

once an admin user has been created, you can navigate to /admin to reach the admin dashboard
to add products to the database, navigate to the products admin dashboard.
from their items can be added to the repository.
image link must be in the format /images/blackAcoustic.png

example data to add to product

Name: Black Guitar
Price: 400
Descirption: a black guitar
Category: 1
image Link: /images/blackGuitar.png

Name: Black Acoustic
Price: 600
Descirption: a black acoustic guitar
Category: 2
image Link: /images/blackAcoustic.png
