/**
 * 
 */
package datastruct;

/**
 * this class is represent a binary tree. this tree is composed by nodes.
 * each node has father, a left son and a right son.
 * the left son is lower than the current node and the right son is upper
 * @author cyril
 *
 */
public class BinaryTreeGen <E extends Comparable<E>, T> implements TableGen<E, T> {

	/**
	 * the first node of the tree
	 */
	private Node root;
	
	/**
	 * default constructor of the binary tree
	 */
	public BinaryTreeGen(){
		this.root = null;
	}
	
	@Override
	public boolean insert (E cle, T donnee) throws RuntimeException {
		
		boolean ret = false;
		Node father = null;
		Node newNode = null;
		
		if(donnee == null) throw new RuntimeException("Donnee = null !");
		else{
			
			if(this.root == null){
				
				this.root = new Node(null, null, null, donnee, cle);
				ret = true;
				
			}else{
				
				//recherche du noeud pere
				father = this.findNode(root, cle);
				
				//creation de la nouvelle feuille
				newNode = new Node(null, null, father,donnee, cle);
				
				//modification de la valeur de lSon ou rSon du pere
				if(father.key.compareTo(cle)>0){
					father.lSon = newNode;
					ret = true;
				}else if(father.key.compareTo(cle)<0){
					father.rSon = newNode;
					ret = true;
				}
			}
		}
		
		return ret;
	}

	@Override
	public T select(E cle) {
		
		T ret = null;
		
		Node noeud = this.findNode(this.root, cle);
		if(noeud != null){
			if(noeud.getKey().compareTo(cle) == 0){
				//le noeud retourne par findNode est le bon noeud, alors on prend la valeur
				ret = noeud.getValue();
			}else{
				//le noeud est le noeud pere, et ne correspond donc pas, et donc on ne prend pas le contenu
				ret = null;
			}
		}else{
			System.out.println("Arbre vide");
		}
			
		
		
		
		return ret;
	}

	@Override
	public boolean delete(E cle) {
		
		boolean ret = false;
		Node noeud = this.findNode(this.root, cle);
		
		if((noeud.lSon == null) && (noeud.rSon == null)){ //si le noeud est une feuille
			
			if(noeud.father != null){
				
				if(noeud.father.lSon == noeud) noeud.father.lSon = null;
				else noeud.father.rSon = null;
				ret = true;
				
			
			}else root = null;
			
		}else if( (noeud.rSon == null) || (noeud.lSon == null) ){ //si le noeud n'a qu'un seul fils
			
			if(noeud.father != null){ //si le noeud pere existe
				
				if(noeud.father.lSon == noeud){ //si le fils gauche du pere est le noeud a supprimer
					
					if(noeud.lSon == null){ //si le fils gauche du noeud a supprimer est null
						
						noeud.father.lSon = noeud.rSon;
						ret = true;
						
					}else{ //si le fils droit du noeud a supprimer est null
						
						noeud.father.lSon = noeud.lSon;
						ret = true;
					}
				}else{ //si le fils gauche du pere n'est pas le noeud a supprimer
					
					if(noeud.rSon == null){ 
						
						noeud.father.rSon = noeud.lSon;
						ret = true;
						
					}else{ 
						
						noeud.father.rSon = noeud.rSon;
						ret = true;
					}
				}
			
			}else System.out.println("Le noeud selectionne n'a pas de pere !");
			
			
		}else{ //si le noeud a supprimer a deux fils


			Node theGNode = this.max(noeud.lSon);
			

			System.out.println("Noeud renvoyé par max() : "+theGNode.key);
			
			E clef = theGNode.key;
			
			
			noeud.theValue = theGNode.theValue;
			this.delete(theGNode.key);
			noeud.key = clef;
			
			ret = true;

		}
		
		return ret;
	}
	

	
	
	/**
	 * this method returns the father node corresponding to a value
	 * @param Node le noeud dont on cherche le pere
	 * @param the key of the node
	 * @return the father node if existing, null otherwise
	 */
	private Node seekFather(Node n, E key){
		Node ret = null;

		if(n == null){
			ret = null;
		}else if(n.key.compareTo(key)>0){
			if(n.lSon == null) ret = n;
			else ret = seekFather(n.lSon, key);
		}else if(n.key.compareTo(key)<0){
			if(n.rSon == null) ret = n;
			else ret = seekFather(n.rSon, key);
		}else{
			ret = n;
		}

		return ret;
	}
	
