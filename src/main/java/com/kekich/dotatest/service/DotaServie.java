package com.kekich.dotatest.service;

import com.kekich.dotatest.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DotaServie {
    public final RestTemplate restTemplate = new RestTemplate();


    public Player getPlayerInfo(long accountId) {
        String url = "https://api.opendota.com/api/players/" + accountId;
        return restTemplate.getForObject(url, Player.class);
    }
}
