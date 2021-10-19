package com.visiogenomx.app.classes;

public class NGLRouteParams extends NGLParams
{
    public NGLRouteParams(String structureId, String structUrl)
    {
        super(structUrl);
        this.structureId = structureId;
    }

    public String getStructureId() {
        return this.structureId;
    }
    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    private String structureId;
}