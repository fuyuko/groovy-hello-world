class Main{
    static void main(String[] args){
       //Read numbers from files and store them in List
       List<Integer> allNumbers = readAllNumbers()
       println allNumbers

       //find the highest number
       Integer highestNumber = allNumbers.max()
       assert highestNumber == 2044

       //create the sum of all numbers
       Integer sum = allNumbers.sum()
       println sum

    }

    private static List<Integer> readAllNumbers(){
        File challengeDir = new File("challenge")
        List<Integer> allNumbers = []

        challengeDir.eachFile { File file ->
            file.eachLine { String line ->
               if (line.isNumber()){
                   allNumbers << line.toInteger()
               }
            }
        }

        return allNumbers
    }
}