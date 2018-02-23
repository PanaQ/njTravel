package travel.nanjing.com.travel.business.travelRecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import travel.nanjing.com.travel.R;

/**
 *
 */
public class RecordDetailActivity extends AppCompatActivity {

    private RecyclerView noteRv;
    private RecyclerView contentRv;
    private NoteAdapter adapter;
    private ImageView attention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteRv = ((RecyclerView) findViewById(R.id.noteRv));
        contentRv = ((RecyclerView) findViewById(R.id.contentRv));
        attention = ((ImageView) findViewById(R.id.attention));

        noteRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(this);
        noteRv.setAdapter(adapter);

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecordDetailActivity.this, "关注", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
