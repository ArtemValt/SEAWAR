package com.example.bot.Controller;


import com.example.bot.battleLogic.battleship.Battle;
import com.example.bot.battleLogic.utils.Utils;
import com.example.bot.mainview.Storage;
import com.example.bot.model.Ship;
import com.example.bot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class WebController {
    private Storage storage;
    Battle battle = new Battle();
    Utils utils = new Utils();
    User user1 = new User("1", "12");
    User user2 = new User("2", "12");


//    @RequestMapping("/shootfirst")
//    public String shootfirst(Model model, @RequestParam("OX") String x,
//                             @RequestParam("OY") String y) {
////        String str = storage.getMessage();
////        System.out.println(str);
//        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield2(), user1);
//        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
//        model.addAttribute("fieldSecond", secondArrayIncognito);
//        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
//        model.addAttribute("YourField1", matrix);
//
//        if (battle.countTrueShots == 1) {
//            return "makeShoot";
//        } else
//            return "redirect:/askshootsecond";
//    }

//    @RequestMapping("/askshootfirst")
//    public String askshootfirst(Model model) {
//        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
//        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
//        model.addAttribute("fieldSecond", secondArrayIncognito);
//        model.addAttribute("YourField1", matrix);
//
//        return "makeShoot";
//    }
//
//
//    @RequestMapping("/shootsecond")
//    public String shootsecond(Model model, @RequestParam("OX") String x,
//                              @RequestParam("OY") String y) {
//        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield1(), user2);
//        String secondArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
//        String matrix = utils.arrayToStr(battle.getBattlefield2()).toString();
//        model.addAttribute("YourField2", matrix);
//        model.addAttribute("fieldFirst", secondArrayIncognito);
//        if (battle.countTrueShots == 1)
//            return "makeShoot2";
//        else
//            return "redirect:/askshootfirst";
//    }
//
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



    @RequestMapping(value = "/locOneField", method = RequestMethod.GET)
    public String chooseLocForFirsPlay(Model model) {
        model.addAttribute("ship", new Ship());
        model.addAttribute("countships", user1.getCountShips());
        model.addAttribute("matrix", utils.arrayToStr(battle.getBattlefield1()).toString());
        if (user1.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "play1";
    }

    @RequestMapping(value = "/locSecField", method = RequestMethod.GET)
    public String chooseLocFoSecondPlay(Model model) {
        model.addAttribute("ship", new Ship());
        model.addAttribute("countships", user2.getCountShips());
        model.addAttribute("matrix", utils.arrayToStr(battle.getBattlefield2()).toString());
        if (user2.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "play2";
    }

    @RequestMapping(value = "/locOneField", method = RequestMethod.POST)
    public String chooseLocForFirsPlayRes(Model model, @ModelAttribute("ship") Ship ship) {
        model.addAttribute("ship", ship);
        battle.placePlayerShips(user1, ship);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("countships", user1.getCountShips());
        model.addAttribute("matrix", matrix);
        return "redirect:/locOneField";
    }

    @RequestMapping(value = "/locSecField", method = RequestMethod.POST)
    public String chooseLocForSecPlayRes(Model model, @ModelAttribute("ship") Ship ship) {
        model.addAttribute("ship", ship);
        battle.placePlayerShips(user2, ship);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("countships", user2.getCountShips());
        model.addAttribute("matrix", matrix);
        if (user2.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "redirect:/locSecField";
//        else
//            return "redirect:/asksecond";
    }


    @RequestMapping(value = "/shoo1", method = RequestMethod.GET)
    public String shoo1(Model model) {
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("YourField2", matrix);
        return "shoot1";
    }

    @RequestMapping(value = "/shoo1", method = RequestMethod.POST)
    public String shoo1Post(Model model, @RequestParam("OX") String x, @RequestParam("OY") String y) {
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield2(), user1);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("YourField2", matrix);
        return "redirect:/shoo1";
    }

    @RequestMapping(value = "/shoo2", method = RequestMethod.GET)
    public String shoo2Get(Model model, @RequestParam("OX") String x, @RequestParam("OY") String y) {
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battle.getBattlefield2()).toString();
        model.addAttribute("YourField2", matrix);
        return "shoot2";
    }

    @RequestMapping(value = "/shoo2", method = RequestMethod.POST)
    public String shoo2Post(Model model, @RequestParam("OX") String x, @RequestParam("OY") String y) {
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield1(), user1);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battle.getBattlefield2()).toString();
        model.addAttribute("YourField2", matrix);
        return "redirect:/shoo2";
    }


}
