package hr.kacan.caesarcipher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private EditText editText;
    private TextView textView;
    private EditText shift_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text);
        shift_key = findViewById(R.id.shift_key);
        Button btn_encrypt = findViewById(R.id.btn_encrypt);
        Button btn_decrypt = findViewById(R.id.btn_decrypt);
        Button btn_clear = findViewById(R.id.btn_clear);

        btn_encrypt.setOnClickListener(this);
        btn_decrypt.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        InputFilter[] editFilters = editText.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        editText.setFilters(newFilters);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case (R.id.btn_encrypt):
                if (!shift_key.getText().toString().isEmpty() & !editText.getText().toString().isEmpty()) {
                    textView.setText
                            (encrypt(editText.getText().toString(), Integer.parseInt(shift_key.getText().toString())));
                }
                break;

            case (R.id.btn_decrypt):
                if (!shift_key.getText().toString().isEmpty() & !editText.getText().toString().isEmpty()) {
                    textView.setText
                            (decrypt(editText.getText().toString(), Integer.parseInt(shift_key.getText().toString())));
                }
                break;
            case (R.id.btn_clear):
                editText.setText("");
                textView.setText("");
                shift_key.setText("1");
                break;
        }

    }

    private String encrypt(String plainText, int shiftKey) {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) == ' ') {
                cipherText += " ";
            } else {
                int charPosition = ALPHABET.indexOf(plainText.charAt(i));
                int keyVal = (shiftKey + charPosition) % ALPHABET.length();
                char replaceVal = ALPHABET.charAt(keyVal);
                cipherText += replaceVal;
            }
        }
        return cipherText;
    }

    private String decrypt(String cipherText, int shiftKey) {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            if (cipherText.charAt(i) == ' ') {
                plainText += " ";
            } else {
                int charPosition = ALPHABET.indexOf(cipherText.charAt(i));
                int keyVal = (charPosition - shiftKey) % ALPHABET.length();
                if (keyVal < 0) {
                    keyVal = ALPHABET.length() + keyVal;
                }
                char replaceVal = ALPHABET.charAt(keyVal);
                plainText += replaceVal;
            }
        }
        return plainText;
    }


}
