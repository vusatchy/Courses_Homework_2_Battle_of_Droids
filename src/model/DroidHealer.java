package model;

/**
 * Created by white on 22.04.2017.
 */
public class DroidHealer extends SimpleDroid {


        private int healPower;
        private  int critHealRatio;
        private  double critHealProbability;

        public DroidHealer(){
            super(150);
            this.critHealRatio=2;
            this.critHealProbability=0.10;
            this.healPower=50;
          //  this.currentHealth=150;
        }



        public int getHealPower(){
            if(Math.random()<=critHealProbability){
                return healPower*critHealRatio;
            }
            else  return healPower;
        }



    public String toString() {
        return "DH "+ this.currentHealth;
    }

}
