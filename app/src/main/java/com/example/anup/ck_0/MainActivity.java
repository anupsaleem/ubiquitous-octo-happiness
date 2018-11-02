package com.example.anup.ck_0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button reg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg=(Button)findViewById(R.id.button2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        onStart();

    }

    public void login(View view)
    {
        EditText t = (EditText) findViewById(R.id.editText);
        EditText p = (EditText) findViewById(R.id.editText2);
        if(!t.getText().toString().equals("") && !p.getText().toString().equals("")){
            mAuth.signInWithEmailAndPassword(t.getText().toString(), p.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("success", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("error", "signInWithEmail:failure", task.getException());
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
        else{
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateUI(FirebaseUser user) {
        if (user == null) {
            Toast.makeText(this, "Error in loging you in.", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            i.putExtra("user", user.getEmail());
            startActivity(i);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}
