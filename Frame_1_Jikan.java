package COMPILATION_JIKAN;

import COMPILATION_JIKAN.Frame_2_Jikan_New_Annee.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Frame_1_Jikan  {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		Frame_1_Choix  f = new Frame_1_Choix ("Frame_1_Choix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(520,360);
		f.setVisible(true);  
	}
 }); 
}


}

class Frame_1_Choix extends JFrame {

	private GridBagConstraints gbc;
    private JPanel p;

    
	public Frame_1_Choix (String titre){
		super(titre);

        p = new JPanel((new GridBagLayout()));
        gbc = new GridBagConstraints();
        JLabel JourSemaine = new JLabel("Voulez-vous éditer une : ");
        JourSemaine.setFont(new Font("Times-Roman",Font.BOLD+ Font.ITALIC, 16));
        gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(5,0,50,0);
		p.add(JourSemaine,gbc);

        gbc.anchor = GridBagConstraints.WEST; //conservé pour la suite
		gbc.insets = new Insets(0,5,0,5); //conservé pour la suite
		gbc.gridwidth= 1;
        JButton Jour = new JButton("Nouvelle année ? ");
		Jour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
				setVisible(false);
				dispose();
				Frame_2_New_Annee  b = new Frame_2_New_Annee ("Nouvelle Année");
				b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				b.setSize(520,360);
				b.setVisible(true);  

            }
            });
        gbc.anchor = GridBagConstraints.WEST; //conservé pour la suite
		gbc.insets = new Insets(0,5,0,5); //conservé pour la suite
		gbc.gridwidth= 1;
        p.add(Jour, gbc);

        JButton Jour2 = new JButton("Une ancienne année ? ");gbc.gridwidth= GridBagConstraints.REMAINDER;
        p.add(Jour2,gbc);
        this.add(p);


	}

}