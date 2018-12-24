package id.gdev.kamus.mvp;

import android.content.Context;

import java.util.ArrayList;

import id.gdev.kamus.adapter.KamusAdapter;
import id.gdev.kamus.db.helper.KamusHelper;
import id.gdev.kamus.model.KamusModel;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusPresenter {
    private Context context;
    private KamusView kamusView;

    public KamusPresenter(Context context, KamusView kamusView) {
        this.context = context;
        this.kamusView = kamusView;
    }

    public void searchWords(String words, boolean isEnglish) {
        KamusHelper kamusHelper = new KamusHelper(context);
        kamusHelper.open();
        KamusAdapter adapter = new KamusAdapter(context);
        ArrayList<KamusModel> kamusModels = kamusHelper.getDataByWords(words, isEnglish);

        if (kamusModels.size() > 0) {
            adapter.addItemSearch(words, kamusModels);
            kamusView.onSearchFound(adapter);
        }
    }
}
