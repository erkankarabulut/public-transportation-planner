package api.controller;

import api.provider.FindingBestRoadProvider;
import org.json.JSONObject;
import parameter.FindingBestRoadParameters;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/findbestroad")
public class FindingBestRoadController {

    /**
     * Contact report personal scope report api.controller.
     *
     * @param request HttpServletRequest
     * @return JSON Result
     */
    @GET
    @Path("/")
    @Produces("application/json;charset=utf-8")
    public Response getNewContactReportPersonalList(@Context HttpServletRequest request, @QueryParam("mx") Integer mx,
                                                    @QueryParam("my") Integer my, @QueryParam("p") Integer p, @QueryParam("r") Integer r) {
        FindingBestRoadParameters parameters = new FindingBestRoadParameters(mx, my, p, r);
        JSONObject data = FindingBestRoadProvider.findBestRoad(request, parameters);

        return Response.status(data.getInt("status")).entity(data.toString()).build();
    }


}
