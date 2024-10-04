package com.example.assignment_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    private EditText nameEditText, emailEditText, phoneEditText, addressEditText, linkedinEditText, skillsEditText;
    private Spinner qualificationSpinner, experienceSpinner;
    private Button submitButton;
    private LinearLayout inputLayout, outputLayout;
    private TextView outputText;


    private Pattern namePattern = Pattern.compile("[A-Za-z. -]+");
    private Pattern emailPattern = Pattern.compile("([a-z\\d\\_]+@+[gmail | yahoo]+.com)");
    private Pattern phonePattern = Pattern.compile("((\\++88+\\d{11})|\\d{11})");
    private Pattern linkedinPattern = Pattern.compile("(^https:\\/\\/www\\.+linkedin+\\.com)+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        addressEditText = findViewById(R.id.address);
        linkedinEditText = findViewById(R.id.linkedinProfile);
        skillsEditText = findViewById(R.id.skills);


        qualificationSpinner = findViewById(R.id.spinner1);
        experienceSpinner = findViewById(R.id.spinner2);


        submitButton = findViewById(R.id.button);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.output);

        // qualification spinner
        String[] qualifications = new String[]{"Select Qualification", "Bachelor’s", "Master’s", "PhD"};
        qualificationSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, qualifications));

        //experience spinner
        String[] experiences = new String[]{"Select Experience", "0-1 years", "2-5 years", "6+ years"};
        experienceSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, experiences));


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                String linkedin = linkedinEditText.getText().toString().trim();
                String skills = skillsEditText.getText().toString().trim();
                String qualification = qualificationSpinner.getSelectedItem().toString();
                String experience = experienceSpinner.getSelectedItem().toString();

                if (name.isEmpty()) {
                    nameEditText.setError("Name cannot be empty");
                    nameEditText.requestFocus();

                } else if (!namePattern.matcher(name).matches()) {
                    nameEditText.setError("Name can only contain alphabets");
                    nameEditText.requestFocus();

                } else if (email.isEmpty()) {
                    emailEditText.setError("Email cannot be empty");
                    emailEditText.requestFocus();

                } else if (!emailPattern.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format");
                    emailEditText.requestFocus();

                } else if (phone.isEmpty()) {
                    phoneEditText.setError("Phone number cannot be empty");
                    phoneEditText.requestFocus();

                } else if (!phonePattern.matcher(phone).matches()) {
                    phoneEditText.setError("Invalid phone number format");
                    phoneEditText.requestFocus();

                } else if (linkedin.isEmpty()) {
                    linkedinEditText.setError("LinkedIn profile cannot be empty");
                    linkedinEditText.requestFocus();

                } else if (!linkedinPattern.matcher(linkedin).matches()) {
                    linkedinEditText.setError("Invalid LinkedIn profile URL");
                    linkedinEditText.requestFocus();

                } else if (Objects.equals(qualification, "Select Qualification")) {
                    Toast.makeText(getApplicationContext(), "Please select a qualification", Toast.LENGTH_SHORT).show();

                } else if (Objects.equals(experience, "Select Experience")) {
                    Toast.makeText(getApplicationContext(), "Please select years of experience", Toast.LENGTH_SHORT).show();

                }

                else{
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);

                    String output = "Name: " + name +
                            "\nEmail: " + email +
                            "\nPhone: " + phone +
                            "\nAddress: " + address +
                            "\nLinkedIn: " + linkedin +
                            "\nSkills: " + skills +
                            "\nQualification: " + qualification +
                            "\nExperience: " + experience;

                    outputText.setText(output);
                }

            }

        });

    }
    }


