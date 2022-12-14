package com.bladyzamosc.quarkusek.model;

import io.vertx.mutiny.sqlclient.Row;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */
public class City
{
  private Long id;
  private String name;
  private Voivodeship voivodeship;
  private Integer population;

  public City()
  {
  }

  public City(Long id, String name, Integer population)
  {
    this.id = id;
    this.name = name;
    this.population = population;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getPopulation()
  {
    return population;
  }

  public void setPopulation(Integer population)
  {
    this.population = population;
  }

  public void setVoivodeship(Voivodeship voivodeship)
  {
    this.voivodeship = voivodeship;
  }

  public Voivodeship getVoivodeship()
  {
    return voivodeship;
  }

  public static City from(Row row)
  {
    return new City(
      row.getLong("id"),
      row.getString("name"),
      row.getInteger("population")
    );
  }
}
