package id.gdev.kamus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.gdev.kamus.R;
import id.gdev.kamus.model.KamusModel;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class DetailKamusActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;

    public static final String EXTRA_KAMUS = "kamus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamus);
        //bind view
        ButterKnife.bind(this);

        setupToolbar();

        KamusModel kamusModel = getIntent().getParcelableExtra(EXTRA_KAMUS);
        tvWord.setText(kamusModel.getWords());
        tvTranslate.setText(kamusModel.getTranslate());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
