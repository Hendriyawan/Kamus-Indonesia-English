package id.gdev.kamus.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.gdev.kamus.R;
import id.gdev.kamus.activity.DetailKamusActivity;
import id.gdev.kamus.adapter.KamusAdapter;
import id.gdev.kamus.db.helper.KamusHelper;
import id.gdev.kamus.model.KamusModel;
import id.gdev.kamus.mvp.KamusPresenter;
import id.gdev.kamus.mvp.KamusView;
/*
 * Created by Hendriyawan
 * 23/12/2018
 * gihtub @Hendriyawan
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class IndEngFragment extends Fragment implements KamusView, KamusAdapter.OnKamusKlik {
    @BindView(R.id.rv_ind_eng)
    RecyclerView recyclerView;

    private KamusHelper kamusHelper;
    private KamusAdapter adapter;
    private KamusPresenter presenter;

    public IndEngFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setQueryHint(getResources().getString(R.string.cari_kata));
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    presenter.searchWords(query, false);
                    return true;
                }
            });
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ind_eng, container, false);
        ButterKnife.bind(this, view);

        presenter = new KamusPresenter(getActivity(), this);
        //recycler view kamus indonesia - english
        setupRecyclerView();
        return view;

    }

    private void setupRecyclerView() {
        kamusHelper = new KamusHelper(getActivity());
        adapter = new KamusAdapter(getActivity());

        kamusHelper.open();
        adapter.addItem(kamusHelper.getAllData(false));
        kamusHelper.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnKamusKlik(this);
    }

    @Override
    public void onSearchFound(KamusAdapter adapterBySearch) {
        recyclerView.setAdapter(adapterBySearch);
        adapterBySearch.setOnKamusKlik(this);
    }

    @Override
    public void onKamusKlik(KamusModel kamusModel) {
        Intent intent = new Intent(getActivity(), DetailKamusActivity.class);
        intent.putExtra(DetailKamusActivity.EXTRA_KAMUS, kamusModel);
        startActivity(intent);
    }
}
