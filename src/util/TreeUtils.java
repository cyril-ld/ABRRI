/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import datastructure.BinaryTree;
import datastructure.TreeNode;

/**
 * Classe regroupant les méthodes utilitaires pour les arbres.
 * 
 * @author Cyril
 * 
 */
public class TreeUtils {

	/**
	 * Permet d'initialiser un arbre binaire d'arbres binaires de recherche inversés depuis un fichier respectant un formalisme particulier :
	 * 
	 * <pre>
	 * 50:75;60:62:75:55
	 * 9:22;9:14:11
	 * 1:6;3:2:1
	 * 24:48;30:32:24
	 * 78:80;80
	 * 92:110;100:104
	 * 
	 * produira un arbre dont la racine sera un arbre tq :
	 * 
	 * min = 50, max = 75 
	 * 				60
	 * 				/ \
	 * 			  62	55
	 * 			  /
	 * 			75
	 * </pre>
	 * 
	 * @param pathToFile - Chemin vers le fichier
	 */
	public static BinaryTree initBinaryTreeFromFile(String pathToFile) {

		// Fail fast
		if (pathToFile == null || pathToFile.equals(""))
			throw new RuntimeException("Le chemin du fichier ne dois pas être vide !");

		BinaryTree AABRI = new BinaryTree();

		// Tableau qui va contenir en position 0 les bornes de l'intervalle du treeNode, et en position 1 les noeud de l'ABRI
		String parametres[];

		// Tableau contenant les bornes
		String bornes[];

		// Tableau contenant les différents éléments de l'ARBI
		String ABRINodes[];

		// TreeNode utilisé pour stocker temporairement le noeud en cours de création
		TreeNode treeNode;

		File file = new File(pathToFile);
		try {
			InputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(isr);
			String ligne;

			while ((ligne = br.readLine()) != null) {

				// A chaque ligne un nouveau noeud !
				treeNode = new TreeNode();

				parametres = ligne.split(";");
				if (parametres.length == 2) {

					// Récupération des bornes contenues dans la ligne
					bornes = parametres[0].split(":");
					treeNode.setMin(Integer.parseInt(bornes[0]));
					treeNode.setMax(Integer.parseInt(bornes[1]));

					// Récupération des noeuds simples (ie des entiers) et ajouts
					ABRINodes = parametres[1].split(":");
					for (int i = 0; i < ABRINodes.length; i++) {
						treeNode.insert(Integer.parseInt(ABRINodes[i]));
					}
					AABRI.insert(treeNode);
				}
			}

			return AABRI;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
