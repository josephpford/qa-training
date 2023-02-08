import { Link } from 'react-router-dom';

function Home() {
  return (
    <>
      <h2 className="my-4">Home</h2>
      <p><strong>Welcome to our ToDos application!</strong></p>
      <p>To get started, <Link to="/login">login using your existing account</Link> or <Link to="/register">register to create a new account</Link>.</p>
    </>
  );
}

export default Home;