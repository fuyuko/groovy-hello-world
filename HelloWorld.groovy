class HelloWorld{
    static void main(String[] args){
        println "Hello World"

        //deifne a typed variable
        int age = 40

        //print variable value
        println age

        //print variable type
        println age.getClass()

        //Create new Person class and instantiate it
        Person johnDoe = new Person()
        johnDoe.setFirstName("John")
        johnDoe.setLastName("Doe")
        johnDoe.setAge(40)

        //asserts
        assert johnDoe.toString() == "Person(John, Doe, 40, John Doe)"
        assert johnDoe.equals(johnDoe)
        assert !johnDoe.equals(new Person(firstName: "Jane", lastName: "Doe", age: 35))
        assert new Person(firstName: "Jane", lastName: "Doe", age: 35).toString() == "Person(Jane, Doe, 35, Jane Doe)"
        //Print the full name and age of the Person instance
        println johnDoe.getFullName()
        println johnDoe.getAge()

        //Identify if Person is middle-aged using a conditional statement
        if(johnDoe.getAge() > 45 && johnDoe.getAge() < 65){
            println johnDoe.getFullName() + " is middle-aged"
        }else{  
            println johnDoe.getFullName() + " is " + johnDoe.getAge() + " years old"
        }

        //Define a list of Persons
        def persons = [johnDoe, new Person(firstName: "Jane", lastName: "Doe", age: 35)]

        //Iterate over Person instance in list
        for (Person p : persons){
            println p.getFullName()
        }

        try {
            //Convert a String into a Long data type
            johnDoe.getFirstName().toLong()
        } catch(NumberFormatException e){
            //Catch the exception and print the error message
            assert e instanceof NumberFormatException
            println "Cannot convert a String into a Long"
        }   


        Calculator calc = new Calculator()
        assert calc.add(5,10) == 15
        assert calc.subtract(10,5) == 5
        assert calc.multiply(5,10) == 50
        assert calc.divide(10,5) == 2.0

        try{
            calc.divide(10,0)
        }catch(RuntimeException e){
            assert e.getMessage() == "Cannot divide by zero"
        }

        //Create Closure that prints String representation of a Person instance
        Closure personToString = { Person person -> println person.toString() }
        //personToString(johnDoe)

        //Pass Closure to a method and execute it
        handlePerson(personToString, johnDoe)
        
        //Create Closure that prints full name of a person
        Closure personFullName = { Person person -> println person.getFullName() }  
        handlePerson(personFullName, johnDoe)


        //Collections
        Person johnDoe2 = new Person(firstName: "John", lastName: "Doe", age: 40)
        Person marryHill = new Person(firstName: "Marry", lastName: "Hill", age: 35)
        Person thomasMarks = new Person(firstName: "Thomas", lastName: "Marks", age: 21)

        //Create a new list of persons
        def allPersons = [johnDoe, johnDoe2, marryHill, thomasMarks]

        //Querying Collections
        assert allPersons instanceof List
        assert allPersons.size() == 4
        assert allPersons[2] == marryHill
        
        //Iterate over the list of persons
        allPersons.each{
            println it
        }

        //Iterate over leemnts and using an index
        allPersons.eachWithIndex{ Person entry, int i ->
            println i + ": " + entry
        }

        //Filtering a specific element
        allPersons.find { it.lastName == "Marks" }  == thomasMarks

        //Transforming elements into something else
        allPersons.collect { it.age <= 30 } ==[false, false, true, true]

        //Sorting elements
        allPersons.sort { it.age } == [thomasMarks, marryHill, johnDoe2, johnDoe]

        //Reading Files
        File file = new File("john-doe.txt")
        println file.getText("UTF-8")

        //Iterate over each line of file
        file.eachLine { String line, Integer no ->
            if(no == 1){
                johnDoe2.setFirstName(line)
            } else if (no == 2){
                johnDoe2.setLastName(line)
            } else if (no == 3){
                johnDoe2.setAge(line.toInteger())
            } else {
                throw new RuntimeException("A person text file should only have 3 lines")
            }
        }

        println johnDoe

        //Writing Files
        File newFile = new File("john-doe2.txt")
        newFile.withWriter("UTF-8") { writer ->
            writer.writeLine(johnDoe2.getFirstName())
            writer.writeLine(johnDoe2.getLastName())
            writer.writeLine(johnDoe2.getAge().toString())
        } 

        //Appending contents to a file
        newFile.append("This is a new line")
        newFile << "2"

        // serializeing an object to a file
        Person pupperGratton = new Person(firstName: "Pupper", lastName: "Gratton", age: 5)
        File binFile = new File("pupper-gratton.bin")
        binFile.withObjectOutputStream { ObjectOutputStream out ->
            out.writeObject(pupperGratton)
        }


     }//end of main method

     static void handlePerson(Closure c, Person p) {
        if(p == null){
            throw new RuntimeException("Person instance is null")
        }
        c(p)
     }
}