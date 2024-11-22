class Calculator{

    int add(int a, int b){
        a + b
    }
    int subtract(int a, int b){
        a - b
    }
    int multiply(int a, int b){
        a * b
    }
    float divide(int a, int b){
        if(b == 0){
            throw new IllegalArgumentException("Cannot divide by zero")
        }
        a / b
    }

}