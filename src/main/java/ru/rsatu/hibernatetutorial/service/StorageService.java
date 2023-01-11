package ru.rsatu.hibernatetutorial.service;

import ru.rsatu.hibernatetutorial.mapper.StorageMapper;
import ru.rsatu.hibernatetutorial.pojo.dto.LoadStorageDto;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.repository.StorageRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StorageService {

    @Inject
    StorageMapper storageMapper;

    @Inject
    StorageRepository storageRepository;

    public LoadStorageDto loadStorageList(int from, int to) {
        return storageRepository.loadStorage(from, to);
    }

    public StorageDto saveStorage(StorageDto storageDto) {
        return storageMapper.toStorageDto(storageRepository.saveStorage(storageDto));
    }

    public LoadStorageDto deleteStorage(Long id, int from, int to) {
        return storageRepository.deleteStorage(id, from, to);
    }
}