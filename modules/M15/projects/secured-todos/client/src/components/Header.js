import { Link } from 'react-router-dom';

import NavBar from './NavBar';

function Header() {
  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <Link className="navbar-brand" to="/">Todos</Link>
        <button className="navbar-toggler" type="button" 
            data-toggle="collapse" data-target="#navbarNavAltMarkup" 
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <NavBar />
      </nav>
    </>
  );
}

export default Header;