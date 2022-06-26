
# ToDos React Router

## Tasks

### [x] Explain what client-side routing is and why it's useful

### [x] Set up a basic static router using React Router

* Install React Router using `npm install react-router-dom`
* Add a `Router` component
* Define links using the `Link` component
  * `NavLink` is a special link type that allows you to apply an active class name if the link's path is active
* Define routes using the `Route` component within a `Switch` component
  * The `Switch` component ensures that only one route will match at a time
  * The `exact` attribute specifies that the path should match exactly

### [x] Manage 404 pages with React Router

* Provide a "Not Found" fallback route

### [x] Use React Router to navigate to pages and components based on CRUD operations

* Now that we have a way to easily navigate between individual components let's split apart our ToDos component
* Split apart a monolithic CRUD component into separate list, create, update, and delete (if not handled from the list component) components
* Define routes for `/todos`, `/todos/add`, `/todos/edit/:id`, `/todos/delete/:id`

#### [x] Stub out components... ToDos, AddToDo, EditToDo, DeleteToDo

* Define routes...
  * `/todos`
  * `/todos/add`
  * `/todos/edit/:id`
  * `/todos/delete/:id`

#### [x] AddToDo

* Update the "Add ToDo" in the ToDos to redirect the user to the /todos/add route
* Move code from ToDos to AddToDo
* Add `Errors` component

#### [x] EditToDo

* Very similar to AddToDo
* Bring the "Edit" button back... and update to a Link... and pass the toDo.id as a route argument
* Use the route `:id` parameter to determine which ToDo to edit

* Use `fetch` to get the ToDo from the API

* More cut and paste from ToDos to EditToDo

### [x] Use the `useHistory` hook to programmatically redirect users

* Use the `useHistory` hook to redirect the user back to the list component after they've successfully created or updated a record

### [x] Access parameters, paths and other data using React Router hooks, including `useParams`

* Define a route with a parameter so that the update component can use the `useParams` hook to get the record ID to update
* https://github.com/pillarjs/path-to-regexp/tree/v1.7.0
* '/todos/edit/:id(//d+)' _should_ work

---

### [x] Login Component

* Add a `Login` component and an accompanying `/login` route to your React project
* Prompt the user for their username and password
* Redirect the user to the "Home" page (i.e. `/`) after they submit the form

### [x] NavBar Component

