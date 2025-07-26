package com.kekich.dotatest.controller;


import com.kekich.dotatest.model.HeroesPeak;
import com.kekich.dotatest.model.Player;
import com.kekich.dotatest.service.DotaServie;
import com.kekich.dotatest.service.ParserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dota")
public class DotaController {

    private final DotaServie dotaServie;
    private final ParserService parserService;

    public DotaController(DotaServie dotaServie, ParserService parserService) {
        this.dotaServie = dotaServie;
        this.parserService = parserService;
    }

    @GetMapping("/player/view/")
    public String viewPlayer(@RequestParam("accountId") long accountId, Model model) {

        Player player = dotaServie.getPlayerInfo(accountId);
        Player winrate = dotaServie.getWinLoseInfo(accountId);
        List<HeroesPeak> picks = dotaServie.getHeroPeakInfo(accountId);
        Map<Integer, String> heroNames = dotaServie.getHeroIdToNameMap();


        Map<Integer, Integer> pickCount = new HashMap<>();

        List<Map<String, Object>> recentMatches = dotaServie.getLastMatches(accountId, 20);


        for (Map<String, Object> match : recentMatches) {
            Integer heroId = (Integer) match.get("hero_id");
            if (heroId != null) {
                pickCount.put(heroId, pickCount.getOrDefault(heroId, 0) + 1);
            }
        }


        int mostPickedHeroId = -1;
        String mostPickedHeroName = "Недостаточно данных";

        if (!pickCount.isEmpty()) {
            mostPickedHeroId = Collections.max(pickCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            mostPickedHeroName = heroNames.getOrDefault(mostPickedHeroId, "Неизвестный герой");
        } else {
            mostPickedHeroId = -1;
            mostPickedHeroName = "Недостаточно данных";
        }

        System.out.println(mostPickedHeroName);


        model.addAttribute("player", player);
        model.addAttribute("winrate", winrate);

        model.addAttribute("heroPeaks", picks); // ✅ исправлено имя
        model.addAttribute("heroNames", heroNames);// ✅ исправлено имя
        model.addAttribute("mostPickedHeroName", mostPickedHeroName);

        return "player-info";
    }


    @GetMapping("/facet/")
    public String viewFacet(@RequestParam("hero") String hero, Model model) throws IOException, InterruptedException {
        List<String> facets;
        facets = parserService.getAspect("https://www.dotabuff.com/heroes/" + hero);
        model.addAttribute("hero", facets);
        return "hero-facet";
    }


}