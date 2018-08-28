package mainul35.com.library_management_app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import mainul35.com.library_management_app.domain.Book;
import mainul35.com.library_management_app.service.BookService;

public class InputBookDetailsActivity extends AppCompatActivity {

    EditText etBookName, etIsbn, etAuhorName, etPublication, etEdition;
    static TextView tvPublishedDate;
    BookService bookService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_book_details);
        bookService = new BookService(this);
        etBookName = findViewById(R.id.etBookName);
        etIsbn = findViewById(R.id.etIsbn);
        etAuhorName = findViewById(R.id.etAuhorName);
        etPublication = findViewById(R.id.etPublication);
        etEdition = findViewById(R.id.etEdition);
        tvPublishedDate = findViewById(R.id.tvPublishedDate);
    }

    public void showDatePicker(View v) {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "Select Date");
    }

    public void addNewBook(View view) {
        if((etBookName!=null && !etBookName.getText().toString().isEmpty())
                && (etAuhorName!=null && !etAuhorName.getText().toString().isEmpty())
                && (etEdition!=null && !etEdition.getText().toString().isEmpty())
                && (etIsbn!=null && !etIsbn.getText().toString().isEmpty())
                && (etPublication!=null && !etPublication.getText().toString().isEmpty())
                && (tvPublishedDate!=null && !tvPublishedDate.getText().toString().isEmpty())
                ){
            Book book = new Book();
            book.setName(etBookName.getText().toString());
            book.setEdition(etEdition.getText().toString());
            book.setPublishingDate(tvPublishedDate.getText().toString());
            book.setPublication(etPublication.getText().toString());
            book.setIsbn(etIsbn.getText().toString());
            book.setAuthorName(etAuhorName.getText().toString());

            bookService.create(book);

            Intent intent = new Intent(InputBookDetailsActivity.this, AddBookActivity.class);
            intent.putExtra("username",intent.getStringExtra("username"));
            startActivity(intent);
        }
    }

    public static class MyDatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }

        private DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        tvPublishedDate.setText(view.getYear() +
                                " / " + (view.getMonth()+1) +
                                " / " + view.getDayOfMonth());
                    }
                };
    }
}
