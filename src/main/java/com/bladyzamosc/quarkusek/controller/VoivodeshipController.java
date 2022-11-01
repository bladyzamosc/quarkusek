package com.bladyzamosc.quarkusek.controller;

import com.bladyzamosc.quarkusek.model.Voivodeship;
import com.bladyzamosc.quarkusek.repository.VoivodeshipsRepositoryAsyncAwait;
import io.smallrye.common.annotation.RunOnVirtualThread;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * User: Z6EKI
 * Date: 01.11.2022
 */
@Path("/voivodeships")
public class VoivodeshipController
{
  @Inject
  VoivodeshipsRepositoryAsyncAwait voivodeshipsRepositoryAsyncAwait;
  @Inject
  Logger log;

  @GET
  @RunOnVirtualThread
  public List<Voivodeship> getAllVoivodeships()
  {
    log.infof("(%s) getAllVoivodeships()", Thread.currentThread());
    return voivodeshipsRepositoryAsyncAwait.findAll();
  }
}
