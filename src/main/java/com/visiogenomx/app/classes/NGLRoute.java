package com.visiogenomx.app.classes;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class NGLRoute implements TemplateViewRoute
{
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception
    {
        String id = request.params(":id");

        if(id == null)
        {
            response.status(404);

            return null;
        }
        else
        {
            try {
                String structUrl = Routes.MMTF.replace(":id", id);
                NGLRouteParams params = new NGLRouteParams(id, structUrl);

                return new ModelAndView(params, "ngl.html.hbs");
            } catch(Exception e) {
                response.status(404);

                return null;
            }
        }
    }
}