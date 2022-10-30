package com.bladyzamosc.quarkusek.controller;

import com.bladyzamosc.quarkusek.model.City;
import com.bladyzamosc.quarkusek.repository.CityRepositoryAsyncAwait;
import io.smallrye.common.annotation.RunOnVirtualThread;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
}
