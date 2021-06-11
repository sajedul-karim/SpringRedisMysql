package com.skarim.countryservice.servioces;

import com.skarim.countryservice.entity.CountryEntity;

import java.util.List;

public interface ICountryService {
    CountryEntity save(CountryEntity countryEntity);
//    List<CountryEntity> findAll();
    CountryEntity findById(Integer id);
    List<CountryEntity> delete(Integer id);
}
