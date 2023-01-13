package ru.rsatu.hibernatetutorial.repository;

import ru.rsatu.hibernatetutorial.mapper.StorageMapper;
import ru.rsatu.hibernatetutorial.pojo.dto.LoadStorageDto;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.pojo.entity.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StorageRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    StorageMapper storageMapper;

    public LoadStorageDto loadStorage(int from, int to) {
        Query query = entityManager.createQuery("select s from Storage s order by s.cellNumber", Storage.class);
        query.setFirstResult(from);
        query.setMaxResults(to);
        List<Storage> storageList = query.getResultList();

        query = entityManager.createQuery("select count(*) from Storage s", Long.class);
        return new LoadStorageDto((Long) query.getSingleResult(), storageList.stream().map(storageMapper::toStorageDto).toList());
    }

    public LoadStorageDto loadStorage2(int from, int to, StorageDto storageDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Storage> cr = cb.createQuery(Storage.class);
        Root<Storage> root = cr.from(Storage.class);
        List<Predicate> predicates = new ArrayList<>();
        if (storageDto.getCellNumber() != null) {
            predicates.add(cb.equal(root.get("cellNumber"), storageDto.getCellNumber()));
        }

        if (storageDto.getQuanity() != -1) {
            predicates.add(cb.equal(root.get("quanity"), storageDto.getQuanity()));
        }

        if (storageDto.getCellSize() != -1) {
            predicates.add(cb.equal(root.get("cellSize"), storageDto.getCellSize()));
        }

        if (storageDto.getProductId() != null) {
            predicates.add(cb.equal(root.get("product"), storageDto.getProductId()));
        }

        if (storageDto.getLastChangeTime() != null) {
            Path<Date> dateCreatedPath = root.get("lastChangeTime");
            predicates.add(cb.greaterThanOrEqualTo(dateCreatedPath, storageDto.getLastChangeTime()));
        }

        cr.orderBy(cb.asc(root.get("cellNumber")));
        cr.select(root).where(predicates.toArray(new Predicate[0]));

        Query query = entityManager.createQuery(cr);
        query.setFirstResult(from);
        query.setMaxResults(to);
        List<Storage> storageList = query.getResultList();

        return new LoadStorageDto((long) entityManager.createQuery(cr).getResultList().size(), storageList.stream().map(storageMapper::toStorageDto).toList());
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
    public LoadStorageDto deleteStorage(Long id, int from, int to) {
        Storage storage = entityManager.find(Storage.class, id);
        if (storage == null) {
            throw new NotFoundException();
        }
        entityManager.remove(storage);
        entityManager.flush();
        return loadStorage(from, to);
    }
}