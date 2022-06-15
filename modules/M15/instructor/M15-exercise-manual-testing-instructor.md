Intentional defects:
Not sorted correctly.
Date of birth displays N/A instead of "unknown"
Duplicate names are allowed.
Agents must be at least 12 years old instead of 16.
Cannot delete agent Hazel C Sauven, no error displayed. (integrity constraint)
In the database the first names and last names are stored in the wrong fields.
In the dob update statement, make it impossible for the dob to update.

Additional Development Requirements:
Not sure where I got the field-agent schema and data. Must pre-insert data so that Agent Hazel C Sauven cannot be deleted due to an integrity constraint.