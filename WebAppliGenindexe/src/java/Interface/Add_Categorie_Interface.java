package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.*;

import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


import ManagedBeans.Database;

/**
 ** Classe interface pour la fenetre de creation de nouvelle categorie.
 **/

public class Add_Categorie_Interface extends JPanel{
	
	
	JPanel Panel_Titre = new JPanel();
	JPanel Panel_Formulaire = new JPanel();
	JPanel Panel_Categorie = new JPanel();
	JPanel Panel_Type = new JPanel();
	JPanel Panel_Submit = new JPanel();
	JPanel Panel_autre = new JPanel();
	JLabel L_titre = new JLabel("Creer Interface");
	JLabel L_nom_new_cat = new JLabel("Nom de la nouvelle categorie : \n");
	JLabel L_type_echantillon = new JLabel("Type d'echantillons prelevables : \n");
	JLabel L_autre = new JLabel("Autre type : ");
	JLabel L_check = new JLabel();
	JTextField TF_nom_new_cat = new JTextField();
	JTextField TF_new_type_echantillon = new JTextField();
	JList List_type_echantillon;
	JScrollPane Scroll_list;
	JButton B_new_type_echantillon = new JButton("Ok");
	JButton B_submit = new JButton("Valider");
	Database DB;
	java.util.List<String> List_type_sample = new ArrayList<>();
	
	
	
	public Add_Categorie_Interface() {
		/*
		 * Definition des panels
		 */
		
		this.setLayout(new BorderLayout());
		Panel_Titre.setLayout(new BorderLayout());
		Panel_Formulaire.setLayout(new BorderLayout());
		Panel_Categorie.setLayout(new BorderLayout());
		Panel_Type.setLayout(new BoxLayout(Panel_Type, BoxLayout.Y_AXIS));
		Panel_autre.setLayout(new BoxLayout(Panel_autre, BoxLayout.X_AXIS));
		Panel_Submit.setLayout(new BorderLayout());
		
		/*
		 * On recupere la liste des type d'echantillons existant
		 */
		try {
			List_type_sample=DB.GetListTypeSamples();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		remplirListEchantillon();
		List_type_echantillon.setVisibleRowCount(4);
		List_type_echantillon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Scroll_list = new JScrollPane(List_type_echantillon);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		TF_nom_new_cat.setSize(10, 4);
		
		Panel_Titre.add(L_titre, BorderLayout.CENTER);
		Panel_Categorie.add(L_nom_new_cat, BorderLayout.NORTH);
		Panel_Categorie.add(TF_nom_new_cat, BorderLayout.WEST);
		Panel_Categorie.add(L_check,BorderLayout.CENTER);
		Panel_Type.add(L_type_echantillon);
		Panel_autre.add(Scroll_list);
		Panel_autre.add(L_autre);
		Panel_autre.add(TF_new_type_echantillon);
		Panel_autre.add(B_new_type_echantillon);
		Panel_Type.add(Panel_autre);
		Panel_Formulaire.add(Panel_Categorie, BorderLayout.NORTH);
		Panel_Formulaire.add(Panel_Type, BorderLayout.SOUTH);
		
		Panel_Submit.add(B_submit, BorderLayout.EAST);
		
		this.add(Panel_Titre, BorderLayout.NORTH);
		this.add(Panel_Formulaire, BorderLayout.CENTER);
		this.add(Panel_Submit, BorderLayout.SOUTH);
		

		
		
	}
	
	
	/**
	 * Methode qui remplis la liste a choix multiples des echantillons
	 */
	public void remplirListEchantillon(){
		String type[] = new String[List_type_sample.size()];
		for(int i=0; i<List_type_sample.size(); i++){
			type[i]=List_type_sample.get(i);
			
		}
		List_type_echantillon = new JList<>(type);
		
	}
	
	
	/**
	 * Methode qui change l'affichage du textField en fonction de si il est correcte ou non
	 * @param Boolean
	 */
	public void IsNewOrNot(Boolean IsNew){
		
	}
	
	public void close(){
		DB.Close();
	}
	
	
//	 public static void main(String[] args) {
//		 Add_Categorie_Interface IT = new Add_Categorie_Interface();
//		 JFrame myFrame = new JFrame("Test interface");
//		 myFrame.setLayout(new GridLayout(1, 2));
//		 myFrame.add(IT);
//		 myFrame.pack();
//			myFrame.setVisible(true);
//		 
//	 }
}
