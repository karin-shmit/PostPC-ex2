package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView button0 = findViewById(R.id.button0);
    TextView button1 = findViewById(R.id.button1);
    TextView button2 = findViewById(R.id.button2);
    TextView button3 = findViewById(R.id.button3);
    TextView button4 = findViewById(R.id.button4);
    TextView button5 = findViewById(R.id.button5);
    TextView button6 = findViewById(R.id.button6);
    TextView button7 = findViewById(R.id.button7);
    TextView button8 = findViewById(R.id.button8);
    TextView button9 = findViewById(R.id.button9);
    TextView buttonPlus = findViewById(R.id.buttonPlus);
    TextView buttonMinus = findViewById(R.id.buttonMinus);
    TextView buttonEquals = findViewById(R.id.buttonEquals);
    TextView buttonClear = findViewById(R.id.buttonClear);
    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);
    View buttonBackspace = findViewById(R.id.buttonBackSpace);

    button0.setOnClickListener(v -> {
      calculator.insertDigit(0);
      calcOutput.setText(calculator.output());
    });

    button1.setOnClickListener(v -> {
      calculator.insertDigit(1);
      calcOutput.setText(calculator.output());
    });

    button2.setOnClickListener(v -> {
      calculator.insertDigit(2);
      calcOutput.setText(calculator.output());
    });

    button3.setOnClickListener(v -> {
      calculator.insertDigit(3);
      calcOutput.setText(calculator.output());
    });

    button4.setOnClickListener(v -> {
      calculator.insertDigit(4);
      calcOutput.setText(calculator.output());
    });

    button5.setOnClickListener(v -> {
      calculator.insertDigit(5);
      calcOutput.setText(calculator.output());
    });

    button6.setOnClickListener(v -> {
      calculator.insertDigit(6);
      calcOutput.setText(calculator.output());
    });

    button7.setOnClickListener(v -> {
      calculator.insertDigit(7);
      calcOutput.setText(calculator.output());
    });

    button8.setOnClickListener(v -> {
      calculator.insertDigit(8);
      calcOutput.setText(calculator.output());
    });

    button9.setOnClickListener(v -> {
      calculator.insertDigit(9);
      calcOutput.setText(calculator.output());
    });

    buttonPlus.setOnClickListener(v -> {
      calculator.insertPlus();
      calcOutput.setText(calculator.output());
    });

    buttonMinus.setOnClickListener(v -> {
      calculator.insertMinus();
      calcOutput.setText(calculator.output());
    });

    buttonEquals.setOnClickListener(v -> {
      calculator.insertEquals();
      calcOutput.setText(calculator.output());
    });

    buttonClear.setOnClickListener(v -> {
      calculator.clear();
      calcOutput.setText(calculator.output());
    });

    buttonBackspace.setOnClickListener(v -> {
      calculator.deleteLast();
      calcOutput.setText(calculator.output());
    });

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    // todo: save calculator state into the bundle
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
  }
}