package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  private double first_val = 0;
  private double second_val = 0;
  private String opernad = "";
  private int state = 0;

  @Override
  public String output() {
    // todo: return output based on the current state
    return "implement me please";
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    this.first_val = 0;
    this.second_val = 0;
    this.opernad = "";
    this.state = 0;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
  }
}
