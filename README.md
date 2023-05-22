# CodeFellowship

## Description
This is a social media app for developers to connect with each other. Users can create an account, create posts, and view other users posts.

## Getting Started
Clone this repository to your local machine.
```
$ git clone 
```
Once downloaded, you can either use IntelliJ to run the application or you can use gradle. If you want to run it from the command line using gradle, navigate to the root directory and run the following command:
```
$ ./gradlew bootRun
```
Once the application is running, navigate to http://localhost:8080/ in your web browser to view the application.


## Task list


1. Setup the base application
  - [x] Set up a new Spring Boot application with the necessary dependencies (Web, Security, JPA, Thymeleaf).

2. Build the user model and repository
  - [x] Create an `ApplicationUser` entity class with the necessary fields (username, password, firstName, lastName, dateOfBirth, bio).
  - [x] Implement a repository for the ApplicationUser entity `ApplicationUserRepository`

3. Create a user service
  - [x] Create a `UserDetailsService` that loads user by username.
  - [x] Implement `UserDetails` in the `ApplicationUser` class.

4. Configure Spring Security
  - [x] Create a `WebSecurityConfig` class that extends `WebSecurityConfigurerAdapter`.
  - [x] Configure `AuthenticationManagerBuilder` and `HttpSecurity`.
  - [x] Set up URL matchers for routes.
  - [x] Configure the login and logout process.

5. Build user registration and authentication controllers
  - [x] Create a `UserController` that handles user registration and authentication.
  - [x] Implement a sign-up page handler.
  - [x] Implement a login page handler.
  - [x] Implement a logout handler.

6. Implement Thymeleaf templates
  - [x] Create a template for the home page.
  - [x] Create a template for the login page.
  - [x] Create a template for the sign-up page.
  - [x] Include username in the header if the user is authenticated.

7. Implement and apply CSS styling
  - [x] Create a CSS file and link it in the Thymeleaf templates.
  - [x] Apply styles to the templates.

### Lab 17

1. Enhance user login process
- [ ] Implement redirection to /myprofile route upon user login.
- [ ] Display user's basic information on /myprofile, including a default profile picture.

2. Build a user detail page
- [ ] Develop a user detail page at route /users/{id}.
- [ ] Display user's basic information on this page, including a default profile picture.

3. Implement and update routing rules
- [ ] Continue to ensure that the homepage, login, and registration routes are accessible to non-logged in users.
- [ ] Restrict all other routes to logged-in users.

4. Build the post model
- [ ] Create a `Post` entity class with necessary fields (body, createdAt).
- [ ] Implement a repository for the Post entity `PostRepository`

5. Enhance user's ability to interact with posts
- [ ] Implement feature allowing logged-in users to create a Post.
- [ ] Implement relationship between Post and ApplicationUser (a post belongs to the user that created it).
- [ ] Display a user's posts on their profile page.
- [ ] Allow logged-in users to edit or delete their own posts.
- [ ] Implement a post “detail” page, displaying the post’s body and information about its author.

6. Implement reusable templates
- [ ] Use at least one Thymeleaf fragment on multiple pages.

7. Improve error handling
- [ ] Implement a non-whitelabel error handling page displaying the error code and a brief message about what went wrong.

8. Implement integration testing
- [ ] Write integration tests for new and existing functionality.

Note: Each of these tasks should be broken down into smaller sub-tasks to manage the complexity of each task. For example, "Implement feature allowing logged-in users to create a Post." could involve several sub-tasks such as designing the database schema, implementing the backend functionality, designing the front-end interface, etc.


## Resources

[Class 16 assignment](https://canvas.instructure.com/courses/6504881/assignments/36513372)

[Class 17 assignment](https://canvas.instructure.com/courses/6504881/assignments/36513373)

[Class 18 assignment](https://canvas.instructure.com/courses/6504881/assignments/36513374)


[Class 16 Github Repo](https://github.com/codefellows/seattle-code-java401d17/tree/main/class-16)

[Class 17 Github Repo](https://github.com/codefellows/seattle-code-java401d17/tree/main/class-17)

[Class 18 Github Repo](https://github.com/codefellows/seattle-code-java401d17/tree/main/class-18)

[CodeFellowship Github Repo](https://github.com/cfosprof/demo)

## Assignment
### Lab 16
- [ ] Build an app users can log into.
- [ ] The site should have a log in page
- [ ] The login page should have a link to a sign up page
- [ ] An ApplicationUser should have a:
  - [ ] username
  - [ ] password
    - [ ] will be hashed with bcrypt
  - [ ] firstName
  - [ ] lastName
  - [ ] dateOfBirth
  - [ ] bio
  - [ ] fields should be available for creation/editing at signup time only.
- Should allow users to create an ApplicationUser on the "sign up" page.
  - [ ] Controller shoul dhave an @Autowired private PasswordEncoder passwordEncoder;
  - [ ] and should use it to run the users password through the encoder before saving it to the database.
-[ ] Add the ability for users to log into app
-[ ] Site should hav a homepage at the root route (/) that contains basic information about the site.
  - [ ] Should have a link to the "sign up" page.
  - [ ] Should have a link to the "login" page if user NOT logged in.
  - [ ] Should have a "logout" button if user IS logged in.
-[ ] Ensure Users can log out and are redirected to the home page.
-[ ] When a user is logged in, the app should display the user's username on every page (probably in the heading).
-[ ] Ensure homepage, login, and signup routes are accessible to non-logged in users.
-[ ] Style site.
-[ ] Should use templates to render new pages (like the homepage and signup page).
-[ ] ensure user registration also logs user into app.


## Lab 17
-[ ] Upon login users should be taken to a /myprofile route that displays their information.
  - [ ] This should include a default profile picture, which is the same for every user, and their basic information from ApplicationUser.
- [ ] The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
  - [ ] This should include a default profile picture, which is the same for every user, and their basic information.
- [ ] Continue to ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
- [ ] Add a Post entity to your app.
  - [ ] A Post has a body and a createdAt timestamp.
  - [ ] A logged-in user should be able to create a Post, and a post should belong to the user that created it.
    - [ ] Hint: This is a relationship between two pieces of data
  - [ ] A user’s posts should be visible on their profile page.
  - [ ] A logged-in user should be able to edit or delete their own posts.
  - [ ] A user should be able to see a post “detail” page with the post’s body and information about its author.
- [ ] The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment that is used on multiple pages.)
- [ ] The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a brief message about what went wrong.
- [ ] The site should contain integration testing.
- [ ] A users posts should be visible on their profile page.
