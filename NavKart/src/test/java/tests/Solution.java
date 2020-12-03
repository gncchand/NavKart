package tests;

public class Solution {
public static void main(String[] args) 
{
		System.out.println("Answer : "+solution(333,3));
}

public static int solution(int A,int B)
{
	int num=A*B; int sol = 0;
	int binary[] = new int[40];
    int index = 0;
    while(num > 0){
      binary[index++] = num%2;
      num = num/2;
    }
    System.out.print("Binary Number : ");
    for(int i = index-1;i >= 0;i--){  
    	System.out.print(binary[i]);
      sol=sol+binary[i];
	}
    System.out.println("");
    return(sol);
}
}
