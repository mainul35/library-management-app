package mainul35.com.library_management_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
            Log.e("User", "register: User = "+userService.findUserByUsername(etUsername.getText().toString()) );

                if(userService.findUserByUsername(etUsername.getText().toString()) instanceof User){
                    User user1 = new User();
                    user1.setName(etName.getText().toString());
                    user1.setUsername(etUsername.getText().toString());
                    user1.setPassword(etPassword.getText().toString());
                    userService.update(user1);
                    Toast.makeText(this, "User with username '"+user1.getUsername()+"' Updated.", Toast.LENGTH_LONG).show();
                }else{
                    userService.create(new User(1, etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), 1));
                    Toast.makeText(this, "New user created with username '"+etUsername.getText().toString()+"'.", Toast.LENGTH_SHORT).show();
                }


            Intent intent = new Intent(InputUserDetailsActivity.this, AddUserActivity.class);
            intent.putExtra("username", intent.getStringExtra("username"));
            startActivity(intent);
        }else{
            Toast.makeText(this, "Could not add!", Toast.LENGTH_SHORT).show();
        }
    }
}
