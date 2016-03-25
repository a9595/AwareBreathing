package edu.tieorange.awarebreathing;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private View mUiView;
    private TextView mUiTvInstructions;
    private TransitionDrawable mTransition;

    private int mInhalesAmount = 0;
    private int mExhalesAmount = 0;
    private boolean isPink = true;
    private boolean mIsFirstPress = true;
    private Vibrator mVibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // setup View:
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mUiTvInstructions = (TextView) findViewById(R.id.main_instructions);
        mUiView = findViewById(R.id.main_view);
        mTransition = (TransitionDrawable) mUiView.getBackground();

        mUiView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {  // TOUCH
                    mInhalesAmount++;
                    Log.d(TAG, "Inhale = " + mInhalesAmount);
                    mTransition.startTransition(2000);

                    if (mIsFirstPress) {
                        startCountDownTimer();
                        mUiTvInstructions.setVisibility(View.GONE);
                        mIsFirstPress = false;
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) { // RELEASE TOUCH
                    mExhalesAmount++;
                    Log.d(TAG, "Exhale = " + mExhalesAmount);
                    mTransition.reverseTransition(2000);
                }
                return true;
            }
        });
    }

    private void startCountDownTimer() {
        // 5sec time
        // TODO: change time
        int timeTimer = 10000;
        new CountDownTimer(timeTimer, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Log.d(TAG, "done!");
                Breathe breathe = new Breathe(mInhalesAmount, mExhalesAmount);

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("Breathe", breathe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // no back
                startActivity(intent);
                mVibrator.vibrate(700);
            }
        }.start();
    }

//    private void reset() {
//        mIsFirstPress = true;
//        mInhalesAmount = 0;
//        mExhalesAmount = 0;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
