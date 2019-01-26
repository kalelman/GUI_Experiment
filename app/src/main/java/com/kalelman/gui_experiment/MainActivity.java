package com.kalelman.gui_experiment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.btnColorRed)
    Button btnColorRed;
    @BindView(R.id.btnColorGreen)
    Button btnColorGreen;
    @BindView(R.id.btnColorBlue)
    Button btnColorBlue;
    @BindView(R.id.layoutMain)
    ConstraintLayout layoutMain;
    @BindView(R.id.btnCalculate)
    Button btnCalculate;
    @BindView(R.id.textView)
    TextView resultField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.v(TAG, "onCreate(): " + Thread.currentThread().getId());
    }


    @OnClick({R.id.btnColorRed, R.id.btnColorGreen, R.id.btnColorBlue, R.id.btnCalculate})
    public void listenerButtonColor(View view) {
        switch (view.getId()) {

            case R.id.btnColorRed:
                Log.v(TAG, "OnClick Red: " + Thread.currentThread().getId());
                layoutMain.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case R.id.btnColorGreen:
                Log.v(TAG, "OnClick Green: " + Thread.currentThread().getId());
                layoutMain.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case R.id.btnColorBlue:
                Log.v(TAG, "OnClick Blue: " + Thread.currentThread().getId());
                layoutMain.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                break;
            case R.id.btnCalculate:
                ExampleRunnable runnable = new ExampleRunnable();
                new Thread(runnable).start();
                break;
            default:
                layoutMain.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                break;
        }
    }

    public void longCalculation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "longCalculation(): " + Thread.currentThread().getId());
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                resultField.setText("Resultado" + resultField.getText());
            }
        });


        /*
        Log.v(TAG, "longCalculation(): " + Thread.currentThread().getId());
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resultField.setText("Resultado" + resultField.getText());
        */


    }

    public class ExampleRunnable implements Runnable {

        @Override
        public void run() {
            longCalculation();
        }
    }

}
