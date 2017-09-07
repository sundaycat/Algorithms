import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MazeJunitTest {

	// Testing 6x6 maze
	Maze testm6 = new Maze(6, 6);

	@Test
	public void m6dfsTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("6x6dfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm6.depthFirstSearch(1, 1);

		assertEquals(fileString, testm6.toString(2));
		testm6.depthFirstSearch(1, 1);
	}

	@Test
	public void m6dfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("6x6dfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm6.depthFirstSearch(1, 1);

		assertEquals(fileString, testm6.toString(3));
		testm6.depthFirstSearch(1, 1);
	}

	@Test
	public void m6bfsTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("6x6bfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm6.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm6.toString(2));
		testm6.breadthFirstSearch(1, 1);
	}

	@Test
	public void m6bfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("6x6bfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm6.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm6.toString(3));
		testm6.breadthFirstSearch(1, 1);
	}

	// Testing 8x8 maze

	Maze testm8 = new Maze(8, 8);

	@Test
	public void m8dfsTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("8x8dfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm8.depthFirstSearch(1, 1);

		assertEquals(fileString, testm8.toString(2));
		testm8.depthFirstSearch(1, 1);
	}

	@Test
	public void m8dfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("8x8dfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm8.depthFirstSearch(1, 1);

		assertEquals(fileString, testm8.toString(3));
		testm8.depthFirstSearch(1, 1);
	}

	@Test
	public void m8bfsTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("8x8bfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm8.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm8.toString(2));
		testm8.breadthFirstSearch(1, 1);
	}

	@Test
	public void m8bfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("8x8bfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm8.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm8.toString(3));
		testm8.breadthFirstSearch(1, 1);
	}

	// Testing 4x4 maze

	Maze testm4 = new Maze(4, 4);

	@Test
	public void m4dfsTest() {
		String fileString = null;
		try {
			//reading the file for the expected output
			fileString = new String(Files.readAllBytes(Paths.get("4x4dfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm4.depthFirstSearch(1, 1);
		
		//comparing string that was read to function's actual output
		assertEquals(fileString, testm4.toString(2));
		testm4.depthFirstSearch(1, 1);
	}

	@Test
	public void m4dfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("4x4dfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm4.depthFirstSearch(1, 1);

		assertEquals(fileString, testm4.toString(3));
		testm4.depthFirstSearch(1, 1);
	}

	@Test
	public void m4bfsTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("4x4bfs.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm4.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm4.toString(2));
		testm4.breadthFirstSearch(1, 1);
	}

	@Test
	public void m4bfsPathTest() {
		String fileString = null;
		try {
			fileString = new String(Files.readAllBytes(Paths.get("4x4bfsPath.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testm4.breadthFirstSearch(1, 1);

		assertEquals(fileString, testm4.toString(3));
		testm4.breadthFirstSearch(1, 1);
	}
}
