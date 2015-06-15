package com.keger.breathing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.keger.breathing.light.BreathingLight;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setViews();
    }

    private void setViews() {
        TextView mTextView = (TextView) findViewById(R.id.tv_hello_world);
        BreathingLight.setBreathing(mTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BreathingLight.stopBreathing(v);
            }
        });
    }
}
