# validateNumbers

# Project Discription
Lists and categorize country phone numbers, it also offers possibility to filter phone numbers by country and their state whether they are valid or not.
Phone numbers are categorized by country, state (valid or not valid), country code and number.
The page renders a list of all phone numbers available in the DB.

# Backend Design
Using DTOs to encapsulate the necessary data inside one object to reduce the overhead of multiple API calls.
Abstracting different app components by depending on interfaces rather than implementations.
Using Strategy Pattern (to take decision based on it filtration works) to make the code cleaner and esiear to read 

# Technologies Used

Frontend:
VueJs,
Vuetify,
Axios.

Backend:
Java (11),
Spring boot,
Spring Data JPA,
SQLite,
Lombok,
Swagger (Springdoc-openapi),
H2 Database.


Jumia's country number validator app
Contents
PROJECT SUMMARY
Lists and categorize country phone numbers, it also offers possibility to filter phone numbers by country and their state whether they are valid or not.

DESIGN DECISIONS
Using DTOs to encapsulate the necessary data inside one object to reduce the overhead of multiple API calls from the frontend to get the required info.
Abstracting different app components by depending on interfaces rather than implementations.
PROJECT STRUCTURE
Frontend

Home page (CountryNumbers component) that renders the content on the user's screen.
Also when the CountryNumbers component is first loaded it sends a request to the backend to retrieve the available countries list.
Service that are responsible for making the requests..
Backend

Controllers : Classes that handle the requests and use the appropriate serving method.
CountryController: Responsible for the request of the list of available countries from the cache.
CustomerController: Responsible for the customer's related requests including filtration and pagination using the service layer.
Models: Represents the models used.
Customer
Country
Repository: This is the DAO in order to deal with the database.
DTO: Represents the Data transfer object used to encapsulate the information that is sent to the frontend.
Services: The service layer that is used to perform the logic for filtering the customers, accessing the repo, using the mapper to map to the DTO, and all the remaining funcionality.
Utils: Classes that helps in optimizing and extending the functionality.
Validator: Used to validate the customers' phone numbers.
Mapper: Used to map the list of customers into a list of customerDtos.
Constants: Used to contain constant values used throughout the application.
Cache: Used to cache country related info and operations.
Exceptions: Customized exceptions for the application's specific domain.
NoCustomersFoundException.
InvalidCountryNameException.
Application.properties: Holds the configurations for the application (Dealing with the database, server port , ... etc).
Tests: The tests for customerService, end-points test for the CountryController and CustomerController.
TECH STACK
Frontend.
VueJs.
Vuetify.
Axios.
Backend
Java (11).
Spring boot.
Spring Data JPA.
SQLite.
Lombok.
Google Guava.
Swagger (Springdoc-openapi).
H2 Database.

# To Run the project:

Clone the repo (using that link : https://github.com/ReemHesham1/validateNumbers.git)
git clone https://github.com/ReemHesham1/validateNumbers.git

You can run the project by cloning the project and using suitable ide for the backend (ex:intelj) and for the front end (ex:vsCode)
or by any of the methods listed:

JAR FILE
In the project's root directory run
mvn clean package
Or you can run the following command instead if you want to put the package in your local repo
mvn clean install
Run the following command
java -jar target/name-app.jar

The app is now running and you can access it through the following url
http://localhost:8085/
You can access swagger api documentation on the following URL
http://localhost:8085/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

USING DOCKER
In the project's root directory run
mvn clean package
Or you can run the following command instead if you want to put the package in your local repo
mvn clean install
Still on the project's root directory run the following command
docker build -t name-app .

in the Project's root folder run the following command
docker run --rm -it -p 8085:8085 name-app

in all the cases :
The app will be running and you can access it through
http://localhost:8085/
and for swagger 
http://localhost:8085/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config



