package com.kekich.dotatest.controller;


import com.kekich.dotatest.model.HeroesPeak;
import com.kekich.dotatest.model.Player;
import com.kekich.dotatest.service.DotaServie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dota")
public class DotaController {

    private final DotaServie dotaServie;

    public DotaController(DotaServie dotaServie) {
        this.dotaServie = dotaServie;
    }

    @GetMapping("/player/view/")
    public String viewPlayer(@RequestParam("accountId") long accountId, Model model) {
        Player player = dotaServie.getPlayerInfo(accountId);
        Player winrate = dotaServie.getWinLoseInfo(accountId);
        List<HeroesPeak> picks = dotaServie.getHeroPeakInfo(accountId);
        Map<Integer, String> heroNames = dotaServie.getHeroIdToNameMap();


        Map<String, Integer> pickCount = new HashMap<>();

        for (HeroesPeak hero : picks) {
            int heroId = hero.getHero_id(); // допустим, у тебя есть метод getHeroId()
            pickCount.put(String.valueOf(heroId), pickCount.getOrDefault(heroId, 0) + 1);
        }

        int mostPickedHero = Integer.parseInt(Collections.max(pickCount.entrySet(),
                Map.Entry.comparingByValue()).getKey());


        String mostPickedHeroName = heroNames.get(mostPickedHero);

        System.out.println("Самый пикаемый герой: " + mostPickedHero);


        model.addAttribute("player", player);
        model.addAttribute("winrate", winrate);

        model.addAttribute("heroPeaks", picks); // ✅ исправлено имя
        model.addAttribute("heroNames", heroNames);// ✅ исправлено имя
        model.addAttribute("mostPickedHeroName", mostPickedHeroName);

        return "player-info";
    }
}