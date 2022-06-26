
function Errors({ errors }) {
  if (!errors || errors.length === 0) {
    return null;
  }

  return (
    <div className="alert alert-danger">
      <ul>
        {errors.map(error => (
          <li key={error}>{error}</li>
        ))}
      </ul>
    </div>
  );
}

export default Errors;