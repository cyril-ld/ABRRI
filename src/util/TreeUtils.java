/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

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

	/**
	 * Enregistre l'abre dans un fichier.
	 * 
	 * @param filePath - /chemin/vers/le/fichier
	 * @param AABRI - L'arbre d'abres binaires de recherche inversés
	 * @return true si l'enregistrement s'est bien passé.
	 */
	public static boolean saveToFile(String filePath, BinaryTree AABRI) {

		File file = new File(filePath);
		boolean eof;
		String infosAABRI, line;

		// Récupération de la représentation de l'arbre
		infosAABRI = AABRI.getInfos(AABRI.getRootNode());

		// Création du fichier s'il n'existe pas
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		// Enregistrement dans le fichier
		try {

			// Création du buffer pour écrirer dans le fichier
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			// Création d'un buffer contenant les informations sur l'arbre
			StringReader sr = new StringReader(infosAABRI);
			BufferedReader br = new BufferedReader(sr);
			eof = false;

			while (!eof) {
				line = br.readLine();
				if (line != null) {

					// Ecriture de la ligne dans le buffer
					bw.write(line);
					bw.newLine();

					// Purge du buffer et écriture dans le fichier
					bw.flush();
				} else {
					eof = true;
				}
			}

			// Fermeture des buffers
			fw.close();
			sr.close();
			br.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Génère un AABRI A à nbreNoeuds noeuds, remplis avec des données générées aléatoirement telles que min(m) = 1 et max(M) = valeurMaxABRI. Aucun
	 * des ABRI générés n'est vide.
	 * 
	 * @param nbreNoeuds - Le nombre de noeuds dans l'AABRI
	 * @param valeurMaxABRI - Nombre maximal de noeuds dans les ABRI
	 * @return l'AABRI construit
	 */
	public static BinaryTree randomAABRI(int nbreNoeuds, int valeurMaxABRI) {

		BinaryTree ret = new BinaryTree();

		// Variable stockant l'ABRI que l'on ajoute dans la boucle suivante
		TreeNode treeNode = null;
		int borneMin, borneMax, nbreNoeudsSimples;

		// TODO
		// étape 1 : Création d'un nombre aléatoire pour la borne min de l'ABRI courant (1 < borneMin < valeurMaxABRI)
		// étape 2 : Création d'un nombre aléatoire poue la borne max de l'ABRI courant (borneMin < borneMax < valeurMaxABRI)
		// étape 3 : Nombre de noeuds max dans ABRI = borneMax - borneMin
		// étape 4 : On ajoute ce nombre de noeuds, stockant tous des valeurs entre borneMin et borneMax

		// Création de nbreNoeuds
		for (int i = 1; i <= nbreNoeuds; i++) {
			borneMin = (int) (Math.random() * 100);
		}

		return ret;
	}
}
