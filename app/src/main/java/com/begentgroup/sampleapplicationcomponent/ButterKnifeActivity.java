package com.begentgroup.sampleapplicationcomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.edit_input)
    EditText inputView;

    @BindView(R.id.text_message)
    TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_send)
    public void onSendButtonClick() {
        messageView.setText(inputView.getText().toString());
    }
}
