package model;

/**
 * Created by white on 22.04.2017.
 */
public class droidKamikadze extends battleDroid {

    public droidKamikadze(){
        this.accuracy=1;
        this.maxHealth=25;
        this.maxDamage=this.minDamage=999999;
        this.currentHealth=this.maxHealth;
    }

    @Override
    public int getDamage() {
        this.currentHealth=0;
        return maxDamage;
    }

    @Override
    public String toString() {
        return "DK "+ this.currentHealth;
    }
}
