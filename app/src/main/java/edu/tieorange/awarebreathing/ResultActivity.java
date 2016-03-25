package edu.tieorange.awarebreathing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Breathe mBreathe;
    private TextView mUiTvInhales;
    private TextView mUiTvExhales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBreathe = (Breathe) getIntent().getSerializableExtra("Breathe");
        mUiTvInhales = (TextView) findViewById(R.id.result_inhales_amount);
        mUiTvExhales = (TextView) findViewById(R.id.result_exhales_amount);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mUiTvInhales.setText(mBreathe.getInhalesAmount() + " Inhale");
        mUiTvExhales.setText(mBreathe.getExhalesAmount() + " Exhale");

    }

}
