import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class Project5 {
    //WHEN DID YOU FIRST START WORKING ON THIS ASSIGNMENT (date and time): <---------------
    //ANSWER: <---------------

    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) {
        //the train array should populated after the call to readFile
        Train[] train = readFile( "departures2.txt" ); //CHANGE NAME OF FILE TO "departures2.txt" FOR OTHER TEST CASE

        //this is the output stream to the output file, it's passed to the writeReport method
        PrintWriter outStream = null;
        try { outStream = new PrintWriter( "arrivals_michaelBlanco.txt" ); } //CHANGE YOURNAME TO your name <------------
        catch ( FileNotFoundException fnf ) { System.exit( -2 ); }

        //IF YOU WANT TO MAKE SURE YOU READ THE INPUT DATA CORRECTLY AND CREATED YOUR ARRAY AND OBJECTS PROPERLY
        //COMMENT THIS IN
       System.out.println( Arrays.toString( train ) );

        //beginning of output report
        outStream.printf( "%d trains arriving today and tomorrow with total cargo %.2f lbs%n", train.length, Train.getWeightOfAllCargo() );

        //calls to WriteReport for every crew shift today
        writeReport(    0,  759, true, train, outStream );
        writeReport(  800, 1559, true, train, outStream );
        writeReport( 1600, 2359, true, train, outStream );

        //calls to WriteReport for every crew shift today
        writeReport(    0,  759, false, train, outStream );
        writeReport(  800, 1559, false, train, outStream );
        writeReport( 1600, 2359, false, train, outStream );

        //flushes the output stream buffer and finalizes the output file
        outStream.close();
    }//DO NOT ALTER THE MAIN METHOD

    /** readFile - method called from the main with the input file name
     *             this methods reads the file into an array of Train objects
     *
     * visibility - public static
     *
     * @param String inputFileName - name of the file containing the input data
     *
     * @returns Train[]
     */

    public static Train[] readFile(String inputFileName) {
        Scanner in = null;
        try {
            File file = new File(inputFileName);
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("THIS FILE DOES NOT EXIST");
        }
        //assert in != null;


        //INITIALIZING TRAIN ARRAY
          assert in != null;
        int sizeOfTrainArray = in.nextInt();
        Train[] myArray = new Train[sizeOfTrainArray];

            //CREATING EACH NEW TRAINOBJECT AND STORING IT INSIDE THE TRAIN ARRAY
            for (int k = 0; k < myArray.length; k++) {

                //FIXME  if (!in.hasNextInt()) break;

                //PARAMETERS FOR TRAIN
                String origin = in.next();
                int departureTime = in.nextInt();
                int stops = in.nextInt();
                int duration = in.nextInt();
                int sizeOfCargoArray = in.nextInt();
                // creating a cargo array to store Cargo objects
                Cargo[] cargoArray = new Cargo[sizeOfCargoArray];


                for (int j = 0; j < sizeOfCargoArray; j++) {

                    int numberOfItem = in.nextInt();
                    double weightPerItem = in.nextDouble();
                    String dangerous = in.next();
                    String item = in.nextLine();
                    Cargo c = new Cargo(item, numberOfItem, weightPerItem, dangerous);
                    cargoArray[j] =  c ;

                }


                Train currentTrain = new Train(origin, departureTime, stops, duration, cargoArray);

                myArray[k] = currentTrain;

            }
            return myArray;
        }


        /** writeReport - method called from the main to print formatted output for a certain time frame to an output file
         *
         * visibility - public static
         *
         * @param int start - start time of the range of interest
         * @param int end - end time of the range of interest
         * @param boolean today - flag determining if the date range is for same day arrivals or next day arrivals
         * @param Train[] t - the array containing data about the trains an cargo
         * @param PrintWriter out - the output stream object for the output file
         *
         * @returns nothing
         */

        public static void writeReport(int start, int end, boolean today, Train[] t, PrintWriter out){
            for (int i =0; i < t.length; i++){

            String starting = String.valueOf(start);
            int missingZeros = 4 - starting.length();
            for ( int zzz = 0; zzz < missingZeros; zzz++){
                starting = "0" + starting;
            }
            String ending = String.valueOf(end);
            missingZeros = 4 - ending.length();
            for (int zz = 0; zz < missingZeros; zz++){
                ending = "0" + ending;
            }
            String when = (today) ? "today" : "tomorrow";

            int departureTime = 0;
            int arrivalTime = 0 ;
            String origin = null;
            Cargo[] c = null;
           double totalWeight = 0.0 ;
            double weightOfCargo = 0.0;


            for (int k = 0; k < t.length; k++) {
                System.out.printf("Arriving %s %s-%s\n", when, starting, ending);
                //out.printf("Arriving %s %s-%s\n", when, starting, ending);
                departureTime = t[k].getDepartureTime();
                arrivalTime = t[k].getArrivalTime();
                origin = t[k].getOrigin();
                c = t[k].getCargo();
                totalWeight = Train.getWeightOfAllCargo();
                weightOfCargo = t[k].getWeightOfTrainCargo();


            }
            System.out.printf("    %04d: The %04d train departing from %s: ", arrivalTime,departureTime,origin);





        }
}}
