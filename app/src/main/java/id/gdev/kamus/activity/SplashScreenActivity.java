package id.gdev.kamus.activity;

import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.gdev.kamus.R;
import id.gdev.kamus.db.helper.KamusHelper;
import id.gdev.kamus.model.KamusModel;
import id.gdev.kamus.utils.AppPreference;
import id.gdev.kamus.utils.Utils;

/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class SplashScreenActivity extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //bind the view
        ButterKnife.bind(this);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(SplashScreenActivity.this);
            appPreference = new AppPreference(SplashScreenActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<KamusModel> indonesia = Utils.getDataKamus(SplashScreenActivity.this, R.raw.indonesia_english);
                ArrayList<KamusModel> english = Utils.getDataKamus(SplashScreenActivity.this, R.raw.english_indonesia);

                progress = 10;
                publishProgress((int) progress);

                try {
                    kamusHelper.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Double progressMaxInsert = 100.0;
                Double progressDif = (progressMaxInsert - progress) / (indonesia.size() + english.size());

                kamusHelper.insertTransaction(false, indonesia);
                progress += progressDif;
                publishProgress((int) progress);

                kamusHelper.insertTransaction(true, english);
                progress += progressDif;
                publishProgress((int) progress);

                kamusHelper.close();
                appPreference.setFirstRun(false);
                publishProgress((int) maxProgress);

            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        publishProgress(50);
                        this.wait(500);
                        publishProgress((int) maxProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
