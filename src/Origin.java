public class Origin {

    /*Variables of the origin point. This is where the player starts. All values are permanently 0.
     */

    private int north;
    private int south;
    private int east;
    private int west;

    //Constructor of Origin point

    public Origin(int north, int south, int east, int west){
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    //Getters for origin point


    public int getNorth() {
        return north;
    }

    public int getSouth() {
        return south;
    }

    public int getEast() {
        return east;
    }

    public int getWest() {
        return west;
    }

    //Setters for origin point


    public void setNorth(int north) {
        this.north = north;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public void setWest(int west) {
        this.west = west;
    }

}
