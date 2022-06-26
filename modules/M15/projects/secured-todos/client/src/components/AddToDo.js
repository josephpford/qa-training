import { useContext, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';

import AuthContext from '../AuthContext';
import Errors from './Errors';

function AddToDo() {
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  const descriptionOnChangeHandler = (event) => {
    setDescription(event.target.value);
  };

  const categoryOnChangeHandler = (event) => {
    setCategory(event.target.value);
  };

  const addToDoFormSubmitHandler = (event) => {
    event.preventDefault();

    const newToDo = {
      description
    };

    const init = {
      method: 'POST', // GET by default
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${auth.user.token}`
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
        // we either created the recorded...
        if (data.id) {
          // redirect the user back to the /todos route
          history.push('/todos');
        } else {
          // we have error messages
          setErrors(data);
        }
      })
      .catch(error => console.log(error));
  };

  return (
    <>
      <h2 className="my-4">Add ToDo</h2>
      <Errors errors={errors} />
      <form onSubmit={addToDoFormSubmitHandler}>
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
            <i className="bi bi-plus-circle-fill"></i> Add ToDo</button>
          <Link to="/todos" className="btn btn-warning ml-2">
            <i className="bi bi-x"></i> Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default AddToDo;