package com.bladyzamosc.quarkusek.controller;

import com.bladyzamosc.quarkusek.model.Voivodeship;
import com.bladyzamosc.quarkusek.repository.VoivodeshipsRepositoryAsyncAwait;
import io.smallrye.common.annotation.RunOnVirtualThread;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * User: Z6EKI
 * Date: 01.11.2022
 */
@Path("/voivodeships")
@RunOnVirtualThread
public class VoivodeshipController
{
  @Inject
  VoivodeshipsRepositoryAsyncAwait voivodeshipsRepositoryAsyncAwait;

  @GET
  public List<Voivodeship> getAllVoivodeships()
  {
    return voivodeshipsRepositoryAsyncAwait.findAll();
  }
}
