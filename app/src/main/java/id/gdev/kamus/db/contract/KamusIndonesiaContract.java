package id.gdev.kamus.db.contract;

import android.provider.BaseColumns;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusIndonesiaContract {
    public static String TABLE_INDONESIA = "indonesia";

    public static final class IndonesiaColumns implements BaseColumns {
        public static String WORD = "word";
        public static String TRANSLATE = "translate";
    }
}
