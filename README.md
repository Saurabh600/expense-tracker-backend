# Expense Tracker Backend
fully-featured backend service implemented in java using spring boot and jwt

## Api Endpoints

### auth (base url = /api/auth)
| endpoints | desc           | body                    |
|-----------|----------------|-------------------------|
| /register | register user  | emailId, name, password |
| /token    | generate token | emailId, password       |

### users (base url = /api/users)
protected route, bearer token needed

| endpoints | desc          | body |
|-----------|---------------|------|
| /         | get all users | null |
