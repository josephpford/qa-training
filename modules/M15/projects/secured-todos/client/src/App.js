import { useEffect, useState } from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

import AuthContext from './AuthContext';
import ToDos from './components/ToDos';
import AddToDo from './components/AddToDo';
import EditToDo from './components/EditToDo';
import DeleteToDo from './components/DeleteToDo';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import NotFound from './components/NotFound';
import Login from './components/Login';
import Header from './components/Header';
import Register from './components/Register';

const TOKEN_KEY = 'user-api-token';

function App() {
  // is someone logged in?
  // null || an object
  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);

  // interacting with local storage is a side effect...
  useEffect(() => {
    const token = localStorage.getItem(TOKEN_KEY);

    if (token) {
      login(token);
    }

    setInitialized(true);
  }, []); 
  // the empty array for the dependency list...
  // this side effect will run only once when the component is loading

  const login = (token) => {
    console.log(token);

    // store the token away to persist the user's login
    localStorage.setItem(TOKEN_KEY, token);

    // example of token payload:

    // {
    //   "iss": "todos",
    //   "sub": "john@smith.com",
    //   "roles": "ADMIN",
    //   "exp": 1631829962
    // }

    // decode the token string into a JavaScript object
    const tokenObj = jwt_decode(token);
    console.log(tokenObj);

    // long form...
    // const username = tokenObj.sub;
    // const rolesString = tokenObj.roles;

    // short form using destructuring...
    const { id, sub: username, roles: rolesString } = jwt_decode(token);

    // Split the roles string into an array of roles.
    const roles = rolesString.split(',');

    // create the "user" object
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

    // update the user state
    setUser(user);

    return user;
  };

  const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
    setUser(null);
  };

  // collect all of the auth related stuff into a single object
  const auth = {
    user: user ? {...user} : null,
    login,
    logout
  };

  // prevent routing until we've prevented restoring the user's login state...
  if (!initialized) {
    return null;
  }

  return (
    // passing the auth object using the context's value property
    <AuthContext.Provider value={auth}>

      <Router>

        <Header />

        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/contact">
            <Contact />
          </Route>
          <Route exact path="/todos">
            {user ? (
              <ToDos />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/todos/add">
            {user ? (
              <AddToDo />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/todos/edit/:id">
            {user ? (
              <EditToDo />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/todos/delete/:id">
            {user ? (
              <DeleteToDo />
            ) : (
              <Redirect to="/login" />
            )}
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="*">
            <NotFound />
          </Route>
        </Switch>

      </Router>

    </AuthContext.Provider>
  );
}

export default App;
