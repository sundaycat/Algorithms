import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MatrixMultiplicationTest {

	private Matrix mt;
	private Matrix I;
	private int bound = 512;

	@Before
	public void setUp() throws Exception {

		mt = new Matrix(4, 4);
		int n = mt.getMatrix().length;
		int[][] identityArr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				identityArr[i][j] = (i == j) ? 1 : 0;
			}
		}
		I = new Matrix(identityArr);
	}

	@Test(timeout = 1000 * 300)
	public void testMultiplyRegular() {

		// Test regular method by multiple 4 x 4 matrix with an identity matrix
		Matrix rs = mt.mutiplyRegular(I);
		assertArrayEquals(mt.getMatrix(), rs.getMatrix());
	}

	@Test(timeout = 1000 * 300)
	public void testMultiplyStrassen() {

		// Test strassen method by multiple 4 x 4 matrix with an identity matrix
		Matrix rs = mt.multiplyStrassen(I);
		assertArrayEquals(mt.getMatrix(), rs.getMatrix());
	}

	@Test(timeout = 1000 * 300)
	public void testMultiplyRandomly() {

		// randomly create two n x n matrices.
		int n = 16;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		// Multiply them by strassen and regular method respectively
		Matrix productStra = A.multiplyStrassen(B);
		Matrix productRegu = A.mutiplyRegular(B);

		// Compare the result
		assertArrayEquals(productStra.getMatrix(), productRegu.getMatrix());
	}

	// Regular method, matrix size n = 4
	@Test(timeout = 1000 * 300)
	public void testRegularTiming1() {

		int n = 4;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method mupli, matrix size 4 x 4: "
				+ elapsed);
	}

	// Regular method, matrix size n = 16
	@Test(timeout = 1000 * 300)
	public void testRegularTiming2() {

		int n = 16;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method , matrix size 16 x 16: " + elapsed);
	}

	// Regular method, matrix size n = 512
	@Test(timeout = 1000 * 300)
	public void testRegularTiming3() {

		int n = 512;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method, matrix size 512 x 512: " + elapsed);
	}

	// Regular method, matrix size n = 1024
	@Test(timeout = 1000 * 300)
	public void testRegularTimeing4() {

		int n = 1024;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method, matrix size 1024 x 1024: "
				+ elapsed);
	}

	// Regular method, matrix size n = 4096
	@Test(timeout = 1000 * 300)
	public void testRegularTimeing5() {

		int n = 4096;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method, matrix size 4096 x 4096: "
				+ elapsed);
	}

	// Regular method, matrix size n = 2048

	@Test(timeout = 1000 * 300)
	public void testRegularTimeing6() {

		int n = 2048;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.mutiplyRegular(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method mupli, matrix size 2048 x 2048: "
				+ elapsed);
	}

	// strassen method without improving, matrix size n = 4
	@Test(timeout = 1000 * 300)
	public void testStrTiming1() {

		int n = 4;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 4 x 4: "
						+ elapsed);
	}

	// strassen method without improving, matrix size n = 16
	@Test(timeout = 1000 * 300)
	public void testStrTiming2() {

		int n = 16;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 16 x 16: "
						+ elapsed);
	}

	// strassen method without improving, matrix size n = 512
	@Test(timeout = 1000 * 300)
	public void testStrTiming3() {

		int n = 512;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 512 x 512: "
						+ elapsed);
	}

	// strassen method without improving, matrix size n = 1024
	@Test(timeout = 1000 * 300)
	public void testStrTiming4() {

		int n = 1024;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 1024 x 1024: "
						+ elapsed);
	}

	// strassen method without improving, matrix size n = 4096
	@Test(timeout = 1000 * 300)
	public void testStrTiming5() {

		int n = 4096;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 4096 x 4096: "
						+ elapsed);
	}

	// strassen method without improving, matrix size n = 2048

	@Test(timeout = 1000 * 300)
	public void testStrTiming6() {

		int n = 2048;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassen(B);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out
				.println("Strassen method without improve, matrix size 2048 x 2048: "
						+ elapsed);
	}

	// strassen method with improving, matrix size n = 4
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming1() {

		int n = 4;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 4 x 4: "
				+ elapsed);
	}

	// strassen method with improving, matrix size n = 16
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming2() {

		int n = 16;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 16 x 16: "
				+ elapsed);
	}

	// strassen method with improving, matrix size n = 512
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming3() {

		int n = 512;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 512 x 512: "
				+ elapsed);
	}

	// strassen method with improving, matrix size n = 1024
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming4() {

		int n = 1024;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 1024 x 1024: "
				+ elapsed);
	}

	// strassen method with improving, matrix size n = 4096
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming5() {

		int n = 4096;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 4096 x 4096: "
				+ elapsed);
	}

	// strassen method with improving, matrix size n = 2048
	@Test(timeout = 1000 * 300)
	public void testImproveStrTiming6() {

		int n = 2048;
		Matrix A = new Matrix(n, n);
		Matrix B = new Matrix(n, n);

		long start = System.currentTimeMillis();
		A.multiplyStrassenImprove(B, bound);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Improve strassen method, matrix size 2048 x 2048: "
				+ elapsed);
	}

}
