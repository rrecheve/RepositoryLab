package es.unex.giiis.asee.executorslab.data.roomdb;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import es.unex.giiis.asee.executorslab.data.model.Repo;


import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RepoDao {

    @Insert(onConflict = REPLACE)
    void bulkInsert(List<Repo> repo);

    // TODO - Migrate List<Repo> to LiveData<List<Repo>>
    @Query("SELECT * FROM repo WHERE owner = :owner")
    List<Repo> getReposByOwner(String owner);

    @Query("SELECT count(*) FROM repo WHERE owner = :owner")
    int getNumberReposByUser(String owner);

    @Query("SELECT * FROM repo WHERE owner = :owner")
    int deleteReposByUser(String owner);
}
