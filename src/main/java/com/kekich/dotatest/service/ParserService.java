package com.kekich.dotatest.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kekich.dotatest.model.Hero;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParserService {

    List<String> facet = new ArrayList<>();



    public List<String> getAspect(String url) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Thread.sleep(3000);
        String html = driver.getPageSource();
        driver.quit();

        Document doc = Jsoup.parse(html);
        List<String> facet = new ArrayList<>();

        Elements aspects = doc.select("div.tw-flex.tw-items-center.tw-gap-2");
        for (Element aspect : aspects) {
            Element img = aspect.selectFirst("img[alt]");
            if (img != null) {
                String name = img.attr("alt").trim();
                if (!name.isEmpty() && !name.equals("0")) {
                    facet.add(name);
                    System.out.println("Фасет: " + name);
                } else {
                    System.out.println("Пропущен пустой или мусорный фасет");
                }
            }
        }

        return facet;
    }
}
