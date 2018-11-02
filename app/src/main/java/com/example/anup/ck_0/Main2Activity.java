package com.example.anup.ck_0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
    }


    public void register(View view) {
        EditText t = (EditText) findViewById(R.id.editText45);
        EditText p = (EditText) findViewById(R.id.editText21);
        EditText cp = (EditText) findViewById(R.id.editText23);
        EditText email = (EditText) findViewById(R.id.editText45);
        EditText phn = (EditText) findViewById(R.id.editText405);
        if (!t.getText().toString().equals("") && p.getText().toString().equals(cp.getText().toString())&& !cp.getText().toString().equals("") && !p.getText().toString().equals("") && !email.getText().toString().equals("") && !phn.getText().toString().equals("")) {
            Editable email1 = t.getText();
            Editable password = p.getText();
            Toast.makeText(this, email1.toString() + " " + password.toString(), Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email1.toString(), password.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("status", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Main2Activity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d("failure", task.getException() + "");
                                Toast.makeText(Main2Activity.this, "" + task.getException() + "", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }else
        {
            Toast.makeText(this, "Passwords do not match or the entries are not valid", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateUI(FirebaseUser user) {
        if (user == null) {
            Toast.makeText(this, "Error in creating an account.", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            i.putExtra("user", user.getEmail());
            startActivity(i);
        }

    }


}

