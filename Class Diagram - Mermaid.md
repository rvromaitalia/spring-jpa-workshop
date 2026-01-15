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
        - email : String
        - name : String
        - birthDate : LocalDate
        }
    AppUser "1" --> "1" Details : OneToOne
````