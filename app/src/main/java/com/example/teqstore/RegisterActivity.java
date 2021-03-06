package com.example.teqstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputUsername, InputPhoneNumber, InputPassword, InputRePassword, InputEmail, InputName;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputUsername = (EditText) findViewById(R.id.register_username);
        InputPhoneNumber = (EditText) findViewById(R.id.register_Number);
        InputPassword = (EditText) findViewById(R.id.register_password);
        InputRePassword = (EditText) findViewById(R.id.register_repassword);
        InputEmail = (EditText) findViewById(R.id.register_email);
        InputName = (EditText) findViewById(R.id.register_name);
        loadingBar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {
        String name = InputName.getText().toString();
        String username = InputUsername.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String repassword = InputRePassword.getText().toString();
        String email = InputEmail.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "Please re-enter your Password", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Creating Account");
            loadingBar.setMessage("Please wait.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(username, name, phone, password, repassword, email);
        }
    }

    private void ValidatephoneNumber(final String username, final String name, final String phone, final String password, final String repassword, final String email)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Users").child(username).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("username", username);
                    userdataMap.put("name", name);
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("email", email);

                    RootRef.child("Users").child(username).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Your account has been created!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Connection Error. Please try again.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
                else
                {

                    Toast.makeText(RegisterActivity.this,"This Username already exists! Try another Username", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {


            }
        });

    }




}
