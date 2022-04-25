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

