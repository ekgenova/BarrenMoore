public class PointOfInterest {

    /*Variables that define a point of interest.
    Distance from origin in each direction and name
     */
    private String name;
    private int distFromOriginNorth;
    private int distFromOriginSouth;
    private int distFromOriginEast;
    private int distFromOriginWest;

    //Constructor of PointOfInterest
    public PointOfInterest(String name, int distFromOriginNorth, int distFromOriginSouth, int distFromOriginEast, int distFromOriginWest){
        this.name = name;
        this.distFromOriginNorth = distFromOriginNorth;
        this.distFromOriginSouth = distFromOriginSouth;
        this.distFromOriginEast = distFromOriginEast;
        this.distFromOriginWest = distFromOriginWest;
    }

    //Getters of point of interest

    public String getName() {
        return name;
    }

    public int getDistFromOriginNorth() {
        return distFromOriginNorth;
    }

    public int getDistFromOriginSouth() {
        return distFromOriginSouth;
    }

    public int getDistFromOriginEast() {
        return distFromOriginEast;
    }

    public int getDistFromOriginWest() {
        return distFromOriginWest;
    }

    //Setters of point of interest

    public void setName(String name) {
        this.name = name;
    }

    public void setDistFromOriginNorth(int distFromOriginNorth) {
        this.distFromOriginNorth = distFromOriginNorth;
    }

    public void setDistFromOriginSouth(int distFromOriginSouth) {
        this.distFromOriginSouth = distFromOriginSouth;
    }

    public void setDistFromOriginEast(int distFromOriginEast) {
        this.distFromOriginEast = distFromOriginEast;
    }

    public void setDistFromOriginWest(int distFromOriginWest) {
        this.distFromOriginWest = distFromOriginWest;
    }
}
