public class Cargo {
    private String item;
    private int numberOfItems;
    private double weightPerIterm;
    private double totalWeight;
    private boolean hazardous;



    public Cargo(){
        this.item = null;
        this.numberOfItems = 0;
        this.weightPerIterm = 0.0;
        this.totalWeight = 0.0;
        this.hazardous = false;

    }

    public Cargo(String item, int numberOfItems, double weightPerIterm, String hazard){
        this.item = item;
        this.numberOfItems = numberOfItems;
        this.weightPerIterm = weightPerIterm;
        this.totalWeight = this.numberOfItems * this.weightPerIterm;
        this.hazardous = hazard.equals("yes");
    }
    public String getItem(){
        return this.item;
    }
    public int getNumberOfItems(){
        return this.numberOfItems;
    }
    public double getTotalWeight(){
        return this.totalWeight;
    }

    public boolean getHazardous(){
        return this.hazardous;
    }



    /** DO NOT ALTER THIS METHOD
     * toString - method to print a Cargo object for testing
     * @returns String
     */
    @Override
    public String toString() {
        return String.format( "%d %s%s %.2flbs", this.numberOfItems, this.item,
                (this.hazardous ?  " (h)" : "" ), this.totalWeight );
    }//DO NOT ALTER THIS METHOD
}