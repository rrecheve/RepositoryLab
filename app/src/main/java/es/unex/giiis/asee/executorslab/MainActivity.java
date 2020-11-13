package es.unex.giiis.asee.executorslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import es.unex.giiis.asee.executorslab.adapter.MyAdapter;
import es.unex.giiis.asee.executorslab.adapter.OnReposLoadedListener;
import es.unex.giiis.asee.executorslab.data.network.RepoNetworkDataSource;
import es.unex.giiis.asee.executorslab.data.RepoRepository;
import es.unex.giiis.asee.executorslab.data.model.Repo;
import es.unex.giiis.asee.executorslab.data.roomdb.RepoDatabase;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener, OnReposLoadedListener {

    private MyAdapter mAdapter;
    private ProgressBar mProgressBar;
    private RepoRepository mRepository;
    private String mUsername = "";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    // Called to search user repos
    private void loadUserRepos(String username){
        this.mUsername = username;
        mAdapter.clear();
        mProgressBar.setVisibility(View.VISIBLE);
        // TODO - Set username to repository

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.repoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(mAdapter);
        EditText searchBox = findViewById(R.id.searchBox);
        Button searchButton = findViewById(R.id.searchButton);
        mProgressBar = findViewById(R.id.progressBar);
        // TODO - Get instance of Repository and observe repos

        searchButton.setOnClickListener(view -> loadUserRepos(searchBox.getText().toString()));
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        // TODO - Force Repository to fetch repos from network

    }

    @Override
    public void onReposLoaded(List<Repo> repos) {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        runOnUiThread(() -> mAdapter.swap(repos));
    }

    @Override
    public void onListInteraction(String url) {
        Uri webPage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        startActivity(webIntent);
    }
}