* Add a `NavBar` component to your React project (if it's not already defined)
* Include links to the "Home", "ToDos", "About", and "Contact" pages
* Within the component, define a `user` variable and initialize it to `null`
* If `user` is `null`, then display links to the "Login" and "Register" pages
* If `user` is not `null`, then display their username and a "Logout" button

### [x] Global State and Props

* Add a global `user` state variable to the `App` component
* Define `login` and `logout` functions that update the `user` state property
* Pass an `auth` object literal containing `user`, `login`, and `logout` to the `Login` and `NavBar` components
* Update the `Login` and `NavBar` components to call the `login` and `logout` methods (respectively)

### [x] Protecting Routes

* Use conditional rendering to protect all of the ToDos related routes (`/todos`, `todos/add`, `/todos/edit/:id`, and `/todos/delete/:id`):

```js
<Route path="/todos">
  {auth.user ? (
    <ToDos />
  ) : (
    <Redirect to="/login" />  
  )}
</Route>
```

> **You now have a semi-working solution!** The "ToDos" page is protected from unauthenticated users. You can login (albeit without actually authenticating the user using the User API), see the user's status in the nav bar updated to reflect their login status, view the "ToDos" page (though the request to ToDos API to retrieve the ToDos will fail), and logout.

> **Next you'll refactor your code to use the Context API.**

### [x] Header Component

* Add a new component between App and NavBar

### [x] Context API

* Leverage the Context API to manage global state
* Create a context object in its own module (so it can be imported into any module that needs access to the global state)

Now that you have a context, you need to update the `App` component so that the context can provide its `value` to any component that needs access to the global state:

* Import `AuthContext`
* Wrap `Router` in `AuthContext.Provider`
* Set the `AuthContext.Provider` component's `value` property to the `auth` object
* Remove `auth` props from all other components

Now within individual components, you can use the `useContext` Hook to listening for changes to the global state.

---

_Part 2_

## [x] Set Up

1\. Clone the [secured-todos](https://github.com/dev10-program/secured-todos) GitHub repo to your local machine.

2\. Familiarize yourself with the Secured ToDos application by reading the included `README.md` file.

3\. Follow the instructions in the `README.md` file to start the Secured ToDos application.

4\. Use the provided HTTP requests (see the `http` folder) to test both the User and ToDos APIs.

### [x] Getting a Token

* Update the `Login` component to use the provided User API to authenticate the user
* `POST` the `username` and `password` values to the User API's `/authenticate` endpoint
* On a successful response (`200 OK`), get the JWT token from the response body and pass it to the `auth.login()` method
* Redirect the user to the default route (`/`)
* On an unsuccessful response (`403 Forbidden`) display a "Login failed" message

### [x] Parsing the Token (`jwt-decode`)

Install the `jwt-decode` npm package:

```
npm install jwt-decode
```

Then use it to decode the token within the `App` component's `login` function:


```js
const login = (token) => {
  // {
  //   "iss": "dev10-users-api",
  //   "sub": "john@smith.com",
  //   "id": "983f1224-af4f-11eb-8368-0242ac110002",
  //   "roles": "ADMIN",
  //   "exp": 1620495306
  // }

  const { id, sub: username, roles } = jwt_decode(token);

  // Split the roles string into an array of roles.
  const roles = roles.split(',');

  const user = {
    id,
    username,
    roles,
    token,
    hasRole(role) {
      return this.roles.includes(role);
    }
  };

  console.log(user);

  setUser(user);

  return user;
};
```

### [x] Passing the Token when Making HTTP Requests

* Now you need to set the `Authorization` header on your Fetch calls
  * If you don't add the JWT token to the request, the server will return a response with a `403 Forbidden` HTTP status code
* Here's an example of what the raw HTTP request needs to look like:

```
POST http://localhost:8080/api/todos HTTP/1.1
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0b2RvcyIsInN1YiI6InNtYXNoZGV2IiwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIiLCJleHAiOjE2MDUyMTE0NDV9.G7kZls2bnrCgZD5hZk0uWU7yziA-YWF3OphKdARCLnw

{
  "description": "New todo again."
}
```

And here's an example of an updated Fetch API call:

```js
const newToDo = {
  description
};

const init = {
  method: 'POST', // GET by default
  headers: {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${auth.user.token}` // NEW
  },
  body: JSON.stringify(newToDo)
};

fetch('http://localhost:8080/api/todos', init)
  .then(response => {
    if (response.status === 201 || response.status === 400) {
      return response.json();
    }
    return Promise.reject('Something unexpected went wrong :)');
  })
  .then(data => {
    if (data.id) {
      history.push('/');
    } else {
      setErrors(data);
    }
  })
  .catch(error => console.log(error));
```

Complete the following...

1. Import AuthContext
2. Import useContext from React
3. Call useContext and pass in AuthContext to get `auth` object
4. Update the fetch call with an `Authorization` header

For these components...

* AddToDo
* EditToDo
* ToDos (just update the fetch call)

### [x] Register Component

* Add a `Register` component and an accompanying `/register` route to your React project
* The `Register` component is similar in form and function to the `Login` component
* Optionally add a "Confirm Password" field

Add the component... add the route... add your component state, form, etc.

* The biggest difference is that you need to make two Fetch calls when the user submits the form
  * Use Fetch to create the account
  * If you get a `201` (i.e. "Success") then use Fetch to authenticate and get the token
  * After receiving the token from the server, pass the token to the `auth.login()` function to login the newly created user

---

### [ ] Unit Testing

* Use Jest and Enzyme to unit test one or more React components

_Optional things to look at..._

### [x] Use Local Storage to persist the user's login +++++

### [ ] Combining the add/edit components +

* Walk through combining our add and edit components into a single form component

### [ ] DRY up authenticate

* Define an `authenticate` function in the App component (i.e. keep our code DRY)

### [ ] DeleteToDo

### [ ] Add Confirmation and Error components

* Redirect the user to a Confirmation page after successfully completing a CRUD operation
* Redirect the user to an Error page after an unexpected error has occurred

## Stretch Goals

* Create PrivateRoute component
* Automatically refresh the user's authentication token before it can expire

## Questions

* Why would use the hash router?
