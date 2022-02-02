# Blog API

## User Stories

* As a user I should be able to log in and list my blog posts.
* As an author I should be able to update my posts.
* As an author I should be able to delete my posts.
* As an author I should be able to create new posts.
* As an unprivileged user I should be able to read all the posts but not make any changes.

## ERD

![](./README/ERD.png)

## Planned deliverables

* Hosted on a webserver.
* Postgres DB.
* Simple front-end.
* Some basic content.

## If I have time:

![](./README/ambitious.erd.png)

* Roles and permissions admin user can create posts.
* Regular users can comment
* Unauthenticated can only read

## Installation instructions:

* You'll need to create a postgres database
* Configure the application.properties to a privileged user on that database
* Then run the application, and you should be able to test the endpoints.

## Endpoints documentation

| Request Type | URL                             | Request Body   | Request Header | Function                | Access |
|--------------|---------------------------------|----------------|----------------|-------------------------|--------|
| GET          | /api/categories                 | None           | None           | List all Categories     | Any    |
| POST         | /api/categories                 | title, content | JPA Token      | Create new category     | Author |
| DELETE       | /api/categories/{id}            | None           | JPA Token      | Remove a category       | Admin  |
| GET          | /api/categories/{id}/posts/{id} | None           | None           | Get a specific post     | Any    |
| POST         | /api/categories/{id}/posts      | title, content | JPA Token      | Create A new post       | Author |
| PUT          | /api/categories/{id}/posts/{id} | title, content | JPA Token      | Update an existing post | Author |
| DELETE       | /api/categories/{id}/posts/{id} | None           | JPA Token      | Delete a post           | Author |
| GET          | /api/users                      | None           | None           | Shows a list of users   | Any    |
| GET          | /api/{username}/posts           | None           | None           | Shows all user posts    | Any    |
| Delete       | /api/{username}                 | None           | None           | Delete a user           | Admin  |
|              |                                 |                |                |                         |        |

### Technologies Used
<img height="100" style="display: inline" src="./README/spring-logo.svg"/>
<img height="100" style="display: inline" src="./README/junit.png"/>
<img height="100" style="display: inline" src="./README/lombok.png"/>
<img height="100" style="display: inline" src="./README/postgres.png"/>
<img height="100" style="display: inline" src="./README/javalogo.png"/>