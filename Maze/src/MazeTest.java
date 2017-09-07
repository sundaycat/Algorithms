/*
  Class to test generated maze and write to a file

  Solves CS146 Project #3

 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MazeTest {

	public static void main(String[] args) {

		try {

			// creating a file
			PrintWriter out = new PrintWriter(new FileWriter("6x6dfs.txt"));
			PrintWriter out1 = new PrintWriter(new FileWriter("6x6dfsPath.txt"));
			PrintWriter out2 = new PrintWriter(new FileWriter("6x6bfs.txt"));
			PrintWriter out3 = new PrintWriter(new FileWriter("6x6bfsPath.txt"));
			PrintWriter out4 = new PrintWriter(new FileWriter("8x8dfs.txt"));
			PrintWriter out5 = new PrintWriter(new FileWriter("8x8dfsPath.txt"));
			PrintWriter out6 = new PrintWriter(new FileWriter("8x8bfs.txt"));
			PrintWriter out7 = new PrintWriter(new FileWriter("8x8bfsPath.txt"));
			PrintWriter out8 = new PrintWriter(new FileWriter("4x4dfs.txt"));
			PrintWriter out9 = new PrintWriter(new FileWriter("4x4dfsPath.txt"));
			PrintWriter out10 = new PrintWriter(new FileWriter("4x4bfs.txt"));
			PrintWriter out11 = new PrintWriter(new FileWriter("4x4bfsPath.txt"));
			
			
			//print 1=maze, 2=solution and 3=shortestpath
			Maze test2 = new Maze(4, 4);
			System.out.println(test2.toString(1));

			System.out.println("############# DSF ###############");
			test2.depthFirstSearch(1, 1);
			System.out.println(test2.toString(2));
			
			//writing printed string to 4x4dfs.txt file
			out8.print(test2.toString(2));
			out8.close();
			
			System.out.println("############# DSF ###############");
			System.out.println();
			System.out.println(test2.toString(3));
			out9.print(test2.toString(3));
			out9.close();
			

			System.out.println();
			System.out.println("############# BSF ###############");
			test2.breadthFirstSearch(1, 1);
			System.out.println(test2.toString(2));
			out10.print(test2.toString(2));
			out10.close();

			System.out.println();
			System.out.println(test2.toString(3));
			System.out.println("############# BSF ###############");
			out11.print(test2.toString(3));
			out11.close();
			
			
			Maze test = new Maze(6, 6);
			System.out.println(test.toString(1));

			System.out.println("############# DSF ###############");
			test.depthFirstSearch(1, 1);
			System.out.println(test.toString(2));
			out.print(test.toString(2));
			out.close();
			
			System.out.println("############# DSF ###############");
			System.out.println();
			System.out.println(test.toString(3));
			out1.print(test.toString(3));
			out1.close();
			

			System.out.println();
			System.out.println("############# BSF ###############");
			test.breadthFirstSearch(1, 1);
			System.out.println(test.toString(2));
			out2.print(test.toString(2));
			out2.close();

			System.out.println();
			System.out.println(test.toString(3));
			System.out.println("############# BSF ###############");
			out3.print(test.toString(3));
			out3.close();
			
			Maze test1 = new Maze(8, 8);
			System.out.println(test1.toString(1));

			System.out.println("############# DSF ###############");
			test1.depthFirstSearch(1, 1);
			System.out.println(test1.toString(2));
			out4.print(test1.toString(2));
			out4.close();

			System.out.println("############# DSF ###############");
			System.out.println();
			System.out.println(test1.toString(3));
			out5.print(test1.toString(3));
			out5.close();
			

			System.out.println();
			System.out.println("############# BSF ###############");
			test1.breadthFirstSearch(1, 1);
			System.out.println(test1.toString(2));
			out6.print(test1.toString(2));
			out6.close();

			System.out.println();
			System.out.println(test1.toString(3));
			System.out.println("############# BSF ###############");
			out7.print(test1.toString(3));
			out7.close();
			
		} catch (IOException e1) {
			System.out.println("Error during reading/writing");

		}

	}
}
