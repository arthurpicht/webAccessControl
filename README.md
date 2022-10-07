# webAccessControl

# work in progress!!!

A simple functionality for managing access control in service oriented web applications like REST backends.

Features:

* session based concept: a persistent session is created for each successfully logged-in user. A session manager for canonical HttpSessions is predefined. Other kinds of session managers can be implemented.
* access is granted to users based on their role
* a login depends on fulfilling specified requirements. 

## User

Create a class extending User for each role. Classes must implement Serializable and should implement serialVersionUID.

## Login Handler




