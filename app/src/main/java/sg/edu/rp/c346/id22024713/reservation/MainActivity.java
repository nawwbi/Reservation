package sg.edu.rp.c346.id22024713.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnReset, btnConfirm;
    DatePicker dp;
    TimePicker tp;
    EditText nameInput, numberInput, sogInput;
    TextView display;
    RadioButton smoking, nonSmoking;
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.buttonReset);
        btnConfirm = findViewById(R.id.buttonConfirm);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        nameInput = findViewById(R.id.name);
        numberInput = findViewById(R.id.number);
        sogInput = findViewById(R.id.sizeOfGroup);
        radio = findViewById(R.id.radioGroup);
        nonSmoking = findViewById(R.id.radioButtonNonSmoking);
        smoking = findViewById(R.id.radioButtonSmoking);

        setDefault();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull() == true) {
                    String nameString = convertString(nameInput);
                    String numberString = convertString(numberInput);
                    String sogString = convertString(sogInput);
                    String displayString = "";
                    displayString += "Name: " + nameString;
                    displayString += "\nPhone number: " + numberString;
                    displayString += "\n2Size of group: " + sogString;
                    displayString += "\nDate: " + dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear() ;
                    displayString += "\nTime: " + tp.getCurrentHour() + ":" + tp.getCurrentMinute();

                    int radioInt = radio.getCheckedRadioButtonId();
                    if (radioInt == R.id.radioButtonSmoking) {
                        displayString += "\nArea: " + smoking.getText() + " area";
                    }
                    else {
                        displayString += "\nArea: " + nonSmoking.getText() + " area";
                    }
                    Toast.makeText(MainActivity.this, displayString, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please input empty details.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefault();

            }
        });

    }
    private String convertString(EditText a) {
        String convertedString = a.getText().toString();
        return convertedString;
    }

    private void setDefault() {
        dp.updateDate(2023, 5, 1);
        tp.setCurrentMinute(30);
        tp.setCurrentHour(19);
        nameInput.setText("");
        numberInput.setText("");
        sogInput.setText("");
        smoking.setChecked(true);
    }

    private boolean checkNull() {
        String nameString = convertString(nameInput);
        String numberString = convertString(numberInput);
        String sogString = convertString(sogInput);

        if (nameString.isEmpty()){
            nameInput.setError("Name required.");
            return false;
        }
        if (numberString.isEmpty()){
            numberInput.setError("Phone number required.");
            return false;
        }
        if (sogString.isEmpty()){
            sogInput.setError("Size of group required.");
            return false;
        }
        return true;
    }

}