package com.amproductions.sharemicroservice;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import java.lang.reflect.Array;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;



class Database {
    private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://192.168.99.100:27017"));
    private static MongoDatabase database = mongoClient.getDatabase("imagePlatform");
    private static MongoCollection collection = database.getCollection("images");

    @SuppressWarnings("unchecked")
    static Document GetSharedPhotos(String userId){
        try {


            FindIterable<Document> result = collection.find(eq("shareUsers", userId));

            Document res = new Document();
            for (Document doc: result) {
               res.append(doc.getString("userId"), doc.getString("imageId"));
            }
            System.out.println(res);
            return res;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")

    static boolean AddShareToPhoto(ShareEntry addShare){
        try {

            collection.updateOne(
                    new BasicDBObject("_id", new ObjectId(addShare.getObjectId())),
                    new BasicDBObject("$push", new BasicDBObject("shareUsers", addShare.getShareId()))
            );

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    static boolean DeleteShareFromPhoto(ShareEntry deleteShare){
        try {

            collection.updateOne(
                    new BasicDBObject("_id", new ObjectId(deleteShare.getObjectId())),
                    new BasicDBObject("$pull", new BasicDBObject("shareUsers", deleteShare.getShareId()))
            );

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
