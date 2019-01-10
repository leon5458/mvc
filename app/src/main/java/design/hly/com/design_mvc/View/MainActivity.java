package design.hly.com.design_mvc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import design.hly.com.design_mvc.Adapter.BookAdapter;
import design.hly.com.design_mvc.Bean.Book;
import design.hly.com.design_mvc.Controller.BookController;
import design.hly.com.design_mvc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BookController bookController;

    private ListView lv_book;
    private List<Book> list;
    private BookAdapter adapter;
    private Button bt_add, bt_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_book = (ListView) findViewById(R.id.lv);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        bookController = new BookController();
        list = bookController.query();
        adapter = new BookAdapter(this, list);
        lv_book.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加书本按钮
            case R.id.bt_add:
                bookController.add(new BookController.onAddBookListener() {
                    @Override
                    public void onComplete() {
                        adapter.notifyDataSetChanged();
                    }
                });
                break;
            //删除书本按钮
            case R.id.bt_delete:
                bookController.delete(new BookController.onDeleteBookListener() {
                    @Override
                    public void onComplete() {
                        adapter.notifyDataSetChanged();
                    }
                });
                break;
        }
    }
}
