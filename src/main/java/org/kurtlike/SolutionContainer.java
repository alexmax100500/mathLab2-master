package org.kurtlike;

import org.kurtlike.elements.interfaces.Manageable;

import java.util.HashMap;

public class SolutionContainer implements Manageable<String,String> {
    public SolutionContainer(){
        solution=new Solution();
        func = new HashMap<>();
        met = new HashMap<>();
        dot=new Double[2];
    }
    Solution solution;
    HashMap<String,String> func;
    Double [][]dots;
    HashMap<String,String> met;
    Double []dot;
    private String chosenFunction="1";
    private String chosenMethod="1";

    @Override
    public HashMap<String, String> getFunctions() {
        func.put("1","x^3−2,92x^2+1,435x+0,791");
        func.put("2","tan(x)*x-x");
        func.put("3","e^-x*x^3-0.4x");
        return func;
    }

    @Override
    public HashMap<String, String> getMethods() {
        met.put("1","Метод хорд");
        met.put("2","Метод секущих");
        met.put("3","Метод простой итерации");
        return met;
    }

    @Override
    public void setBorders(Number left, Number right) {
        solution.setBorders((double) left, (double) right);
        solution.isEnd=false;
        solution.checkInterval();
    }
    @Override
    public void setAccuracy(Number accuracy) {
        solution.accurancy=(double)accuracy;
    }

    @Override
    public void setChosenFunction(String s) {
//        System.out.println("we chose func " + s);
//        solution.function="1";
        solution.function=s;
        this.chosenFunction=s;
    }

    @Override
    public void setChosenMethod(String s) {
        this.chosenMethod=s;
        solution.method=s;

    }

    @Override
    public Number[][] getFuncDots() {
        double s;
        switch (chosenFunction){
            case ("1"):
                dots=new Double[60][2];
                for(int i=0;i<60;i++){
                    s=(double)(i-20)/10;
                    dots[i][0]= s;
                    dots[i][1]= solution.fun1(s);
                }
                break;
            case ("2"):
                dots=new Double[40][2];
                for(int i=0;i<40;i++){
                    s=(double) (i - 20)/20;
                    dots[i][0]= s;
                    dots[i][1]= solution.fun2(s);
                }
                break;
            case ("3"):
                dots=new Double[60][2];
                for(int i=0;i<60;i++){
                    s=(double) (i - 10)/10;
                    dots[i][0]=s;
                    dots[i][1]= solution.fun3(s);
                }
                break;
        }
        return dots;
    }
    @Override
    public boolean isFieldSingle(){
        return !chosenMethod.equals("1");
    }
    @Override
    public String getFuncName() {
        switch (chosenFunction){
            case ("1"):
                return "x^3...";
            case ("2"):
                return "tan(x)...";
            case ("3"):
                return "e^-x...";
        }
        return null;
    }

    @Override
    public Number[] getNextApproximationDot() {
        switch(chosenMethod){
            case("1"):
                return solution.hords();
        }
        return new Number[0];
    }

    @Override
    public String getApproximationDotsName() {
        return "xyi";
    }

    @Override
    public Number getAnswer() {
        return solution.x;
    }

    @Override
    public String getAnswersNote() {
        return "xyi";
    }

    @Override
    public boolean isEnd() {
        return solution.getIsEnd();
    }
}
