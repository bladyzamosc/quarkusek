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
  private String code;
  private Integer area;
  private String abbreviation;

  public Voivodeship()
  {

  }

  public Voivodeship(Long id, String name, String code, Integer area, String abbreviation)
  {
    this.id = id;
    this.name = name;
    this.code = code;
    this.area = area;
    this.abbreviation = abbreviation;
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

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public Integer getArea()
  {
    return area;
  }

  public void setArea(Integer area)
  {
    this.area = area;
  }

  public String getAbbreviation()
  {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation)
  {
    this.abbreviation = abbreviation;
  }

  public static Voivodeship from(Row row)
  {
    return new Voivodeship(
      row.getLong("id"),
      row.getString("name"),
      row.getString("code"),
      row.getInteger("area"),
      row.getString("abbreviation"));
  }
}
