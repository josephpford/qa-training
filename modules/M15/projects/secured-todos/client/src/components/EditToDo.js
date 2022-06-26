import { useContext, useState, useEffect } from 'react';
import { Link, useHistory, useParams } from 'react-router-dom';

import AuthContext from '../AuthContext';
import Errors from './Errors';

function EditToDo() {
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const { id } = useParams();
  const history = useHistory();

  const descriptionOnChangeHandler = (event) => {
    setDescription(event.target.value);
  };

  const categoryOnChangeHandler = (event) => {
    setCategory(event.target.value);
  };

  useEffect(() => {
    // GET http://localhost:8080/api/todos/1 HTTP/1.1

    const init = {
      headers: {
        'Authorization': `Bearer ${auth.user.token}`
      }
    }

    fetch(`http://localhost:8080/api/todos/${id}`, init)
      // Response object
      .then(response => { 
        if (response.status === 404) {
          return Promise.reject(`Received 404 Not Found for ToDo ID: ${id}`);
        }
        return response.json();
      })
      .then(data => {
        setDescription(data.description);
      })
      .catch(error => console.log(error));
  }, [id, auth.user]); // run anytime that the id or auth.user object changes

  const editToDoFormSubmitHandler = (event) => {
    event.preventDefault();

    const updatedToDo = {
      id: id,
      description
    };

    const init = {
      method: 'PUT', // GET by default
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${auth.user.token}`
      },
      body: JSON.stringify(updatedToDo)
    };

    fetch(`http://localhost:8080/api/todos/${updatedToDo.id}`, init)
      .then(response => {
        if (response.status === 204) {
          return null;
        } else if (response.status === 400) {
          return response.json();
        }
        return Promise.reject('Something unexpected went wrong :)');
      })
      .then(data => {
        if (!data) {
          // redirect the user back to the /todos route
          history.push('/todos');
        } else {
          // we have errors to display
          setErrors(data);
        }
      })
      .catch(error => console.log(error));
  };

  return (
    <>
      <h2 className="my-4">Edit ToDo</h2>
      <Errors errors={errors} />
      <form onSubmit={editToDoFormSubmitHandler}>
        <div className="form-group">
          <label htmlFor="description">Description: </label>
          <input className="form-control" type="text" id="description" name="description" 
            value={description} onChange={descriptionOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="category">Category:</label>
          <input className="form-control" type="text" id="category" name="category" 
            value={category} onChange={categoryOnChangeHandler} />
        </div>
        <div className="mt-5">
          <button className="btn btn-success" type="submit">
            <i className="bi bi-save"></i> Update ToDo</button>
            <Link to="/todos" className="btn btn-warning ml-2">
              <i className="bi bi-x"></i> Cancel
            </Link>
        </div>
      </form>
    </>
  );
}

export default EditToDo;