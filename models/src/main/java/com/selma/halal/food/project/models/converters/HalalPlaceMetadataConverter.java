package com.selma.halal.food.project.models.converters;

import com.selma.halal.food.project.lib.HalalPlaceMetadata;
import com.selma.halal.food.project.models.entities.HalalPlaceMetadataEntity;

public class HalalPlaceMetadataConverter {

    public static HalalPlaceMetadata toDto(HalalPlaceMetadataEntity entity) {

        HalalPlaceMetadata dto = new HalalPlaceMetadata();
        dto.setPlaceId(entity.getPlaceId());
        dto.setDateCreated(entity.getDateCreated());
        dto.setPlaceName(entity.getPlaceName());
        dto.setPlaceType(entity.getPlaceType());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setZipCode(entity.getZipCode());
        dto.setDescription(entity.getDescription());
        dto.setUri(entity.getUri());


        return dto;

    }

    public static HalalPlaceMetadataEntity toEntity(HalalPlaceMetadata dto) {

        HalalPlaceMetadataEntity entity = new HalalPlaceMetadataEntity();
        entity.setDateCreated(dto.getDateCreated());
        entity.setPlaceName(dto.getPlaceName());
        entity.setPlaceType(dto.getPlaceType());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(entity.getStreetNumber());
        entity.setZipCode(dto.getZipCode());
        entity.setDescription(dto.getDescription());
        entity.setUri(dto.getUri());

        return entity;
    }
}
