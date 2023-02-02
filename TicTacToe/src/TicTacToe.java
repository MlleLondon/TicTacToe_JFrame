import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[][] terrain = new JButton[3][3];
    boolean tourJoueur1 = true;
    int compteurTour = 0;

    public TicTacToe() {
    	//On mets en place la fenêtre
        this.setTitle("Tic Tac Toe");
        this.setBounds(500,200,300,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        Font unePolice= new Font("Arial", Font.BOLD, 20);
		
        //On initialise les 9 boutons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	this.terrain[i][j] = new JButton();
            	this.terrain[i][j].setFont(unePolice);
            	this.terrain[i][j].addActionListener(this);
                panel.add(this.terrain[i][j]);
            }
        }

        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    public void actionPerformed(ActionEvent e) {
    	//Une fois qu'une action est effectué
    	this.compteurTour++;
        JButton clickedButton = (JButton) e.getSource();
        //Si c'est le joueur 1 qui a cliqué le bouton aura X en valeur
        if (this.tourJoueur1) {
            clickedButton.setText("X");
        } else {
        	//Sinon c'est le tour de l'autre joueur, on mets 0
            clickedButton.setText("O");
        }
        //Le bouton cliqué n'est plus cliquable car déjà utilisé
        clickedButton.setEnabled(false);
        //On change de joueur
        this.tourJoueur1 = !this.tourJoueur1;
        //On regarde si y'a un composant gagnant
        this.checkGagnant();
        
        //Si tous les boutons ont été cliqués et qu'il n'y a pas de gagnant cela veut dire qu'il y a égalite
        if (this.compteurTour == 9) {
            JOptionPane.showMessageDialog(this, "Egalité !");
            this.renitialiserJeu();
        }
    }

    public void checkGagnant() {
        // check Lignes
        for (int i = 0; i < 3; i++) {
        	//On regarde s'il y a un composant gagnant dans les lignes
            if (this.terrain[i][0].getText().equals(this.terrain[i][1].getText())
                    && this.terrain[i][0].getText().equals(this.terrain[i][2].getText())
                    && !this.terrain[i][0].getText().equals("")) {
            	
                this.gameOver(this.terrain[i][0].getText());
            }
        }

        // check Colonnes
        for (int i = 0; i < 3; i++) {
        	//On regarde s'il y a un composant gagnant dans les colonnes
            if (this.terrain[0][i].getText().equals(this.terrain[1][i].getText())
                    && this.terrain[0][i].getText().equals(this.terrain[2][i].getText())
                    && !this.terrain[0][i].getText().equals("")) {
                
            	this.gameOver(this.terrain[0][i].getText());
            }
        }

        // check Diagonales
        if (this.terrain[0][0].getText().equals(this.terrain[1][1].getText())
        		//On regarde s'il y a un composant gagnant sur les diagonales
                && this.terrain[0][0].getText().equals(this.terrain[2][2].getText())
                && !this.terrain[0][0].getText().equals("")) {
            
        	this.gameOver(this.terrain[0][0].getText());
        }
        if (this.terrain[0][2].getText().equals(this.terrain[1][1].getText())
                && this.terrain[0][2].getText().equals(this.terrain[2][0].getText())
                && !this.terrain[0][2].getText().equals("")) {
            
        	this.gameOver(this.terrain[0][2].getText());
        }
        //S'il y a un composant gagnant le joueur adverse a perdu
    }

    public void gameOver(String winner) {
    	//Un joueur a gagné on l'indique et on relance le jeu
        JOptionPane.showMessageDialog(this, winner + " a gagné !");
        this.renitialiserJeu();
    }

    public void renitialiserJeu() {
    	//Le jeu est relancé, on mets le carré de jeu vide
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	this.terrain[i][j].setText("");
            	this.terrain[i][j].setEnabled(true);
            }
        }
        //On remet le joueur de base et le compteur
        this.tourJoueur1 = true;
        this.compteurTour = 0;
    }

}