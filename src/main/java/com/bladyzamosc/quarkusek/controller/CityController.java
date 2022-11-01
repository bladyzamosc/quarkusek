package com.bladyzamosc.quarkusek.controller;

import com.bladyzamosc.quarkusek.model.City;
import com.bladyzamosc.quarkusek.repository.CityRepositoryAsyncAwait;
import io.smallrye.common.annotation.RunOnVirtualThread;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * User: Z6EKI
 * Date: 30.10.2022
 */

@Path("/cities")
@RunOnVirtualThread
public class CityController
{
  @Inject
  CityRepositoryAsyncAwait cityRepositoryAsyncAwait;
  @Inject
  Logger log;

  @POST
  public City addCity(City city)
  {
    city = cityRepositoryAsyncAwait.save(city);
    return city;
  }

  @GET
  public List<City> getAllCities(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset)
  {
    return cityRepositoryAsyncAwait.findAll(limit == null ? 10 : limit, offset == null ? 0 : offset);
  }
}
