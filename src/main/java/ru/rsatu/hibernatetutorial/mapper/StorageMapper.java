package ru.rsatu.hibernatetutorial.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.pojo.entity.Product;
import ru.rsatu.hibernatetutorial.pojo.entity.Storage;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Mapper(componentModel = "cdi")
public abstract class StorageMapper {
    @Inject
    EntityManager entityManager;

    @Mapping(target = "quanity", source = "quanity")
    @Mapping(target = "lastChangeTime", source = "lastChangeTime")
    @Mapping(target = "cellSize", source = "cellSize")
    @Mapping(target = "cellNumber", source = "cellNumber")
    @Mapping(target = "productId", source = "product.id")
    public abstract StorageDto toStorageDto(Storage storage);

    @Mapping(target = "cellSize", source = "cellSize")
    public abstract Storage toStorage(StorageDto storageDto);

    @AfterMapping
    public void afterStorageMapping(@MappingTarget Storage result, StorageDto storageDto) {
        Product product = entityManager.getReference(Product.class, storageDto.getProductId());
        result.setProduct(product);
    }
}