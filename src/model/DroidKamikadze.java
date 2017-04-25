package model;

/**
 * Created by white on 22.04.2017.
 */
public class DroidKamikadze extends BattleDroid {

    public DroidKamikadze(){
        super(50,999999,999999,1);
       // this.accuracy=1;
       // this.maxHealth=25;
       // this.maxDamage=this.minDamage=999999;
       // this.currentHealth=this.maxHealth;
    }

    @Override
    public int getDamage() {
        getHit(maxDamage);
        return maxDamage;
    }

    @Override
    public String toString() {
        if(this.isAlive()) {
            return "DK " + this.currentHealth;
        }
        else return  "";
    }
}
