package id.gdev.kamus.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import id.gdev.kamus.R;

public class AppPreference {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(boolean state) {
        editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.app_first_run), state);
        editor.apply();
    }

    public boolean getFirstRun() {
        return sharedPreferences.getBoolean(context.getResources().getString(R.string.app_first_run), true);
    }
}
