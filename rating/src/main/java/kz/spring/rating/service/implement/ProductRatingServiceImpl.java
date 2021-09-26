package kz.spring.rating.service.implement;

import kz.spring.rating.service.ProductRatingService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProductRatingServiceImpl implements ProductRatingService {

    @Override
    public Double getProductRatingById(Long id) {
        Random r = new Random();
        return 5.0 * r.nextDouble();
    }


}