	/**
	 * finds the node matching to the given key
	 * @param n the node's father
	 * @param key the key of the wanted node
	 * @return the wanted node
	 */
	private Node findNode(Node n, E key){
		
		Node ret = null;
		
		if(n == null){
			ret = null;
		}else if(n.key.compareTo(key)>0){ //si la cle du noeur est superieure a celle passee
			
			if(n.lSon == null) ret = n;
			else ret = findNode(n.lSon, key);
			
		}else if(n.key.compareTo(key)<0){ //si la cle du noeur est inferieure a celle passee
			
			if(n.rSon == null) ret = n;
			else ret = findNode(n.rSon, key);
			
		}else{
			ret = n;
		}

		return ret;
		
	}
	
	
	/**
	 * returns the representation of all the element succeeding the given element
	 * @param theN
	 * @return the representation of the elements succeeding the given node, including itself
	 */
	private String getInfo ( Node theN ) {

		String infosLNode, infosRNode, infosNode ;
		String ret ;

		if ( theN != null ) {
			infosLNode = getInfo ( theN.lSon ) ;
			infosRNode = getInfo ( theN.rSon ) ;
			infosNode = new String ( "\nclé=" + theN.key.toString() + "\tdata = " + theN.theValue.toString() ) ;
			ret = new String ( infosLNode + infosNode + infosRNode ) ;
		}
		
		else ret = new String ("") ;
		
		return ret ;
	}
	
	/**
	 * return the node with the max key value
	 * @param n
	 * @return the node with the max key value
	 */
	public Node max(Node n){
		
		Node ret = null;
		
		if(n.rSon != null){
			ret = this.max(n.rSon);
		}else{
			ret = n;
		}
		
		return ret;
	}
	
	/**
	 * returns the height of the node
	 * @return the height of the node
	 */
	public String balanceLevel(){
		
		String ret = "";
		
		if(root == null) ret +="0";
		else{
			ret += "Taille du sous arbre de gauche : "+this.hauteur(this.root.lSon)+"\n";
			ret += "Taille du sous arbre de droite : "+this.hauteur(this.root.rSon)+"\n";
		}
		
		return ret;
		
	}
	
	/**
	 * calculates the height of the tree
	 * @param noeud the node to calculate the height
	 * @return the height of the node
	 */
	private int hauteur(Node noeud){
		
		int ret = 0;
		int tailleGauche = 0;
		int tailleDroite = 0;
		
		if(noeud == null) ret = 0;
		else{
			
			tailleGauche = hauteur(noeud.lSon);
			tailleDroite = hauteur(noeud.rSon);
			
			if(tailleGauche > tailleDroite) ret = tailleGauche;
			else ret = tailleDroite;
			
		}
		
		
		return ret +1;
		
	}
	
	/**
	 * returns the content of the tree
	 * @return the representation of the tree
	 */
	public String toString(){
		
		String ret = this.getInfo(this.root);
		
		return ret;
	} // ------------------------------------------------ BinaryTreeGen
	
	/**
	 * this class represents a node of the binary tree
	 * @author cyril
	 *
	 */
	private class Node{
		
		/**
		 * the left son of the node, the owned key is lower than the current node
		 * this node can be null if there is no lower key
		 */
		private Node lSon;
		
		/**
		 * the right son of the node, the owned key is upper than the current node
		 * this node can be null if there is no upper key
		 */
		private Node rSon;
		
		/**
		 * the father of the node. Can be null if the node is the root node
		 */
		private Node father;
		
		/**
		 * represent the data owned
		 */
		private T theValue;
		
		/**
		 * the key of the node
		 */
		private E key;
		
		/**
		 * builds a Node with the given parameters
		 * @param lSon the left son of the node
		 * @param rSon the right son of the node
		 * @param father the father of the node/leaf
		 * @param theValue the content of the node/leaf
		 * @param key the key of the node/leaf
		 */
		public Node(Node lSon, Node rSon, Node father, T theValue, E key){
			this.lSon = lSon;
			this.rSon = rSon;
			this.father = father;
			this.theValue = theValue;
			this.key = key;
		}
		
		/**
		 * returns the content of the node
		 * @return
		 */
		public T getValue(){
			return this.theValue;
		}
		
		/**
		 * returns the left son of the node
		 * @return the left son of the node
		 */
		@SuppressWarnings("unused")
		public Node getLSon(){
			return this.lSon;
		}
		
		/**
		 * returns the right son of the node
		 * @return the right son of the node
		 */
		@SuppressWarnings("unused")
		public Node getRSon(){
			return this.rSon;
		}
		
		/**
		 * returns the father of the node
		 * @return the father of the node
		 */
		@SuppressWarnings("unused")
		public Node getFather(){
			return this.father;
		}
		
		/**
		 * returns the key of the node
		 * @return the key of the node
		 */
		public E getKey(){
			return this.key;
		}
	}

}
