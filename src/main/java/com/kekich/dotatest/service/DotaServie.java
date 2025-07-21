package com.kekich.dotatest.service;

import com.kekich.dotatest.model.HeroesPeak;
import com.kekich.dotatest.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DotaServie {
    public final RestTemplate restTemplate = new RestTemplate();


    public Player getPlayerInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId;
        return restTemplate.getForObject(url, Player.class);
    }

    public Player getWinLoseInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId + "/wl";
        return restTemplate.getForObject(url, Player.class);
    }

    public List<HeroesPeak> getHeroPeakInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId + "/matches";
        ResponseEntity<HeroesPeak[]> response = restTemplate.getForEntity(url, HeroesPeak[].class);
        return Arrays.asList(response.getBody());
    }
}
