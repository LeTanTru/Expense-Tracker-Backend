### Expense Tracker Backend

- baseUrl: http://localhost:8080/api/v1/
- GET: getAllUsers: /users
- GET: getUserById: /user/:id
- PUT: updateUser: /user/:id

```js
{
    "password": ,
    "firstName": ,
    "lastName": ,
    "dob": ,
    "phoneNumber":
}
```

- DELETE: deleteUser: /user/:id
- POST: register: /register

```js
{
    "username": ,
    "password": ,
    "firstName": ,
    "lastName": ,
    "dob": ,
    "email": ,
    "phoneNumber":
}
```

- Login: login: /login

```js
{
    "username": ,
    "password":
}
```

- Forgot Password: /forgot-password

```js
{
    "email": ,
    "password": ,
    "confirmPassword":
}
```

- Verify OTP: /verify-otp

```js
{
    "email": ,
    "otp": 
}
```

- Send OTP: /send-otp

```js
{
    "email": 
}
```
