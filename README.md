### Overview

This is a simple app that uses Github's REST API to display a list of users. Upon
tapping a user the app should open a detail screen for the selected user or in
other words a screen that shows that user's profile.

### Features
- User List
- User Profile

### Architecture
I believe MVVM would a great choice for this since our app is only displaying data.
We will also use a Clean Architecture approach for this.Our project will have the
following modules:

- app
- api
- models
- theme
- user-list
- user-profile
- repository

`user-list` & `user-profile` will each have their own ui, state classes, and ViewModels

### UI

For the UI we will use Compose.\
User List
- Lazy Column
  - Row
    - AsyncImage - Coil (avatar_url)
    - Text (login)

Tapping on the user item will use shared transition to the user profile screen

User profile:

- Column
  - AsyncImage
  - Text (name)
  - Text (company)
  - Text (location)
  - Text (repos)
