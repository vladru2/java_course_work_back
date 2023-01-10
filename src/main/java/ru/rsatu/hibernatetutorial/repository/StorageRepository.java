package ru.rsatu.hibernatetutorial.repository;

import ru.rsatu.hibernatetutorial.mapper.StorageMapper;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.pojo.entity.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StorageRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    StorageMapper storageMapper;

    public List<Storage> loadStorage() {
        return entityManager.createQuery("select s from Storage s", Storage.class)
                .getResultList();
    }

    @Transactional
    public Storage saveStorage(StorageDto storageDto) {
        Storage storage = storageMapper.toStorage(storageDto);

        storage.setLastChangeTime(new Date());

        if (storage.getQuanity() > storage.getCellSize()) {
            throw new BadRequestException();
        }

        if (storage.getCellNumber() != null) {
            entityManager.merge(storage);
        } else {
            entityManager.persist(storage);
        }
        entityManager.flush();
        return storage;
    }

    @Transactional
    public List<Storage> deleteStorage(Long id) {
        Storage storage = entityManager.find(Storage.class, id);
        if (storage == null) {
            throw new NotFoundException();
        }
        entityManager.remove(storage);
        entityManager.flush();
        return entityManager.createQuery("select s from Storage s", Storage.class)
                .getResultList();
    }
}
