package android.exercise.mini.calculator.app;

import java.io.Serializable;


public class SimpleCalculatorImpl implements SimpleCalculator {

    private String res = "";
    private int state = 0;

    @Override
    public String output() {
        // no input
        if (this.state == 0) {
            return "0";
        }
        return this.res;
    }

    @Override
    public void insertDigit(int digit) {
        this.state = 1;
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        this.res = this.res + String.valueOf(digit);
    }

    @Override
    public void insertPlus() {
        if (this.state == 0){
            this.res = "0+";
            this.state = 2;
            return;
        }
        if (this.state == 1) {
            this.res = this.res + "+";
            this.state = 2;
        }
    }

    @Override
    public void insertMinus() {
        if (this.state == 0){
            this.res = "0-";
            this.state = 2;
            return;
        }
        if (this.state == 1) {
            this.res = this.res + "-";
            this.state = 2;
        }
    }

    @Override
    public void insertEquals() {
        if (this.state == 2) {
            this.res = this.res.substring(0, this.res.length() - 1);
            this.state = 1;
        }
        if(this.res.charAt(0) == '-'){
            this.res="0"+this.res;
        }
        int i = 1;
        String[] elements = this.res.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        int result = Integer.parseInt(elements[0]);
        while (i + 1 < elements.length) {
            if (elements[i].equals("+")){
                result = result + Integer.parseInt(elements[i+1]);
            }
            if (elements[i].equals("-")){
                result = result - Integer.parseInt(elements[i+1]);
            }
            i += 2;
        }
        this.res = String.valueOf(result);
    }

    @Override
    public void deleteLast() {
        if (this.state == 0) {
            return;
        }
        if (this.state == 2){
            this.state = 1;
        }
        this.res = this.res.substring(0, this.res.length() - 1);
        if (this.res.equals("")){
            this.state = 0;
        }
    }

    @Override
    public void clear() {
        this.res = "";
        this.state = 0;
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        state.setParams(this.res, this.state);
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        this.res = casted.getRes();
        this.state = casted.getState();
    }

    private static class CalculatorState implements Serializable {
        String mem_res;
        int mem_state;

        private void setParams(String res, int state){
            this.mem_res = res;
            this.mem_state = state;
        }

        private int getState(){
            return this.mem_state;
        }

        private String getRes(){
            return this.mem_res;
        }
    }
}
