package com.amproductions.sharemicroservice;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class ShareEntry {
    private String objectId;
    private String shareId;

    @JsonbCreator
    public ShareEntry(@JsonbProperty("objectId")String objectId,
                        @JsonbProperty("shareId")String shareId){
        this.objectId = objectId;
        this.shareId = shareId;
    }



    public String getObjectId(){
        return objectId;
    }

    public String getShareId(){
        return shareId;
    }

}

