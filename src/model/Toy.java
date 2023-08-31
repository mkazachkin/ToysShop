package model;

public class Toy {
    private final Integer id;
    private final String name;
    private final Integer chanceWeight;
    public Toy(int id, String name, int chanceWeight) {
        this.id = id;
        this.name = name;
        this.chanceWeight = chanceWeight;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public int getChanceWeight() {
        return this.chanceWeight;
    }

    public boolean equals (Toy toy) {
        return (this.id == toy.getId() && this.name.equals(toy.getName()) &&
                this.chanceWeight == toy.getChanceWeight());
    }
    @Override
    public String toString () {
        return "ID: " + this.id + " Наименование: " + this.name;
    }
}
