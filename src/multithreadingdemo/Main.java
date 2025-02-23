package multithreadingdemo;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello to multi-threading");
		int[] arr = {1,5,3,9,11,15,25};
		int iMax=0, iMin=1;
		int pMax = iMax;
		int pMin = iMin;
		printArr(arr);
		while(iMin < arr.length-1 || iMax < arr.length-1) {
			if(arr[iMax] < arr[iMin]) {
				int temp = arr[iMax];
				arr[iMax] = arr[iMin];
				arr[iMin] = temp;
			}
			pMax = iMax;
			pMin = iMin;
			for(int i=iMax; i<arr.length; i++) {
				if(arr[i] > arr[pMax]) {
					pMax=i;
				}
				if(arr[i] < arr[pMin]) {
					pMin=i;
				}
			}
			if(arr[iMax] < arr[pMax]) {
				int temp = arr[iMax];
				arr[iMax] = arr[pMax];
				arr[pMax] = temp;
			}
			if(arr[iMin] > arr[pMin]) {
				int temp = arr[iMin];
				arr[iMin] = arr[pMin];
				arr[pMin] = temp;
			}
			iMax+=2;
			iMin+=2;
		}
		printArr(arr);
	}

	private static void printArr(int[] arr) {
		for(int i: arr) {
			System.out.print(i+ ", ");
		}
		System.out.println();
	}
}
