package systexproject_yonnie;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	System.out.print("請輸入一則四則運算（輸入end退出計算器）：");
    	//ex: 3+(-1.2*1.2+8)/2
    	Scanner in = new Scanner(System.in);
    	while (in.hasNextLine()) {
    		String input = in.nextLine();
    		if ("end".equals(input)) {
    		System.out.println("謝謝使用！");
    		in.close();
    		break;
    		}
    		else {
    			System.out.println(Calculator.getResult(input));
    			System.out.print("請輸入一則四則運算（輸入end退出計算器）：");
    		}
    	}
    }
}
