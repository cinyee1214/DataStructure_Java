package Lecture;


import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    private Stack<Integer> operandStack;
    private int result;
    public Postfix(String str){
        result = evaluate(str);
    }



    public Integer evaluate(String expression) {
        // TODO: Implement evalutation

        int tmp;
        operandStack = new Stack<>();
        for (int i =0; i< expression.length(); ++i){
            if(!isOperator(expression.charAt(i))){
                int t = expression.charAt(i)-'0';
                operandStack.push(t);
            }else{
                tmp = evalOp(expression.charAt(i));
                operandStack.push(tmp);
            }
        }
        return operandStack.pop();
    }

    private int evalOp(char op){
        int b = operandStack.pop();
        int a = operandStack.pop();
        switch(op){
            case '-':
                return a-b;
            case '+':
                return a+b;
            case '*':
                return a*b;
            case '/':
                return a/b;
        }
        return -1;
    }

    private boolean isOperator (char ch){
        if (ch =='+' || ch=='-' || ch=='*' || ch=='/'){
            return true;
        }
        return false;
    }







    public static void main(String[] args) {
//        // Scanner allows us to read from stdin
//        Scanner in = new Scanner(System.in);
//        String line;
//        // Instantiate a new PostfixSol object
//        Postfix p = new Postfix();
//        // Prompt stdin to indicate how many expressions will be evaluated
//        System.out.print("Enter number of expressions to evaluate: ");
//        int numExpression = in.nextInt();
//        for (int i = 0; i < numExpression; i++) {
//            // Read each expression from stdin, and pass it to the evaluate function.
//            System.out.print("Input an expression: ");
//            line = in.next();
//            Integer eval = p.evaluate(line);
//            // Print out the result and its corresponding expression.
//            System.out.printf("%s = %d\n", line, eval);
//        }
//        // All readers like this need to be closed when done, or there will be a "resource leak."
//        in.close();
        Postfix pf = new Postfix("347*2/+");
        System.out.println(pf.result);

    }


}
