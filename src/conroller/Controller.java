package conroller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import view.MyGUI;

import javax.swing.*;


/**
 * Created by white on 23.04.2017.
 */
public class Controller {
    private MyGUI myGUI;
    private final int ROWS = 2;
    private final int COLUMS = 4;
    

    private Cell[][] allyCell=new Cell[ROWS][COLUMS];
    private Cell[][] enemyCell=new Cell[ROWS][COLUMS];


    private int currentEnemyRow;
    private int currentEnemyColumn;

    private int currentAllyRow;
    private int currentAllyColumn;

    private int currentHealerRow;
    private int currentHealerColumn;

    boolean itsHeal;



    public Controller() {
        myGUI = new MyGUI();
        initialization();
        setEnemyField();
        myGUI.getButtonStep().addActionListener(this::actionPerformedMakeStep);
        myGUI.getButtonReset().addActionListener(this::actionPerformedReset);

    }


    private void initialization(){
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMS;j++){

                allyCell[i][j]=new Cell( myGUI.getMyTeamButtons(i,j),null);
                allyCell[i][j].setAction(this::actionPerformedMyTeam);
                allyCell[i][j].setButtonHiddenStr(new Integer(i).toString()+new Integer(j).toString());

                enemyCell[i][j]=new Cell( myGUI.getButtonsEnemy(i,j),null);
                enemyCell[i][j].setAction(this::actionPerformedEnemy);
                enemyCell[i][j].setButtonHiddenStr(new Integer(i).toString()+new Integer(j).toString());

            }
        }
    }

    private void setEnemyField() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMS; j++) {
                    enemyCell[i][j].setRandomDroid();
                }

            }
        }


    private void botMakeStep(){
        Cell enemyCell = getRandomEnemy();
        if(enemyCell.isInstanceOfBattleDroid()){
            int dmg=enemyCell.getDamage();
            getRandomAlly().getHit(dmg);
            System.out.println(enemyCell.getDroid().toString()+" make "+dmg+" dmg");
        }
        if(enemyCell.isInstanceOfHealerDriod()){
            int heal=enemyCell.getHealPower();
            getRandomEnemy().getHeal(heal);
            System.out.println(enemyCell.getDroid().toString()+" make "+heal+" heal");
        }

    }

    private Cell getRandomEnemy(int row, int column){
        Random random = new Random();
        if(!enemyCell[row][column].isNull()){
            return enemyCell[row][column];
        }
        else return getRandomEnemy(random.nextInt(ROWS),random.nextInt(COLUMS));

    }

    private Cell getRandomEnemy(){
        Random random = new Random();
        if(!enemyCell[random.nextInt(ROWS)][random.nextInt(COLUMS)].isNull()){
            return enemyCell[random.nextInt(ROWS)][random.nextInt(COLUMS)];
        }
        else return getRandomEnemy(random.nextInt(ROWS),random.nextInt(COLUMS));
    }


    private Cell getRandomAlly(int row, int column){
        Random random = new Random();
        if(!allyCell[row][column].isNull()){
            return allyCell[row][column];
        }
        else return getRandomAlly(random.nextInt(ROWS),random.nextInt(COLUMS));

    }

    private Cell getRandomAlly(){
        Random random = new Random();
        if(!allyCell[random.nextInt(ROWS)][random.nextInt(COLUMS)].isNull()){
            return allyCell[random.nextInt(ROWS)][random.nextInt(COLUMS)];
        }
        else return getRandomAlly(random.nextInt(ROWS),random.nextInt(COLUMS));
    }

     private void findWinner() {

         int enemyCounter = 0;
         int myCounter = 0;
         for (int i = 0; i < ROWS; i++) {
             for (int j = 0; j < COLUMS; j++) {
                 if (!enemyCell[i][j].isNull()) enemyCounter++;
                 if (!allyCell[i][j].isNull()) myCounter++;
             }
         }
         if (enemyCounter == 0) {

             JOptionPane.showMessageDialog(null,
                     "You win!",
                     "Results",
                     JOptionPane.PLAIN_MESSAGE);
             initialization();
             setEnemyField();

         }

         if (myCounter == 0) {
             JOptionPane.showMessageDialog(null,
                     "Enemy win!",
                     "Results",
                     JOptionPane.PLAIN_MESSAGE);
             initialization();
             setEnemyField();
         }

     }



    public void update(){
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMS;j++){
                allyCell[i][j].update();
                enemyCell[i][j].update();
            }
        }

    }


     private void resetMyTeam(){

         for (int i = 0; i < ROWS; i++) {
             for (int j = 0; j < COLUMS; j++) {
                allyCell[i][j]=null;
                myGUI.getMyTeamButtons(i,j).setText("");
             }
         }
     }


    //<-----------ACTIONS---------------->

    private void actionPerformedEnemy(ActionEvent e) {

        JButton button = (JButton)e.getSource();
        int currentRow=Character.getNumericValue(button.getName().charAt(0));
        int currentColumn=Character.getNumericValue(button.getName().charAt(1));
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        button.setBackground(Color.red);
    }




    private void actionPerformedMyTeam(ActionEvent e) {

        JButton button = (JButton)e.getSource();
        if(button.getText().equals("")) {
            if (myGUI.getBattleDroidRadioButton().isSelected() == true) {
                button.setText("Creating BD");
            } else if (myGUI.getDroidSniperRadioButton().isSelected() == true ) {
                button.setText("Creating DS");
            } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true ) {
                button.setText("Creating DK");
            } else if (myGUI.getDroidHealerRadioButton().isSelected() == true ) {
                button.setText("Creating DH");
            }
        }
        else {
            int currentRow=Character.getNumericValue(button.getName().charAt(0));
            int currentColumn=Character.getNumericValue(button.getName().charAt(1));

            if (allyCell[currentRow][currentColumn] != null && allyCell[currentRow][currentColumn].isInstanceOfBattleDroid()) {
                currentAllyRow = currentRow;
                currentAllyColumn = currentColumn;
                button.setBackground(Color.BLUE);

            } else if (allyCell[currentRow][currentColumn] != null && allyCell[currentRow][currentColumn].isInstanceOfHealerDriod()) {
                itsHeal=true;
                currentHealerRow = currentRow;
                currentHealerColumn = currentColumn;
                button.setBackground(Color.BLUE);
            }

        }
    }

    private void actionPerformedMakeStep(ActionEvent e){
        if (!itsHeal) {
            if (!enemyCell[currentEnemyRow][currentEnemyColumn].isNull()&&!allyCell[currentAllyRow][currentAllyColumn].isNull() ) {
                enemyCell[currentEnemyRow][currentEnemyColumn].getHit(allyCell[currentAllyRow][currentAllyColumn].getDamage());
            }
        } else {
            if (!allyCell[currentAllyRow][currentAllyColumn] .isNull()&&!allyCell[currentHealerRow][currentHealerColumn].isNull()) {
                allyCell[currentAllyRow][currentAllyColumn].getHeal(allyCell[currentHealerRow][currentHealerColumn].getHealPower());
            }
        }

        enemyCell[currentEnemyRow][currentEnemyColumn].setButtonColour(Color.white);
        allyCell[currentAllyRow][currentAllyColumn].setButtonColour(Color.white);
        allyCell[currentHealerRow][currentHealerColumn].setButtonColour(Color.white);

        currentEnemyRow = 0;
        currentEnemyColumn = 0;

        currentAllyRow = 0;
        currentAllyColumn = 0;

        currentHealerRow = 0;
        currentHealerColumn = 0;


        itsHeal=false;
        findWinner();
        botMakeStep();
        findWinner();
    }

    private void actionPerformedReset(ActionEvent e){
        initialization();
        setEnemyField();

    }



}
