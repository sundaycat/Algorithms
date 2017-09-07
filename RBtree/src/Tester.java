/*
 * @CS 146 PROJECT 2 
 * 
 * @HONGZHE LIU 010030891
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tester {

	public static void main(String[] args) {

		long start, end, elapsed, totalTime = 0;
		RedBlackTree rbt = new RedBlackTree();
		NodeRBT NIL = RedBlackTree.NIL;
		NodeRBT rbn = null;

		String fileName = "resource/dictionary.txt";
		try {
			
			//Open the file
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//Counting time for creation of the dictionary
			start = System.currentTimeMillis();
			String word = bufferedReader.readLine();
			while (word != null) {

				rbn = new NodeRBT(word, NIL, NIL, null, Color.RED);
				rbt.insertRBT(rbn);
				word = bufferedReader.readLine();
			}
			end = System.currentTimeMillis();
			
			//Output the time of dictionary creation time
			elapsed = end - start;
			System.out.println("Total time for build up a dictionary in ms: "
					+ elapsed);
			
			//close the file
			bufferedReader.close();
			fileReader.close();
			
			//Reopen the file again for searching purpose
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			
			//Searching each word in the dictionary individually, counting the time and add them up.
			word = bufferedReader.readLine();
			NodeRBT root = rbt.getRoot();
			while (word != null) {

				start = System.currentTimeMillis();
				rbt.search(root, word);
				end = System.currentTimeMillis();
				elapsed = end - start;
				totalTime += elapsed;

				word = bufferedReader.readLine();
			}
			
			//Output the Sum of all searching times
			System.out.println("Sum of all searching times: " + totalTime);
			
			//close the file again
			bufferedReader.close();
			fileReader.close();

			System.out.println("Tree size: " + rbt.getSize());
			System.out.println("Min Node: "
					+ rbt.findMinimum(rbt.getRoot()).getValue());
			System.out.println("Max Node: "
					+ rbt.findMaximum(rbt.getRoot()).getValue());

		} catch (FileNotFoundException e) {

			System.out.println("Unable to open file");

		} catch (IOException e) {

			System.out.println("Error reading file");

		} catch (Exception e) {

			System.out.println("Error!");
		}

	}
}
