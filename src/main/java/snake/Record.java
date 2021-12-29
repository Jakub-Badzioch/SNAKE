package snake;

public class Record {
    private String name;
    private int points;

    public Record(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
