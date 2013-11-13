/**
 * 
 */
package datastruct;

/**
 * test class of BinaryTreeGen
 * @author cyril
 *
 */
public class BinaryTreeGenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("debut execution");
		
		
		BinaryTreeGen<Integer, String> tdo = new BinaryTreeGen<Integer, String>();
		
		//test de insert()
		System.out.println("\n");
		System.out.println("insert : "+tdo.insert(10, "n10"));
		System.out.println("insert : "+tdo.insert(15, "n15"));
		System.out.println("insert : "+tdo.insert(19, "n19"));
		System.out.println("insert : "+tdo.insert(20, "n20"));
		System.out.println("insert : "+tdo.insert(16, "n16"));
		System.out.println("insert : "+tdo.insert(5, "n5"));
		System.out.println("insert : "+tdo.insert(7, "n7"));
		System.out.println("insert : "+tdo.insert(3, "n3"));
		System.out.println("insert : "+tdo.insert(4, "n4"));
		
		//test de toString()
		System.out.println("\n");
		System.out.println("contenu de l'arbre : \n"+tdo.toString());
		
		//test de select()
		System.out.println("\n");
		System.out.println("Contenu du noeud correspondant à la clef "+10+" : "+tdo.select(10));
		System.out.println("Contenu du noeud correspondant à la clef  "+1+" (qui n'existe pas) : "+tdo.select(1));
		
		//test de balanceLevel()
		System.out.println("\n");
		System.out.println("Taille de l'arbre : \n"+tdo.balanceLevel());
		
		
		//test de delete()
		int nombre = 10;
		System.out.println("Affichage de l'arbre avant suppression de "+nombre+" : \n"+tdo.toString());
		tdo.delete(nombre);
		System.out.println("Affichage de l'arbre après suppression de "+nombre+" : \n"+tdo.toString());
		
	}

}
