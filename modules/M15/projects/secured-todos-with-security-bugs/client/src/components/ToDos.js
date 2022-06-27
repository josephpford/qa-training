import { useContext, useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../AuthContext';

function ToDos() {
  const [toDos, setToDos] = useState([]);

  const auth = useContext(AuthContext);

  const getToDos = (token) => {
    /*
    GET http://localhost:8080/api/todos HTTP/1.1
    Authorization: Bearer {{token}}
    */

    const init = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }

    // loading initial data for our component
    fetch(`http://localhost:8080/api/todos/${auth.user.id}/incomplete`, init)
      .then(response => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then(data => setToDos(data))
      .catch(error => console.log(error));
  };

  useEffect(() => {
    getToDos(auth.user.token);
  }, [auth.user.token]);

  const completedClickHandler = (toDoId) => {
    const toDoToComplete = toDos.find(toDo => toDo.id === toDoId);

    if (window.confirm(`Complete the todo with the description "${toDoToComplete.description}"?`)) {
      const init = {
        method: 'PUT', // GET by default
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${auth.user.token}`
        },
        body: JSON.stringify({ ...toDoToComplete, completed: true })
      };
  
      fetch(`http://localhost:8080/api/todos/${toDoId}`, init)
        .then(response => {
          if (response.status === 204) {
            getToDos(auth.user.token);
          } else {
            Promise.reject('Something unexpected went wrong :)');
          }
        })
        .catch(error => console.log(error));
    }
  };

  const toDoDeleteClickHandler = (toDoId) => {

    const toDoToDelete = toDos.find(toDo => toDo.id === toDoId);

    if (window.confirm(`Delete the todo with the description "${toDoToDelete.description}"?`)) {
      const init = {
        method: 'DELETE', // GET by default
        headers: {
          'Authorization': `Bearer ${auth.user.token}`
        }
      };
  
      fetch(`http://localhost:8080/api/todos/${toDoId}`, init)
        .then(response => {
          if (response.status === 204) {
            getToDos(auth.user.token);
          } else if (response.status === 404) {
            Promise.reject(`ToDo ID ${toDoId} not found`);
          } else {
            Promise.reject('Something unexpected went wrong :)');
          }
        })
        .catch(error => console.log(error));
    }
  };

  return (
    <div>

      <h2 className="my-4">ToDos</h2>

      <Link to="/todos/add" className="btn btn-primary mb-4">
        <i className="bi bi-plus-circle-fill"></i> Add ToDo
      </Link>

      {toDos.length > 0 ? (
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Description</th>
              <th>Due Date</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            {toDos.map(toDo => (
              <tr key={toDo.id}>
                <td>{toDo.id}</td>
                <td dangerouslySetInnerHTML={{__html: toDo.description}}></td>
                <td>{toDo.dueDate ? toDo.dueDate : 'N/A'}</td>
                <td>
                  <div className="float-right">
                    <button className="btn btn-success btn-sm"
                      onClick={() => completedClickHandler(toDo.id)}>
                        <i className="bi bi-check"></i> Complete</button>
                    <Link to={`/todos/edit/${toDo.id}`} className="btn btn-primary btn-sm ml-2">
                      <i className="bi bi-pencil"></i> Edit
                    </Link>
                    <button className="btn btn-danger btn-sm ml-2" 
                      onClick={() => toDoDeleteClickHandler(toDo.id)}>
                        <i className="bi bi-trash"></i> Delete</button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>You don't have any todos yet! Better get busy adding some.</p>
      )}

    </div>
  );
}

export default ToDos;