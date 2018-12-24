package id.gdev.kamus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.gdev.kamus.R;
import id.gdev.kamus.model.KamusModel;
import id.gdev.kamus.utils.Utils;

/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */
public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {
    private Context context;
    private ArrayList<KamusModel> list;
    private String query;
    private OnKamusKlik onKamusKlik;

    public KamusAdapter(Context context) {
        this.context = context;
    }

    public void setOnKamusKlik(OnKamusKlik onKamusKlik) {
        this.onKamusKlik = onKamusKlik;
    }

    public void addItem(ArrayList<KamusModel> list) {
        this.list = list;
    }

    public void addItemSearch(String query, ArrayList<KamusModel> list) {
        this.query = query;
        this.list = list;
    }

    @Override
    public void onViewDetachedFromWindow(KamusHolder kamusHolder) {
        super.onViewDetachedFromWindow(kamusHolder);
        kamusHolder.getView().clearAnimation();
    }

    @NonNull
    @Override
    public KamusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_kamus, viewGroup, false);
        return new KamusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KamusHolder kamusHolder, int position) {
        if (query != null) {
            Utils.setColorSpanned(context, kamusHolder.tvWord, query, list.get(position).getWords());
        } else {
            kamusHolder.tvWord.setText(list.get(position).getWords());
        }

        kamusHolder.getView().setAnimation(AnimationUtils.loadAnimation(context, R.anim.scroll_anim));
        kamusHolder.tvTranslate.setText(list.get(position).getTranslate());
        final KamusModel kamusModel = list.get(position);
        kamusHolder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKamusKlik.onKamusKlik(kamusModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class KamusHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_item)
        CardView cvItem;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_translate)
        TextView tvTranslate;
        View view;

        public KamusHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public View getView() {
            return view;
        }
    }

    //interface
    public interface OnKamusKlik {
        void onKamusKlik(KamusModel kamusModel);
    }
}
