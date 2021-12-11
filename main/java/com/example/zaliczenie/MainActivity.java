package com.example.zaliczenie;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public int wynikGry = 0;
    int upIlosc = 0;
    int upIlosc1 = 0;
    int upIlosc2 = 0;
    int klik = 1;

    int progresBarUpStatus = 0;
    int progresBarUpStatus1 = 0;
    int progresBarUpStatus2 = 0;

    int progresBarUpTimerStatus = 0;
    int progresBarUpTimerStatus1 = 0;
    int progresBarUpTimerStatus2 = 0;

    Button punktButton;
    Button punktUp;
    TextView punktyTextView;

    Button upButton;
    Button upButton1;
    Button upButton2;

    TextView textViewUp;
    TextView textViewUp1;
    TextView textViewUp2;

    ProgressBar progressBarUp;
    ProgressBar progressBarUp1;
    ProgressBar progressBarUp2;

    ProgressBar progressBarUpTimer;
    ProgressBar progressBarUpTimer1;
    ProgressBar progressBarUpTimer2;

    Handler Handler = new Handler();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        punktButton = findViewById(R.id.punktButton);
        punktUp = findViewById(R.id.punktUp);
        punktyTextView = findViewById(R.id.punktyTextView);

        upButton = findViewById(R.id.upButton);
        upButton1 = findViewById(R.id.upButton1);
        upButton2 = findViewById(R.id.upButton2);

        textViewUp = findViewById(R.id.textViewUp);
        textViewUp1 = findViewById(R.id.textViewUp1);
        textViewUp2 = findViewById(R.id.textViewUp2);

        progressBarUp = findViewById(R.id.progressBarUp);
        progressBarUp1 = findViewById(R.id.progressBarUp1);
        progressBarUp2 = findViewById(R.id.progressBarUp2);

        progressBarUpTimer = findViewById(R.id.progressBarUpTimer);
        progressBarUpTimer1 = findViewById(R.id.progressBarUpTimer1);
        progressBarUpTimer2 = findViewById(R.id.progressBarUpTimer2);

        odswiezanie();

        punktUp.setOnClickListener(v -> {
            if (wynikGry >= 100 * klik) {
                wynikGry -= 100 * klik;
                klik *= 2;
                punktButton.setText("+" + klik + "\nKlikaj");
                punktUp.setText(Integer.toString((klik * 100)));
            }
        });

        punktButton.setOnClickListener(v -> {
            wynikGry += klik;
            punktyTextView.setText(Integer.toString(wynikGry));
            wygrana();
        });

        upButton.setOnClickListener(v -> {
            if (wynikGry >= 10) {
                upIlosc++;
                wynikGry -= 10;
                punktyTextView.setText(Integer.toString(wynikGry));
                textViewUp.setText("+" + upIlosc * 10);
                if (progresBarUpStatus == 100) {
                    progresBarUpStatus = 10;
                } else {
                    progresBarUpStatus += 10;
                }
                progressBarUp.setProgress(progresBarUpStatus);
            }
            wygrana();
        });

        upButton1.setOnClickListener(v -> {
            if (wynikGry >= 100) {
                upIlosc1++;
                wynikGry -= 100;
                punktyTextView.setText(Integer.toString(wynikGry));
                textViewUp1.setText("+" + upIlosc1 * 100);
                if (progresBarUpStatus1 == 100) {
                    progresBarUpStatus1 = 10;
                } else {
                    progresBarUpStatus1 += 10;
                }
                progressBarUp1.setProgress(progresBarUpStatus1);
            }
            wygrana();
        });

        upButton2.setOnClickListener(v -> {
            if (wynikGry >= 10000) {
                upIlosc2++;
                wynikGry -= 10000;
                punktyTextView.setText(Integer.toString(wynikGry));
                textViewUp2.setText("+" + upIlosc2 * 10000);
                if (progresBarUpStatus2 == 100) {
                    progresBarUpStatus2 = 10;
                } else {
                    progresBarUpStatus2 += 10;
                }
                progressBarUp2.setProgress(progresBarUpStatus2);
            }
            wygrana();
        });

        up();
        up1();
        up2();

    }
    public void odswiezanie() {
        Thread t = new Thread() {
            @Override
            public void run() {

                while (!isInterrupted()) {
                    try {
                        Thread.sleep(100);
                        runOnUiThread(() -> punktyTextView.setText(String.valueOf(wynikGry)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    public void wygrana() {
        if (wynikGry >= 10000000) {
            progressBarUp.setProgress(0);
            progressBarUp1.setProgress(0);
            progressBarUp2.setProgress(0);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("KONIEC GRY")
                    .setMessage("Wyklikałeś 10mln Brawo")
                    .setPositiveButton("Reset", null)
                    .setNegativeButton("Cancel", null)
                    .show();
            Button postiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            postiveButton.setOnClickListener(v -> {
                finish();
                startActivity(getIntent());

            });
        }
    }

    public void up() {
        new Thread(() -> {
            while (progresBarUpTimerStatus < 100) {
                if (upIlosc > 0) {
                    progresBarUpTimerStatus++;
                    SystemClock.sleep(300 / upIlosc);
                    Handler.post(() -> progressBarUpTimer.setProgress(progresBarUpTimerStatus));
                    if (progresBarUpTimerStatus == 100) {
                        progresBarUpTimerStatus = 0;
                        wynikGry += 10 * upIlosc;
                    }
                }
            }
        }).start();
    }

    public void up1() {
        new Thread(() -> {
            while (progresBarUpTimerStatus1 < 100) {
                if (upIlosc1 > 0) {
                    progresBarUpTimerStatus1++;
                    SystemClock.sleep(600 / upIlosc1);
                    Handler.post(() -> progressBarUpTimer1.setProgress(progresBarUpTimerStatus1));
                    if (progresBarUpTimerStatus1 == 100) {
                        progresBarUpTimerStatus1 = 0;
                        wynikGry += 100 * upIlosc1;
                    }
                }
            }
        }).start();
    }

    public void up2() {
        new Thread(() -> {
            while (progresBarUpTimerStatus2 < 100) {
                if (upIlosc2 > 0) {
                    progresBarUpTimerStatus2++;
                    SystemClock.sleep(1200 / upIlosc2);
                    Handler.post(() -> progressBarUpTimer2.setProgress(progresBarUpTimerStatus2));
                    if (progresBarUpTimerStatus2 == 100) {
                        progresBarUpTimerStatus2 = 0;
                        wynikGry += 10000 * upIlosc2;
                    }
                }
            }
        }).start();
    }

}