// Written by Siddh Patel
class Sheep implements Comparable<Sheep> {
    String name;
    int shearTime;
    int arrivalTime;

    // constructor.
    public Sheep(String name, int shearTime, int arrivalTime) {
        this.name = name;
        this.shearTime = shearTime;
        this.arrivalTime = arrivalTime;
    }

    // implementing Comparable for MinHeap.
    @Override
    public int compareTo(Sheep other) {
        if (this.shearTime == other.shearTime) {
            return this.name.compareTo(other.name); // if shear times are equal, compare by name.
        }
        return Integer.compare(this.shearTime, other.shearTime); // otherwise, compare by shear time.
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Shear Time: " + shearTime + ", Arrival Time: " + arrivalTime;
    }
}