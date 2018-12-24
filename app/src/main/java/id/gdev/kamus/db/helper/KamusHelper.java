package id.gdev.kamus.db.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import id.gdev.kamus.model.KamusModel;

import static android.provider.BaseColumns._ID;
import static id.gdev.kamus.db.contract.KamusEnglishContract.EnglishColumns.TRANSLATE;
import static id.gdev.kamus.db.contract.KamusEnglishContract.EnglishColumns.WORD;
import static id.gdev.kamus.db.contract.KamusEnglishContract.TABLE_ENGLISH;
import static id.gdev.kamus.db.contract.KamusIndonesiaContract.TABLE_INDONESIA;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    //query search data by word
    public Cursor searchQueryByWords(String query, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? TABLE_ENGLISH : TABLE_INDONESIA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + WORD + " LIKE '%" + query.trim() + "%'", null);
    }

    //query get all data
    public Cursor querAllData(boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? TABLE_ENGLISH : TABLE_INDONESIA;
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null, _ID + " ASC", null);
    }

    //get data by words
    public ArrayList<KamusModel> getDataByWords(String words, boolean isEnglish) {
        Cursor cursor = searchQueryByWords(words, isEnglish);
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));
                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    //get all data
    public ArrayList<KamusModel> getAllData(boolean isEnglish) {
        Cursor cursor = querAllData(isEnglish);
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWords(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));
                arrayList.add(kamusModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    //insert transaction
    public void insertTransaction(boolean isEnglish, ArrayList<KamusModel> kamusModels) {
        String DATABASE_TABLE = isEnglish ? TABLE_ENGLISH : TABLE_INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE +
                " (" + WORD + ", " +
                TRANSLATE + ") VALUES (?, ?)";

        database.beginTransaction();
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        for (int i = 0; i < kamusModels.size(); i++) {
            sqLiteStatement.bindString(1, kamusModels.get(i).getWords());
            sqLiteStatement.bindString(2, kamusModels.get(i).getTranslate());
            sqLiteStatement.execute();
            sqLiteStatement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
}
