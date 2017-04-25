package model;

/**
 * Created by white on 23.04.2017.
 */
public class SimpleDroid {
    protected int maxHealth;
    protected int currentHealth;

    public SimpleDroid(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }

    public SimpleDroid() {
        this.maxHealth = 25;
        this.currentHealth = this.maxHealth;
    }
        public void getHit (int damage) {
            this.currentHealth -= damage;
        }

        public boolean isAlive() {
            return currentHealth > 0;
        }



        public int getCurrentHealth() {
            return currentHealth;
        }

        public  void  getHeal(int heal){
            this.currentHealth+=heal;
            if (this.currentHealth>this.maxHealth){
                this.currentHealth=this.maxHealth;
            }
    }

}
