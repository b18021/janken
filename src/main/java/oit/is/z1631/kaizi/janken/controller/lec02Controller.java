package oit.is.z1631.kaizi.janken.controller;

import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z1631.kaizi.janken.model.Janken;

@Controller
public class lec02Controller {

  @GetMapping("/lec02")
  public String janken(@RequestParam Integer myhand, ModelMap model) {
    int result = Janken.playjanken(myhand);
    String resultString = "";
    String myhandString = "";
    switch (result) {
    case Janken.win:
      resultString = "win";
      break;
    case Janken.lose:
      resultString = "lose";
      break;
    case Janken.draw:
      resultString = "draw";
      break;
    }
    switch (myhand) {
    case Janken.GU:
      myhandString = "GU";
      break;
    case Janken.PA:
      myhandString = "PA";
      break;
    case Janken.CHOKI:
      myhandString = "CHOKI";
      break;
    }

    model.addAttribute("result", 0 <= result);
    model.addAttribute("myhandString", myhandString);
    model.addAttribute("resultString", resultString);
    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "lec02.html";
  }
}
