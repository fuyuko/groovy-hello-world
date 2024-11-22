@groovy.transform.Canonical
@groovy.transform.ToString
@groovy.transform.EqualsAndHashCode
@groovy.transform.TupleConstructor
class Person implements Serializable{
    String firstName
    String lastName
    int age
    String getFullName(){
        return firstName + " " + lastName
    }   
}