package id.gdev.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusModel implements Parcelable {
    private int id;
    private String words;
    private String translate;

    public KamusModel(String words, String translate) {
        this.words = words;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.words);
        dest.writeString(this.translate);
    }

    public KamusModel() {
    }

    protected KamusModel(Parcel in) {
        this.id = in.readInt();
        this.words = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<KamusModel> CREATOR = new Parcelable.Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}
