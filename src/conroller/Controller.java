package conroller;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.jar.JarEntry;

import view.MyGUI;

import javax.swing.*;


/**
 * Created by white on 23.04.2017.
 */
public class Controller {
    private MyGUI myGUI;
    private final int ROWS = 2;
    private final int COLUMS = 4;
    private simpleDroid[][] myDroids = new model.simpleDroid[ROWS][COLUMS];
    private simpleDroid[][] enemyDroids = new model.simpleDroid[ROWS][COLUMS];
    private int currentRow;
    private int currentColumn;


    private int currentEnemyRow;
    private int currentEnemyColumn;

    private int currentMyDroidRow;
    private int currentMyDroidColumn;

    private int currentTargetRow;
    private int currentTargetColumn;

    boolean itsHeal;

    private int damageSender = 0;
    private int healSender = 0;


    public Controller() {
        myGUI = new MyGUI();
        setEnemyField(myGUI.getEnemyButtons());


        myGUI.getButtonMyTeam1().addActionListener(this::actionPerformedMyTeam1);
        myGUI.getButtonMyTeam2().addActionListener(this::actionPerformedMyTeam2);
        myGUI.getButtonMyTeam3().addActionListener(this::actionPerformedMyTeam3);
        myGUI.getButtonMyTeam4().addActionListener(this::actionPerformedMyTeam4);
        myGUI.getButtonMyTeam5().addActionListener(this::actionPerformedMyTeam5);
        myGUI.getButtonMyTeam6().addActionListener(this::actionPerformedMyTeam6);
        myGUI.getButtonMyTeam7().addActionListener(this::actionPerformedMyTeam7);
        myGUI.getButtonMyTeam8().addActionListener(this::actionPerformedMyTeam8);

        myGUI.getButtonEnemy1().addActionListener(this::actionPerformedEnemy1);
        myGUI.getButtonEnemy2().addActionListener(this::actionPerformedEnemy2);
        myGUI.getButtonEnemy3().addActionListener(this::actionPerformedEnemy3);
        myGUI.getButtonEnemy4().addActionListener(this::actionPerformedEnemy4);
        myGUI.getButtonEnemy5().addActionListener(this::actionPerformedEnemy5);
        myGUI.getButtonEnemy6().addActionListener(this::actionPerformedEnemy6);
        myGUI.getButtonEnemy7().addActionListener(this::actionPerformedEnemy7);
        myGUI.getButtonEnemy8().addActionListener(this::actionPerformedEnemy8);


        myGUI.getButtonStep().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!itsHeal) {
                    if (enemyDroids[currentEnemyRow][currentEnemyColumn] != null ) {
                        enemyDroids[currentEnemyRow][currentEnemyColumn].getHit(((battleDroid) myDroids[currentMyDroidRow][currentMyDroidColumn]).getDamage());
                        myGUI.getButtonsEnemy(currentEnemyRow, currentEnemyColumn).setText(enemyDroids[currentEnemyRow][currentEnemyColumn].toString());
                        if (!enemyDroids[currentEnemyRow][currentEnemyColumn].isAlive()) {
                            myGUI.getButtonsEnemy(currentEnemyRow, currentEnemyColumn).setText("");
                            enemyDroids[currentEnemyRow][currentEnemyColumn]=null;
                        }
                        if (!myDroids[currentMyDroidRow][currentMyDroidColumn].isAlive()) {
                            myGUI.getMyTeamButtons(currentMyDroidRow, currentMyDroidColumn).setText("");
                            myDroids[currentMyDroidRow][currentMyDroidColumn]=null;
                        }
                    }
                } else {
                    if (myDroids[currentMyDroidRow][currentMyDroidColumn] != null ) {
                        myDroids[currentMyDroidRow][currentMyDroidColumn].getHeal(((droidHealer)  myDroids[currentTargetRow][currentTargetColumn]).getHealPower());
                        myGUI.getMyTeamButtons(currentMyDroidRow, currentMyDroidColumn).setText(myDroids[currentMyDroidRow][currentMyDroidColumn].toString());
                    }
                }
                myGUI.getMyTeamButtons(currentMyDroidRow, currentMyDroidColumn).setBackground(Color.white);
                myGUI.getButtonsEnemy(currentEnemyRow, currentEnemyColumn).setBackground(Color.white);
                myGUI.getMyTeamButtons(currentTargetRow, currentTargetColumn).setBackground(Color.white);
                currentEnemyRow = 0;
                currentEnemyColumn = 0;

