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
import mainul35.com.library_management_app.domain.User;
import mainul35.com.library_management_app.service.BookService;
import mainul35.com.library_management_app.service.UserService;

public class AddUserActivity extends AppCompatActivity {

    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userService = new UserService(this);
        TextView tvEmptyList = findViewById(R.id.tvEmptyList);
        if (userService.getAllUsers().isEmpty()){
            tvEmptyList.setText("No user found!");
        }else{
            tvEmptyList.setText("");
        }
        ListView list_view = findViewById(R.id.list_view);

        UserListItemAdapter gKeepListItemAdapter = new UserListItemAdapter(this, R.layout.activity_main, userService.getAllUsers());
        list_view.setAdapter(gKeepListItemAdapter);
    }

    public void addNewUser(View view) {
        Intent intent = new Intent(this, InputUserDetailsActivity.class);
        intent.putExtra("username", intent.getStringExtra("username"));
        startActivity(intent);
    }

    class UserListItemAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> noteItems;

        public UserListItemAdapter(@NonNull Context context, int resource, List<User> items) {
            super(context, resource, items);
            this.context = context;
            this.noteItems = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_user_list, parent, false);
            ImageView ivBookImage = convertView.findViewById(R.id.ivImage);
            TextView tvBookName = convertView.findViewById(R.id.tvName);

            tvBookName.setText(noteItems.get(position).getName());

            return convertView;
        }
    }
}
