package org.icgeb.irsvpred_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;


public class ContactUsActivity extends AppCompatActivity {

    private TextInputLayout first;
    private TextInputLayout last;
    private TextInputLayout email;
    private TextInputLayout phone;
    private TextInputLayout message;

    private TextInputEditText firstText;
    private TextInputEditText lastText;
    private TextInputEditText emailText;
    private TextInputEditText phoneText;
    private TextInputEditText messageText;


    Button bt;

    // String server_url = server address to send user contact form data;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cu);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        first = findViewById(R.id.text_input_first);
        last = findViewById(R.id.text_input_last);
        email = findViewById(R.id.text_input_email);
        phone = findViewById(R.id.text_input_number);
        message = findViewById(R.id.text_input_message);


        firstText = findViewById(R.id.first_name);
        lastText = findViewById(R.id.last_name);
        emailText = findViewById(R.id.email);
        phoneText = findViewById(R.id.phone);
        messageText = findViewById(R.id.message);

        bt = (Button) findViewById(R.id.submit);
        builder = new AlertDialog.Builder(ContactUsActivity.this);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validateFirstName() | !validateLastName() | !validateMessage() | !validatePhone()) {
                    return;
                }

                final String first, last, email, phone, message;
                first = firstText.getText().toString();
                last = lastText.getText().toString();
                email = emailText.getText().toString();
                phone = phoneText.getText().toString();
                message = messageText.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response :" + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        firstText.setText("");
                                        lastText.setText("");
                                        emailText.setText("");
                                        phoneText.setText("");
                                        messageText.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ContactUsActivity.this, "Unable to reach the servers. Please try again after sometime or email us at" +
                                " dinesh@icgeb.res.in", Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("first", first);
                        params.put("last", last);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("message", message);
                        return params;
//                        return super.getParams();
                    }
                };

                MySingleton.getInstance(ContactUsActivity.this).addTorequestque(stringRequest);


            }
        });
    }


    private boolean validateEmail() {
        String emailInput = email.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validateFirstName() {
        String usernameInput = first.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            first.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            first.setError("First name too long");
            return false;
        } else {
            first.setError(null);
            return true;
        }
    }

    private boolean validateLastName() {
        String lastnameInput = last.getEditText().getText().toString().trim();
        if (lastnameInput.isEmpty()) {
            last.setError("Field can't be empty");
            return false;
        } else if (lastnameInput.length() > 15) {
            last.setError("Last name too long");
            return false;
        } else {
            last.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = phone.getEditText().getText().toString().trim();
        if (phoneInput.isEmpty()) {
            phone.setError("Field can't be empty");
            return false;
        } else if ((phoneInput.length() >= 25)) {
            phone.setError("Please Enter Correct Phone Number");
            return false;

        } else {
            phone.setError(null);
            return true;
        }
    }


    private boolean validateMessage() {
        String messageInput = message.getEditText().getText().toString().trim();
        if (messageInput.isEmpty()) {
            message.setError("Field can't be empty");
            return false;
        } else if (messageInput.length() > 300) {
            message.setError("Message too long");
            return false;
        } else {
            message.setError(null);
            return true;
        }
    }



}