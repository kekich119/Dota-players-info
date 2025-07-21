package com.kekich.dotatest.controller;


import com.kekich.dotatest.model.Player;
import com.kekich.dotatest.service.DotaServie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("player", player);
        return "player-info"; // БЕЗ "dota/" !!!
    }
}