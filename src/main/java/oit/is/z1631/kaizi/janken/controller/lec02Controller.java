package oit.is.z1631.kaizi.janken.controller;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z1631.kaizi.janken.model.Janken;
import oit.is.z1631.kaizi.janken.model.Match;
import oit.is.z1631.kaizi.janken.model.MatchMapper;
import oit.is.z1631.kaizi.janken.model.User;
import oit.is.z1631.kaizi.janken.model.UserMapper;
import oit.is.z1631.kaizi.janken.model.Entry;

@Controller
public class lec02Controller {

  @Autowired
  private Entry entry;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MatchMapper matchMapper;

  private int userid;

  /*
   * @GetMapping("/lec02") public String lec03janken(Principal prin, ModelMap
   * model) { String loginUser = prin.getName(); this.entry.adduser(loginUser);
   * model.addAttribute("entry", this.entry); model.addAttribute("user_name",
   * loginUser); return "lec02.html"; }
   */

  /*
   * @GetMapping("/lec02") public String janken(@RequestParam Integer myhand,
   * ModelMap model) { int result = Janken.playjanken(myhand); String resultString
   * = ""; String myhandString = ""; switch (result) { case Janken.win:
   * resultString = "win"; break; case Janken.lose: resultString = "lose"; break;
   * case Janken.draw: resultString = "draw"; break; } switch (myhand) { case
   * Janken.GU: myhandString = "GU"; break; case Janken.PA: myhandString = "PA";
   * break; case Janken.CHOKI: myhandString = "CHOKI"; break; }
   *
   * model.addAttribute("result", 0 <= result); model.addAttribute("myhandString",
   * myhandString); model.addAttribute("resultString", resultString); return
   * "lec02.html"; }
   */

  @PostMapping("/lec02")
  public String lec02(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "lec02.html";
  }

  @GetMapping("/lec02")
  @Transactional
  public String lec04(ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    this.entry.adduser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("loginUser", loginUser);
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);
    return "lec02.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, ModelMap model, Principal prin) {
    ArrayList<User> users = userMapper.selectAllUsers();
    String loginuser = users.get(id - 1).getName();
    String cpu = users.get(0).getName();
    this.userid = id;
    model.addAttribute("loginuser", loginuser);
    model.addAttribute("cpu", cpu);
    model.addAttribute("id", userid);
    return "match.html";
  }

  @GetMapping("/game")
  public String game(@RequestParam Integer myhand, ModelMap model, Principal prin) {
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
      case Janken.Gu:
        myhandString = "GU";
        break;
      case Janken.Pa:
        myhandString = "PA";
        break;
      case Janken.Choki:
        myhandString = "CHOKI";
        break;
    }
    model.addAttribute("result", 0 <= result);
    model.addAttribute("myhandString", myhandString);
    model.addAttribute("resultString", resultString);

    Match match = new Match();
    int cpu_id = 1;
    match.setUser_1(this.userid);
    match.setUser_2(cpu_id);
    String userHand = myhandString;
    String cpuHand = "Gu";
    match.setUser_2_hand(userHand);
    match.setUser_1_hand(cpuHand);
    matchMapper.insertMatch(match);
    return "match.html";
  }

}
