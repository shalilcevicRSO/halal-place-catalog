package com.selma.halal.food.project.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.selma.halal.food.project.lib.HalalPlaceMetadata;
import com.selma.halal.food.project.services.beans.HalalPlaceMetadataBean;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class HalalPlaceMetadataMutations {
    @Inject
    private HalalPlaceMetadataBean halalPlaceMetadataBean;

    @GraphQLMutation
    public HalalPlaceMetadata createHalalPlaceMetadata(@GraphQLArgument(name = "halalPlaceMetadata") HalalPlaceMetadata halalPlaceMetadata) {
        halalPlaceMetadataBean.createHalalPlaceMetadata(halalPlaceMetadata);
        return halalPlaceMetadata;
    }

    @GraphQLMutation
    public DeleteResponse deleteHalalPlaceMetadata(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(halalPlaceMetadataBean.deleteHalalPlaceMetadata(id));
    }

    @GraphQLMutation
    public void putHalalPlaceMetadata(@GraphQLArgument(name = "halalPlaceMetadataId") Integer halalPlaceMetadataId,
                                      HalalPlaceMetadata halalPlaceMetadata) {

        halalPlaceMetadata = halalPlaceMetadataBean.putHalalPlaceMetadata(halalPlaceMetadataId, halalPlaceMetadata);
    }

}
