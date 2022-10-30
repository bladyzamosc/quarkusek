package com.bladyzamosc.quarkusek.model;

import io.vertx.mutiny.sqlclient.Row;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */
public class Voivodeship
{
  private Long id;
  private String name;

  public Voivodeship()
  {
  }

  public Voivodeship(Long id, String name)
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

  public static Voivodeship from(Row row)
  {
    return new Voivodeship(
      row.getLong("id"),
      row.getString("name"));
  }
}
