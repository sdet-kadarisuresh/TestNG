package Task;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[]= {2,7,0,9,0,6,5,4,0,1,0};
		
		int min=arr[0];
		int index=0;
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!=0) {
				arr[index++]=arr[i];
			}
		}
		while(index<arr.length)
		{
			arr[index++]=0;
		}
		
		for(int a:arr) {
			System.out.print(a+" ");
		}
				}

}
