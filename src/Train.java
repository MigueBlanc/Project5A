import java.util.Arrays;

public class Train {
    private String origin;
    private int departureTime;
    private int arrivalTime;
    private boolean sameDayArrival;
    private Cargo[] cargo;
    private double weightOfTrainCargo;
    private static double weightOfAllCargo;

    public Train(){
        this.origin = null;
        this.departureTime = 0;
        this.arrivalTime = 0;
        this.sameDayArrival = false;
        this.cargo = null;
        this.weightOfTrainCargo = 0.0;
    }


    public Train(String origin, int departureTime, int stops, int duration, Cargo[] c){

        this.origin = origin;
        this.departureTime = departureTime;

        this.arrivalTime = 0; // FIXME
        this.sameDayArrival = false; // FIXME


        this.cargo = c;


        //FORMAT: 0000-2400
        calculateArrivalTime(stops,duration);

        // TRAVERSING THROUGH CARGO OBJECT ARRAY AND GETTING THE WEIGHT OF EACH OBJECT.
        for(int i = 0; i < this.cargo.length; i++)
        {
            this.weightOfTrainCargo += c[i].getTotalWeight();
        }
        weightOfAllCargo += this.weightOfTrainCargo;
    }


    public void calculateArrivalTime(int stops, int duration){


        // stops * 10 =
        int currentTime = this.departureTime;
        int totalTime = duration + (stops * 10);
        // mod to get the minutes and hours == interger which have to be multiplied to hundreds.

        int hours = totalTime/60;
        int minutes = totalTime % 60;
        currentTime += (hours * 100) + minutes;



        // CHECKING FOR EDGE CASE WHEN MINUTES ARE 60 EXACT
        // IF ( X-Y = 40) MEANS the two LSB are 60.
        int x = ((currentTime/100) + 1) * 100;
        int check = x - currentTime;
        if (check == 40)
            currentTime += 40;

        if(currentTime <= 2400)
            this.sameDayArrival = true ;

        if(currentTime >= 2400) {
            this.sameDayArrival = false;
            currentTime -= 2400;
        }

        this.arrivalTime = currentTime;






    }

    public String getOrigin(){
        return this.origin;
    }



    public int getDepartureTime(){
        return departureTime;
    }


    public int getArrivalTime(){
        return this.arrivalTime;
    }


    public boolean getSameDayArrival(){
        return this.sameDayArrival;
    }

    public Cargo[] getCargo() {
        return this.cargo;
    }


    public double getWeightOfTrainCargo() {
        return weightOfTrainCargo;
    }
    public static double getWeightOfAllCargo() {
        return weightOfAllCargo;
    }
    /** DO NOT ALTER THIS METHOD
     * toString - method to print a Train object for testing
     * @param
     * @returns String
     */
    @Override
    public String toString() {
        return String.format( "%nTrain: %s-%s from %s %s, %.2flbs Cargo: %s", this.departureTime, this.arrivalTime, this.origin,
                ( this.sameDayArrival ? "same day" : "next day" ), this.weightOfTrainCargo, Arrays.toString( this.cargo ) );
    }// DO NOT ALTER THIS METHOD
}
