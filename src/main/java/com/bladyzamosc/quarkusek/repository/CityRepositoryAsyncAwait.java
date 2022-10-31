package com.bladyzamosc.quarkusek.repository;

import com.bladyzamosc.quarkusek.model.City;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */
@ApplicationScoped
public class CityRepositoryAsyncAwait
{
  @Inject
  PgPool pgPool;
  @Inject
  Logger log;

  public City save(City city)
  {
    Long id = pgPool
      .preparedQuery("INSERT INTO city (name, population, voivodeship_id) VALUES ($1,$2, $3) RETURNING id")
      .executeAndAwait(Tuple.of(city.getName(), city.getPopulation(), city.getVoivodeship().getId()))
      .iterator().next().getLong("id");
    city.setId(id);
    return city;
  }

  public List<City> findAll()
  {
    log.info("FindAll()" + Thread.currentThread());
    RowSet<Row> rowSet = pgPool
      .preparedQuery("SELECT * FROM city")
      .executeAndAwait();
    return iterateAndCreate(rowSet);
  }

  private List<City> iterateAndCreate(RowSet<Row> rowSet)
  {
    List<City> persons = new ArrayList<>();
    for (Row row : rowSet)
    {
      persons.add(City.from(row));
    }
    return persons;
  }
}
