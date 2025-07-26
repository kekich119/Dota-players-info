package com.kekich.dotatest.service;

import com.kekich.dotatest.model.Hero;
import com.kekich.dotatest.model.HeroesPeak;
import com.kekich.dotatest.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DotaServie {
    public final RestTemplate restTemplate = new RestTemplate();

    Map<Integer, String> heroMapCache;

    public Map<Integer, String> getHeroIdToNameMap() {
        if (heroMapCache == null) {
            String url = "https://api.opendota.com/api/heroes";
            ResponseEntity<Hero[]> response = restTemplate.getForEntity(url, Hero[].class);
            Hero[] heroes = response.getBody();

            if (heroes == null) {
                System.out.println("❗ Не удалось получить список героев!");
                return Collections.emptyMap(); // важно
            }

            heroMapCache = Arrays.stream(heroes)
                    .filter(h -> h.getId() != null && h.getLocalized_name() != null)
                    .collect(Collectors.toMap(
                            Hero::getId,
                            Hero::getLocalized_name,
                            (a, b) -> a
                    ));
        }
        return heroMapCache;
    }

    public Player getPlayerInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId;
        return restTemplate.getForObject(url, Player.class);
    }

    public Player getWinLoseInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId + "/wl";
        return restTemplate.getForObject(url, Player.class);
    }

    public List<HeroesPeak> getHeroPeakInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId + "/heroes";
        ResponseEntity<HeroesPeak[]> response = restTemplate.getForEntity(url, HeroesPeak[].class);
        return Arrays.asList(response.getBody());
    }


    public List<Map<String, Object>> getLastMatches(long accountId, int count) {
        String url = "https://api.opendota.com/api/players/" + accountId + "/matches?limit=" + count;
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }







}
