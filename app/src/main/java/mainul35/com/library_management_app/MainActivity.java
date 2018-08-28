package mainul35.com.library_management_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mainul35.com.library_management_app.domain.User;
import mainul35.com.library_management_app.service.UserService;

public class MainActivity extends AppCompatActivity {

    UserService userService;
    private EditText etInputUsername, etInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userService = new UserService(this);
//        userService.delete("mainul35");
//        userService.delete("mainul36");
        if(userService.findUserByUsername("mainul35")==null || userService.findUserByUsername("mainul36")==null) {
            User user = new User(1, "Syed Mainul Hasan", "mainul35", "secret", 0);
            User user1 = new User(2, "Syed Hasan", "mainul36", "secret", 1);
            userService.create(user);
            userService.create(user1);
        }
        etInputUsername = findViewById(R.id.etInputUsername);
        etInputPassword = findViewById(R.id.etInputPassword);
    }

    public void login(View view) {
        if ((etInputUsername!=null && !etInputUsername.getText().toString().isEmpty())
                && (etInputPassword!=null && !etInputPassword.getText().toString().isEmpty())){
            User user = userService.findUserByUsername(etInputUsername.getText().toString());
            if (user!=null){
                if (user.getPassword().equals(etInputPassword.getText().toString())){
                    if (user.getUserType()==0){
                        Intent intent = new Intent(MainActivity.this, AdminHome.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                        Toast.makeText(this, "admin logged in!", Toast.LENGTH_SHORT).show();
                    }else if (user.getUserType()==1){
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                        Toast.makeText(this, "user logged in!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Invalid password!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "No user found with this username!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
