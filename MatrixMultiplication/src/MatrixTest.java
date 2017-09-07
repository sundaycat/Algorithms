public class MatrixTest {

	public static void main(String[] args) {

		Matrix m1 = new Matrix(500, 500);
		Matrix m2 = new Matrix(500, 500);
		
		/*long start = System.currentTimeMillis();
		m1.mutiplyRegular(m2);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Regular method, matrix size 4096 x 4096: "
				+ elapsed);*/
		
		long start = System.currentTimeMillis();
		m1.multiplyStrassenImprove(m2, 1024);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Strassen Improve, matrix size 500 x 500: "
				+ elapsed);
		
		/*start = System.currentTimeMillis();
		m1.multiplyStrassen(m2);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("Strassen method, matrix size 4096 x 4096: "
				+ elapsed);*/
	}
}
