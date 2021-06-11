package com.skarim.countryservice.servioces;

import com.skarim.countryservice.entity.CountryEntity;
import com.skarim.countryservice.repository.CountryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceServiceImpl implements ICountryService {
    private static final String REDIS_CACHE_VALUE = "country";

    private CountryRepository countryRepository;

    public CountryServiceServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @CachePut(value = REDIS_CACHE_VALUE, key = "#countryEntity.id")
    public CountryEntity save(CountryEntity countryEntity) {
        return countryRepository.save(countryEntity);
    }

    @Override
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
    public CountryEntity findById(Integer id) {
        return countryRepository.findFirstById(id);
    }

    @Override
    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
    public List<CountryEntity> delete(Integer id) {
        countryRepository.deleteById(id);
        return countryRepository.findAll();
    }
}
