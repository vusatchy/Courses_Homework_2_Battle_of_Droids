package model;

/**
 * Created by white on 22.04.2017.
 */
public class DroidSniper extends BattleDroid {

    private  int critDamageRatio;
    private  double critDamageProbability;

    public DroidSniper(){
        super(100,50,25,0.95);
        this.critDamageProbability =0.15;
        this.critDamageRatio =2;
        //this.maxHealth=100;
       // this.currentHealth=this.maxHealth;
       // this.maxDamage=50;
       // this.minDamage=25;
        //this.accuracy=0.95;
    }

    @Override
    public int getDamage() {
        int max=maxDamage+1;
        int min=minDamage;
        if(Math.random()<=accuracy){
            if (Math.random()<= critDamageProbability) {
                return ((int) (Math.random() * (max - min)) + min)* critDamageRatio;
            }
            else return (int) (Math.random() * (max - min)) + min;
        }
        else return  0;
    }
    public String toString() {
        return "DS "+currentHealth;
    }
}
