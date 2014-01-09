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

import datastructure.AABRI;
import datastructure.AABRINode;
import datastructure.TypeABR;
import exceptions.IntervalleChevauchantException;
import exceptions.ValeurDejaPresenteException;

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
	public static AABRI initBinaryTreeFromFile(String pathToFile) {

		// Fail fast
		if (pathToFile == null || pathToFile.equals(""))
			throw new RuntimeException("Le chemin du fichier ne dois pas être vide !");

		AABRI AABRI = new AABRI();

		// Tableau qui va contenir en position 0 les bornes de l'intervalle du treeNode, et en position 1 les noeud de l'ABRI
		String parametres[];

		// Tableau contenant les bornes
		String bornes[];

		// Tableau contenant les différents éléments de l'ARBI
		String ABRINodes[];

		// TreeNode utilisé pour stocker temporairement le noeud en cours de création
		AABRINode treeNode;

		// Ligne lue dans le fichier
		String ligne;

		File file = new File(pathToFile);
		try {
			InputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(isr);

			while ((ligne = br.readLine()) != null) {

				// A chaque ligne un nouveau noeud !
				treeNode = new AABRINode();

				parametres = ligne.split(";");
				if (parametres.length == 2 || parametres[0] != null) {

					// Récupération des bornes contenues dans la ligne
					bornes = parametres[0].split(":");
					treeNode.setMin(Integer.parseInt(bornes[0]));
					treeNode.setMax(Integer.parseInt(bornes[1]));

					// Récupération des noeuds simples (ie des entiers) et ajouts
					if (parametres.length == 2) {
						ABRINodes = parametres[1].split(":");
						for (int i = 0; i < ABRINodes.length; i++) {
							treeNode.insert(Integer.parseInt(ABRINodes[i]));
						}
					}
					try {
						AABRI.insert(treeNode);
					} catch (Exception e) {
						e.printStackTrace();
					}
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
	 * @throws IOException si problème d'enregistrement
	 */
	public static boolean saveToFile(String filePath, AABRI AABRI) throws IOException {

		File file = new File(filePath);
		boolean eof;
		String infosAABRI, line;

		// Récupération de la représentation de l'arbre
		infosAABRI = AABRI.getInfos(AABRI.getRootNode());

		// Création du fichier s'il n'existe pas
		if (!file.exists()) {
			file.createNewFile();
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
	 * @param valeurMaxAABRI - Nombre maximal de noeuds dans les ABRI
	 * @return l'AABRI construit
	 */
	public static AABRI randomAABRI(int nbreNoeuds, int valeurMaxAABRI) {

		if (valeurMaxAABRI < 2 * nbreNoeuds) {
			throw new RuntimeException("valeurMaxABRI doit être >= 2 * NbreNoeuds + 1 pour pouvoir générer des intervalles non chevauchants!");
		}

		// Tableau contenant les valeurs
		Integer[] valeurs;

		// Pointeur sur le noeud utilisé lors de l'ajout dans l'AABRI
		AABRINode treeNode;

		// AABRI retour de la fonction
		AABRI ret = new AABRI();

		// Récupération d'un tableau de bornes constituant toutes les bornes des noeuds de l'AABRI
		Integer[] bornes = TreeUtils.getRandomIntervals(nbreNoeuds, valeurMaxAABRI);

		// Liste contenant les bornes (les tableaux contenus sont donc de taille 2)
		List<Integer[]> bornesRandom = new ArrayList<Integer[]>(bornes.length / 2);

		// Mélange des couples de bornes qui se suivent pour que l'arbre généré ne soit pas construit avec des noeuds insérés dans l'ordre croissant
		// (le tableau des bornes est trié dans l'ordre croissant)
		for (int i = 0; i < bornes.length; i = i + 2) {
			Integer[] tempTab = { bornes[i], bornes[i + 1] };
			bornesRandom.add(tempTab);
		}

		// Mélange des couples de bornes
		Collections.shuffle(bornesRandom);

		if (bornes.length != nbreNoeuds * 2) {
			throw new RuntimeException("Le nombre de bornes générées n'est pas bon !");
		}

		// On place les bornes dans une liste permettant de les récupérer.
		// Création des TreeNodes contenant les ABRI et ajout dans l'AABRI
		for (Object[] tab : bornesRandom) {

			// Création d'un noeud d'AABRI avec les deux bornes
			treeNode = new AABRINode((int) tab[0], (int) tab[1], null, TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE);

			// Récupération d'un tableau contenant les valeurs du noeud
			valeurs = TreeUtils.getRandomIntegers((int) tab[0], (int) tab[1]);

			// Ajout des noeuds simples dans l'ABRI
			for (int j = 0; j < valeurs.length; j++) {
				try {
					treeNode.insert(valeurs[j]);
				} catch (ValeurDejaPresenteException e) {
					e.printStackTrace();
				}
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
	 * @param nbreNoeudsAABRI - Le nombre de noeuds de l'arbre
	 * @param borneMax - La valeur majorant le set de retour. ie Set[length-1] <= borneMax
	 * @return une collection de bornes, triées dans l'ordre croissant, permettant d'assurer que les intervalles ne se chevauchent pas.
	 */
	private static Integer[] getRandomIntervals(int nbreNoeudsAABRI, int borneMax) {

		Set<Integer> bornes = new TreeSet<Integer>();
		int borne;
		boolean estAjoute = false;

		for (int i = 1; i <= nbreNoeudsAABRI * 2; i++) {
			estAjoute = false;
			while (!estAjoute) {
				borne = (int) (Math.random() * (borneMax)) + 1;
				estAjoute = bornes.add(borne);
			}
		}
		return bornes.toArray(new Integer[bornes.size()]);
	}

	/**
	 * Génère une collection d'entiers aléatoires.
	 * 
	 * @param nbreValeurs - Le nombre de valeurs que l'on souhaite obtenir.
	 * @param valeurMin - La valeur minimale à obtenir.
	 * @param valeurMax - La valeur maximale à obtenir.
	 * @return un tableau contenant les entiers
	 */
	private static Integer[] getRandomIntegers(int valeurMin, int valeurMax) {

		// Génération du nombre de valeurs que l'on va ajouter dans le noeud
		// On cherche à obtenir un nombre entre 0 et valeurMax - valeurMin
		int nbreValeurs = (int) Math.round((Math.random() * (valeurMax - valeurMin + 1)));

		// Liste contenant les valeurs
		List<Integer> valeurs = new ArrayList<Integer>(nbreValeurs);

		// Valeur temporaire utilisée dans la boucle
		int randomValue;

		// Marqueur d'ajout dans liste en cas de non doublon
		boolean estAjoute = false;

		for (int i = 1; i <= nbreValeurs; i++) {
			estAjoute = false;
			while (!estAjoute) {
				randomValue = (int) (Math.random() * (valeurMax - valeurMin + 1)) + valeurMin;
				if (!valeurs.contains(new Integer(randomValue))) {
					estAjoute = valeurs.add(randomValue);
				}
			}
		}
		return valeurs.toArray(new Integer[valeurs.size()]);
	}
}
