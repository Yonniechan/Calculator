package systexproject_yonnie;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	System.out.print("�п�J�@�h�|�h�B��]��Jend�h�X�p�⾹�^�G");
    	//ex: 3+(-1.2*1.2+8)/2
    	Scanner in = new Scanner(System.in);
    	while (in.hasNextLine()) {
    		String input = in.nextLine();
    		if ("end".equals(input)) {
    		System.out.println("���¨ϥΡI");
    		in.close();
    		break;
    		}
    		else {
    			System.out.println(Calculator.getResult(input));
    			System.out.print("�п�J�@�h�|�h�B��]��Jend�h�X�p�⾹�^�G");
    		}
    	}
    }
}
