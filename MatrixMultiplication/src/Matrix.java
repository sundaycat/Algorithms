import java.util.Random;

public class Matrix {

	private int col;
	private int row;
	private int[][] matrix = null;

	/**
	 * Initialize the matrix by random number and reserve two decimal place for
	 * each elements.
	 */
	public Matrix(int row, int col) {

		this.row = row;
		this.col = col;
		matrix = new int[row][col];
		Random rand = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int num = rand.nextInt(100);
				matrix[i][j] = num;
			}
		}
	}

	public Matrix(int[][] matrix) {

		this.row = matrix.length;
		this.col = matrix.length;
		this.matrix = matrix;
	}

	public static int[][] add(int[][] A, int[][] B) {

		int size = A.length;
		int[][] C = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}

	public static int[][] subtract(int[][] A, int[][] B) {

		int size = A.length;
		int[][] C = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}

	public Matrix mutiplyRegular(Matrix m) {

		int[][] A = this.getMatrix();
		int[][] B = m.getMatrix();
		int[][] C = regularMethod(A, B);

		return new Matrix(C);
	}

	public static int[][] regularMethod(int[][] A, int[][] B) {

		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}

	public Matrix multiplyStrassen(Matrix m) {

		int[][] A = this.getMatrix();
		int[][] B = m.getMatrix();
		int[][] C = strassen(A, B);

		return new Matrix(C);
	}

	// Strassen's algorithm without improving.
	public static int[][] strassen(int[][] A, int[][] B) {

		int n = A.length;
		int[][] C = new int[n][n];

		if (n == 1) {
			C[0][0] = A[0][0] * B[0][0];
		} else {

			// partition size
			int ptnSize = n / 2;

			// create sub-matrices for A, B
			int a11[][] = new int[ptnSize][ptnSize];
			int a12[][] = new int[ptnSize][ptnSize];
			int a21[][] = new int[ptnSize][ptnSize];
			int a22[][] = new int[ptnSize][ptnSize];

			int b11[][] = new int[ptnSize][ptnSize];
			int b12[][] = new int[ptnSize][ptnSize];
			int b21[][] = new int[ptnSize][ptnSize];
			int b22[][] = new int[ptnSize][ptnSize];

			// partition A, B into 4 sub-matrices respectively
			for (int i = 0; i < ptnSize; i++) {
				for (int j = 0; j < ptnSize; j++) {

					a11[i][j] = A[i][j];
					a12[i][j] = A[i][j + ptnSize];
					a21[i][j] = A[i + ptnSize][j];
					a22[i][j] = A[i + ptnSize][j + ptnSize];

					b11[i][j] = B[i][j];
					b12[i][j] = B[i][j + ptnSize];
					b21[i][j] = B[i + ptnSize][j];
					b22[i][j] = B[i + ptnSize][j + ptnSize];
				}
			}

			// Calculating p1 to p7 recursively
			int[][] aInterRS = new int[ptnSize][ptnSize];
			int[][] bInterRS = new int[ptnSize][ptnSize];

			// p1 = a11 * (b12 - b22)
			bInterRS = subtract(b12, b22);
			int[][] p1 = strassen(a11, bInterRS);

			// p2 = (a11+a12) * b22
			aInterRS = add(a11, a12);
			int[][] p2 = strassen(aInterRS, b22);

			// p3 = (a21+a22) * b11
			aInterRS = add(a21, a22);
			int[][] p3 = strassen(aInterRS, b11);

			// p4 = a22 * (b21 - b11)
			bInterRS = subtract(b21, b11);
			int[][] p4 = strassen(a22, bInterRS);

			// p5 = (a11+a22) * (b11+b22)
			aInterRS = add(a11, a22);
			bInterRS = add(b11, b22);
			int[][] p5 = strassen(aInterRS, bInterRS);

			// p6 = (a12-a22) * (b21+b22)
			aInterRS = subtract(a12, a22);
			bInterRS = add(b21, b22);
			int[][] p6 = strassen(aInterRS, bInterRS);

			// p7 = (a11-a21) * (b11+b12)
			aInterRS = subtract(a11, a21);
			bInterRS = add(b11, b12);
			int[][] p7 = strassen(aInterRS, bInterRS);

			// calculate c11, c12, c21, c22
			// c11 = p5 + p4 + p6 - p2; c12 = p1 + p2
			// c22 = p5 + p1 - p3 - p7; c21 = p3 + p4
			int[][] c11 = subtract(add(add(p5, p4), p6), p2);
			int[][] c12 = add(p1, p2);
			int[][] c21 = add(p3, p4);
			int[][] c22 = subtract(add(p5, p1), add(p3, p7));

			// combine c11,c12,c21,c22 to a single matrix C
			for (int i = 0; i < ptnSize; i++) {
				for (int j = 0; j < ptnSize; j++) {
					C[i][j] = c11[i][j];
					C[i][j + ptnSize] = c12[i][j];
					C[i + ptnSize][j] = c21[i][j];
					C[i + ptnSize][j + ptnSize] = c22[i][j];
				}
			}
		}

		return C;
	}

	public Matrix multiplyStrassenImprove(Matrix m, int bound) {

		int[][] A = this.getMatrix();
		int[][] B = m.getMatrix();
		int[][] C = strassen(A, B, bound);

		return new Matrix(C);
	}

	/**
	 * Improving strassen's algorithm by specify an lower bound. For all the
	 * matrices whose size is smaller than the lower bound, we calculate them by
	 * regular algorithm. Otherwise, calculate them by strassen's algorithm.
	 */
	public static int[][] strassen(int[][] A, int[][] B, int bound) {

		int n = A.length;
		int[][] C = new int[n][n];

		if (n <= bound) {

			return regularMethod(A, B);

		} else {

			// partition size
			int ptnSize = n / 2;

			// create sub-matrices for A, B
			int a11[][] = new int[ptnSize][ptnSize];
			int a12[][] = new int[ptnSize][ptnSize];
			int a21[][] = new int[ptnSize][ptnSize];
			int a22[][] = new int[ptnSize][ptnSize];

			int b11[][] = new int[ptnSize][ptnSize];
			int b12[][] = new int[ptnSize][ptnSize];
			int b21[][] = new int[ptnSize][ptnSize];
			int b22[][] = new int[ptnSize][ptnSize];

			// partition A, B into 4 sub-matrices respectively
			for (int i = 0; i < ptnSize; i++) {
				for (int j = 0; j < ptnSize; j++) {

					a11[i][j] = A[i][j];
					a12[i][j] = A[i][j + ptnSize];
					a21[i][j] = A[i + ptnSize][j];
					a22[i][j] = A[i + ptnSize][j + ptnSize];

					b11[i][j] = B[i][j];
					b12[i][j] = B[i][j + ptnSize];
					b21[i][j] = B[i + ptnSize][j];
					b22[i][j] = B[i + ptnSize][j + ptnSize];
				}
			}

			// Calculating p1 to p7 recursively
			int[][] aInterRS = new int[ptnSize][ptnSize];
			int[][] bInterRS = new int[ptnSize][ptnSize];

			// p1 = a11 * (b12 - b22)
			bInterRS = subtract(b12, b22);
			int[][] p1 = strassen(a11, bInterRS);

			// p2 = (a11+a12) * b22
			aInterRS = add(a11, a12);
			int[][] p2 = strassen(aInterRS, b22);

			// p3 = (a21+a22) * b11
			aInterRS = add(a21, a22);
			int[][] p3 = strassen(aInterRS, b11);

			// p4 = a22 * (b21 - b11)
			bInterRS = subtract(b21, b11);
			int[][] p4 = strassen(a22, bInterRS);

			// p5 = (a11+a22) * (b11+b22)
			aInterRS = add(a11, a22);
			bInterRS = add(b11, b22);
			int[][] p5 = strassen(aInterRS, bInterRS);

			// p6 = (a12-a22) * (b21+b22)
			aInterRS = subtract(a12, a22);
			bInterRS = add(b21, b22);
			int[][] p6 = strassen(aInterRS, bInterRS);

			// p7 = (a11-a21) * (b11+b12)
			aInterRS = subtract(a11, a21);
			bInterRS = add(b11, b12);
			int[][] p7 = strassen(aInterRS, bInterRS);

			// calculate c11, c12, c21, c22
			// c11 = p5 + p4 + p6 - p2; c12 = p1 + p2
			// c22 = p5 + p1 - p3 - p7; c21 = p3 + p4
			int[][] c11 = subtract(add(add(p5, p4), p6), p2);
			int[][] c12 = add(p1, p2);
			int[][] c21 = add(p3, p4);
			int[][] c22 = subtract(add(p5, p1), add(p3, p7));

			// combine c11,c12,c21,c22 to a single matrix C
			for (int i = 0; i < ptnSize; i++) {
				for (int j = 0; j < ptnSize; j++) {
					C[i][j] = c11[i][j];
					C[i][j + ptnSize] = c12[i][j];
					C[i + ptnSize][j] = c21[i][j];
					C[i + ptnSize][j + ptnSize] = c22[i][j];
				}
			}
		}

		return C;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String toString() {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(String.format("%15.2f", matrix[i][j]));
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
