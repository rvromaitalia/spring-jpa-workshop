````mermaid
classDiagram 
    direction LR
    class Details {
        - id : int
        - email : String
        - name : String
        - birthDate : LocalDate
    }
    
    class AppUser{
        - id : int
        - username : String
        - password : String
        - regDate : LocalDate
        - userDetails : Details
        }
    
        class Book{
        - id :int
        - isbn : String
        - title : String
        - maxLoanDays : int
        - author : String
        }
        
        class BookLoan{
        - id : int
        - loanDate : LocalDate
        - dueDate : LocalDate
        - returned : Boolean
        - borrower : AppUser
        - book : Book
        }
        
        class Author{
            - id : int
            - firstName : String
            - lastName : String
            - books : Book[]
        }
    Author "0..*" -- "0..*" Book : writes
    AppUser *-- Details
    AppUser "1" --> "1" Details : OneToOne
    AppUser "1" <-- "0..*" BookLoan : borrower, many to one
    Book   "1" <-- "0..*" BookLoan : book,many to one
````