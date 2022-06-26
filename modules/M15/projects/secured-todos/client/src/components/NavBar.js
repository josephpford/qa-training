import { useContext } from 'react';
import { NavLink } from 'react-router-dom';

import AuthContext from '../AuthContext';

function NavBar() {
  const auth = useContext(AuthContext);

  return (
    <>
      <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div className="navbar-nav">
          <NavLink className="nav-link" activeClassName="active" to="/about">About</NavLink>
          <NavLink className="nav-link" activeClassName="active" to="/contact">Contact</NavLink>
          <NavLink className="nav-link" activeClassName="active" to="/todos">ToDos</NavLink>
        </div>
      </div>
      {!auth.user && (
        <>
          <NavLink className="nav-link" activeClassName="active" to="/login">Login</NavLink>
          <NavLink className="nav-link" activeClassName="active" to="/register">Register</NavLink>
        </>
      )}
      {auth.user && (
        <>
          <div className="align-middle mr-4">Hello {auth.user.username}!</div>
          <button onClick={() => auth.logout()} className="btn btn-primary">Logout</button>
        </>
      )}
    </>
  );
}

export default NavBar;