package com.zosh.service;

import com.zosh.model.Category;
import com.zosh.model.Cloth;
import com.zosh.repository.ClothRepository;
import com.zosh.request.CreateClothRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClothServiceImp implements ClothService{

    @Autowired
    private ClothRepository clothRepository;
    @Override
    public Cloth createCloth(CreateClothRequest req, Category category) {
        Cloth cloth=new Cloth();
        cloth.setClothCategory(category);
        cloth.setDescription(req.getDescription());
        cloth.setImages(req.getImages());
        cloth.setName(req.getName());
        cloth.setPrice(req.getPrice());


        Cloth savedCloth = clothRepository.save(cloth);

        return savedCloth;
    }

    @Override
    public void deleteCloth(long clothId) throws Exception {
        Cloth cloth=findClothById(clothId);
        clothRepository.save(cloth);

    }

    @Override
    public List<Cloth> getCloth(String clothCategory) {

        if (clothCategory!=null && !clothCategory.equals("")) {
            List<Cloth> clothsList = filterByCategory(clothCategory);
            return clothsList;
        }
        else{
            List<Cloth> clothsList = clothRepository.findAll();
            return clothsList;
        }

    }

    @Override
    public List<Cloth> searchCloth(String keyword) {

        return clothRepository.searchCloth();
    }

    @Override
    public Cloth findClothById(Long clothId) throws Exception {
        Optional<Cloth> optionalCloth=clothRepository.findById(clothId);

        if(optionalCloth.isEmpty()){
            throw new Exception("cloth not exits...");
        }
        return optionalCloth.get();
    }

    @Override
    public Cloth updateAvailabilityStatus(Long clothId) throws Exception {
        Cloth cloth=findClothById(clothId);
        cloth.setAvailable(!cloth.isAvailable());
        return clothRepository.save(cloth);
    }

    @Override
    public List<Cloth> filterByCategory(String cloth) {
        return null;
    }

    //@Override
    public List<Cloth> filterByCategory(List<Cloth> cloths,String clothCategory) {
        return cloths.stream().filter(cloth -> {
            if(cloth.getClothCategory()!=null){
                return cloth.getClothCategory().getName().equals(clothCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }
}
