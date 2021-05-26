package systexproject_yonnie;

import java.util.ArrayList;
import java.util.LinkedList;

/**該程式是一可以做到四則運算的計算器(小括號及負號皆可運算)，並且有偵錯設定**/

/**
 * 通過對括號位置的觀察，由左而右掃描字串，將每個右括號與未配對的左括號配對
 * 在掃描過程中，將每個遇到的左括號標記，出現右括號的時後再標記，並先進到doCalculation計算
**/


public class Calculator {
	private static boolean isRightFormat = true;
	
    public static double getResult(String formula){
        double returnValue = 0;
        try{
            returnValue = doAnalysis(formula);
        }
        catch(NumberFormatException e){
            System.out.println("公式格式有誤，請檢查:" + formula);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(!isRightFormat){
            System.out.println("公式格式有誤，請檢查:" + formula);
        }
        return returnValue;
    }
    
  /**
     * doAnalysis是一個將括弧內外分開計算的方法
     * @param formula
     * @return returnValue
  **/
    private static double doAnalysis(String formula){
        double returnValue = 0;
        //LinkedList是每個節點除了記錄資料之外，還使用額外的指標指向下一個節點，將節點以此方式串連起來形成一個連結串列
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int curPos = 0;
        String beforePart = "";
        String afterPart = "";
        String calculator = "";
        isRightFormat = true;
        while(isRightFormat && (formula.indexOf('(') >= 0 || formula.indexOf(')') >= 0)){
        	//將字串轉換成新的character array，並一個一個取出
        	for(char s : formula.toCharArray()){
                if(s == '('){
                	//將左括號的位置標記
                    stack.add(curPos);
                }
                else if(s == ')'){
                    if(stack.size() > 0){
                    	//將括弧內的式子與括弧外的式子分開，並做doCalculation
                        beforePart = formula.substring(0, stack.getLast()); 
                        calculator = formula.substring(stack.getLast() + 1, curPos); 
                        afterPart = formula.substring(curPos + 1); 
                        formula = beforePart + doCalculation(calculator) + afterPart; 
                        stack.clear(); 
                        break; 
                    }
                    else{
                        System.out.println("有未關閉的右括號！");
                        isRightFormat = false;
                    }
                }
                curPos++;
            }
            if(stack.size() > 0){
                System.out.println("有未關閉的左括號！");
                break;
            }
        }
        if(isRightFormat){
            returnValue = doCalculation(formula);
        }
        return returnValue;
    }
     

    private static double doCalculation(String formula) {
        ArrayList<Double> values = new ArrayList<Double>();
        ArrayList<String> operators = new ArrayList<String>();
        int curPos = 0;
        int prePos = 0;
        int minus = 0;     
        for (char s : formula.toCharArray()) {
             if ((s == '+' || s == '-' || s == '*' || s == '/') && minus !=0 && minus !=2) {
            	 //trim(): 將字串兩端空白去掉，形成新的字串
                 values.add(Double.parseDouble(formula.substring(prePos, curPos).trim()));
                 operators.add("" + s);
                 prePos = curPos + 1;
                 minus = minus +1;
             }
             else{
                 minus = 1;
             }
             curPos++;
        }
        values.add(Double.parseDouble(formula.substring(prePos).trim()));
        //先乘除後加減
        char op;
        for (curPos = 0; curPos <= operators.size() - 1; curPos++) {
            op = operators.get(curPos).charAt(0);
            switch (op) {
            case '*':
                values.add(curPos, values.get(curPos) * values.get(curPos + 1));
                values.remove(curPos + 1);
                values.remove(curPos + 1);
                operators.remove(curPos);
                curPos = -1;
                break;
            case '/':
                values.add(curPos, values.get(curPos) / values.get(curPos + 1));
                values.remove(curPos + 1);
                values.remove(curPos + 1);
                operators.remove(curPos);
                curPos = -1;
                break;
            }
        }
        for (curPos = 0; curPos <= operators.size() - 1; curPos++) {
            op = operators.get(curPos).charAt(0);
            switch (op) {
            case '+':
                values.add(curPos, values.get(curPos) + values.get(curPos + 1));
                values.remove(curPos + 1);
                values.remove(curPos + 1);
                operators.remove(curPos);
                curPos = -1;
                break;
            case '-':
                values.add(curPos, values.get(curPos) - values.get(curPos + 1));
                values.remove(curPos + 1);
                values.remove(curPos + 1);
                operators.remove(curPos);
                curPos = -1;
                break;
            }
        }
        return values.get(0).doubleValue();
    }
}
