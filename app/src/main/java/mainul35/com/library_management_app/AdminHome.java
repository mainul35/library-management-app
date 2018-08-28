package mainul35.com.library_management_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mainul35.com.library_management_app.domain.Book;
import mainul35.com.library_management_app.service.BookService;

public class AdminHome extends AppCompatActivity {

    BookService bookService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }


    public void jumpToAddBookView(View view) {
        Intent intent = new Intent(this, AddBookActivity.class);
        intent.putExtra("username", intent.getStringExtra("username"));
        startActivity(intent);
    }

    public void jumpToAddUserView(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        intent.putExtra("username", intent.getStringExtra("username"));
        startActivity(intent);
    }
}
