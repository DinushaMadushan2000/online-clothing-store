package com.zosh.service;

import com.zosh.model.Category;
import com.zosh.model.Cloth;
import com.zosh.request.CreateClothRequest;

import java.util.List;

public interface ClothService {
    public Cloth createCloth(CreateClothRequest req, Category category);

    void deleteCloth(long clothId) throws Exception;
    public List<Cloth> getCloth(String clothCategory);

    public List<Cloth> searchCloth(String keyword);

    public  Cloth findClothById(Long clothId) throws Exception;

    public Cloth updateAvailabilityStatus(Long clothId)throws Exception;

    public List<Cloth> filterByCategory(String cloth);
}
