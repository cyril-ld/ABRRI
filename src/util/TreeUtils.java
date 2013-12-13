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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import datastructure.BinaryTree;
import datastructure.TreeNode;
import exceptions.IntervalleChevauchantException;

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

		if (valeurMaxABRI < 2) {
			throw new RuntimeException("La valeur max doit être au moins de 2 pour pouvoir générer un intervalle !");
		}

		BinaryTree ret = new BinaryTree();

		// Variable stockant l'ABRI que l'on ajoute dans la boucle suivante
		int rangTableauValeurs;
		Object[] bornes = TreeUtils.getRandomIntegers(nbreNoeuds, valeurMaxABRI).toArray();
		List<Object[]> bornesRandom = new ArrayList<Object[]>();

		// Mélange des bornes par paires qui se suivent pour que l'arbre généré ne soit pas construit avec des noeuds insérés dans l'ordre croissant
		// (le tableau des bornes est trié dans l'ordre croissant)
		for (int i = 0; i < bornes.length; i = i + 2) {
			Object[] tempTab = { bornes[i], bornes[i + 1] };
			bornesRandom.add(tempTab);
		}
		Collections.shuffle(bornesRandom);

		for (int i = 0; i < bornesRandom.size(); i++) {
			Object[] tempTab = bornesRandom.get(i);
			bornes[i * 2] = tempTab[0];
			bornes[i * 2 + 1] = tempTab[1];
		}

		Object[] valeurs;
		TreeNode treeNode;

		if (bornes.length != nbreNoeuds * 2) {
			throw new RuntimeException("Le nombre de bornes générées n'est pas bon !");
		}

		// On place les bornes dans une liste permettant de les récupérer.
		// Création des TreeNodes contenant les ABRI et ajout dans l'AABRI
		for (int i = 0; i < bornes.length; i = i + 2) {
			treeNode = new TreeNode((int) bornes[i], (int) bornes[i + 1], null);
			valeurs = TreeUtils.getRandomIntegers(((int) bornes[i + 1]) - ((int) bornes[i]), (int) bornes[i], (int) bornes[i + 1]);

			rangTableauValeurs = 0;

			// Ajout des noeuds simples dans l'ABRI
			for (int j = 0; j < valeurs.length; j++) {
				treeNode.insert((int) valeurs[rangTableauValeurs]);
				rangTableauValeurs++;
			}
			try {
				ret.insert(treeNode);
			} catch (IntervalleChevauchantException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Génère une collection d'intervalles permettant de construire un arbre de manière totalement aléatoire.
	 * 
	 * @param nbreNoeuds - Le nombre de noeuds de l'arb
	 * @param borneMax - La valeur majorant le set de retour. ie Set[length-1] <= borneMax
	 * @return une collection de bornes, triées dans l'ordre croissant, permettant d'assurer que les intervalles ne se chevauchent pas.
	 */
	private static Set<Integer> getRandomIntegers(int nbreNoeuds, int borneMax) {

		Set<Integer> bornes = new TreeSet<Integer>();
		int borne;
		boolean estAjoute = false;

		for (int i = 1; i <= nbreNoeuds * 2; i++) {
			estAjoute = false;
			while (!estAjoute) {
				borne = (int) (Math.random() * borneMax) + 1;
				estAjoute = bornes.add(borne);
			}
		}
		return bornes;
	}

	/**
	 * Génère une collection d'entiers.
	 * 
	 * @param nbreValeurs - Le nombre de valeurs que l'on souhaite obtenir.
	 * @param valeurMin - La valeur minimale à obtenir.
	 * @param valeurMax - La valeur maximale à obtenir.
	 * @return un tableau contenant les entiers
	 */
	private static Object[] getRandomIntegers(int nbreValeurs, int valeurMin, int valeurMax) {
		List<Integer> valeurs = new ArrayList<Integer>();
		int randomValue;
		boolean estAjoute = false;

		for (int i = 1; i <= nbreValeurs; i++) {
			estAjoute = false;
			while (!estAjoute) {
				randomValue = (int) (Math.random() * (valeurMax - valeurMin)) + valeurMin;
				if (!valeurs.contains(new Integer(randomValue))) {
					estAjoute = valeurs.add(randomValue);
				}
			}
		}
		return valeurs.toArray();
	}
}
