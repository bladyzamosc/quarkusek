package com.bladyzamosc.quarkusek.model;

import io.vertx.mutiny.sqlclient.Row;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */
public class County
{
  private Long id;
  private String name;
  private Voivodeship voivodeship;

  public County()
  {

  }

  public County(Long id, String name)
  {
    this.id = id;
    this.name = name;
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

  public void setVoivodeship(Voivodeship voivodeship)
  {
    this.voivodeship = voivodeship;
  }

  public Voivodeship getVoivodeship()
  {
    return voivodeship;
  }

  public static County from(Row row)
  {
    return new County(
      row.getLong("id"),
      row.getString("name"));
  }
}
