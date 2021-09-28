package kz.spring.rating.service.implement;

import kz.spring.rating.service.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class ProductRatingServiceImpl implements ProductRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Double getProductRatingById(Long id) {
        Random number = new Random();
        return 5.0 * number.nextDouble();
    }

    @Override
    public int getProductClickById(Long id) {
        Random number = new Random();
        return number.nextInt();
    }

}
