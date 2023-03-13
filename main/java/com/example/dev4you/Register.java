package com.example.dev4you;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextView logintv;
    EditText name, number, pnr, ticket, weight;
    Button insertToOurProject;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference nodeOurProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hooks
        {
            logintv = findViewById(R.id.logintv);
            name = findViewById(R.id.name);
            number = findViewById(R.id.number);
            pnr = findViewById(R.id.pnr);
            ticket = findViewById(R.id.ticket);
            weight = findViewById(R.id.weight);
            insertToOurProject = findViewById(R.id.insertToOurProject);
        }

        nodeOurProject = db.getReference().child("Our Data");

        //validations
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Required");
                } else {
                    name.setError(null);
                }
            }
        });
        number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (number.getText().toString().isEmpty()) {
                    number.setError("Required");
                } else {
                    number.setError(null);
                }
            }
        });
        pnr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (pnr.getText().toString().isEmpty()) {
                    pnr.setError("Required");
                } else {
                    pnr.setError(null);
                }
            }
        });
        ticket.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ticket.getText().toString().isEmpty()) {
                    ticket.setError("Required");
                } else {
                    ticket.setError(null);
                }
            }
        });
        weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (weight.getText().toString().isEmpty()) {
                    weight.setError("Required");
                } else {
                    weight.setError(null);
                }
            }
        });

        insertToOurProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToOurProject();
            }
        });
    }

    private void addToOurProject() {
        String fname = name.getText().toString();
        String num = number.getText().toString();
        String pnr_no = pnr.getText().toString();
        String tic_no = ticket.getText().toString();
        String luggage_weight = weight.getText().toString();

        DataHolder dataHolder = new DataHolder(fname,num,pnr_no,tic_no,luggage_weight);

        if (!validateName() | !validateMobile()) {
            Toast.makeText(Register.this, "Nope", Toast.LENGTH_SHORT).show();
        } else {
            nodeOurProject.child("Ticket_No: " + tic_no).setValue(dataHolder).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(Register.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this,Choose.class);
                    i.putExtra("pnr_no",pnr_no);
                    i.putExtra("luggage_weight",luggage_weight);
                    startActivity(i);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateName() {
        String valid = name.getText().toString();
        String noWhiteSpace = "(?=.*[@#$%^&+=])";

        if (valid.isEmpty()) {
            name.setError("Field cannot be empty.");
            return false;
        } else if (valid.length() >= 30) {
            name.setError("Name is too long.");
            return false;
        } else if (valid.length() < 3) {
            name.setError("Invalid name!");
            return false;
        } else if (valid.matches(noWhiteSpace)) {
            name.setError("Special characters are not allowed.");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validateMobile() {
        String valid = number.getText().toString();

        if (valid.isEmpty()) {
            number.setError("Field cannot be empty");
            return false;
        } else if (valid.length() < 10) {
            number.setError("Invalid mobile number");
            return false;
        } else {
            number.setError(null);
            return true;
        }
    }
}