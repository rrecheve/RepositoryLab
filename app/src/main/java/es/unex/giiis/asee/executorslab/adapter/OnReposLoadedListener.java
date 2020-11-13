package es.unex.giiis.asee.executorslab.adapter;

import java.util.List;

import es.unex.giiis.asee.executorslab.data.model.Repo;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<Repo> repos);
}
