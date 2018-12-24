package id.gdev.kamus.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.gdev.kamus.db.contract.KamusEnglishContract;
import id.gdev.kamus.db.contract.KamusIndonesiaContract;

import static android.provider.BaseColumns._ID;
import static id.gdev.kamus.db.contract.KamusEnglishContract.TABLE_ENGLISH;
import static id.gdev.kamus.db.contract.KamusIndonesiaContract.TABLE_INDONESIA;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "kamus.db";
    private static final int DATABASE_VERSION = 1;

    //create table indonesia
    private static String CREATE_TABLE_INDONESIA = "CREATE TABLE " + TABLE_INDONESIA +
            " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KamusIndonesiaContract.IndonesiaColumns.WORD + " TEXT NOT NULL, " +
            KamusIndonesiaContract.IndonesiaColumns.TRANSLATE + " TEXT NOT NULL);";

    //create table english
    private static String CREATE_TABLE_ENGLISH = "CREATE TABLE " + TABLE_ENGLISH +
            " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KamusEnglishContract.EnglishColumns.WORD + " TEXT NOT NULL, " +
            KamusEnglishContract.EnglishColumns.TRANSLATE + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INDONESIA);
        db.execSQL(CREATE_TABLE_ENGLISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH);
    }
}
