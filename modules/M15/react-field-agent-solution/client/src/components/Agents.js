import { useState, useEffect } from 'react';

function Agents() {
  const [agents, setAgents] = useState([]);
  const [firstName, setFirstName] = useState('');
  const [middleName, setMiddleName] = useState('');
  const [lastName, setLastName] = useState('');
  const [dob, setDob] = useState('');
  const [heightInInches, setHeightInInches] = useState(0);
  const [errors, setErrors] = useState([]);
  const [deleteAgentId, setDeleteAgentId] = useState(0);
  const [editAgentId, setEditAgentId] = useState(0);
  const [view, setView] = useState('List'); // Other views: Add, Edit, Delete

  useEffect(() => {
    fetch('http://localhost:8080/api/agent')
      .then(response => response.json())
      .then(data => setAgents(data))
      .catch(console.log);
  }, []);

  const handleFirstNameChange = (event) => {
    setFirstName(event.target.value);
  }

  const handleMiddleNameChange = (event) => {
    setMiddleName(event.target.value);
  }

  const handleLastNameChange = (event) => {
    setLastName(event.target.value);
  }

  const handleDobChange = (event) => {
    setDob(event.target.value);
  }

  const handleHeightInInchesChange = (event) => {
    setHeightInInches(event.target.value);
  }

  const handleAddAgentClick = () => {
    setView('Add');
  };

  const handleEditAgentClick = (agentId) => {
    setEditAgentId(agentId);

    const agent = agents.find(a => a.agentId === agentId);

    console.log(agent);

    setFirstName(agent.firstName);
    setMiddleName(agent.middleName);
    setLastName(agent.lastName);
    setDob(agent.dob ?? '');
    setHeightInInches(agent.heightInInches);

    setView('Edit');
  };

  const handleDeleteAgentClick = (agentId) => {
    setDeleteAgentId(agentId);

    const agent = agents.find(a => a.agentId === agentId);

    console.log(agent);

    setFirstName(agent.firstName);
    setMiddleName(agent.middleName);
    setLastName(agent.lastName);
    setDob(agent.dob ?? '');
    setHeightInInches(agent.heightInInches);

    setView('Delete');
  };

  const handleOnSubmit = (event) => {
    event.preventDefault();

    const agent = {
      firstName,
      middleName,
      lastName,
      dob,
      // The backend will happily accept a string value here
      // but you can optionally parse the string to an integer if you prefer:
      // heightInInches: heightInInches ? Number.parseInt(heightInInches) : 0
      heightInInches
    };

    if (view === 'Add') {
      const init = {
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
      };
  
      fetch('http://localhost:8080/api/agent', init)
        .then(response => {
          if (response.status === 201 || response.status === 400) {
            return response.json();
          }
          return Promise.reject('Shoot! Something unexpected went wrong :(');
        })
        .then(data => {
          // Determine if we have an agent or errors...
          if (data.agentId) {
            // If we have an agent, then add it to the list of agents.
            setAgents([...agents, data]);
  
            resetState();
          } else {
            // Otherwise display the errors that were returned from the API.
            setErrors(data);
          }
        })
        .catch(console.log);  
    } else {
      // Add the agent's ID.
      agent.agentId = editAgentId;

      const init = {
        method: 'PUT',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(agent)
      };

      fetch(`http://localhost:8080/api/agent/${editAgentId}`, init)
        .then(response => {
          if (response.status === 204) {
            return null;
          } else if (response.status === 400) {
            return response.json();
          }
          return Promise.reject('Shoot! Something unexpected went wrong :(');
        })
        .then(data => {
          // If we don't have data then the request succeeded...
          if (!data) {
            // Create a copy of the agents array.
            const newAgents = [...agents];

            // Get the index of agent that was just updated.
            const agentIndexToUpdate = agents.findIndex(a => a.agentId === editAgentId);

            // Update the agent in the array copy.
            newAgents[agentIndexToUpdate] = agent;
            
            // Update the agents state.
            setAgents(newAgents);

            resetState();
          } else {
            // Otherwise display the errors that were returned from the API.
            setErrors(data);
          }
        })
        .catch(console.log);
    }
  };

  const handleDeleteAgentConfirmationClick = () => {
    fetch(`http://localhost:8080/api/agent/${deleteAgentId}`, {
      method: 'DELETE'
    })
      .then(response => {
        if (response.status === 204) {
          const newAgents = agents.filter(agent => agent.agentId !== deleteAgentId);
          setAgents(newAgents);
          resetState();
        } else if (response.status === 404) {
          return Promise.reject(`Agent ID #${deleteAgentId} not found.`);
        }
        return Promise.reject('Shoot! Something unexpected went wrong :(');
      })
      .catch(console.log);
  };

  const handleCancelClick = () => {
    resetState();
  };

  const resetState = () => {
    setFirstName('');
    setMiddleName('');
    setLastName('');
    setDob('');
    setHeightInInches(0);
    setErrors([]);
    setDeleteAgentId(0);
    setEditAgentId(0);
    setView('List');
  };

  return (
    <>
      <h2 className="py-2">Agents</h2>

      {(view === 'Add' || view === 'Edit') && (
        <>
          {errors.length > 0 && (
            <div className="alert alert-danger">
              The following errors were found:
              <ul>
                {errors.map(error => (
                  <li key={error}>{error}</li>
                ))}
              </ul>
            </div>
          )}

          <form onSubmit={handleOnSubmit}>
            <div className="form-group">
              <label htmlFor="firstName">First Name:</label>
              <input id="firstName" name="firstName" type="text" className="form-control"
                onChange={handleFirstNameChange} value={firstName} />
            </div>
            <div className="form-group">
              <label htmlFor="middleName">Middle Name:</label>
              <input id="middleName" name="middleName" type="text" className="form-control" 
                onChange={handleMiddleNameChange} value={middleName} />
            </div>
            <div className="form-group">
              <label htmlFor="lastName">Last Name:</label>
              <input id="lastName" name="lastName" type="text" className="form-control" 
                onChange={handleLastNameChange} value={lastName} />
            </div>
            <div className="form-group">
              <label htmlFor="dob">Date of Birth:</label>
              <input id="dob" name="dob" type="date" className="form-control" 
                onChange={handleDobChange} value={dob} />
            </div>
            <div className="form-group">
              <label htmlFor="heightInInches">Height in Inches:</label>
              <input id="heightInInches" name="heightInInches" type="number" className="form-control" 
                onChange={handleHeightInInchesChange} value={heightInInches} />
            </div>
            <div>
              <button className="btn btn-success" type="submit">{view === 'Add' ? 'Add' : 'Update'} Agent</button>
              <button className="btn btn-warning ml-2" type="button" onClick={handleCancelClick}>Cancel</button>
            </div>
          </form>
        </>
      )}

      {view === 'Delete' && (
        <>
          <p>Delete the following agent?</p>
          <ul>
            <li>First Name: {firstName}</li>
            <li>Middle Name: {middleName ? middleName : 'N/A'}</li>
            <li>Last Name: {lastName}</li>
            <li>Date of Birth: {dob ? dob : 'N/A'}</li>
            <li>Height in Inches: {heightInInches}</li>
          </ul>
          <div>
            <button className="btn btn-danger" onClick={handleDeleteAgentConfirmationClick}>Delete Agent</button>
            <button className="btn btn-warning ml-2" type="button" onClick={handleCancelClick}>Cancel</button>
          </div>
        </>
      )}

      {view === 'List' && (
        <>
          <button className="btn btn-success my-3" onClick={handleAddAgentClick}>Add Agent</button>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>&nbsp;</th>
              </tr>
            </thead>
            <tbody>
              {agents.map((agent, index) => (
                <tr id={`row-${index}`} key={agent.agentId}>
                  <td name="name">{agent.lastName}, {agent.firstName}</td>
                  <td name="dob">{agent.dob ? agent.dob : 'N/A'}</td>
                  <td>
                    <div className="float-right">
                      <button className="btn btn-primary btn-sm" 
                        onClick={() => handleEditAgentClick(agent.agentId)}>Edit</button>
                      <button className="btn btn-danger btn-sm ml-2" 
                        onClick={() => handleDeleteAgentClick(agent.agentId)}>Delete</button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
    </>
  );
}

export default Agents;
