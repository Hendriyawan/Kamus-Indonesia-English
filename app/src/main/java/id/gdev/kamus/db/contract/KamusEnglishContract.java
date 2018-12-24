package id.gdev.kamus.db.contract;

import android.provider.BaseColumns;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusEnglishContract {
    public static String TABLE_ENGLISH = "english";

    public static final class EnglishColumns implements BaseColumns {
        public static String WORD = "word";
        public static String TRANSLATE = "translate";
    }
}