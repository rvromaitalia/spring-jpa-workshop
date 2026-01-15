Part 2: Adding AppUser and Details Entities

Create a package for your entity models. x

Create classes AppUser and Details according to Table 1.

Turn Details into an entity.

Turn AppUser into an entity.

Set up the OneToOne relationship.

Create a package named repository for the interfaces.

Create interfaces following the repository requirements.

Test the implementations of the repositories.

Note: Always commit your changes regularly.

AppUser Requirements
id generated with the identity strategy.
username must be unique.
Details Requirements
id generated with the identity strategy.
email must be unique.
AppUserRepository Requirements
Basic CRUD operations.
Find by username.
Find by registration date between two specific dates.
Find by details' id.
Find by email (case-insensitive).
(Optional) Add more query methods as needed.
DetailsRepository Requirements
Basic CRUD operations.
Find by email.
Find by name containing a substring.
Find by name (case-insensitive).
(Optional) Add more query methods as needed.
Table 1: AppUser and Details Entity Relationships
classDiagram
class Details {
- int id
- String email
- String name
- LocalDate birthDate
// Constructor() and other methods as needed

    }

    class AppUser {
        - int id
        - String username
        - String password
        - LocalDate regDate
        - Details userDetails
        // Constructor() and other methods as needed
    }

    AppUser "1" --> "1" Details : OneToOne