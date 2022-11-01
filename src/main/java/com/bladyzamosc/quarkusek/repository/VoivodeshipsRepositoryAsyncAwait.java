package com.bladyzamosc.quarkusek.repository;

import com.bladyzamosc.quarkusek.model.Voivodeship;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */
@ApplicationScoped
public class VoivodeshipsRepositoryAsyncAwait
{
  @Inject
  PgPool pgPool;
  @Inject
  Logger log;

  private Map<Long, Voivodeship> voivodeshipCache = null;

  public Map<Long, Voivodeship> getVoivodeshipCache()
  {
    ensureVoivodeships();
    return voivodeshipCache;
  }

  public List<Voivodeship> findAll()
  {
    return getVoivodeshipCache().values().stream().toList();
  }

  private void ensureVoivodeships()
  {
    if (voivodeshipCache == null)
    {
      RowSet<Row> rowSet = pgPool
        .preparedQuery("SELECT * FROM voivodeship")
        .executeAndAwait();
      voivodeshipCache = iterateAndCreate(rowSet).stream().collect(Collectors.toMap(Voivodeship::getId, Function.identity()));
    }
  }

  private List<Voivodeship> iterateAndCreate(RowSet<Row> rowSet)
  {
    List<Voivodeship> persons = new ArrayList<>();
    for (Row row : rowSet)
    {
      persons.add(Voivodeship.from(row));
    }
    return persons;
  }
}
