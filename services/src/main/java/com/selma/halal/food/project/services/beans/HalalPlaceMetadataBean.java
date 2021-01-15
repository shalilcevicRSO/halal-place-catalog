package com.selma.halal.food.project.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.selma.halal.food.project.lib.HalalPlaceMetadata;
import com.selma.halal.food.project.models.converters.HalalPlaceMetadataConverter;
import com.selma.halal.food.project.models.entities.HalalPlaceMetadataEntity;
import com.selma.halal.food.project.services.producers.PersistenceProducer;
import com.selma.halal.food.project.services.producers.PersistenceProducer.*;
import org.glassfish.jersey.server.Uri;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
//import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
//import org.eclipse.microprofile.faulttolerance.Fallback;
//import org.eclipse.microprofile.faulttolerance.Timeout;
//import org.eclipse.microprofile.metrics.annotation.Timed;

@RequestScoped
public class HalalPlaceMetadataBean {

    private Logger log = Logger.getLogger(HalalPlaceMetadataBean.class.getName());


    @PersistenceContext(unitName = "halal-place-catalog-jpa")
    private EntityManager em;


    public List<HalalPlaceMetadata> getHalalPlaceMetadata() {

        TypedQuery<HalalPlaceMetadataEntity> query = em.createNamedQuery(
                "HalalPlaceMetadataEntity.getAll", HalalPlaceMetadataEntity.class);

        List<HalalPlaceMetadataEntity> resultList = query.getResultList();

        return resultList.stream().map(HalalPlaceMetadataConverter::toDto).collect(Collectors.toList());

    }

    public List<HalalPlaceMetadata> getHalalPlaceMetadataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, HalalPlaceMetadataEntity.class, queryParameters).stream()
                .map(HalalPlaceMetadataConverter::toDto).collect(Collectors.toList());
    }

    public HalalPlaceMetadata getHalalPlaceMetadata(Integer id) {

        HalalPlaceMetadataEntity halalPlaceMetadataEntity = em.find(HalalPlaceMetadataEntity.class, id);

        if (halalPlaceMetadataEntity == null) {
            throw new NotFoundException();
        }

        HalalPlaceMetadata halalPlaceMetadata = HalalPlaceMetadataConverter.toDto(halalPlaceMetadataEntity);
        return halalPlaceMetadata;
    }

    public HalalPlaceMetadata createHalalPlaceMetadata(HalalPlaceMetadata halalPlaceMetadata) {

        HalalPlaceMetadataEntity halalPlaceMetadataEntity = HalalPlaceMetadataConverter.toEntity(halalPlaceMetadata);

        try {
            beginTx();
            em.persist(halalPlaceMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (halalPlaceMetadataEntity.getPlaceId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return HalalPlaceMetadataConverter.toDto(halalPlaceMetadataEntity);
    }

    public HalalPlaceMetadata putHalalPlaceMetadata(Integer id, HalalPlaceMetadata halalPlaceMetadata) {

        HalalPlaceMetadataEntity c = em.find(HalalPlaceMetadataEntity.class, id);

        if (c == null) {
            return null;
        }

        HalalPlaceMetadataEntity updatedHalalPlaceMetadataEntity = HalalPlaceMetadataConverter.toEntity(halalPlaceMetadata);

        try {
            beginTx();
            updatedHalalPlaceMetadataEntity.setPlaceId(c.getPlaceId());
            updatedHalalPlaceMetadataEntity = em.merge(updatedHalalPlaceMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return HalalPlaceMetadataConverter.toDto(updatedHalalPlaceMetadataEntity);
    }

    public boolean deleteHalalPlaceMetadata(Integer id) {

        HalalPlaceMetadataEntity halalPlaceMetadata = em.find(HalalPlaceMetadataEntity.class, id);

        if (halalPlaceMetadata != null) {
            try {
                beginTx();
                em.remove(halalPlaceMetadata);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return  false;
        }

        return true;
    }


    private void beginTx() {
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

//    @Timeout(value = 2, unit = ChronoUnit.SECONDS)
//    @CircuitBreaker(requestVolumeThreshold = 3)
//    @Fallback(fallbackMethod = "getImagesFallback")
//    public List<HalalPlaceMetadata> getImages(Integer id) {
//
//        try {
//            return httpClient
//                    .target(baseUrl + "/v1/images/")
//                    .queryParam("imageId", imageId)
//                    .request().get(new GenericType<Integer>() {
//                    });
//        }
//        catch (WebApplicationException | ProcessingException e) {
//            log.severe(e.getMessage());
//            throw new InternalServerErrorException(e);
//        }
//    }
//
//    public Integer getImagesFallback(Integer imageId) {
//        return null;
//    }
//    }



}
