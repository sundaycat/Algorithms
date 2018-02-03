import java.util.Random;
import java.util.Arrays;

public class Quicksort {

	private long partCount;
	
	public static void main(String[] args){
		
		Quicksort qs = new Quicksort();
		
		int[] arr1 = qs.populate(10000);
		qs.quicksortByLast(arr1, 0, arr1.length - 1);
		int[] arr2 = Arrays.copyOf(arr1, arr1.length);
		int[] arr3 = Arrays.copyOf(arr1, arr1.length);
		
		long start = System.currentTimeMillis();
		qs.quicksortByMedian(arr3, 0, arr3.length-1);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("QS by median of medians in ms:" + elapsed);
		
		start = System.currentTimeMillis();
		qs.quicksortByLast(arr2, 0, arr2.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS by last elements in ms:" + elapsed);

		
		/*int[] arr = { 5, 2, 17, 13, 14, 17, 19, 14, 18, 2, 4, 8, 2, 5, 0, 6, 13, 4, 12, 18, 10 };
		qs.quicksortByMedian(arr, 0, arr.length-1);
		for(int i = 0; i < arr3.length; i++){
			if(i % 5 == 0) System.out.println();
			System.out.print(arr3[i] + ",");
		}*/
	}
	
	public Quicksort() {

		partCount = 0;
	}

	public long getPartCount() {
		return partCount;
	}

	public void setPartCount(long partCount) {
		this.partCount = partCount;
	}

	public int[] populate(int n) {

		int[] sortArr = new int[n];

		Random rand = new Random();
		for (int i = 0; i < sortArr.length; i++) {
			sortArr[i] = rand.nextInt()%20000000;
		}

		return sortArr;
	}

	// Partition method for quick sort by last element
	public int partition(int[] arr, int p, int r) {

		int pivot = arr[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {

			partCount++;
			if (arr[j] <= pivot) {
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}

		int temp = arr[i + 1];
		arr[i + 1] = arr[r];
		arr[r] = temp;

		// return the pivot's index in full array
		return (i + 1);
	}

	// Quciksort, using last element as pivot
	public void quicksortByLast(int[] arr, int p, int r) {
		
		if (p < r) {

			int q = partition(arr, p, r);
			quicksortByLast(arr, p, q - 1);
			quicksortByLast(arr, q + 1, r);
		}
	}

	// Quicksort, using median of medians as pivot.
	public void quicksortByMedian(int[] arr, int begin, int end){

		// find pivot by median of medians
		int size = end - begin + 1;

		// Divide the whole array into n/5 sub-array and find their medians
		int subLeft, subRight;
		int numOfmedians = (size % 5 == 0) ? (size / 5) : (size / 5 + 1);
		for (int i = 0; i < size; i += 5) {

			// last sub-array may have less than 5 elements
			subLeft = begin + i;
			subRight = ((i + 5) > size) ? begin + (size - 1) : begin + (i + 4);

			int median = findMedian(arr, subLeft, subRight);

			// move the medians of five-element subgroups to the first n/5 positions
			int temp = arr[begin + (i / 5)];
			arr[begin + (i / 5)] = arr[median];
			arr[median] = temp;
		}

		// Find the median of medians as pivot, median position = (subEnd - subBegin) / 2 + 1
		int pivotIdx = select(arr, begin, begin + numOfmedians - 1,
                (numOfmedians - 1) / 2 + 1);

		if(pivotIdx != -1) {

			// partition the array by the pivot
			int r = partition(arr, begin, end, pivotIdx);

			// recursively call the quicksort method to sort left/right sub-array
			quicksortByMedian(arr, begin, r - 1);
			quicksortByMedian(arr, r + 1, end);
		}
	}

	// Partition the array into two sub-array, whose element in the first
	// half is smaller than pivot, while the other half is larger than pivot
	// return the current position of pivot.
	public int partition(int[] arr, int p, int r, int pivotIdx) {

		// Swap pivot with last element of the list.
		int temp = arr[r];
		arr[r] = arr[pivotIdx];
		arr[pivotIdx] = temp;

		// return the pivot's index in full array
		return partition(arr, p, r);
	}

	// InsertionSort
	public void insertionSort(int[] arr, int left, int right) {

		for (int i = left + 1; i <= right; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= left && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	// Find the median's index of sub-arry with 5 elements
	public int findMedian(int[] arr, int left, int right) {

		insertionSort(arr, left, right);
		return left + (right - left) / 2;
	}

	// Return the index of "find" smallest element in array
	// begin: the starting index of the array
	// end: the ending index of the array
	// find: want to find the "find-th" largest element in the array
	public int select(int[] arr, int begin, int end, int find) {

		int size = end - begin + 1;

		if (find > size) return -1;

		if (size <= 500) {
			
			// returning the "find-th" smallest element's index in array
			quicksortByLast(arr, begin, end);
			return begin + find - 1;
		}

		// Divide the whole array into n/5 sub-array and find their medians
		int subLeft, subRight;
		int numOfmedians = (size % 5 == 0) ? (size / 5) : (size / 5 + 1);
		for (int i = 0; i < size; i += 5) {

			// last sub-array may have less than 5 elements
			subLeft = begin + i;
			subRight = ((i + 5) > size) ? begin + (size - 1) : begin + (i + 4);

			int median = findMedian(arr, subLeft, subRight);

			// move the medians of five-element subgroups to the first n/5
			// positions
			int temp = arr[begin + (i / 5)];
			arr[begin + (i / 5)] = arr[median];
			arr[median] = temp;
		}

        // Find the median of medians as pivot, median position = (subEnd - subBegin) / 2 + 1
        int pivotIdx = select(arr, begin, begin + numOfmedians - 1,
                (numOfmedians - 1) / 2 + 1);

		// Rearrange the sub-array by arr[pivotIdx] and return the pivot's index
		pivotIdx = partition(arr, begin, end, pivotIdx);

		// Pivot's current order in the array. Suppose the pivot is the second
		// smallest elements in the sub-array or full array, then the value of
		// "pos" should be 2
		int pos = pivotIdx - begin + 1;
		if (find == pos)
			return pivotIdx;
		if (find < pos) {
			return select(arr, begin, pivotIdx - 1, find);
		} else {
			return select(arr, pivotIdx + 1, end, find - pos);
		}

	}
	
}
