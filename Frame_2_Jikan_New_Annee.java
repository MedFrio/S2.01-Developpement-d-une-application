package COMPILATION_JIKAN;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.time.Year;
//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

import java.lang.Thread;


public class Frame_2_Jikan_New_Annee  {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		Frame_2_New_Annee  f = new Frame_2_New_Annee ("Nouvelle Année");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(520,360);
		f.setVisible(true);  
        
	}
 }); 
}

}

class Frame_2_New_Annee extends JFrame {

    private GridBagConstraints gbc,gbc_2;
    private JPanel p,p_2;
    public static String Year;
    private JTextField nombrTextArea;


	public Frame_2_New_Annee (String titre){
        
		super(titre);
        

        p = new JPanel((new GridBagLayout()));
        gbc = new GridBagConstraints();
        JLabel JourSemaine = new JLabel("Quel sera l'année à créer ?");
        JLabel numAnnee = new JLabel("Numéro de l'année : ");
        JButton ajoutAnne = new JButton("Ajout");
        
        
        JourSemaine.setFont(new Font("Times-Roman",Font.BOLD+ Font.ITALIC, 16));
        gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(5,0,50,0);
		p.add(JourSemaine,gbc);
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(0,5,0,5); //conservé pour la suite
        gbc.gridwidth= 1;
        p.add(numAnnee,gbc);
        gbc.anchor = GridBagConstraints.WEST; 
		gbc.insets = new Insets(0,5,0,5); //conservé pour la suite
		gbc.gridwidth= 1;
        nombrTextArea = new JTextField("",4);
        nombrTextArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (nombrTextArea.getText().length() >= 4 ) // limit textfield to 3 characters
                    e.consume(); 
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
        });


        ajoutAnne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                Year = nombrTextArea.getText();
                String Annee = nombrTextArea.getText();
                File src = new File("data/jikan2122");
                File dest = new File("data/jikan" + Annee);     
                try{
                 copy(src, dest);
                 remplace();
                }catch(IOException b){
                    b.printStackTrace();
        }
               setVisible(false);
                dispose();
                Frame_3_Creation  ZR = new Frame_3_Creation ("Nouvelle Année");
                ZR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ZR.setSize(520,360);
                ZR.setVisible(true);  
            }
            });
        
            

        
        
        p.add(nombrTextArea,gbc);
        this.add(p,BorderLayout.CENTER);
        p_2 = new JPanel(new GridBagLayout());
        gbc_2 = new GridBagConstraints();
        gbc_2.insets = new Insets(0,0,60,0);
        p_2.add(ajoutAnne,gbc_2);
        this.add(p_2,BorderLayout.SOUTH);
        
        // faire l'action de créer un nouveau fichier txt et d'y ajouter l'année 
        
	}



    
/** Copie une année pour ensuite la modifier selon les choix de l'utilisateur */

    public static void copy(File src, File dest) throws IOException{
    
        if(src.isDirectory()){
        //si le répertoire n'existe pas, créez-le
          if(!dest.exists()){
             dest.mkdir();
             System.out.println("Dossier "+ src + "  > " + dest);
          }
          //lister le contenu du répertoire
          String files[] = src.list();
          
          for (String f : files) {
             //construire la structure des fichiers src et dest
             File srcF = new File(src, f);
             File destF = new File(dest, f);
             //copie récursive
             copy(srcF, destF);
          }
        }else{
            //si src est un fichier, copiez-le.
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest); 
                             
            byte[] buffer = new byte[1024];
            int length;
            //copier le contenu du fichier
            while ((length = in.read(buffer)) > 0){
              out.write(buffer, 0, length);
            }
   
            in.close();
            out.close();
            System.out.println("Fichier " + src + " > " + dest);
        }
//
//
//
//
        File file = new File("data/release.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();



        /** Chnage l'année dans le release*/

        try {
            FileWriter myWriter = new FileWriter("data/release.txt");
            myWriter.write("jikan"+ Year);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }









        }
	/** Remplace l'année actuelle par le choix*/
    public static void remplace()
    {
        try
        {
        File file = new File("data/jikan"+Year+"/calendar.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "", oldtext = "";
        while((line = reader.readLine()) != null)
            {
            oldtext += line + "\r\n";
        }
        reader.close();

        String replacedtext  = oldtext.replaceAll("2021", Year);

        FileWriter writer = new FileWriter("data/jikan"+Year+"/calendar.txt");
        writer.write(replacedtext);


        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    }
    


    
}
