package com.begentgroup.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    public static final String EXTRA_MY_DATA = "mydata";
    TextView messageView;
    EditText inputResultView;

    public static final String EXTRA_KEYWORD = "keyword";
    public static final String EXTRA_AGE = "age";
    public static final String RESULT_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        messageView = (TextView)findViewById(R.id.text_message);
        inputResultView = (EditText)findViewById(R.id.edit_input_result);

        Intent intent = getIntent();
        MyData data = (MyData)intent.getParcelableExtra(EXTRA_MY_DATA);
//        String text = intent.getStringExtra(EXTRA_KEYWORD);
//        int age = intent.getIntExtra(EXTRA_AGE, 0);
        String text = data.keyword;
        int age = data.age;

        messageView.setText(text + ", age : " + age);
        Button btn = (Button)findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = inputResultView.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(RESULT_MESSAGE, result);
                setResult(Activity.RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            }
        });
    }

}
