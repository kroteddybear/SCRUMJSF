package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ManagedBeans.Database;

/**
 * Classe des Listener pour l'interface ajout de nouvelle categorie.
 * @author Akoma
 *
 */

public class Add_Categorie_Listener implements ActionListener{

	private Add_Categorie_Interface myPanel;

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==myPanel.TF_nom_new_cat){
			/*
			 * Recuperer la liste des nom de Categorie, comparer avec celle qui est rentre et si aucune correspondance croix verte
			 * sinon croix rouge
			 */
			Boolean IsNew = false;
			List<String> L_categorie = new ArrayList<>();
			
			try {
				L_categorie = myPanel.DB.getListCategorie();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (String string : L_categorie) {
				if(string==myPanel.TF_nom_new_cat.getText()){
					IsNew=false;
				}
			}
				myPanel.IsNewOrNot(IsNew);

		}
		else if(e.getSource()==myPanel.TF_new_type_echantillon){
			/*
			 * Controle Type echantillons deja existant ou non
			 */
		}
		else if(e.getSource()==myPanel.B_new_type_echantillon){

			/*
			 * Controle TF_new_type_echantillon non vide et valide.
			 * Recharger la liste des type avec ce nouveau type qui serra selectionner de base
			 * Stocker ce nom quelque part pour l'ajouter
			 */
		}
		else if(e.getSource()==myPanel.B_submit){
		
			/*
			 * Insert dans la BDD la nouvelle Categorie avec la liste des type.
			 * Si nouveau type faire les insert aussi.
			 */
		}
			
		
	}



}
