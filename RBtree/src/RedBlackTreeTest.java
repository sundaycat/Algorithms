import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alan
 *
 */
public class RedBlackTreeTest {

	private RedBlackTree rbt;
	private NodeRBT NIL = RedBlackTree.NIL;
	private String[] keyList = {"D","B","A","C","F","E","H","G","I","J"};
	private String str = null;
	
	
	
	@Before
	public void setUp() throws Exception {

		rbt = new RedBlackTree();		
		str = "Color: BLACK, Key:D Parent: \n"+
			  "Color: BLACK, Key:B Parent: D\n"+
			  "Color: BLACK, Key:A Parent: B\n"+
			  "Color: BLACK, Key:C Parent: B\n"+
			  "Color: BLACK, Key:F Parent: D\n"+
			  "Color: BLACK, Key:E Parent: F\n"+
			  "Color: RED, Key:H Parent: F\n"+
			  "Color: BLACK, Key:G Parent: H\n"+
			  "Color: BLACK, Key:I Parent: H\n"+
			  "Color: RED, Key:J Parent: I\n";

		NodeRBT node = null;
		for (int i = 0; i < keyList.length; i++) {
			node = new NodeRBT(keyList[i], NIL, NIL, null, Color.RED);
			rbt.insertRBT(node);
		}
		
	}

	@Test
	public void testSearch() {

		// Search each elements in the RBtree individually and compare result
		String result = null;
		for (int i = 0; i < keyList.length; i++) {
			result = rbt.search(rbt.getRoot(), keyList[i]).getValue();
			assertEquals(result, keyList[i]);
		}
		
		// Search element that do not exist in the RBtree
		result = rbt.search(rbt.getRoot(), "ABC").getValue();
		assertEquals(result, null);

	}

	@Test
	public void testFindMinimum() {
		
		String min = rbt.findMinimum(rbt.getRoot()).getValue();
		assertEquals(min, "A");
	}

	@Test
	public void testFindMaximum() {
		
		String max = rbt.findMaximum(rbt.getRoot()).getValue();
		assertEquals(max, "J");
	}

	@Test
	public void testPreorderTreeWalk() {
		
		assertEquals(str, rbt.preorderTreeWalk(rbt.getRoot()));
	}

	@Test
	public void testInsertRBT() {

		NodeRBT node = null;
		RedBlackTree rbt1 = new RedBlackTree();
		for (int i = 0; i < keyList.length; i++) {
			node = new NodeRBT(keyList[i], NIL, NIL, null, Color.RED);
			rbt1.insertRBT(node);
		}
		assertEquals(str, rbt1.preorderTreeWalk(rbt1.getRoot()));
	}
	
	@Test
	public void testGetSibling() {
		String test = null;
		// Sibling doesn't exist, take "D", "J" as example
		NodeRBT D = rbt.search(rbt.getRoot(), "D");
		test = rbt.getSibling(D).getValue();
		assertEquals(test, NIL.getValue());
		
		NodeRBT J = rbt.search(rbt.getRoot(), "J");
		test = rbt.getSibling(J).getValue();
		assertEquals(test, NIL.getValue());
		
		// Find "C"'s sibling, which is "A"
		NodeRBT C = rbt.search(rbt.getRoot(), "C");
		test = rbt.getSibling(C).getValue();
		assertEquals(test, "A");
		
	}

	@Test
	public void testGetAunt() {
		
		String test = null;
		// Aunt doesn't exist, take "D","B" as example
		NodeRBT D = rbt.search(rbt.getRoot(), "D");
		test = rbt.getAunt(D).getValue();
		assertEquals(test, NIL.getValue());
		
		NodeRBT B = rbt.search(rbt.getRoot(), "B");
		test = rbt.getAunt(B).getValue();
		assertEquals(test, NIL.getValue());
		
		// Find "C"'s aunt, which is "F"
		NodeRBT C = rbt.search(rbt.getRoot(), "C");
		test = rbt.getAunt(C).getValue();
		assertEquals(test, "F");
	}
	
	@Test
	public void testGetGrandparent() {
		
		String test = null;
		// grandparent doesn't exist, take "D","B" as example
		NodeRBT D = rbt.search(rbt.getRoot(), "D");
		test = rbt.getGrandparent(D).getValue();
		assertEquals(test, NIL.getValue());
		
		NodeRBT B = rbt.search(rbt.getRoot(), "B");
		test = rbt.getGrandparent(B).getValue();
		assertEquals(test, NIL.getValue());
		
		// Find "C"'s grandparent, which is "F"
		NodeRBT C = rbt.search(rbt.getRoot(), "C");
		test = rbt.getAunt(C).getValue();
		assertEquals(test, "F");
	}
	
	
	//Professor's test code
	@Test
	public void test() {
		
		RedBlackTree rbt = new RedBlackTree();
		
		//insert the nodes to red black tree
		NodeRBT node = null;
		for (int i = 0; i < keyList.length; i++) {
			node = new NodeRBT(keyList[i], NIL, NIL, null, Color.RED);
			rbt.insertRBT(node);
		}
		
		assertEquals("DBACFEHGIJ", makeString(rbt));
		assertEquals(str, makeStringDetails(rbt));

	}
	
	public static String makeString(RedBlackTree t) {
		
		//nested class implements the interface Visitor in class RedBlackTree
		//output the value of nodes in preorder
		class MyVisitor implements RedBlackTree.Visitor {
			
			String result = "";
			public void visit(NodeRBT n) {
				result = result + n.getValue();
			}

		};
		
		MyVisitor v = new MyVisitor();
		t.preOrderVisit(v);
		return v.result;
	}

	public static String makeStringDetails(RedBlackTree t) {
		{
			class MyVisitor implements RedBlackTree.Visitor {
				String result = "";
				
				//nested class implements the interface Visitor in class RedBlackTree
				//output the value of nodes, its color and parent in preorder
				public void visit(NodeRBT n) {
					if (!(n.getValue()).equals("")){
						
						NodeRBT p = n.getParent();
						String parVal = p.getValue() == null ? "" : p.getValue();
						result = result + "Color: " + n.getColor() + ", Key:"
								+ n.getValue() + " Parent: " + parVal + "\n";
					}	
				}
			};
			
			MyVisitor v = new MyVisitor();
			t.preOrderVisit(v);
			return v.result;
		}
	}

}
