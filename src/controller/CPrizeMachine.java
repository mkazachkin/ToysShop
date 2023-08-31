package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Toy;

public class CPrizeMachine {
    private Integer totalWeight;
    private final ArrayList<Toy> prizePool = new ArrayList<>();
   public CPrizeMachine (){
        this.totalWeight = 0;
    }
    public void addToys (Toy toy, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.prizePool.add(toy);
            this.totalWeight += toy.getChanceWeight();
        }
    }
    public void delToys (Toy toy, int quantity) {
        int counter = 0;
        boolean flag = false;
        for (int i = 0; i < this.prizePool.size(); i++) {
            if (!flag && toy.equals(this.prizePool.get(i)) && counter < quantity) {
                counter++;
                this.totalWeight -= this.prizePool.remove(i).getChanceWeight();
                if (counter == quantity) {
                    flag = true;
                }
            }
        }
    }
    public Toy getPrize () {
        int n = new Random().nextInt(this.totalWeight+1);
        int chance = 0;
        for (Toy toy : this.prizePool) {
            chance += toy.getChanceWeight();
            if (n <= chance) {
                this.delToys(toy, 1);
                return toy;
            }
        }
        return null;
    }
    public boolean toysAvailable () {
        return this.totalWeight > 0;
    }
}
