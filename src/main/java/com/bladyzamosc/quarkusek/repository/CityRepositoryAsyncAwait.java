package com.bladyzamosc.quarkusek.repository;

import com.bladyzamosc.quarkusek.model.City;
import com.bladyzamosc.quarkusek.model.Voivodeship;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

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

  @Inject
  VoivodeshipsRepositoryAsyncAwait voivodeshipsRepositoryAsyncAwait;

  public City save(City city)
  {
    Long id = pgPool
      .preparedQuery("INSERT INTO city (name, population, voivodeship_id) VALUES ($1,$2, $3) RETURNING id")
      .executeAndAwait(Tuple.of(city.getName(), city.getPopulation(), city.getVoivodeship().getId()))
      .iterator().next().getLong("id");
    city.setId(id);
    Voivodeship voivodeship = voivodeshipsRepositoryAsyncAwait.getVoivodeshipCache().get(city.getVoivodeship().getId());
    voivodeship.addPopulation(city.getPopulation());
    city.setVoivodeship(voivodeship);
    return city;
  }

  public List<City> findAll(int limit, int offset)
  {
    log.info("FindAll()" + Thread.currentThread());
    RowSet<Row> rowSet = pgPool
      .preparedQuery("SELECT * FROM city LIMIT $1 OFFSET $2")
      .executeAndAwait(Tuple.of(limit, offset));
    return convert(rowSet);
  }

  private List<City> convert(RowSet<Row> rowSet)
  {
    Map<Long, Voivodeship> map = voivodeshipsRepositoryAsyncAwait.getVoivodeshipCache();
    List<City> cities = new ArrayList<>();
    for (Row row : rowSet)
    {
      City city = City.from(row);
      Long voivodeshipId = row.getLong("voivodeship_id");
      city.setVoivodeship(map.get(voivodeshipId));
      cities.add(city);
    }
    return cities;
  }
}
