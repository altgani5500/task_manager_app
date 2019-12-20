package com.example.ienovo.bnan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button etUserName23 = (Button) findViewById(R.id.login);
        etUserName23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {

                Intent alvvwefrerxx = new Intent(Login.this, Login2.class);

                startActivity(alvvwefrerxx);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });
//        getWindow()
    }
}