                currentMyDroidRow = 0;
                currentMyDroidColumn = 0;

                currentTargetRow = 0;
                currentTargetColumn = 0;

                itsHeal=false;
                findWinner();
                botMakeStep();
                findWinner();
                System.out.println();

            }
        });

        myGUI.getButtonReset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnemyField(myGUI.getEnemyButtons());
                resetMyTeam();
            }
        });
    }


    private void setEnemyField(JButton[][] buttons) {
        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMS; j++) {
                int someValue = random.nextInt(4);
                switch (someValue) {
                    case 0:
                        enemyDroids[i][j] = new battleDroid();
                        buttons[i][j].setText(enemyDroids[i][j].toString());
                        break;
                    case 1:
                        enemyDroids[i][j] = new droidHealer();
                        buttons[i][j].setText(enemyDroids[i][j].toString());
                        break;
                    case 2:
                        enemyDroids[i][j] = new droidSniper();
                        buttons[i][j].setText(enemyDroids[i][j].toString());
                        break;
                    case 3:
                        enemyDroids[i][j] = new droidKamikadze();
                        buttons[i][j].setText(enemyDroids[i][j].toString());
                        break;

                }

            }
        }
    }


    public void botMakeStep() {
         mainLoop:for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMS;j++){
                if(enemyDroids[i][j]!=null)
                {
                    if (enemyDroids[i][j] instanceof battleDroid){
                        damageSender =((battleDroid) enemyDroids[i][j]).getDamage();
                        for (int z=0;z<ROWS;z++){
                            for(int t=0;t<COLUMS;t++){
                                if(myDroids[z][t]!=null){
                                    myDroids[z][t].getHit(damageSender);
                                    System.out.println(damageSender);

                                    if(!enemyDroids[i][j].isAlive()){
                                        myGUI.getButtonsEnemy(i,j).setText("");
                                        enemyDroids[i][j]=null;

                                    }

                                    if(!myDroids[z][t].isAlive()){
                                        myGUI.getMyTeamButtons(z,t).setText("");
                                        myDroids[z][t]=null;

                                    }
                                    else  myGUI.getMyTeamButtons(z,t).setText(myDroids[z][t].toString());
                                    break mainLoop;
                                }
                            }
                        }

                    }
                    if (enemyDroids[i][j] instanceof droidHealer){
                        healSender=((droidHealer)enemyDroids[i][j]).getHealPower();
                        for (int z=0;z<ROWS;z++){
                            for(int t=0;t<COLUMS;t++){
                                if(enemyDroids[z][t]!=null){
                                    enemyDroids[z][t].getHeal(healSender);
                                    myGUI.getButtonsEnemy(z,t).setText(enemyDroids[z][t].toString());
                                    break mainLoop;
                                }
                            }
                        }

                    }
                }
            }
        }

    }

     public void findWinner() {

         int enemyCounter = 0;
         int myCounter = 0;
         for (int i = 0; i < ROWS; i++) {
             for (int j = 0; j < COLUMS; j++) {
                 if (enemyDroids[i][j] != null) enemyCounter++;
                 if (myDroids[i][j] != null) myCounter++;
             }
         }
         if (enemyCounter == 0) {

             JOptionPane.showMessageDialog(null,
                     "You win!",
                     "Results",
                     JOptionPane.PLAIN_MESSAGE);
         }

         if (myCounter == 0) {
             JOptionPane.showMessageDialog(null,
                     "Enemy win!",
                     "Results",
                     JOptionPane.PLAIN_MESSAGE);
         }
     }




    //First chaos

     private void resetMyTeam(){

         for (int i = 0; i < ROWS; i++) {
             for (int j = 0; j < COLUMS; j++) {
                myDroids[i][j]=null;
                myGUI.getMyTeamButtons(i,j).setText("");
             }
         }
     }

    public void actionPerformedEnemy1(ActionEvent e) {
        currentColumn = 0;
        currentRow = 0;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy2(ActionEvent e) {
        currentColumn = 1;
        currentRow = 0;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy3(ActionEvent e) {
        currentColumn = 2;
        currentRow = 0;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy4(ActionEvent e) {
        currentColumn = 3;
        currentRow = 0;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy5(ActionEvent e) {
        currentColumn = 0;
        currentRow = 1;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy6(ActionEvent e) {
        currentColumn = 1;
        currentRow = 1;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy7(ActionEvent e) {
        currentColumn = 2;
        currentRow = 1;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    public void actionPerformedEnemy8(ActionEvent e) {
        currentColumn = 3;
        currentRow = 1;
        currentEnemyRow = currentRow;
        currentEnemyColumn = currentColumn;
        myGUI.getButtonsEnemy(currentRow, currentColumn).setBackground(Color.red);
    }

    // Secon chaos statrs here

    public void actionPerformedMyTeam1(ActionEvent e) {
        currentRow = 0;
        currentColumn = 0;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam1().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam1().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam1().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam1().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam1().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam1().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam1().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam1().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {
                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {
                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }

    public void actionPerformedMyTeam2(ActionEvent e) {
        currentRow = 0;
        currentColumn = 1;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam2().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam2().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam2().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam2().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam2().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam2().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam2().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam2().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {
                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {
                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }

    public void actionPerformedMyTeam3(ActionEvent e) {
        currentRow = 0;
        currentColumn = 2;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam3().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam3().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam3().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam3().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam3().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam3().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam3().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam3().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {
                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }

    public void actionPerformedMyTeam4(ActionEvent e) {
        currentRow = 0;
        currentColumn = 3;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam4().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam4().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam4().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam4().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam4().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam4().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam4().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam4().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {

                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {

                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }

    public void actionPerformedMyTeam5(ActionEvent e) {
        currentRow = 1;
        currentColumn = 0;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam5().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam5().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam5().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam5().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam5().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam5().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam5().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam5().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {

                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {

                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }
        }
    }

    public void actionPerformedMyTeam6(ActionEvent e) {
        currentRow = 1;
        currentColumn = 1;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam6().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam6().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam6().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam6().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam6().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam6().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam6().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam6().setText(myDroids[currentRow][currentColumn].toString());
        } else {
            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {

                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {

                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }

    public void actionPerformedMyTeam7(ActionEvent e) {
        currentRow = 1;
        currentColumn = 2;
        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam7().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam7().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam7().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam7().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam7().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam7().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam7().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam7().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {
                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {
                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }
        }
    }

    public void actionPerformedMyTeam8(ActionEvent e) {
        currentRow = 1;
        currentColumn = 3;

        if (myGUI.getBattleDroidRadioButton().isSelected() == true && myGUI.getButtonMyTeam8().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new battleDroid();
            myGUI.getButtonMyTeam8().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidSniperRadioButton().isSelected() == true && myGUI.getButtonMyTeam8().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidSniper();
            myGUI.getButtonMyTeam8().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidKamikadzeRadioButton().isSelected() == true && myGUI.getButtonMyTeam8().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidKamikadze();
            myGUI.getButtonMyTeam8().setText(myDroids[currentRow][currentColumn].toString());
        } else if (myGUI.getDroidHealerRadioButton().isSelected() == true && myGUI.getButtonMyTeam8().getText().equals("")) {
            myDroids[currentRow][currentColumn] = new droidHealer();
            myGUI.getButtonMyTeam8().setText(myDroids[currentRow][currentColumn].toString());
        } else {

            if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof battleDroid) {
                currentMyDroidRow = currentRow;
                currentMyDroidColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);

            } else if (myDroids[currentRow][currentColumn] != null && myDroids[currentRow][currentColumn] instanceof droidHealer) {
                itsHeal=true;
                currentTargetRow = currentRow;
                currentTargetColumn = currentColumn;
                myGUI.getMyTeamButtons(currentRow, currentColumn).setBackground(Color.BLUE);
            }

        }
    }
}












