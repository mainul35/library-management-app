package mainul35.com.library_management_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mainul35.com.library_management_app.domain.User;
import mainul35.com.library_management_app.service.UserService;

public class InputUserDetailsActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_details);
        userService = new UserService(this);
        etName = findViewById(R.id.etInputName);
        etUsername = findViewById(R.id.etInputUsername);
        etPassword = findViewById(R.id.etInputPassword);
    }

    public void register(View view) {
        if((etName!=null && !etName.getText().toString().isEmpty())
                && (etUsername!=null && !etUsername.getText().toString().isEmpty())
                && (etPassword!=null && !etPassword.getText().toString().isEmpty())
                ) {
            if(userService.findUserByUsername("mainul35")==null) {
                userService.create(new User(1, etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), 1));
            }else{
                User user = new User();
                user.setName(etName.getText().toString());
                user.setName(etUsername.getText().toString());
                user.setName(etPassword.getText().toString());
                userService.update(user);
            }
            Intent intent = new Intent(InputUserDetailsActivity.this, AddUserActivity.class);
            intent.putExtra("username", intent.getStringExtra("username"));
            startActivity(intent);
        }else{
            Toast.makeText(this, "Could not add!", Toast.LENGTH_SHORT).show();
        }
    }
}
