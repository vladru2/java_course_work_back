package ru.rsatu.hibernatetutorial.pojo.dto;

import java.util.List;

public class LoadStorageDto {
    Long storageSize;
    List<StorageDto> storageList;

    public LoadStorageDto(Long storageSize, List<StorageDto> storageList) {
        this.storageSize = storageSize;
        this.storageList = storageList;
    }

    public Long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(Long storageSize) {
        this.storageSize = storageSize;
    }

    public List<StorageDto> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<StorageDto> storageList) {
        this.storageList = storageList;
    }
}
