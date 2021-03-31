package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    String expected = "12+";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.clear();
    String expected = "0";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // give some input to first calculator
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // check the current state of the second calculator
    assertEquals("0", secondCalculator.output());
    // load the saved state to the second calculator and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());

  }

  @Test
  public void when_insertingSeveralPlus_should_showOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();

    assertEquals("5+", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingSeveralMinus_should_showOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();

    assertEquals("5-", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingSeveralDeleteLast_should_showAnswerCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();

    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void negResultCalculation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();

    assertEquals("-2", calculatorUnderTest.output());
  }

  @Test
  public void calcluationAfterEquals(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();

    assertEquals("2", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithMinusAtTheBegining(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
      calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();

    assertEquals("2", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithPlusAtTheBegining(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();

    assertEquals("12", calculatorUnderTest.output());
  }

  @Test
  public void calcluationManyNegatives(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();

    assertEquals("-20", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithMinusBeforeEquals(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();

    assertEquals("-20", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithPlusBeforeEquals(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertEquals();

    assertEquals("20", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithManyEquals(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input to first calculator
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();

    assertEquals("20", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithDeleteLastAndEquals(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input to first calculator
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);

    assertEquals("5+17-125", calculatorUnderTest.output());
  }

  @Test
  public void calcluationWithClears(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input to first calculator
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertEquals();

    assertEquals("-1", calculatorUnderTest.output());
  }

  @Test
  public void noInputAndBackspace(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input to first calculator
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void TwoCalculatorsWithLoadAndContinueCalculation(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // give some input to first calculator
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // check the current state of the second calculator
    assertEquals("0", secondCalculator.output());
    // load the saved state to the second calculator and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());
    secondCalculator.insertPlus();
    secondCalculator.insertDigit(8);
    secondCalculator.insertEquals();

    assertEquals("20", secondCalculator.output());

  }
}