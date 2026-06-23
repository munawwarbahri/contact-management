# User API Spec

## Register User

Endpoint: POST /api/users

Request Body:

```json
{
  "username": "bahri",
  "password": "rahasia",
  "name": "Bahri Munawwar"
}
```

Response Body (Success):

```json
{
  "data": "ok"
}
```

Response Body (Failed):

```json
{
  "error": "Username must not blank, ???"
}
```

## Login User

- Endpoint: POST /api/auth/login

Request Body :

```json
{
  "username": "bahri",
  "password": "rahasia"
}
```

Response Body (Success) :

```json
{
  "data": {
    "token": "TOKEN",
    "expiredAt": 234566936 //milliseconds
  }
}
```

Response Body (Failed, 401):

```json
{
  "error": "Wrong Username or Password"
}
```

## Get User

Endpoint: GET /api/users/current

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response Body (Success):

```json
{
  "data": {
    "username": "bahri",
    "name": "Bahri Munawwar"
  }
}
```

Response Body (Failed, 401):

```json
{
  "error": "Unauthorized"
}
```

## Update User

Endpoint: PATCH /api/users/current

Request Header:

- X-API-TOKEN: TOKEN (Mandatory)

Request Body:

```json
{
  "name": "Munawwar Bahri",       // put if only want to update name
    "password": "new password"      // put if only want to update password
}
```

Response Body (Success):

```json
{
  "data": {
    "username": "bahri",
    "name": "Munawwar Bahri"
  }
}
```

Response Body (Failed, 401):

```json
{
  "error": "Unauthorized"
}
```

## Logout User

Endpoint: DELETE /api/auth/logout

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response Body (Success):

```json
{
  "data": "ok"
}
```