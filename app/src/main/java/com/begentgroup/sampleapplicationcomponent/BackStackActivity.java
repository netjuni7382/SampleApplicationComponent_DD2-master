package com.begentgroup.sampleapplicationcomponent;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BackStackActivity extends AppCompatActivity {

    String[] items = {"one", "two", "three"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);

        if (savedInstanceState == null) {
            MessageFragment f = MessageFragment.newInstance("base");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        }

        Button btn = (Button)findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count < items.length) {
                    MessageFragment f = MessageFragment.newInstance(items[count]);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, f)
                            .addToBackStack("entry"+count)
                            .commit();
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count > 0) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_empty);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }
}
