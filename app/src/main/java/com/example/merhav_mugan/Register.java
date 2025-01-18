    package com.example.merhav_mugan;


    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    public class Register extends AppCompatActivity {

        private EditText UserName, Password, Email;
        private Button btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            UserName = (EditText) findViewById(R.id.username);
            Password = (EditText) findViewById(R.id.password);
            Email = (EditText) findViewById(R.id.email);

            btn = (Button) findViewById(R.id.register_button);


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String userName = UserName.getText().toString();
                    String Pass = Password.getText().toString();
                    String email = Email.getText().toString();

                    //ולידציות:
                    if (userName.isEmpty())
                        Toast.makeText(getApplicationContext(), "Fill UserName", Toast.LENGTH_LONG).show();
                    if (Pass.length() < 8)
                        Toast.makeText(getApplicationContext(), "8 chars for password ", Toast.LENGTH_LONG).show();
                    if (email.isEmpty())
                        Toast.makeText(getApplicationContext(), "Fill Email", Toast.LENGTH_LONG).show();

                    editor.putString("userName", userName);
                    editor.putString("Pass", Pass);
                    editor.putString("email", email);

                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                }
            });
        }
    }