package mainul35.com.library_management_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mainul35.com.library_management_app.service.UserService;

public class MainActivity extends AppCompatActivity {

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userService = new UserService(this);
    }

    public void login(View view) {
    }
}
