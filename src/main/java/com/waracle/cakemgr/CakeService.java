package com.waracle.cakemgr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.waracle.cakemgr.SimpleHttpClient.getJson;
import static java.util.stream.Collectors.toSet;

public class CakeService {

    private CakeDao cakeDao = new CakeDao();

    public Set<CakeEntity> getInitialCakes() {
        Set<CakeEntity> cakeSet = null;
        try {
            final ObjectMapper jsonMapper = new ObjectMapper();
            final InputStream jsonInputstream = getJson(CakeConstants.INITIAL_CAKE_DATA_URL);
            final List<CakeEntity> remoteCakeList = jsonMapper.readValue(jsonInputstream, new TypeReference<List<CakeEntity>>(){});
            cakeSet = remoteCakeList.stream().filter(Objects::nonNull).distinct().collect(toSet());
        } catch (final IOException ex) {
            System.out.println("Failed to init data store");
        }
        return cakeSet;
    }

    public List<CakeEntity> getAllCakes() {
        return cakeDao.getCakes();
    }

    public void saveCake(CakeEntity cakeEntity) {
        if (cakeEntity != null) {
            cakeDao.addCake(cakeEntity);
        }
    }

    public void saveCakes(List<CakeEntity> cakeList) {
        if (cakeList != null && cakeList.size() > 0) {
            cakeList.forEach(cake -> cakeDao.addCake(cake));
        }
    }
}
