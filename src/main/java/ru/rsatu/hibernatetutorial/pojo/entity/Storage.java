package ru.rsatu.hibernatetutorial.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    private Long cellNumber;

    private int quanity;
    private int cellSize;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Date lastChangeTime;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(Long cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public Date getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Date lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }
}