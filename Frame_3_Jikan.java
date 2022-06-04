package COMPILATION_JIKAN;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Frame_3_Jikan {
   
    public static void main(String[] args) { 
    
     EventQueue.invokeLater(new Runnable() { 
        public void run() { 
            Frame_3_Creation  f = new Frame_3_Creation ("Frame_3_Choix");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(520,460);
            f.setVisible(true);  
        }
     }); 
    }
}

class Frame_3_Creation extends JFrame {

    private JPanel p;
    private GridBagConstraints gbc;

    public Frame_3_Creation (String titre){
		super(titre);

        p = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        JLabel JourSemaine = new JLabel("Conception de l'année");
        JourSemaine.setFont(new Font("Times-Roman",Font.BOLD+ Font.ITALIC, 16));
        gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(20,0,20,0);
        p.add(JourSemaine,gbc);

        JLabel CurYDay = new JLabel("L'année est constitué de :" /**+ nb jours*/ +  " et commence le :" /** début anne */);

        gbc.anchor = GridBagConstraints.WEST; //conservé pour la suite
        gbc.insets = new Insets(0,5,0,5); //conservé pour la suite
        gbc.gridwidth= 1;
        JLabel label1 = new JLabel("Nombres de Semaines ? :");
        p.add(label1, gbc);

        JTextField Text1 = new JTextField("",2);
        gbc.gridwidth= GridBagConstraints.REMAINDER;
        Text1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (Text1.getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume(); 
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
        });
        p.add(Text1,gbc);

        JLabel label2 = new JLabel("Durée d'une semaine ? :");
        gbc.gridwidth= 1;
        p.add(label2, gbc);

        JTextField Text2 = new JTextField("",2);
        gbc.gridwidth= GridBagConstraints.REMAINDER;
        Text2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (Text2.getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume(); 
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
        });
        p.add(Text2,gbc);

        JLabel label3 = new JLabel("Ajouter le nombre de la contrainte occasionnelle : "); /**(préciser la semaine et le jour ainsi que sa contrainte) */
        gbc.gridwidth= 1;
        p.add(label3, gbc);

        JTextField Text3 = new JTextField("",2);
        gbc.gridwidth= GridBagConstraints.REMAINDER;
        p.add(Text3,gbc);

        JLabel label4 = new JLabel("à quel semaine voulez-vous rajouter des vacances  :"); /** (préciser son numéro) */
        gbc.gridwidth= 1;
        p.add(label4, gbc);

        JTextField Text4 = new JTextField("",2);
        gbc.gridwidth= GridBagConstraints.REMAINDER;
        p.add(Text4,gbc);

        JLabel label5 = new JLabel("Ajouter un jour férié ");
        gbc.gridwidth= 1;
        p.add(label5, gbc);

        JTextField Text5 = new JTextField("",4);
        gbc.gridwidth= GridBagConstraints.REMAINDER;
        Text5.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (Text5.getText().length() >= 4 ) // limit textfield to 3 characters
                    e.consume(); 
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9' || (c == ' '))) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
        });
        p.add(Text5,gbc);

        JButton south = new JButton("valider");
        south.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    remplace1();
                   }catch(Exception b){
                       b.printStackTrace();
           }
               
            }
            });
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,5,0,5);
        p.add(south,gbc);


        this.add(p);
        

    }

    public static void remplace1()
    {
        /** Remplacer le nb de semaines par le choix de l'utilisateur */
        try
        {
        File file = new File("data/jikan2000/calendar.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "", oldtext = "";
        while((line = reader.readLine()) != null)
            {
            oldtext += line + "\r\n";
        }
        reader.close();

        String replacedtext  = oldtext.replaceAll("NbSemaines 43", "NbSemaines ");

        FileWriter writer = new FileWriter("data/jikan2000/calendar.txt");
        writer.write(replacedtext);


        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    /** Fonctionalités restantes */
    }

}

