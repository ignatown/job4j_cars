package ru.job4j.store;

import ru.job4j.model.Ad;
import ru.job4j.model.Body;
import ru.job4j.model.Brand;
import ru.job4j.model.User;

import java.util.List;

public interface Store {
    List<Ad> findAllAds();
    List<Ad> findAdsForUser(int id);
    List<Body> findAllBodies();
    List<Brand> findAllBrands();
    void addUser(User user);
    User findUserByEmail(String email);
    void addAd(Ad ad, String[] bodyId, String[] brandId);
    void deleteAd(int id);
}
