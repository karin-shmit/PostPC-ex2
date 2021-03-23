package android.exercise.mini.calculator.app;

import java.io.Serializable;


public class SimpleCalculatorImpl implements SimpleCalculator {

    // todo: add fields as needed
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
        if (this.state == 1) {
            this.res = this.res + "+";
            this.state = 2;
        }
    }

    @Override
    public void insertMinus() {
        if (this.state == 1) {
            this.res = this.res + "-";
            this.state = 2;
        }
    }

    @Override
    public void insertEquals() {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
        if (this.state == 2) {
            this.res = this.res.substring(0, this.res.length() - 1);
            this.state = 1;
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
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here
        if (this.state == 0) {
            return;
        }
        if (this.state == 2){
            this.state = 1;
        }
        this.res = this.res.substring(0, this.res.length() - 1);
    }

    @Override
    public void clear() {
        // todo: clear everything (same as no-input was never given)
        this.res = "";
        this.state = 0;
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
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
    /*
    TODO: add fields to this class that will store the calculator state
     */
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
