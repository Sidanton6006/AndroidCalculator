package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private EditText numField;
    private TextView equationField;

    private double firstNum;
    private double secondNum;
    private char op = '+';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numField = findViewById(R.id.numField);
        equationField = findViewById(R.id.equationField);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            numField.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(numField, false);
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private String deleteLastSym(String str){
        return str.substring(0,str.length()-1);
    }

    private void setText(String str,String optional){
        String text = numField.getText().toString();

        if(text.equals("0")){
            numField.setText(text+optional);
        }
        else if(text.charAt(0)==op){
            equationField.setText(equationField.getText().toString()+op);
            numField.setText(str);
        }
        else {
            numField.setText(text + str);
        }
    }

    private void setText(String str){
        String text = numField.getText().toString();

        if(text.equals("0")){
            numField.setText(str);
        }
        else if(text.charAt(0)==op){
            equationField.setText(equationField.getText().toString()+op);
            numField.setText(str);
        }
        else {
            numField.setText(text + str);
        }
    }

    private double calculate(){
        double res = 0;
        switch (op){
            case ('+'):
                res=firstNum+secondNum;
                break;
            case ('-'):
                res=firstNum-secondNum;
                break;
            case ('*'):
                res=firstNum*secondNum;
                break;
            case('/'):
                res=firstNum/secondNum;
                break;
        }
        return res;
    }

    public void handleClick(View view){
        String text = numField.getText().toString();

        switch (view.getId()){
            case (R.id.btn0):
                setText("0",".0");
                break;
            case (R.id.btn1):
                setText("1");
                break;
            case (R.id.btn2):
                setText("2");
                break;
            case (R.id.btn3):
                setText("3");
                break;
            case (R.id.btn4):
                setText("4");
                break;
            case (R.id.btn5):
                setText("5");
                break;
            case (R.id.btn6):
                setText("6");
                break;
            case (R.id.btn7):
                setText("7");
                break;
            case (R.id.btn8):
                setText("8");
                break;
            case (R.id.btn9):
                setText("9");
                break;
            case (R.id.btnDot):
                numField.setText(text+".");
                break;
            case (R.id.btnClear):
                numField.setText("0");
                equationField.setText("");
                break;
            case(R.id.btnRemove):
                numField.setText(deleteLastSym(text));
                break;
            case(R.id.btnDiv):
                firstNum = Double.parseDouble(text);
                equationField.setText(text);
                numField.setText("/");
                op='/';
                break;
            case(R.id.btnMult):
                firstNum = Double.parseDouble(text);
                equationField.setText(text);
                numField.setText("*");
                op='*';
                break;
            case(R.id.btnMinus):
                firstNum = Double.parseDouble(text);
                equationField.setText(text);
                numField.setText("-");
                op='-';
                break;
            case(R.id.btnPlus):
                firstNum = Double.parseDouble(text);
                equationField.setText(text);
                numField.setText("+");
                op='+';
                break;
            case(R.id.btnEqual):
                equationField.setText(equationField.getText().toString()+text+"=");
                secondNum = Double.parseDouble(numField.getText().toString());
                numField.setText(String.valueOf(calculate()));
                break;
        }
    }

}