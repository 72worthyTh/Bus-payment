package ism.com.worthyth.beep.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;



import java.util.List;

import ism.com.worthyth.beep.R;
import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.chare.SharedPrefManager;
import ism.com.worthyth.beep.interfaces.MainViewHisDiv;
import ism.com.worthyth.beep.model.HistoryDivers;
import ism.com.worthyth.beep.model.MainAdapterHistory;
import ism.com.worthyth.beep.model.MainPresenterHistDiv;
import ism.com.worthyth.beep.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment implements MainViewHisDiv {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenterHistDiv presenter;
    MainAdapterHistory adapter;
    MainAdapterHistory.ItemClickListener itemClickListener;

    private RecyclerView.LayoutManager layoutManager;
    private ApiInterface apiInterface;

    List<HistoryDivers> historyDivers;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public HistoryFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=  inflater.inflate(R.layout.fragment_history, container, false);

        swipeRefresh = v.findViewById(R.id.swipe_refreshcompte);
        recyclerView = v.findViewById(R.id.recycler_viewcompte);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("");
        presenter = new MainPresenterHistDiv(this);
        presenter.getDataHistoryDiv();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataHistoryDiv();
            }
        });
        /*editText= v.findViewById(R.id.editext);*/

        return v;

    }






    public void fetchContact(String key){
        Users user = SharedPrefManager.getInstance(getContext()).getUser();
        final String ncompte=user.getCompte();
        key=ncompte;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<HistoryDivers>> call = apiInterface.getOpBykey(key);
        call.enqueue(new Callback<List<HistoryDivers>>() {
            @Override
            public void onResponse(Call<List<HistoryDivers>> call, Response<List<HistoryDivers>> response) {
                //progressBar.setVisibility(View.GONE);
                historyDivers = response.body();
                adapter = new MainAdapterHistory(getContext(),historyDivers,itemClickListener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HistoryDivers>> call, Throwable t) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }





    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onGetResultCompte(List<HistoryDivers> historyDiver) {

        adapter = new MainAdapterHistory(getContext(), historyDiver, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        historyDivers = historyDiver;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
