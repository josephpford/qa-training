
# React ToDos with Security

## Tasks

* [x] Add todo table to the database

* [x] Update the controller to require tokens
  * Restrict the paths in the security config
  * Update the controller methods to get the principal user (to filter records by and associate records with)

* [x] Add `/api` prefix to user routes

* [x] Add global error handling

* [x] Combine ErrorResponse (from Field Agent API) and ValidationErrorResult (from ToDos API)

* [x] Update the HTTP requests

* [x] Move the project to the instruction team repo

* [x] Add recent React app example (use the project from the Best Buy cohort?)
  * [x] Fix role handling in the token... looks like the client app is expecting a "roles" property not an "authorities" property
  * [x] Update the token payload example in the App component

* [x] Review User API for possible code improvements
  * [x] Update the error messages to be consistent across services
  * [x] Switch to using env vars for the database connection information

* [x] Try using a custom user with Spring Security
  * [x] See the POC that I was working on... try using a custom UserDetails class again... seems like if I handle this correctly that it should allow me to explicitly cast getPrincipal() to the custom UserDetails class
  * [x] Cleanup...
    Remove original AppUser class
    Rename CustomAppUser to AppUser
    Update all layers to use the new user class

* [x] Rename projects with clearer names

---

* [x] Share with Corbin for his review before sharing with Alan

---

* [ ] Add unit tests

* [ ] Add user first name, last name, etc. to the backend and frontend

* [ ] Add todo "category" to the backend

* [ ] Client app improvements
  * [ ] Create a generic server error page?
  * [ ] Style the top menu using Bootstrap?

* [ ] Consider showing how to store tokens using HTTP cookies?
