package kz.spring.rating.service;

public interface ProductRatingService {
    Double getProductRatingById(Long id);
    int getProductClickById(Long id);
}
