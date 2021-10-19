package com.visiogenomx.app.classes;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.io.mmtf.MmtfActions;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

public class MMTFRoute implements Route
{
    @Override
    public String handle(Request request, Response response)
    {
        String id = request.params(":id");

        if(id == null)
        {
            Spark.halt(404, "No structure specified!" );

            return null;
        }
        else
        {
            try {
                Structure structure = StructureIO.getStructure(id);

                if(structure == null)
                {
                    response.status(404);

                    return "Error fetching " + request;
                }

                handleStructure(structure, response);

                return "200 OK";
            } catch(StructureException | IOException e) {
                Spark.halt(404,"Error fetching " + id);

                return null;
            }
        }
    }

    public static void handleStructure(Structure structure, Response response) throws IOException
    {
        ServletOutputStream out = response.raw().getOutputStream();
        MmtfActions.writeToOutputStream(structure, out);
        response.type("application/octet-stream");
    }
}