package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by white on 23.04.2017.
 */
public class MyGUI  extends JFrame{
    private final int ROWS=2;
    private final int COLUMS=4;
    private JPanel rootPanel;
    private JRadioButton battleDroidRadioButton;
    private JButton buttonEnemy1;
    private JButton buttonEnemy2;
    private JButton buttonEnemy3;
    private JButton buttonEnemy4;
    private JButton buttonEnemy5;
    private JButton buttonEnemy6;
    private JButton buttonEnemy7;
    private JButton buttonEnemy8;
    private JButton buttonMyTeam1;
    private JButton buttonMyTeam2;
    private JButton[][] myTeamButtons;
    private JButton[][] enemyButtons;

    public JRadioButton getBattleDroidRadioButton() {
        return battleDroidRadioButton;
    }

    public JButton getButtonEnemy1() {
        return buttonEnemy1;
    }

    public JButton getButtonEnemy2() {
        return buttonEnemy2;
    }

    public JButton getButtonEnemy3() {
        return buttonEnemy3;
    }

    public JButton getButtonEnemy4() {
        return buttonEnemy4;
    }

    public JButton getButtonEnemy5() {
        return buttonEnemy5;
    }

    public JButton getButtonEnemy6() {
        return buttonEnemy6;
    }

    public JButton getButtonEnemy7() {
        return buttonEnemy7;
    }

    public JButton getButtonEnemy8() {
        return buttonEnemy8;
    }

    public JButton getButtonMyTeam1() {
        return buttonMyTeam1;
    }

    public JButton getButtonMyTeam2() {
        return buttonMyTeam2;
    }

    public JButton getButtonMyTeam3() {
        return buttonMyTeam3;
    }

    public JButton getButtonMyTeam4() {
        return buttonMyTeam4;
    }

    public JButton getButtonMyTeam5() {
        return buttonMyTeam5;
    }

    public JButton getButtonMyTeam6() {
        return buttonMyTeam6;
    }

    public JButton getButtonMyTeam7() {
        return buttonMyTeam7;
    }

    public JButton getButtonMyTeam8() {
        return buttonMyTeam8;
    }

    public JRadioButton getDroidSniperRadioButton() {
        return droidSniperRadioButton;
    }

    public JRadioButton getDroidHealerRadioButton() {
        return droidHealerRadioButton;
    }

    public JRadioButton getDroidKamikadzeRadioButton() {
        return droidKamikadzeRadioButton;
    }

    private JButton buttonMyTeam3;
    private JButton buttonMyTeam4;
    private JButton buttonMyTeam5;
    private JButton buttonMyTeam6;
    private JButton buttonMyTeam7;
    private JButton buttonMyTeam8;
    private JRadioButton droidSniperRadioButton;
    private JRadioButton droidHealerRadioButton;
    private JRadioButton droidKamikadzeRadioButton;
    private JButton buttonStep;

    public JButton getButtonStep() {
        return buttonStep;
    }

    public JButton getButtonReset() {
        return buttonReset;
    }

    private JButton buttonReset;

    public MyGUI(){
        setContentPane(rootPanel);
        setVisible(true);
        setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTeamButtons = new JButton[ROWS][COLUMS];

        myTeamButtons[0][0]= buttonMyTeam1;
        myTeamButtons[0][1]= buttonMyTeam2;
        myTeamButtons[0][2]= buttonMyTeam3;
        myTeamButtons[0][3]= buttonMyTeam4;

        myTeamButtons[1][0]= buttonMyTeam5;
        myTeamButtons[1][1]= buttonMyTeam6;
        myTeamButtons[1][2]= buttonMyTeam7;
        myTeamButtons[1][3]= buttonMyTeam8;

        enemyButtons = new JButton[ROWS][COLUMS];

        enemyButtons[0][0]= buttonEnemy1;
        enemyButtons[0][1]= buttonEnemy2;
        enemyButtons[0][2]= buttonEnemy3;
        enemyButtons[0][3]= buttonEnemy4;

        enemyButtons[1][0]= buttonEnemy5;
        enemyButtons[1][1]= buttonEnemy6;
        enemyButtons[1][2]= buttonEnemy7;
        enemyButtons[1][3]= buttonEnemy8;

        buttonMyTeam1.setName("hello");
        buttonMyTeam2.setToolTipText("Hello");
        buttonMyTeam3.setToolTipText("Hello");
        buttonMyTeam4.setToolTipText("Hello");
        buttonMyTeam5.setToolTipText("Hello");


    }

    public void  reset(){
        setContentPane(rootPanel);
        setVisible(true);
        setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTeamButtons = new JButton[ROWS][COLUMS];

        myTeamButtons[0][0]= buttonMyTeam1;
        myTeamButtons[0][1]= buttonMyTeam2;
        myTeamButtons[0][2]= buttonMyTeam3;
        myTeamButtons[0][3]= buttonMyTeam4;

        myTeamButtons[1][0]= buttonMyTeam5;
        myTeamButtons[1][1]= buttonMyTeam6;
        myTeamButtons[1][2]= buttonMyTeam7;
        myTeamButtons[1][3]= buttonMyTeam8;

        enemyButtons = new JButton[ROWS][COLUMS];

        enemyButtons[0][0]= buttonEnemy1;
        enemyButtons[0][1]= buttonEnemy2;
        enemyButtons[0][2]= buttonEnemy3;
        enemyButtons[0][3]= buttonEnemy4;

        enemyButtons[1][0]= buttonEnemy5;
        enemyButtons[1][1]= buttonEnemy6;
        enemyButtons[1][2]= buttonEnemy7;
        enemyButtons[1][3]= buttonEnemy8;

    }
    public  JButton getMyTeamButtons(int i, int j){
        return myTeamButtons[i][j];
    }

    public  JButton getButtonsEnemy(int i,int j){
        return enemyButtons[i][j];
    }

    public JButton[][] getEnemyButtons(){
        return enemyButtons;
    }

}
