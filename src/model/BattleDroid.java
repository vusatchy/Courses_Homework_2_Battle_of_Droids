package model;

/**
 * Created by white on 22.04.2017.
 */
public class BattleDroid extends SimpleDroid {


        //protected int maxHealth;
      //  protected int currentHealth;
        protected int maxDamage;
        protected int minDamage;
        protected double accuracy;



        public BattleDroid(int maxHealth, int maxDamage, int minDamage, double accuracy) {
            super(maxHealth);
            this.maxDamage = maxDamage;
            this.minDamage = minDamage;
            this.accuracy = accuracy;
          //  this.maxHealth=maxHealth;
            //this.currentHealth=this.maxHealth;
        }

         public BattleDroid(){
             //this.maxHealth=200;
           //  this.currentHealth=this.maxHealth;
             super(200);
             this.maxDamage = 15;
             this.minDamage = 30;
             this.accuracy =  0.65;

         }


    @Override
    public void getHit(int damage) {
        this.currentHealth-=damage;
    }
    public int getCurrentHealth(){
             return  this.currentHealth;
    }

    public  int getDamage(){
            int max=maxDamage+1;
            int min=minDamage;
            if(Math.random()<=accuracy){
                 return (int) (Math.random() * (max - min)) + min;
            }
            else return  0;
        }

    @Override
    public String toString() {
        return "BD "+getCurrentHealth();
    }

}
