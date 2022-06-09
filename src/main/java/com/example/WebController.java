package com.example;


import com.example.battleLogic.battleship.Battle;
import com.example.battleLogic.battleship.Ships.Ship;
import com.example.battleLogic.user.User;
import com.example.battleLogic.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
public class WebController {
    private Storage.User storage;
    RedirectView redirectView = new RedirectView();
    Battle battle = new Battle();
    Utils utils = new Utils();

    User user1 = new User("1", "12");
    User user2 = new User("2", "12");


//    @RequestMapping("/askfirst")
//    public String askfirst(Model model) {
//        model.addAttribute("user", new User());
//        return "askDetails";
//    }
//
//    @RequestMapping("/asksecond")
//    public String askSecond(Model model) {
//        model.addAttribute("user", new User());
//        return "AskDetails2";
//    }


    @RequestMapping("/shootfirst")
    public String shootfirst(Model model, @RequestParam("OX") String x,
                             @RequestParam("OY") String y) {
        String str = storage.getMessage();
        System.out.println(str);
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield2(), user1);
        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
        model.addAttribute("fieldSecond", secondArrayIncognito);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("YourField1", matrix);

        if (battle.countTrueShots == 1) {
            return "makeShoot";
        } else
            return "redirect:/askshootsecond";
    }

//    @RequestMapping("/askshootfirst")
//    public String askshootfirst(Model model) {
//        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
//        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
//        model.addAttribute("fieldSecond", secondArrayIncognito);
//        model.addAttribute("YourField1", matrix);
//
//        return "makeShoot";
//    }


    @RequestMapping("/shootsecond")
    public String shootsecond(Model model, @RequestParam("OX") String x,
                              @RequestParam("OY") String y) {
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield1(), user2);
        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
        String matrix = utils.arrayToStr(battle.getBattlefield2()).toString();
        model.addAttribute("YourField2", matrix);
        model.addAttribute("fieldFirst", secondArrayIncognito);
        if (battle.countTrueShots == 1)
            return "makeShoot2";
        else
            return "redirect:/askshootfirst";
    }

//    @RequestMapping("/askshootsecond")
//    public String askshootsecond(Model model) {
//        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
//        model.addAttribute("fieldFirst", FirstArrayIncognito);
//        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
//        model.addAttribute("YourField2", matrix);
//
//        return "makeShoot2";
//    }

    //    @ModelAttribute("user") User user
    @RequestMapping("/ShowDetails")
    public String showDeatils(
            Model model) {
        model.addAttribute("fieldfirst", Arrays.deepToString(battle.getBattlefield1()));
        model.addAttribute("fieldsecond", Arrays.deepToString(battle.getBattlefield2()));

        return "ShowDetails";
    }

    //    @ModelAttribute("user") User user2
    @RequestMapping("/locationfirstPlayer")
    public String chooseLocForFirsPlay(Model model, @ModelAttribute("ship") Ship ship) {
        model.addAttribute("ship", new Ship());
        battle.placePlayerShips(user1, ship);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("countships", user1.getCountShips());
        model.addAttribute("matrix", matrix);
        if (user1.countShips != 0)
            return "locationFirstPlayer";
        else
            return "redirect:/asksecond";
    }

    //    @ModelAttribute("user") User user2
    @RequestMapping("/locationSecondPlay")
    public String chooseLocForSecondPlayer(Model model, @ModelAttribute("ship") Ship ship) {
        model.addAttribute("ship", new Ship());
        battle.placePlayerShips(user2, ship);
        String matrix = utils.arrayToStr(battle.getBattlefield2()).toString();
        model.addAttribute("countshipssecondPlayer", user2.getCountShips());
        model.addAttribute("fieldfoSecond", matrix);
        if (user2.countShips != 0)
            return "locationSecondPlay.html";
        else
            return "redirect:/askshootfirst";
    }

    @RequestMapping("/x")
    public String test() {
        return "Test";
    }
}
