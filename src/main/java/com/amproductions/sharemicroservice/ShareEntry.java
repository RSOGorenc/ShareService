package com.amproductions.sharemicroservice;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class ShareEntry {
    private String imageId;
    private String shareId;

    @JsonbCreator
    public ShareEntry(@JsonbProperty("imageId") String imageId,
                        @JsonbProperty("shareId")String shareId){
        this.imageId = imageId;
        this.shareId = shareId;
    }



    public String getImageId(){
        return imageId;
    }

    public String getShareId(){
        return shareId;
    }

}

