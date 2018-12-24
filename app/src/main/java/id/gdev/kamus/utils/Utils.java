package id.gdev.kamus.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import id.gdev.kamus.R;
import id.gdev.kamus.model.KamusModel;

/*
* Created by Hendriyawan
* 23/12/2018
* gihtub @Hendriyawan
 */
public class Utils {
    //set text color spanned
    public static void setColorSpanned(Context context, TextView textView, String query, String word) {
        if (word.contains(query)) {
            SpannableString spannable = new SpannableString(word);
            spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), word.indexOf(query), word.indexOf(query) + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannable);
        } else {
            textView.setText(word);
        }
    }

    //get data from raw file
    public static ArrayList<KamusModel> getDataKamus(Context context, int data) {
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        BufferedReader reader;
        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(data);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitstr = line.split("\t");
                KamusModel kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                arrayList.add(kamusModel);

            }
        } catch (IOException e) {
            //handle exception
            e.printStackTrace();
        }
        return arrayList;
    }
}
