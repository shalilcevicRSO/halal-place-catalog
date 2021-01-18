package com.selma.halal.food.project.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.selma.halal.food.project.lib.HalalPlaceMetadata;
import com.selma.halal.food.project.services.beans.HalalPlaceMetadataBean;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@GraphQLClass
@ApplicationScoped
public class HalalPlaceMetadataQueries {
    @Inject
    private HalalPlaceMetadataBean halalPlaceMetadataBean;

    @GraphQLQuery
    public PaginationWrapper<HalalPlaceMetadata> allHalalPlaceMetadata(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                                  @GraphQLArgument(name = "sort") Sort sort,
                                                                  @GraphQLArgument(name = "filter") Filter filter) {

        return GraphQLUtils.process(halalPlaceMetadataBean.getHalalPlaceMetadata(), pagination, sort, filter);
    }

    @GraphQLQuery
    public HalalPlaceMetadata getHalalPlaceMetadata(@GraphQLArgument(name = "halalPlaceMetadataId") Integer id) {
        return halalPlaceMetadataBean.getHalalPlaceMetadata(id);
    }

    @GraphQLQuery
    public HalalPlaceMetadata getSearchHalalPlaceMetadata(
            @GraphQLArgument(name = "city") String city,
            @GraphQLArgument(name = "country") String country) {

        return halalPlaceMetadataBean.getSearchHalalPlaceMetadata(city, country);


    }

    // 3rd Party API
    @GraphQLQuery
    public String getCurrentWeather(
            @GraphQLArgument(name = "city") String city) {

        HttpResponse<String> response = null;
        try{
            Unirest.setTimeouts(0, 0);
            response =  Unirest.get("http://api.openweathermap.org/data/2.5/weather?q=" +
                    city +
                    "&appid=f786409db597cff4cd0f594a80cb1bad&units=metric")
                    .asString();

        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
        return response.getBody();


    }


}
