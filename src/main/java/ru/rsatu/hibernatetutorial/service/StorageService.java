package ru.rsatu.hibernatetutorial.service;

import ru.rsatu.hibernatetutorial.mapper.StorageMapper;
import ru.rsatu.hibernatetutorial.pojo.dto.StorageDto;
import ru.rsatu.hibernatetutorial.repository.StorageRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StorageService {

    @Inject
    StorageMapper storageMapper;

    @Inject
    StorageRepository storageRepository;

    public List<StorageDto> loadStorageList() {
        return storageRepository.loadStorage()
                .stream()
                .map(storageMapper::toStorageDto)
                .toList();
    }

    public StorageDto saveStorage(StorageDto storageDto) {
        return storageMapper.toStorageDto(storageRepository.saveStorage(storageDto));
    }

    public List<StorageDto> deleteStorage(Long id) {
        return storageRepository.deleteStorage(id)
                .stream()
                .map(storageMapper::toStorageDto)
                .toList();
    }
}
