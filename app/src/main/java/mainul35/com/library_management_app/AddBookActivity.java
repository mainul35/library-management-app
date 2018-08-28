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

public class AddBookActivity extends AppCompatActivity {

    BookService bookService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookService = new BookService(this);
        TextView tvEmptyList = findViewById(R.id.tvEmptyList);
        if (bookService.getAllBooks().isEmpty()){
            tvEmptyList.setText("No book found!");
        }else{
            tvEmptyList.setText("");
        }
        ListView list_view = findViewById(R.id.list_view);

        BookListItemAdapter gKeepListItemAdapter = new BookListItemAdapter(this, R.layout.activity_main, bookService.getAllBooks());
        list_view.setAdapter(gKeepListItemAdapter);
    }

    public void addNewBook(View view) {
        Intent intent = new Intent(this, InputBookDetailsActivity.class);
        intent.putExtra("username", intent.getStringExtra("username"));
        startActivity(intent);
    }

    class BookListItemAdapter extends ArrayAdapter<Book> {

        private Context context;
        private List<Book> noteItems;

        public BookListItemAdapter(@NonNull Context context, int resource, List<Book> items) {
            super(context, resource, items);
            this.context = context;
            this.noteItems = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_book_list, parent, false);
            ImageView ivBookImage = convertView.findViewById(R.id.ivBookImage);
            TextView tvBookName = convertView.findViewById(R.id.tvBookName);
            TextView tvAuthorName = convertView.findViewById(R.id.tvAuthorName);

            tvBookName.setText(noteItems.get(position).getName());
            tvAuthorName.setText(noteItems.get(position).getAuthorName());

            return convertView;
        }
    }
}
