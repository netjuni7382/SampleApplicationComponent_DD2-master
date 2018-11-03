package com.begentgroup.sampleapplicationcomponent;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    TextView resultView;
    private static final int RC_OTHER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        resultView = (TextView)findViewById(R.id.text_result);
        Button btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                String input = inputView.getText().toString();
                MyData data = new MyData();
                data.keyword = input;
                data.age = 42;

                intent.putExtra(OtherActivity.EXTRA_MY_DATA, data);
//                intent.putExtra(OtherActivity.EXTRA_KEYWORD, input);
//                intent.putExtra(OtherActivity.EXTRA_AGE, 42);

//                startActivity(intent);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(MainActivity.this, R.anim.slide_right_in, R.anim.slide_left_out);

//                startActivityForResult(intent, RC_OTHER);
                ActivityCompat.startActivityForResult(MainActivity.this, intent, RC_OTHER, options.toBundle());
//                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });


        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("count", 100);
                startService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(MyService.ACTION_MOD_TEN_ZERO);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count", 0);
            Toast.makeText(context, "count : " + count, Toast.LENGTH_SHORT).show();
            setResultCode(Activity.RESULT_OK);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_OTHER) {
            if (resultCode == Activity.RESULT_OK) {
                String text = data.getStringExtra(OtherActivity.RESULT_MESSAGE);
                resultView.setText(text);
            } else {
                Toast.makeText(this, "result canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
