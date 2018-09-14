package dancecomp.facade;

import dancecomp.logics.City;
import dancecomp.repositories.CitiesRepository;

import java.util.List;

public class CityFacade {

    public static List<City> getCities() {
        return CitiesRepository.getInstance().getAll();
    }

    public static City getCityById(int id) {
        return CitiesRepository.getInstance().getById(id);
    }
}
