# GrafikManager API

A simple backend for managing users’ work schedules. The API provides operations for users and their assigned work shifts.

⚠️ **Security Warning**  
This API does **not** implement any authentication or authorization — login is done **without passwords**.  
Intended **only for local or home use**. **Not recommended for public or production environments without additional security!**

---

## 📦 Endpoints

### 👤 Users

- `GET /user`  
  Returns a list of all users.

- `GET /user/add/{name}`  
  Adds a new user with the given name.  
  Returns `202 Accepted` or `403 Forbidden` if an error occurs.

- `GET /user/del/{id}`  
  Deletes a user by the given ID.  
  Returns `202 Accepted` or `404 Not Found`.

---

### 🕒 Work Shifts (Schedule)

- `PUT /work/add/{ownerId}/{timeStart}/{timeEnd}`  
  Adds a new work shift for the user.  
  `timeStart` and `timeEnd` format: `YYYY-MM-DDTHH:MM:SS` (e.g. `2025-06-12T09:00:00`)  
  Returns `202 Accepted` or `400 Bad Request`.

- `GET /work`  
  Returns all registered work shifts.

- `GET /work/del/{id}`  
  Deletes a work shift by the given ID.  
  Returns `200 OK` or `404 Not Found`.

- `POST /work/id/{ownerId}`  
  Returns all work shifts assigned to the user with the given ID.

- `POST /work/date/{date}`  
  Returns all work shifts on a specific date (`YYYY-MM-DD`).

---

## 🛠 Technologies

- Java
- Spring Boot
- REST API
- Default database (H2 or JDBC)

---

## 🚫 No Authentication

This API **does not include any authentication** — anyone with access to the server can add or delete data.  
**Not suitable for production or public use without additional security layers!**

