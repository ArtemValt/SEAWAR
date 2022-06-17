package com.example.bot.Controller;


import com.example.bot.battleLogic.battleship.Battle;
import com.example.bot.battleLogic.utils.Utils;
import com.example.bot.mainview.Storage;
import com.example.bot.model.BattleUser;
import com.example.bot.model.Ship;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private final Storage storage;
    Battle battle = new Battle();
    Utils utils = new Utils();

    BattleUser battleUser1;
    BattleUser battleUser2;

    public WebController(Storage storage) {
        this.storage = storage;
    }


    @RequestMapping("/showdetails")
    public String showDeatils(
            Model model) {
        model.addAttribute("fieldfirst", utils.arrayToStr(battleUser1.getBatllefield()).toString());
        model.addAttribute("fieldsecond", utils.arrayToStr(battleUser2.getBatllefield()).toString());
        return "showdetails";
    }


    @RequestMapping(value = "/locOneField", method = RequestMethod.GET)
    public String chooseLocForFirsPlay(Model model) {
        battleUser1 = storage.getUserStorage().get(1);
        model.addAttribute("ship", new Ship());
        model.addAttribute("countships", battleUser1.getCountShips());
        model.addAttribute("matrix", utils.arrayToStr(battleUser1.getBatllefield()).toString());
        if (battleUser1.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "play1";
    }

    @RequestMapping(value = "/locSecField", method = RequestMethod.GET)
    public String chooseLocFoSecondPlay(Model model) {
        battleUser2 = storage.getUserStorage().get(2);
        model.addAttribute("ship", new Ship());
        model.addAttribute("countships", battleUser2.getCountShips());
        model.addAttribute("matrix", utils.arrayToStr(battleUser2.getBatllefield()).toString());
        if (battleUser2.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "play2";
    }

    @RequestMapping(value = "/locOneField", method = RequestMethod.POST)
    public String chooseLocForFirsPlayRes(Model model, @ModelAttribute("ship") Ship ship) {
        battleUser1 = storage.getUserStorage().get(1);
        model.addAttribute("ship", ship);
        battle.placePlayerShips(battleUser1, ship);
        model.addAttribute("countships", battleUser1.getCountShips());
        model.addAttribute("matrix", utils.arrayToStr(battleUser1.getBatllefield()).toString());
        return "redirect:/locOneField";
    }

    @RequestMapping(value = "/locSecField", method = RequestMethod.POST)
    public String chooseLocForSecPlayRes(Model model, @ModelAttribute("ship") Ship ship) {
        battleUser2 = storage.getUserStorage().get(2);
        model.addAttribute("ship", ship);
        battle.placePlayerShips(battleUser2, ship);
        String matrix = utils.arrayToStr(battle.getBattlefield1()).toString();
        model.addAttribute("countships", battleUser2.getCountShips());
        model.addAttribute("matrix", matrix);
        if (battleUser2.countShips == 0)
            model.addAttribute("redirect", "Чтобы закончить расстановку напишите в чат готово ");
        return "redirect:/locSecField";
    }


    @RequestMapping(value = "/shoo1", method = RequestMethod.GET)
    public String shoo1(Model model) {
        battleUser1 = storage.getUserStorage().get(1);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battleUser1.getBatllefield()).toString();
        model.addAttribute("YourField2", matrix);
        if (battleUser1.getCountTrueShots() == 0)
            model.addAttribute("currentShoot", "Промах,напиите в чат , что вы сделалил ход");
        else if (battleUser1.getCountTrueShots() == 1)
            model.addAttribute("currentShoot", "Поздравляю вы попали ");

        if (battle.whoWin(battleUser1))
            model.addAttribute("whoWin", "Поздравляю вы победили , чтобы узнать результаты напишите <Результаты> ");

        return "shoot1";
    }


    @RequestMapping(value = "/shoo1", method = RequestMethod.POST)
    public String shoo1Post(Model model, @RequestParam("OX") String x, @RequestParam("OY") String y) {
        battleUser1 = storage.getUserStorage().get(1);
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield2(), battleUser1);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield2()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battleUser1.getBatllefield()).toString();
        model.addAttribute("YourField2", matrix);
        if (battleUser1.getCountTrueShots() == 0)
            model.addAttribute("currentShoot", "Промах,напиите в чат , что вы сделалил ход");
        else if (battleUser1.getCountTrueShots() == 1)
            model.addAttribute("currentShoot", "Поздравляю вы попали ");

        if (battle.whoWin(battleUser1))
            model.addAttribute("whoWin", "Поздравляю вы победили , чтобы узнать результаты напишите <Результаты> ");

        return "redirect:/shoo1";
    }

    @RequestMapping(value = "/shoo2", method = RequestMethod.GET)
    public String shoo2Get(Model model) {
        battleUser2 = storage.getUserStorage().get(2);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battleUser2.getBatllefield()).toString();
        model.addAttribute("YourField2", matrix);
        if (battleUser2.getCountTrueShots() == 0)
            model.addAttribute("currentShoot", "Промах,напиите в чат , что вы сделалил ход");
        else if (battleUser2.getCountTrueShots() == 1)
            model.addAttribute("currentShoot", "Поздравляю вы попали ");

        if (battle.whoWin(battleUser2) == true)
            model.addAttribute("whoWin", "Поздравляю вы победили , чтобы узнать результаты напишите <Результаты> ");
        return "shoot2";
    }

    @RequestMapping(value = "/shoo2", method = RequestMethod.POST)
    public String shoo2Post(Model model, @RequestParam("OX") String x, @RequestParam("OY") String y) {
        battleUser2 = storage.getUserStorage().get(2);
        battle.makeShoot(Integer.parseInt(x), Integer.parseInt(y), battle.getBattlefield1(), battleUser2);
        String FirstArrayIncognito = utils.incognitoArray(battle.getBattlefield1()).toString();
        model.addAttribute("fieldFirst", FirstArrayIncognito);
        String matrix = utils.arrayToStr(battleUser2.getBatllefield());
        model.addAttribute("YourField2", matrix);
        if (battleUser2.getCountTrueShots() == 0)
            model.addAttribute("currentShoot", "Промах,напиите в чат , что вы сделалил ход");
        else if (battleUser2.getCountTrueShots() == 1)
            model.addAttribute("currentShoot", "Поздравляю вы попали ");

        if (battle.whoWin(battleUser2) == true)
            model.addAttribute("whoWin", "Поздравляю вы победили , чтобы узнать результаты напишите <Результаты> ");

        return "redirect:/shoo2";
    }


}
