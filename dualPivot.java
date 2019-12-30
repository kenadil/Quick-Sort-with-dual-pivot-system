import java.util.*;

public class dualPivot {
	public static void main(String[] args) {
		int[] ar = generateArray(1, 10000000);
		sort(ar);
		System.out.println(Arrays.toString(ar));

	}

	public static int[] generateArray(int a, int b) {
		Random gen = new Random();

		int size = b - a + 1;
		int[] array = new int[size];

		for (int i = 0; i < size; i++)
			array[i] = gen.nextInt(1000000);

		return array;
	}

	public static boolean isSorted(int[] ar) {
		return isSorted(ar, 0, ar.length - 1);
	}

	public static boolean isSorted(int[] ar, int low, int high) {
		for (int i = 0; i < ar.length; i++) 
			if (less(ar[i], ar[i-1])) return false;
		return true;
	}

	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void exchange(int[] ar, int a, int b) {
		int swap = ar[a];
		ar[a] = ar[b];
		ar[b] = swap;
	}

	public static void sort(int[] ar) {
		Collections.shuffle(Arrays.asList(ar));
		sort(ar, 0, ar.length - 1);
		assert isSorted(ar);
	}

	public static void sort(int[] ar, int low, int high) {
		if (high <= low) return;

		if (less(ar[low], ar[high])) exchange(ar, low, high);

		int lt = low + 1, gt = high - 1;
		int i = lt;

		while (i <= gt) {
			if (less(ar[i], ar[low])) exchange(ar, lt++, i++);
			else if (less(ar[high], ar[i])) exchange(ar, i, gt--);
			else i++;
		}

		exchange(ar, low, --lt);
		exchange(ar, high, ++gt);

		sort(ar, low, lt-1);
		if (less(ar[lt], ar[gt])) sort(ar, lt+1, gt-1);
		sort(ar, gt+1, high);

		assert isSorted(ar, low, high);
	}
}